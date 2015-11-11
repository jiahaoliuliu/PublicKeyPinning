package com.jiahaoliuliu.publickeypinning;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String RANDOM_URL  = "https://api.random.org/json-rpc/1/invoke";

    // The number of results that we expect to receive
    private static final int NUMBER_RESULTS = 1;

    // The minimum number which starts
    private static final int MINIMUM_NUMBER = 1;

    // The maximum number which should be generated
    private static final int MAX_NUMBER = 100000;

    private TextView mRandomNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        // TODO: Generate random number

        mRandomNumberTextView.setText(String.valueOf(10000));
    }
}
