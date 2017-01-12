package com.example.scherr3143.flippycard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Button answerBtn = (Button) findViewById(R.id.button1);
        answerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showAnswer= new Intent(QuestionActivity.this, AnswerActivity.class);
                startActivity(showAnswer);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.question_out, R.anim.answer_in);
    }
}
