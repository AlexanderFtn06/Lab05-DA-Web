package com.tecsup.lab05.aspect;

import com.tecsup.lab05.service.AuditoriaService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditoriaAspect {

    @Autowired
    private AuditoriaService auditoriaService;

    @AfterReturning("execution(* com.tecsup.lab05.service.ProductoService.guardar(..))")
    public void auditarGuardar(JoinPoint joinPoint) {

        auditoriaService.registrar(
                "CREAR",
                joinPoint.getSignature().getName(),
                "Se registró un producto"
        );
    }

    @AfterReturning("execution(* com.tecsup.lab05.service.ProductoService.eliminar(..))")
    public void auditarEliminar(JoinPoint joinPoint) {

        auditoriaService.registrar(
                "ELIMINAR",
                joinPoint.getSignature().getName(),
                "Se eliminó un producto"
        );
    }
}