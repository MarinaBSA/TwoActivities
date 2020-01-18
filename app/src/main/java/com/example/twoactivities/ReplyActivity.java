package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ReplyActivity extends AppCompatActivity {
    private static final String LOG_TAG = ReplyActivity.class.getSimpleName();
    public static final String EXTRA_REPLY = "com.example.android.twoactivities.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        Intent intent = getIntent();
        String mainActivityMessage = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        if(mainActivityMessage != null) {
            //Log.d(LOG_TAG, message);
            TextView textView = findViewById(R.id.textview_reply_message);
            textView.setText(mainActivityMessage);
        }

        Log.d(LOG_TAG, "--------");
        Log.d(LOG_TAG, "onCreate");

    }
    //////////////////// Observing the activities lifecycle /////////////////////////////

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Log.d(LOG_TAG, "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }


    /////////////////////////////////////////////////////////////////////////////////////

    public void finishReplyActivity(View view) {
        EditText editText = findViewById(R.id.reply_activity_input);
        String messageReply = editText.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, messageReply);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}
