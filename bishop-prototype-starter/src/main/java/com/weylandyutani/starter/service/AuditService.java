package com.weylandyutani.starter.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuditService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String auditTopic;

    public AuditService(KafkaTemplate<String, String> kafkaTemplate,
                        @Qualifier("auditTopic") String auditTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.auditTopic = auditTopic;
    }

    public void audit(String methodName, Object[] args, Object result) {
        String auditMessage = String.format(
                "AUDIT LOG: method=%s, args=%s, result=%s",
                methodName, Arrays.toString(args), result
        );

        kafkaTemplate.send(auditTopic, auditMessage);

        System.out.println("Отправлено в Kafka: " + auditMessage);
    }
}
