package ingressunibank.bookstore.aspect;


import lombok.var;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("execution(* ingressunibank.bookstore..*(..)))")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        var methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        final var stopWatch = new StopWatch();
        stopWatch.start();

        var result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        log.info(methodSignature.getDeclaringType().getSimpleName()
                + "." + methodSignature.getName() + " "
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }
}
