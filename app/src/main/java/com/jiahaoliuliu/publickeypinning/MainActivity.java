package com.jiahaoliuliu.publickeypinning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jiahaoliuliu.publickeypinning.model.Params;
import com.jiahaoliuliu.publickeypinning.model.Request;

import org.json.JSONException;
import org.json.JSONObject;

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
    private Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set internal variables
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
                    mRandomNumberTextView.setText(String.valueOf(10000));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error received " + error, error);
                }
            });

            Volley.newRequestQueue(this).add(jsonObjectRequest);

        } catch (JSONException exception) {
            Log.e(TAG, "Error parsing json", exception);
        }

    }
}
