package com.hometriangle.consumer.utility;

import android.util.Log;
import android.widget.EditText;

/**
 * Created by sanyam on 11/11/17.
 */

public class EditTextConvertorUtil {

    public static String getStringValueFromEditText(EditText editText){
        return editText.getText().toString();
    }

    public static int getIntFromString(String str){
        try{
            return Integer.valueOf(str);
        }catch (NumberFormatException ex){
            Log.e("NumberFormatException", str, ex);
            return 0;
        }
    }
}
