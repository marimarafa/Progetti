package com.example.magazzino.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class aspectControllerBanco {

    @Before("execution(* com.example.magazzino.controller.BancoController*(..))")
    public void beforeBancoController(JoinPoint joinPoint) {
        System.out.println("Entrata nel metodo " + joinPoint.getSignature().getName() + " della classe BancoController()");
        System.out.println("Argomenti " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.example.magazzino.controller.BancoController*(..))",
            returning = "result")
    public void afterBancoController(JoinPoint joinPoint, Object result) {
        System.out.println("Uscita dal metodo " +  joinPoint.getSignature().getName() +" della classe BancoController()");
        System.out.println("Risultato " + result);
    }
}