package com.tsystems.jschool.railage.view;

import com.tsystems.jschool.railage.domain.Role;

import java.util.StringTokenizer;

/**
 * Created by rudolph on 25.06.15.
 */
public class Utils {

    public static final String LOGGED_SESSION_ATTRIB = "logged";

    public static final String USER_ID_SESSION_ATTRIB = "userId";

    public static final String USER_LOGIN_SESSION_ATTRIB = "login";

    public static final String IS_EMPLOYEE_SESSION_ATTRIB = "isEmployee";

    public static final String TRAINS = "trains";

    public static final String TRAIN_RIDES = "trainRides";

    public static boolean isEmployee(String role){
        return Role.EMPLOYEE.toString().equals(role);
    }

    public static Integer extractId(String uri){
        StringTokenizer tokenizer = new StringTokenizer(uri,"/");
        Integer result = null;
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens()){
                try {
                    result = Integer.parseInt(token);
                }
                catch(NumberFormatException e){
                    return null;
                }
            }
        }
        return result;
    }
}
