package com.hometriangle.consumer.utility;

import com.hometriangle.consumer.constants.Constants;

/**
 * Created by sanyam on 11/11/17.
 */

public class Util {

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(Constants.PHONE_REGEX_INDIA);
    }
}
