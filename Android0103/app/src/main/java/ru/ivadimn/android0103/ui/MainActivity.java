package ru.ivadimn.android0103.ui;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.ivadimn.android0103.R;
import ru.ivadimn.android0103.model.Person;

public class MainActivity extends AppCompatActivity {

    private static final int PASS_TEST_CODE = 1;
    private static final int PRIVATE_ACTIVITY_CODE = 2;
    private EditText edPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edPhone = (EditText) findViewById(R.id.et_phone_id);
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
                checkActivity();
                break;
            case R.id.btn_private_activity_id:
                privateActivity();
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

    private  void checkActivity() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(edPhone.getText().toString()));
        try {
            startActivity(intent);
        }
        catch(ActivityNotFoundException ex) {
            Toast.makeText(this, R.string.no_activity, Toast.LENGTH_LONG).show();
        }
    }

    private void privateActivity() {
        final Person person = new Person("Петя Иванов", "9213457890", "ivanov@mail.ru");
        //Toast.makeText(this, "До вызова активности\n" + person.toString(), Toast.LENGTH_LONG).show();
        InfoDialog dlg = InfoDialog.getDialog("До вызова активности\n", person.toString(), new InfoDialog.DialogListener() {
            @Override
            public void onPositiveClick(DialogInterface dialog) {
                dialog.dismiss();
                runPrivateActivity(person);
            }
        });
        dlg.show(getSupportFragmentManager(), InfoDialog.TAG);


   }

    private void runPrivateActivity(Person p) {
        Intent intent = PrivateActivity.createIntent(this, p);
        startActivityForResult(intent, PRIVATE_ACTIVITY_CODE);
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
        else if (requestCode == PRIVATE_ACTIVITY_CODE && resultCode == RESULT_OK) {
            Person person = PrivateActivity.getPerson(data);
            showDialog("После вызова активности", person);
        }
    }

    private void showDialog(final String title, final Person p) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InfoDialog dlg = InfoDialog.getDialog(title, p.toString(), null);
                dlg.show(getSupportFragmentManager(), InfoDialog.TAG);
            }
        }).start();

    }
}
