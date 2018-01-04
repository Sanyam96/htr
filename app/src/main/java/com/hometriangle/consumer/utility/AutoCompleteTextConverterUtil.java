package com.hometriangle.consumer.utility;

import android.widget.AutoCompleteTextView;

/**
 * Created by sanyam on 11/11/17.
 */

public class AutoCompleteTextConverterUtil {
    public static String getStringValueFromAutoCompleteText(AutoCompleteTextView autoCompleteText){
        return autoCompleteText.getText().toString();
    }
}
