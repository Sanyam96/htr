package com.hometriangle.consumer.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hometriangle.consumer.R;
import com.hometriangle.consumer.api.SendOTPAPI;
import com.hometriangle.consumer.dto.SendOTPDTO;
import com.hometriangle.consumer.utility.AutoCompleteTextConverterUtil;
import com.hometriangle.consumer.utility.EditTextConvertorUtil;
import com.hometriangle.consumer.utility.Util;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hometriangle.consumer.constants.Constants.EXIT_MESSAGE_ON_BACK_PRESS_BUTTON;
import static com.hometriangle.consumer.constants.Constants.INVALID_PHONE_NUMBER;

public class MainActivity extends AppCompatActivity {

    // UI related fields
    private AutoCompleteTextView mPhoneNumber;
    private String getOTPDataField;
    private ProgressDialog mProgress;

    // Local variables
    private long backPressedTime = 0;

    // Global variables
    private final int countryCode = 91;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPhoneNumber = (AutoCompleteTextView) findViewById(R.id.mobileNumber);

    }

    // On handle back press buttons
    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long currentTimeOfSystem = System.currentTimeMillis();
        if (currentTimeOfSystem - backPressedTime > 2000) {    // 2 secs = 2000 milliseconds
            backPressedTime = currentTimeOfSystem;
            Toast.makeText(this, EXIT_MESSAGE_ON_BACK_PRESS_BUTTON, Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up
            super.onBackPressed();       // bye
        }
    }

    // to check login or send OTP
    public void checkLogin(View arg0){

        final Button myButton = (Button) findViewById(R.id.phone_number_send_button);

        myButton.setEnabled(false);

        Timer buttonTimer = new Timer();
        buttonTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        myButton.setEnabled(true);
                    }
                });
            }
        }, 2500);   // Button restrict for 2.5seconds

        final String mobNumber = AutoCompleteTextConverterUtil.getStringValueFromAutoCompleteText(mPhoneNumber);
        if(!Util.isValidPhoneNumber(mobNumber)) {
            mPhoneNumber.setError(INVALID_PHONE_NUMBER);
        }
        else {
            progressDialogFunctioning();
            mProgress.show();
            sendOTPToNumberData(String.valueOf(countryCode), mobNumber);
        }
    }

    public void progressDialogFunctioning() {
        mProgress = new ProgressDialog(this);
        String titleId="Sending OTP...";
        mProgress.setTitle(titleId);
        mProgress.setMessage("Please Wait...");
    }

    // Retrofit calls for sendOTP to the mobile number!
    private void sendOTPToNumberData(final String countryCode, String mobNumber) {
        Call<SendOTPDTO> sendOTPData = SendOTPAPI.getService().getOTPData(countryCode, mobNumber);
        sendOTPData.enqueue(new Callback<SendOTPDTO>() {
            @Override
            public void onResponse(Call<SendOTPDTO> call, Response<SendOTPDTO> response) {
                Log.i("RETROFIT THREAD NAME : ",Thread.currentThread().getName().toString());
                SendOTPDTO list = response.body();
                getOTPDataField = list.getData();
                Toast.makeText(MainActivity.this, "Success" + list.getData()+ "," + list.getMessage()+ "," + list.getStatus(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, OTPActivity.class);
                Bundle b = new Bundle();
                b.putString("data", getOTPDataField);
                b.putString("phoneNumber", AutoCompleteTextConverterUtil.getStringValueFromAutoCompleteText(mPhoneNumber));
                b.putString("countryCode", countryCode+"");
                intent.putExtras(b);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SendOTPDTO> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Check Your Internet Connection!", Toast.LENGTH_LONG).show();
                mProgress.dismiss();
            }
        });
    }

}
