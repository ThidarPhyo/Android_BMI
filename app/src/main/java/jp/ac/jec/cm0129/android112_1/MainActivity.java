package jp.ac.jec.cm0129.android112_1;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView ms;
    TextView height;
    TextView weight;
    TextView res;
    String ja = "";
    TextView name,he,we;
    private TextView dateTime;
    String messageName,messageHeight,messageWeight = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.message);
        he = findViewById(R.id.height);
        we = findViewById(R.id.weight);
        res = findViewById(R.id.result);

        Button btnNext = findViewById(R.id.nextBtn);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);

                System.out.println("MessageName"+messageName);
                intent.putExtra("messageFromNext",messageName);
                startActivityForResult(intent,123);
            }
        });

        Button btnHeight = findViewById(R.id.nextBtn2);
        btnHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HeightEntActivity.class);
                intent.putExtra("messageFromNext",messageHeight);
                startActivityForResult(intent,456);
            }
        });

        Button btnWeight = findViewById(R.id.nextBtn3);
        btnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WeightEntActivity.class);
                intent.putExtra("messageFromNext",messageWeight);
                startActivityForResult(intent,789);
            }
        });

        Button btn4 = findViewById(R.id.nextBtn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);

                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.JAPAN);
                sdf = new SimpleDateFormat("yyyy/MM/dd");


                DatePickerDialog datePicker = new DatePickerDialog(MainActivity.this, new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int
                                    dayOfMonth) {

                                String date = String.valueOf(dayOfMonth) + "/" +
                                        String.valueOf(monthOfYear+1) + "/" + String.valueOf(year);

                                System.out.println("date1--->"+date);

                                TextView bod = (TextView) findViewById(R.id.bod);
                                bod.setText(date);


                            }



                        }, yy, mm, dd);
                datePicker.show();
            }
        });

        Button btnJudge = findViewById(R.id.btnJudge);
        btnJudge.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("")){
                    //FancyToast.makeText(MainActivity.this,"Please Enter Name", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    callAlertDialog("Please Enter Name");
                } else if(he.getText().toString().equals("")){
                    //FancyToast.makeText(MainActivity.this,"Please Enter Height", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    callAlertDialog("Please Enter Height");
                } else if(we.getText().toString().equals("")){
                    //FancyToast.makeText(MainActivity.this,"Please Enter Weight", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                    callAlertDialog("Please Enter Weight");
                } else {
                    double height_in_metre = Double.valueOf(height.getText().toString()) / 100;
                    double bmi =  Double.valueOf(weight.getText().toString()) / (height_in_metre * height_in_metre);

                    double b = Math.floor(bmi * 100) / 100;

                    if (b < 18.5) {
                        ja = "低体重(痩せ型)";
                    } else if((b > 18.5 ) && (b < 25)) {
                        ja = "普通体重";
                    } else if((b > 25 ) && (b < 30)) {

                        ja = "肥満(1 度)";
                    } else if((b > 30 ) && (b < 35)) {

                        ja = "肥満(2 度)";
                    } else if((b > 35 ) && (b < 40)) {

                        ja = "肥満(3 度)";
                    } else {

                        ja = "肥満(4 度)";
                    }
                    String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
                    res.setText(date+"   "+weight.getText().toString()+"cm  "+height.getText().toString()+"kg =  "+b+"("+ja+")");
                }

                //System.out.println("BMI--->"+bmi);
            }
        });



    }

    public void callAlertDialog(String message) {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Missing")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Whatever...
                    }
                }).show();
    }

    class DialogDataSetEvent implements DatePickerDialog.OnDateSetListener {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            TextView bod = (TextView) findViewById(R.id.bod);
            bod.setText(year+ "/" +(month+1) +"/"+dayOfMonth);
            System.out.println("year"+year+"---> month--->"+month+"---->day---->"+dayOfMonth);



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MainActivityTest","requestCode = "+requestCode);
        Log.d("MainActivityTest","resultCode = "+resultCode);
        ms = findViewById(R.id.message);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        if(requestCode == 123){
            if(resultCode == RESULT_OK){
                messageName = data.getStringExtra("messageFromNext");
                Log.d("android112",messageName);
                ms.setText(messageName);
            }
        } else if (requestCode == 456){
            if(resultCode == RESULT_OK){
                messageHeight = data.getStringExtra("messageFromNext");
                Log.d("android112",messageHeight);
                height.setText(messageHeight);
            }
        }  else if (requestCode == 789){
            if(resultCode == RESULT_OK){
                messageWeight = data.getStringExtra("messageFromNext");
                Log.d("android112",messageWeight);
                weight.setText(messageWeight);
            }
        }
    }
}