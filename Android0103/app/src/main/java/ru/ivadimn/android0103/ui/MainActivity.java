package ru.ivadimn.android0103.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ru.ivadimn.android0103.R;

public class MainActivity extends AppCompatActivity {

    private static final int PASS_TEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch(id) {
            case R.id.btn_test_id:
                passTest();
                break;
            case R.id.btn_email_id:
                sendEmail();
                break;
            case R.id.btn_check_id:
                break;
        }
    }

    private void passTest() {
        Intent intent = new Intent(this, TestActivity.class);
        startActivityForResult(intent, PASS_TEST_CODE);
    }

    private void sendEmail() {
        Intent intent = new Intent(this, EmailActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PASS_TEST_CODE && resultCode == RESULT_OK) {
            int numberQuestions = data.getIntExtra(TestActivity.TOTAL_COUNT, 0);
            int rightAnswer = data.getIntExtra(TestActivity.RIGHT_COUNT, 0);
            String msg = String.format("%s\n%s %d %s %d", "Вы прошли тест", "правильно ответили на", rightAnswer,
                    "вопросов из", numberQuestions);
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }
}
