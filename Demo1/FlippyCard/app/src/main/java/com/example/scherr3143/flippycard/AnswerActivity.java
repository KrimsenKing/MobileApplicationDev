package com.example.scherr3143.flippycard;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by scherr3143 on 1/11/2017.
 */
public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_answer);

        Button questionBtn = (Button) findViewById(R.id.button1);
        questionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showQuestion = new Intent(AnswerActivity.this, QuestionActivity.class);
                startActivity(showQuestion);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(R.anim.answer_out, R.anim.question_in);
    }
}
