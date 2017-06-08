package cn.muye.core;

public class AjaxResult {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_LOG_FAILED = 1; //未登录失败
    public static final int CODE_PARAM_MISTAKE_FAILED = 2; //参数有误失败
    public static final int CODE_NOT_AUTHORIZED_FAILED = 3; //用户无权限
    public static final int CODE_ERROR_FAILED = 4; //其他错误
    private int code;
    private String message;
    private Object data;

    public AjaxResult(int code, Object data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AjaxResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public int getCode(){return code;}

    public boolean isSuccess() {
        return CODE_SUCCESS == code;
    }

    public static final AjaxResult success() {
        return new AjaxResult(CODE_SUCCESS, null, null);
    }

    public static final AjaxResult success(Object data) {
        return new AjaxResult(CODE_SUCCESS, data, null);
    }

    public static final AjaxResult success(Object data, String message) {
        return new AjaxResult(CODE_SUCCESS, data, message);
    }

    public static final AjaxResult failed() {
        return new AjaxResult(CODE_LOG_FAILED, null, null);
    }

    public static final AjaxResult failed(String message) {
        return new AjaxResult(CODE_LOG_FAILED, null, message);
    }

    public static final AjaxResult failed(Object data, String message) {
        return new AjaxResult(CODE_LOG_FAILED, data, message);
    }

    public static final AjaxResult failed(int code, String message) {
        return new AjaxResult(code, "", message);
    }

    public static final AjaxResult failed(Object data) {
        return new AjaxResult(CODE_LOG_FAILED, data, "");
    }

    public static final AjaxResult failed(int code, Object data, String message) {
        return new AjaxResult(code, data, message);
    }

    public static final AjaxResult emptyArray() {
        String[] arr = new String[]{};
        return new AjaxResult(CODE_SUCCESS, arr, "empty");
    }
}
