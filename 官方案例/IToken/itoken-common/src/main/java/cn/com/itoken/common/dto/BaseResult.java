package cn.com.itoken.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BaseResult implements Serializable {
    public static final String RESULT_OK = "ok";
    public static final String RESULT_NOT_OK = "not_ok";
    public static final String SUCCESS = "成功操作";
    public static final String FAIL = "操作失败";

    private String result;
    private Object data;
    private Cursor cursor;
    private String success;
    private List<Error> errors;

    public static BaseResult ok(){
        return createBaseResult(RESULT_OK, null, null, SUCCESS, null);
    }

    public static BaseResult ok(Object data){
        return createBaseResult(RESULT_OK, data, null, SUCCESS, null);
    }

    public static BaseResult notOk(List<BaseResult.Error> errors){
        return createBaseResult(RESULT_NOT_OK, null, null, FAIL, errors);
    }

    /**
     *
     * @param result
     * @param data
     * @param cursor
     * @param success
     * @param errors
     * @return
     */
    private static BaseResult createBaseResult(String result, Object data, Cursor cursor, String success, List<Error> errors){
        BaseResult baseResult = new BaseResult();
        baseResult.setResult(result);
        baseResult.setData(data);
        baseResult.setCursor(cursor);
        baseResult.setSuccess(success);
        baseResult.setErrors(errors);

        return baseResult;
    }

    @Data
    public static class Cursor{
        private int total;
        private int offset;
        private int limit;
    }

    @Data
    @AllArgsConstructor
    public static class Error{
        private String field;
        private String message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
