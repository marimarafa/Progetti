package org.example.magazzino.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class aspectControllerMagazzino {

    @Before("execution(* org.example.magazzino.controller.BancoController*(..))")
    public void beforeMagazzinoController(JoinPoint joinPoint) {
        System.out.println("Entrata nel metodo " + joinPoint.getSignature().getName() + " della classe MagazzinoController()");
        System.out.println("Argomenti " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* org.example.magazzino.controller.BancoController*(..))",
            returning = "result")
    public void afterMagazzinoController(JoinPoint joinPoint, Object result) {
        System.out.println("Uscita dal metodo " +  joinPoint.getSignature().getName() +" della classe MagazzinoController()");
        System.out.println("Risultato " + result);
    }
}