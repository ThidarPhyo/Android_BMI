package jp.ac.jec.cm0129.android112_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shashank.sony.fancytoastlib.FancyToast;

public class NextActivity extends AppCompatActivity {

    EditText edt,mei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        Intent intent = getIntent();
        String message = intent.getStringExtra("messageFromNext");
        System.out.println("message"+message);

        edt = findViewById(R.id.message);
        mei = findViewById(R.id.meiMessage);

        if(TextUtils.isEmpty(message)){

        } else {
            System.out.println("message"+message);
            String[] strArr = message.split(" ");
            edt.setText(strArr[0]);
            mei.setText(strArr[1]);

        }

        Button btnFinish = findViewById(R.id.okBtn);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt.getText().toString().equals("") || mei.getText().toString().equals("")) {
                    FancyToast.makeText(NextActivity.this,"Please Enter Name", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                } else {
                    Intent intent = getIntent();
                    //String message = intent.getStringExtra("message");
                    intent.putExtra("messageFromNext",edt.getText().toString()+" "+mei.getText().toString());
                    //System.out.println("message"+message);
                    setResult(RESULT_OK,intent);


                    //System.out.println("message"+edt.getText().toString());
                    finish();
                }

            }
        });


        Button cancel = findViewById(R.id.cancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}