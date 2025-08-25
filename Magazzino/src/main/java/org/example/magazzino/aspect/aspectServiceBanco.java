package org.example.magazzino.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class aspectServiceBanco {

    @Before("execution(* org.example.magazzino.service.BancoServiceImpl.*(..))")
    public void beforeBancoService(JoinPoint joinPoint) {
        System.out.println("Entrata nel metodo " + joinPoint.getSignature().getName() + " della classe BancoService()");
        System.out.println("Argomenti " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* org.example.magazzino.service.BancoServiceImpl.*(..))",
            returning = "result")
    public void afterBancoService(JoinPoint joinPoint, Object result) {
        System.out.println("Uscita dal metodo " +  joinPoint.getSignature().getName() +" della classe BancoService()");
        System.out.println("Risultato " + result);
    }
}