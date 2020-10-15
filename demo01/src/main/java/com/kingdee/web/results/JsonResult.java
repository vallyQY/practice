package com.kingdee.web.results;

import com.xzkingdee.comm.constants.RespCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import java.util.stream.Collectors;

/**
 * JSON result object for AJAX method
 *
 * @author coder
 */
public class JsonResult {

    /**
     * 响应码
     */
    @Getter
    @Setter
    protected String code;

    /**
     * 响应消息
     */
    @Getter
    @Setter
    protected String message;

    /**
     * New successful instance
     */
    public JsonResult() {
        this.code = RespCode.OK;
    }

    /**
     * New failed instance
     */
    public JsonResult(String errMsg) {
        this.code = RespCode.FAILED;
        this.message = errMsg;
    }

    public JsonResult(String code, String errMsg) {
        this.code = code;
        this.message = errMsg;
    }

    public boolean isSuccessful() {
        return RespCode.OK.equals(this.code);
    }

    public JsonResult succeed() {
        this.code = RespCode.OK;
        this.message = null;
        return this;
    }

    public JsonResult fail(String errMsg) {
        this.code = RespCode.FAILED;
        this.message = errMsg;
        return this;
    }

    public JsonResult apply(String code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public JsonResult copy(JsonResult other) {
        this.code = other.code;
        this.message = other.message;
        return this;
    }

    public static JsonResult of(){
        return new JsonResult();
    }

    public static JsonResult success() {
        return new JsonResult().succeed();
    }

    public static JsonResult failed(String message) {
        return new JsonResult().fail(message);
    }

    /**
     * 根据binding生成JsonResult对象
     */
    public static JsonResult of(BindingResult binding) {
        JsonResult result = new JsonResult();
        if (binding.hasErrors()) {
            String errors = binding.getAllErrors()
                                   .stream()
                                   .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                   .collect(Collectors.joining(";"));
            result.fail(errors);
        }

        return result;
    }

    /**
     * 根据binding生成JsonResult对象
     */
    public static JsonResult of(Errors errors) {
        JsonResult result = new JsonResult();
        if (errors.hasErrors()) {
            String message = errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            result.fail(message);
        }
        return result;
    }

    public static JsonResult of(Exception e) {
        return new JsonResult(e.getMessage());
    }
}
