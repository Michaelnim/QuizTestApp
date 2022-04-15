package android.bignerdranch.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    public static final String TAG = "QuizActivity";
    public static final String KEY_INDEX = "index";
    private Button mTrueButton, mFalseButton, mResetButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true ),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int mCurrentIndex = 0;
    private int TrueCount = 0;
    private int FalseCount = 0;
    private String DisplayResult ="True: " + TrueCount + " False: " + FalseCount;
    private ArrayList<Boolean> Temp = new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getmTextResId();

        mTrueButton = (Button)findViewById(R.id.true_button);
        mFalseButton = (Button)findViewById(R.id.false_button);
        mNextButton = (Button)findViewById(R.id.next_button);
        mResetButton = (Button)findViewById(R.id.reset_button);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // After User click Next, Get the last element of the Temp List and check the Answer Then Save it to TrueCount or FalseCount

                checkAnswer(Temp.get(Temp.size()-1));
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                System.out.println(mCurrentIndex);
                if(mCurrentIndex == 0) {
                    Toast.makeText(getApplicationContext(), DisplayResult, Toast.LENGTH_SHORT).show();
                }
                updateQuestion();
            }
        });


        // True Button Listener
        mTrueButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Code to execute on button click goes here.
                Temp.add(true); //          Adding Boolean True to the list
            //checkAnswer(true);
            }
        });

        //False Button Listener
        mFalseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // Code to execute on button click goes here.
                Temp.add(false); // adding Boolean False to the list
            //checkAnswer(false);
            }
        });


        mResetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){    //Reset button, Clear the Temp list and set the question to initial state, and clear TrueCount and FalseCount
                Temp.clear();
                TrueCount = 0;
                FalseCount = 0;
                mCurrentIndex = 0;
                updateQuestion();
            }
        });
        updateQuestion();
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "OnSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getmTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;


        if(userPressedTrue == answerIsTrue) {
            messageResId = R.string.true_button;
            TrueCount = TrueCount + 1;
        }
        else {
            messageResId = R.string.incorrect_toast;
            FalseCount = FalseCount + 1;

        }
        DisplayResult ="True: " + TrueCount + " False: " + FalseCount;


    }
}
