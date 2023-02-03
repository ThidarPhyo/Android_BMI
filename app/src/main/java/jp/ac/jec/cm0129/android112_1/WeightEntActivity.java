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

public class WeightEntActivity extends AppCompatActivity {

    TextView we;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_ent);

        Intent intent = getIntent();
        String message = intent.getStringExtra("messageFromNext");
        System.out.println("message"+message);

        we = findViewById(R.id.weightMessage);


        if(TextUtils.isEmpty(message)){

        } else {
            System.out.println("message"+message);
            we.setText(message);

        }

        Button btnFinish = findViewById(R.id.weightOKBtn);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = findViewById(R.id.weightMessage);
                if (edt.getText().toString().equals("")) {
                    FancyToast.makeText(WeightEntActivity.this,"Please Enter Name", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                } else {
                    Intent intent = getIntent();
                    intent.putExtra("messageFromNext",edt.getText().toString());
                    setResult(RESULT_OK,intent);
                    System.out.println("message"+edt.getText().toString());
                    finish();
                }

            }
        });

        Button cancel = findViewById(R.id.cancelWeightBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}