package com.tecsup.lab05.aspect;

import com.tecsup.lab05.service.AuditoriaService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private HttpServletRequest request;

    @AfterThrowing(
            pointcut = "execution(* com.tecsup.lab05.service.*.*(..))",
            throwing = "ex"
    )
    public void capturarError(JoinPoint joinPoint, Exception ex) {

        System.out.println("ERROR AOP: " + ex.getMessage());

        String usuario = request.getHeader("Usuario");
        if (usuario == null) usuario = "ANONIMO";
        auditoriaService.registrar(
                "ERROR",
                joinPoint.getSignature().getName(),
                "Excepción: " + ex.getMessage(),
                usuario
        );
    }
}