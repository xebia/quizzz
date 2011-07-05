package com.xebia.faces;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class QuizActivity extends Activity implements OnClickListener {

    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;

    public void onClick(View v) {

        Log.v("UI", "Click event");
    }

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // people selection
        //
        answer1Button = (Button) findViewById(R.id.answer1);
        answer1Button.setOnClickListener(this);

        answer2Button = (Button) findViewById(R.id.answer2);
        answer2Button.setOnClickListener(this);

        answer3Button = (Button) findViewById(R.id.answer3);
        answer3Button.setOnClickListener(this);

    }
}
