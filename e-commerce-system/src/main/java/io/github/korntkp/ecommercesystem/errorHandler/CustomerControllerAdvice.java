package io.github.korntkp.ecommercesystem.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse customerNotFound(CustomerNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Customer=" + ex.getMessage() + " not found");
    }
}
