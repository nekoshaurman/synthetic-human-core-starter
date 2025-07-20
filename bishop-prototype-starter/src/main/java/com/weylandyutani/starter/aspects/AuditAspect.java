package com.weylandyutani.starter.aspects;

import com.weylandyutani.starter.service.AuditService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    private final AuditService auditService;

    public AuditAspect(AuditService auditService) {
        this.auditService = auditService;
    }

    @Around("@annotation(com.weylandyutani.starter.annotations.WeylandWatchingYou)")
    public Object audit(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed(); // вызов целевого метода
            return result;
        } finally {
            auditService.audit(
                    joinPoint.getSignature().toShortString(),
                    joinPoint.getArgs(),
                    result
            );
        }
    }
}