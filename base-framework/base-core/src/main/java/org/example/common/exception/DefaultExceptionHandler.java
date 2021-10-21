package org.example.common.exception;


import lombok.extern.slf4j.Slf4j;
import org.example.common.response.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * 异常统一处理
 *
 * @author SHK
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {


    /**
     * 参数解析失败异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseResult handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error(request.getRequestURI() + ":参数解析失败",e);
        return new ResponseResult(GlobalExceptionEnum.BAD_REQUEST_ERROR.getCode(),"参数解析失败");
    }

    /**
     * 不支持当前请求方法异常处理
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error(request.getRequestURI() + ":不支持当前请求方法",e);
        return new ResponseResult(GlobalExceptionEnum.METHOD_NOT_ALLOWED.getCode(),"不支持当前请求方法");
    }

    /**
     * 项目运行异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult handleException(Exception e, HttpServletRequest request) {
        log.error(request.getRequestURI() + ":服务运行异常",e);
        return new ResponseResult(GlobalExceptionEnum.SYS_ERROR.getCode(),"服务运行异常");
    }

    /**
     * SQL异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResponseResult handleSQLException(Exception e, HttpServletRequest request) {
        log.error(request.getRequestURI() + ":数据库异常",e);
        return new ResponseResult(GlobalExceptionEnum.SQL_ERROR.getCode(),"数据库异常");
    }


    /**
     * 自定义异常处理
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public ResponseResult handleException(GlobalException e, HttpServletRequest request) {
        log.error(request.getRequestURI() + ":自定义内部异常",e.getDetails());
        return new ResponseResult(e.getCode(),e.getDetails());

    }


}
