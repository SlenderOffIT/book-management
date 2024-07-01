package com.app.book_management.util;

import com.app.book_management.entity.stat.StatMadeRequest;
import com.app.book_management.service.ExecutionMetricService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * ExecutionTimeAspect используется для измерения времени выполнения методов,
 * аннотированных специальной аннотацией @ExecutionTime
 * @Around("@annotation(com.app.book_management.util.ExecutionTime)"):
 * аспект будет оперировать вокруг методов, аннотированных @ExecutionTime.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ExecutionTimeAspect {

    private final ExecutionMetricService metricService;

    @Around("@annotation(com.app.book_management.util.ExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - startTime;

        String methodName = "";

        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof StatMadeRequest request) {
                methodName += request.getFromView();
                break;
            }
        }

        metricService.saveExecutionTime(methodName, executionTime);

        return proceed;
    }

//    @Around("@annotation(com.app.book_management.util.ExecutionTime)")
//    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object proceed = joinPoint.proceed();
//        long executionTime = System.currentTimeMillis() - startTime;
//
//        metricService.saveExecutionTime(joinPoint.getSignature().getName(), executionTime);
//
//        return proceed;
//    }
}
