package com.tecsup.lab05.aspect;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {

    @AfterThrowing(
            pointcut = "execution(* com.tecsup.lab05.service.*.*(..))",
            throwing = "ex"
    )
    public void capturarError(Exception ex) {
        System.out.println("ERROR AOP: " + ex.getMessage());
    }
}