package com.spring.workshopmongo.resources.exception;

import com.spring.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice   // trata possíveis erros nas requisições
public class ResourceExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)  // quando ocorrer o erro faça isso
  public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Not Found!", e.getMessage(), req.getRequestURI());
    return ResponseEntity.status(status).body(err);
  }
}
