package fhtw.msc.swe.aigensberger.exercise_12factor.db;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EmptyResultDataAccessExceptionAdvice {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleEmptyResultDataAccessException(EmptyResultDataAccessException exception) {
        return exception.getMessage();
    }
}
