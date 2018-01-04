package com.hometriangle.consumer.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.hometriangle.consumer.ui.OTPActivity;

/**
 * Created by sanyam on 13/11/17.
 */

public class ReadSms extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {

            if (bundle != null)
            {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++)
                {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber ;
                    String message = currentMessage .getDisplayMessageBody();

                    try
                    {

                        if (senderNum.equals("DM-HOMETR"))
                        {

                            OTPActivity Sms = new OTPActivity();
                            Sms.recivedSms(message );
                        }
                    } catch(Exception e){}
                }
            }

        } catch (Exception e) {}
    }
}
