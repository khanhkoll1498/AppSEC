package com.kna.appsec.base;

public class Constants {
    public static final String USER_NAME = "USER_NAME";
    public static final String RATED = "RATED";
    public static final String MK = "MK";
    public static final Integer REQUEST_CODE_SCAN = 1111;
    public static final Integer RESULT_FRAGMENT = 200;
    public static final Integer CHANGE_PASSWORD = 201;
    public static final Integer RESULT_OK = 9999;
    public static final String DISCONNECTED_NETWORK = "DISCONNECTED_NETWORK";
    public static final String RESULT_SCAN = "RESULT_SCAN";
    public static final String IS_NOTIFICATION = "isNotification";
    public static final int REQUEST_UPDATE = 1;

    public static final String KEY_PAGE = "key_page";
    public static final String SUCCESS_API = "200";
    public static final String FAIL_API = "-1";
    public static final String RATING_SUCCESS = "RATING_SUCCESS";
    public static final String RATING_FAIL = "RATING_FAIL";
    public static final String DATA_RESULT = "DATA_RESULT";
    public static final String MULTI_SCAN_QR_CODE = "MULTI_SCAN_QR_CODE";
    public static final String FROM_ACTIVITY = "FROM_ACTIVITY";
    public static final String DATA = "DATA";
    public static final String QR_DATA = "QR_DATA";
    public static final String COMPLETE = "COMPLETE";


    public static final String ROUTING_CODE = "routing_plan_day_code";
    public static final String ITEM_ID = "item_id";
    public static final String FRAGMENT = "FRAGMENT";
    public static final String TYPE_NOTIFICATION_ROUTING = "routing";
    public static final String TYPE_NOTIFICATION_BIDDING = "bidding_vehicle";
    public static final String TYPE_NOTIFICATION_SYSTEM = "system";
    public static final String ONLY_READ = "ONLY_READ";


    public static final String DATA_PASS_FRAGMENT = "da_pass_fragment";
    public static final String OBJECT_SERIALIZABLE = "object_serializable";
    public static final String EMPTY_STRING = "";

    public static final int LOCATION_SERVICE_ID = 1412;
    public static final String ACTION_START_LOCATION_SERVICE = "startLocationService";
    public static final String ACTION_STOP_LOCATION_SERVICE = "stopLocationService";
    public static final String INTERVAL = "INTERVAL";
    public static final String DURATION_REQUEST = "DURATION_REQUEST";
    public static final String FAST_SAVE_LOG_DURATION = "FAST_SAVE_LOG_DURATION";
    public static final String VEHICLE_ID = "VEHICLE_ID";
    public static final int REQUEST_PERMISSION_CAMERA = 9999;
    public static final int PERMISSIONS_REQUEST_LOCATION = 2222;
    public static final String CLICK_ACTION = "click_action";

    public static final String TYPE_IN_ZONE = "0";
    public static final String TYPE_OUT_ZONE = "1";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String DISTANCE = "DISTANCE";
    public static final String ROUTING_ID = "ROUTING_ID";
    public static final String MINUTE = "MINUTE";
    public static final String FROM_FRAGMENT = "FROM_FRAGMENT";
    public static final String NOT_CONTACT_CUSTOMER_SUCCESS = "NOT_CONTACT_CUSTOMER_SUCCESS";
    public static final String NOT_CONTACT_CUSTOMER_FAIL = "NOT_CONTACT_CUSTOMER_FAIL";
    public static final String GET_ROUTING_DETAIL = "GET_ROUTING_DETAIL";
    public static final String CONFIRM_SUCCESS = "CONFIRM_SUCCESS";
    public static final String CONFIRM_FAIL = "CONFIRM_FAIL";
    public static final String INVALID_QR_SO = "INVALID_QR_SO";
    public static final String CANCEL_ORDER_SUCCESS = "CANCEL_ORDER_SUCCESS";
    public static final String CANCEL_ORDER_FAIL = "CANCEL_ORDER_FAIL";
    public static final String CONFIRM_ARRIVED_FIRST = "CONFIRM_ARRIVED_FIRST";
    public static final String CONFIRM_ARRIVED_FAIL = "CONFIRM_ARRIVED_FAIL";
    public static final String KEY_NOUN = "KEY_NOUN";
    public static final String KEY_BASE = "KEY_BASE";
    public final static String FIRST_USE = "FIRST_USE";
    public final static int METHOD_WALLET = 1;//tra bang vi
    //    public static final int CODE_SUCCESS =200;
//    public static final int CODE_NOT_FOUND =404;
//    public static final int CODE_ERROR_INTERNAL =500;
//    public static final int CODE_AUTHEN_FAIL =401;
    public final static int METHOD_CARD = 2; // tra bang the
    public final static int METHOD_DIRECTORY = 3; //tra truc tiep
    public final static String SPACE = " ";
    //    public static final int ORDER_PENDING = 0;
//    public static final int ORDER_APPROVE = 1;
//    public static final int ORDER_REJECT = 2;
//    public static final int LANG_VI = 1;
    public static final int LANG_EN = 2;
    public static final long MERCHANT_ID_FAKE = 1;
    public static final int INCENTIVE = 1;
    public static final int SALE = 0;
    public static final int GET_DATA_MANUFACTURE = 1001;
    public static final int GET_DATA_CATEGORIES = 1002;
    public static final int GET_DATA_PRODUCT = 1003;
    public static final int GET_PRODUCT_DETAIL = 1004;
    public static final int GET_DATA_LIST_ORDER = 1005;
    public static final int GET_DATA_LIST_PRODUCT_ORDER_CART = 1020;
    public static final int INTENT_GET_RECEPTION_ADDRESS = 1021;
    public static final int INTENT_GET_METHOD_PAYMENT = 1022;
    public static final int GET_DATE_ORDER_DETAIL = 1006;
    public static final int REGISTER_MERCHANT = 1007;
    public static final int GET_DATA_MERCHANT = 1008;
    public static final int CONNECT_TIMEOUT = 30;
    public static final int READ_TIMEOUT = 30;
    public static final int WRITE_TIMEOUT = 30;
    public static final String KEY_CLAUSE = "CLAUSE";
    public static final String GET_DATA_SUCCESS = "GET_DATA_SUCCESS";
    public static final String GET_DATA_FAIL = "GET_DATA_FAIL";
    public static boolean IS_REFRESH_ORDERED = false;
    public static boolean IS_REFRESH_ORDER_PENDING = false;
    public static boolean IS_REFRESH_ORDER_REJECT = false;

    public enum CODE {
        SUCCESS(200), NOT_FOUND(404), ERROR_INTERNAL(500), AUTHEN_FAIL(401), USER_DEFIEND(-200), FATAL_ERROR(-1);

        private Integer code;

        CODE(Integer i) {
            code = i;
        }

        public Integer getCode() {
            return code;
        }
    }

    public enum ORDER {
        PENDING(0), APPROVE(1), REJECT(2);
        private Integer code;

        ORDER(Integer i) {
            code = i;
        }

        public Integer getCode() {
            return code;
        }
    }
}
