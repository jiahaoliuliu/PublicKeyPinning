package com.jiahaoliuliu.publickeypinning;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jiahaoliuliu.publickeypinning.model.Params;
import com.jiahaoliuliu.publickeypinning.model.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String RANDOM_URL  = "https://api.random.org/json-rpc/1/invoke";

    // The number of results that we expect to receive
    private static final int NUMBER_RESULTS = 1;

    // The minimum number which starts
    private static final int MINIMUM_NUMBER = 1;

    // The maximum number which should be generated
    private static final int MAX_NUMBER = 100000;

    // Views
    private TextView mRandomNumberTextView;

    // Internal variables
    private Context mContext;
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set internal variables
        mContext = this;
        mGson = new Gson();

        // Link the views
        mRandomNumberTextView = (TextView)findViewById(R.id.random_number_text_view);
        mRandomNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Generate random number
                generateRandomNumber();
            }
        });
    }

    private void generateRandomNumber() {
        // Generate the request
        Params params = new Params(APIKeys.RANDOM_ORG_API_KEY, NUMBER_RESULTS, MINIMUM_NUMBER, MAX_NUMBER);
        final Request request = new Request(params);
        String json = mGson.toJson(request);
        Log.v(TAG, "json generated " + json);

        try {
            JSONObject jsonObject = new JSONObject(json);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(RANDOM_URL, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.v(TAG, "Response received " + response);
                    com.jiahaoliuliu.publickeypinning.model.Response responseReturned = mGson.fromJson(response.toString(),
                            com.jiahaoliuliu.publickeypinning.model.Response.class);
                    Log.v(TAG, responseReturned.toString());

                    mRandomNumberTextView.setText(String.valueOf(responseReturned.getResult().getRandom().getData().get(0)));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error received " + error, error);
                    Toast.makeText(mContext, "Error Requesting the random number " + error, Toast.LENGTH_LONG).show();
                }
            });

            // SSL Pinning
            SSLContext sslContext = null;
            try {
                // Set the public key pinning
                TrustManager tm[] = {new PubKeyManager()};
                sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, tm, null);
            } catch (NoSuchAlgorithmException e) {
                Log.e(TAG, "Error getting the instance of SSL context", e);
            } catch (KeyManagementException e) {
                Log.e(TAG, "Error getting the instance of SSL context", e);
            }

            HurlStack hurlStack = null;
            if (sslContext == null) {
                hurlStack = new HurlStack();
            } else {
                Log.v(TAG, "The public key pinning is enabled");
                hurlStack = new HurlStack(null, sslContext.getSocketFactory());
            }

            Volley.newRequestQueue(this, hurlStack).add(jsonObjectRequest);

        } catch (JSONException exception) {
            Log.e(TAG, "Error parsing json", exception);
        }

    }
}
