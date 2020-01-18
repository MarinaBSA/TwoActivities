package com.example.twoactivities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText userInsertion;
    public static final String EXTRA_MESSAGE = "com.example.android.twoactivities.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;
    private TextView messageReplyHeadTextView;
    private TextView messageReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userInsertion = findViewById(R.id.main_activity_text);

        messageReplyHeadTextView = findViewById(R.id.text_header_reply);
        messageReplyTextView = findViewById(R.id.textview_new_reply_msg);

        Log.d(LOG_TAG, "----------");
        Log.d(LOG_TAG, "onCreate");

        // Restore the state
        if (savedInstanceState != null) {
            boolean isVisible = savedInstanceState.getBoolean("reply_visible");
            if(isVisible) {
                Log.d(LOG_TAG, "is visible");
                messageReplyHeadTextView.setVisibility(View.VISIBLE);
                messageReplyTextView.setText(savedInstanceState.getString("reply_text"));
                messageReplyTextView.setVisibility(View.VISIBLE);
            }
            else Log.d(LOG_TAG, "not visible");
        } else {
            Log.d(LOG_TAG, "savedInstanceState == null");
        }
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
        Log.d(LOG_TAG, "onRestart");
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

    // Saving instance data
    // The system calls this method on your Activity (between onPause() and onStop())
    // when there is a possibility the Activity may be destroyed and recreated
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(messageReplyHeadTextView.getVisibility() == View.VISIBLE) {
            outState.putBoolean("reply_visible", true);
            outState.putString("reply_text", messageReplyTextView.getText().toString());
        }
        Log.d(LOG_TAG, "Saving instance state (called onSaveInstanceState)");

    }


    //////////////////////////////////////////////////////////////////////////////////////////

    public void launchSecondActivity(View view) {
        String message = userInsertion.getText().toString();
        //Log.d(LOG_TAG, userInsertion.getText().toString());

        //Send data through intent
        Intent secondActivity = new Intent(getApplicationContext(), ReplyActivity.class);
        secondActivity.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(secondActivity, TEXT_REQUEST);

    }

    //This is called when my activity that was started with a result code, was finished
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            if((data.getStringExtra(ReplyActivity.EXTRA_REPLY)) != null) {
                String replyMessage = data.getStringExtra(ReplyActivity.EXTRA_REPLY);
                messageReplyHeadTextView.setVisibility(View.VISIBLE);

                messageReplyTextView.setVisibility(View.VISIBLE);
                messageReplyTextView.setText(replyMessage);
            } else {
                Log.d(LOG_TAG, "replyMessage is null");
            }
        }

        //Delete previous message from editText inside mainActivity
        userInsertion.setText(null);


    }
}
