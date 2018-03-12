package cn.muye.core;

/**
 * Created by Ray.Fu on 2017/6/15.
 */
public class AjaxResult4App {

    public static final int CODE_SUCCESS = 0; //成功
    public static final int CODE_ERROR_LIMIT = 1; //授权机器已达上限
    public static final int CODE_ERROR_NOT_EXIST = 2; //AppId不存在
    public static final int CODE_ERROR_EXPIRED = 3; //到期错误
    public static final int CODE_ERROR_PARAM= 4; //参数出错

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AjaxResult4App(int code, Object data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public AjaxResult4App(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final AjaxResult4App success() {
        return new AjaxResult4App(CODE_SUCCESS, "", "");
    }

    public static final AjaxResult4App success(Object data) {
        return new AjaxResult4App(CODE_SUCCESS, data, "");
    }

    public static final AjaxResult4App success(Object data, String message) {
        return new AjaxResult4App(CODE_SUCCESS, data, message);
    }

    public static final AjaxResult4App failed(int code, String message) {
        return new AjaxResult4App(code, "", message);
    }

    public static final AjaxResult4App failed(int code, Object data, String message) {
        return new AjaxResult4App(code, data, message);
    }

    public static final AjaxResult4App emptyArray() {
        String[] arr = new String[]{};
        return new AjaxResult4App(CODE_SUCCESS, arr, "empty");
    }
}
