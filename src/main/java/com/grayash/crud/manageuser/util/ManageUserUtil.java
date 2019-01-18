package com.grayash.crud.manageuser.util;

public class ManageUserUtil {

    public static String unFormatPhoneNumber(String phoneNumber){
        if(phoneNumber!=null){
            return phoneNumber.replaceAll("[^0-9]", "");
        }
        return null;
    }

}
