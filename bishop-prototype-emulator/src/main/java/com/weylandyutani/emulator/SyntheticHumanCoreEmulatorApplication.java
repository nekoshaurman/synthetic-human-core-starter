package com.weylandyutani.emulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.weylandyutani.emulator", "com.weylandyutani.starter"})
public class SyntheticHumanCoreEmulatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyntheticHumanCoreEmulatorApplication.class, args);
    }
}
