package jp.ac.jec.cm0129.android112_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

public class HeightEntActivity extends AppCompatActivity {

    TextView he;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_ent);

        Intent intent = getIntent();
        String message = intent.getStringExtra("messageFromNext");
        System.out.println("message"+message);

        he = findViewById(R.id.heightMessage);


        if(TextUtils.isEmpty(message)){

        } else {
            System.out.println("message"+message);
            he.setText(message);

        }

        Button btnHeight = findViewById(R.id.heightOKBtn);
        btnHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = findViewById(R.id.heightMessage);
                if (edt.getText().toString().equals("")) {
                    FancyToast.makeText(HeightEntActivity.this,"Please Enter Name", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                } else {
                    Intent intent = getIntent();
                    intent.putExtra("messageFromNext",edt.getText().toString());
                    setResult(RESULT_OK,intent);

                    finish();
                }

            }
        });
        Button cancel = findViewById(R.id.cancelHeightBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}