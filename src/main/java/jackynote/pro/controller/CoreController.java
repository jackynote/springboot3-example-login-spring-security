package jackynote.pro.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoreController {

    @ExceptionHandler
    String handleException(Exception e) {
        e.printStackTrace();
        return "error";
    }
}
