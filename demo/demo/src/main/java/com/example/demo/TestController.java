package com.example.demo;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "Test";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/index")
    public String index(@RequestParam String num1, @RequestParam String num2) {
        int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
        return String.format("sum : %d", sum);
    }

    @GetMapping("/map/sum")
    public Map<String, String> mapSum(@RequestParam @Pattern(regexp = "^[0-9]*$") String num1, @RequestParam @Pattern(regexp = "^[0-9]*$") String num2){
        Map<String, String> map = new HashMap<>();

        int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
        map.put("sum", Integer.toString(sum));

        return map;
    }

    @GetMapping("/map/mul")
    public Map<String, String> mapMul(@RequestParam @Pattern(regexp = "^[0-9]*$") String num1, @RequestParam @Pattern(regexp = "^[0-9]*$") String num2){
        Map<String, String> map = new HashMap<>();

        int mul = Integer.parseInt(num1) * Integer.parseInt(num2);
        map.put("mul", Integer.toString(mul));

        return map;
    }

    @ControllerAdvice
    public class CustomErrorHandler {
        @ExceptionHandler(ConstraintViolationException.class)
        public void handleConstraintViolationException(ConstraintViolationException exception, ServletWebRequest webRequest) throws IOException {
            webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        }
    }
}
