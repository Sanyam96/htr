package com.hometriangle.consumer.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hometriangle.consumer.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPActivity extends AppCompatActivity {

    TextView mMobNo;
    EditText mOTPNumber;
    Button mVerifyButton;

    String mRecievedNumber;
    String mUserData;
    String mImportantData;
    String mCountryCode;

    boolean loginInOrNot = false;


    static EditText OtpNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Bundle b = getIntent().getExtras();

        mRecievedNumber = b.getString("phoneNumber");
        mUserData = b.getString("data");
        mImportantData = b.getString("impData");
        mCountryCode = b.getString("countryCode");

        mMobNo = (TextView) findViewById(R.id.mobNumber);
        mOTPNumber = (EditText) findViewById(R.id.EnteredOTPNumber);
        mVerifyButton = (Button) findViewById(R.id.verifyButton);

        mMobNo.setText("+" + mCountryCode + "-" + mRecievedNumber);

        mVerifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String OTPNumber = mOTPNumber.getText().toString();

                verifyData(mUserData, OTPNumber);

            }
        });

    }

    public void recivedSms(String message)
    {
        try
        {
            Toast.makeText(this, "OTP Recieved->" + message, Toast.LENGTH_LONG).show();
            OtpNumber.setText(message);
        }
        catch (Exception e) {}

    }

    private void verifyData(String data, String status) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
//        Call<VerifyOTPDTO> verifiedUserData = VerifyOTPAPI.getService().getVerifiedUserData(data, status);
//        verifiedUserData.enqueue(new Callback<VerifyOTPDTO>() {
//            @Override
//            public void onResponse(Call<VerifyOTPDTO> call, Response<VerifyOTPDTO> response) {
//                VerifyOTPDTO list = response.body();
//                Toast.makeText(OTPActivity.this, "Verified" + list.getData() + "," + list.getStatus(), Toast.LENGTH_LONG).show();
//                if(list.getStatus().equalsIgnoreCase("SUCCESS")) {
//                    Intent i = new Intent(OTPActivity.this, FullscreenActivity.class);
//                    startActivity(i);
//                }
//                else{
//                    Toast.makeText(OTPActivity.this, "UnAuth", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<VerifyOTPDTO> call, Throwable t) {
//                Toast.makeText(OTPActivity.this, "Not Verified", Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
