package com.tecsup.lab05.aspect;

import com.tecsup.lab05.service.AuditoriaService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @AfterReturning("execution(* com.tecsup.lab05.service.ProductoService.actualizar(..))")
    public void auditarActualizar(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        String idProducto = args.length > 0 ? String.valueOf(args[0]) : "desconocido";

        auditoriaService.registrar(
                "ACTUALIZAR",
                joinPoint.getSignature().getName(),
                "Se actualizó producto con ID: " + idProducto
        );
    }

    @AfterReturning(
            pointcut = "execution(* com.tecsup.lab05.service.ProductoService.listar(..))",
            returning = "resultado"
    )
    public void auditarListar(JoinPoint joinPoint, Object resultado) {

        int cantidad = 0;
        if (resultado instanceof List<?> lista) {
            cantidad = lista.size();
        }

        auditoriaService.registrar(
                "LISTAR",
                joinPoint.getSignature().getName(),
                "Se obtuvieron " + cantidad + " registros"
        );
    }
}