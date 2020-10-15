package com.kingdee.web.advise;

import com.kingdee.exceptions.RestApiException;
import com.kingdee.web.results.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Description: 全局Controller异常
 *
 * @author libin
 * Created on 2019/4/18
 **/
@Slf4j
@RestControllerAdvice
public class DefaultRestControllerAdvice {

    @ExceptionHandler(SQLException.class)
    public JsonResult handleSqlException(SQLException ex) {
        JsonResult result = JsonResult.of();
        //log.error(ex.getMessage(), ex);
        return result.fail("数据库操作异常");
    }

    @ExceptionHandler(DataAccessException.class)
    public JsonResult handleDataAccessException(DataAccessException ex) {
        JsonResult result = JsonResult.of();
        //log.error(ex.getMessage(), ex);
        return result.fail("数据库操作异常");
    }

    /**
     * REST接口通用异常。
     * 返回指定的HTTP状态码，以异常消息作为响应文本
     */
    @ExceptionHandler(RestApiException.class)
    public JsonResult handleIllegalParamEx(RestApiException ex) {
        //log.error(ex.getMessage(), ex);
        JsonResult result = JsonResult.of();
        return result.fail(ex.getMessage());
    }

    /**
     * 参数验证
     *
     * @param ex 异常
     * @return JsonResult
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResult handleValidEx(MethodArgumentNotValidException ex) {
        //log.error(ex.getMessage(), ex);
        return JsonResult.of(ex.getBindingResult());
    }

    /**
     * 400 - (Validation failed)
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public JsonResult handleBindException(BindException ex) {
        //log.error(ex.getMessage(), ex);
        return JsonResult.of(ex.getBindingResult());
    }


    /**
     * 请求的 URL 参数检验
     *
     * @param ex 异常信息
     * @return 返回提示信息
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResult handleBindException2(ConstraintViolationException ex) {
        String errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
        JsonResult result = JsonResult.of();
        //log.error(ex.getMessage(), ex);
        return result.fail(errors);
    }


    /**
     * 400 - (Missing request parameter)
     */
   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JsonResult handleMissingParamEx(MissingServletRequestParameterException ex) {
        //log.error(ex.getMessage(), ex);
        JsonResult result = JsonResult.of();
        return result.fail("缺少请求参数信息：".concat(ex.getParameterName()));
    }*/

    /**
     * 500 - Server Error
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public JsonResult handleUnexpectedException(Exception ex) {
        //log.error(ex.getMessage(), ex);
        JsonResult result = JsonResult.of();
        return result.fail(ex.getMessage());
    }
}
