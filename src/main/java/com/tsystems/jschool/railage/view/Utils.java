package com.tsystems.jschool.railage.view;

import com.tsystems.jschool.railage.domain.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static final String HAS_CURRENT_TRAIN = "hasCurrentTrain";

    public static final String HAS_CURRENT_RIDE = "hasCurrentRide";

    public static final String CURRENT_TRAIN_RIDE = "currentTrainRide";

    public static final String CURRENT_TRAIN = "currentTrain";

    public static final String TRAIN_RIDES = "trainRides";

    public static final String IS_SEARCH_RESULT = "isSearchResult";

    public static final String SOURCE_STATION = "sourceStation";

    public static final String DEST_STATION = "destStation";

    public static final String PASSENGERS = "passengers";

    public static final String CURRENT_STATION = "currentStation";

    public static final String STATIONS = "stations";

    public static final String TIMETABLE = "timetable";

    public static final String ROUTES = "routes";

    public static final String PERIODS = "periods";

    public static final String IS_VALIDATION_ERR = "isValidationError";

    public static final String VALIDATION_ERROR_MSG = "errorMsg";

    public static final String SUCCESS = "success";

    public static final String INFO_MSG = "infoMsg";

    public static final String CMD_FIND_RIDES = "findrides";

    public static final String TRAIN_ADDITION_FORM_PARAMS = "trainAdditionFormParams";

    public static final String RIDE_ADDITION_FORM_PARAMS = "rideAdditionFormParams";

    public static final String RIDE_SEARCH_FORM_PARAMS = "findRidesFormParams";

    public static final String ROUTE_ADDITION_FORM_PARAMS = "routeFormParams";

    public static final String BUY_TICKET_FORM_PARAMS = "buyTicketFormParams";


    public static boolean isEmployee(String role){
        return Role.ROLE_EMPLOYEE.toString().equals(role);
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

    public static String extractParamFromQueryStr(String queryStr,String paramName){

        StringTokenizer tokenizer = new StringTokenizer(queryStr,"?&=");
        boolean foundRequiredParamName = false;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (paramName.equals(token)){
                foundRequiredParamName = true;
                continue;
            }
            if (foundRequiredParamName){
                return token;
            }
        }
        return null;
    }

    public static Integer extractNumberParam(String queryStr,String paramName){
        String paramValue = extractParamFromQueryStr(queryStr,paramName);
        Integer result = null;
        if(paramValue != null){
            try {
                result = Integer.parseInt(paramValue);
            }
            catch(NumberFormatException e){
                return null;
            }
        }
        return result;
    }

    public static Date convert(String dateStr) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        return  dateFormat.parse(dateStr);
    }



}
