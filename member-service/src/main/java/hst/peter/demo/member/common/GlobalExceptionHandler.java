package hst.peter.demo.member.common;

import hst.peter.demo.core.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author peter.huang
 * @date 2019/8/31 14:12
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.fail(ErrCode.EXCEPTION.getCode(), e.getMessage());
    }
}
