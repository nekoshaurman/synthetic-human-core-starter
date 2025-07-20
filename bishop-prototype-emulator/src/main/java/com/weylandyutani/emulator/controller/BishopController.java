package com.weylandyutani.emulator.controller;

import com.weylandyutani.starter.dto.CommandRequest;
import com.weylandyutani.starter.service.CommandExecutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commands")
@RequiredArgsConstructor
public class BishopController {

    private final CommandExecutorService commandExecutorService;

    @PostMapping
    public ResponseEntity<?> submitCommand(@Valid @RequestBody CommandRequest command) {
        commandExecutorService.execute(command);
        return ResponseEntity.ok("Команда принята в обработку");
    }

    @GetMapping("/busy")
    public ResponseEntity<Integer> getBusy() {
        return ResponseEntity.ok(commandExecutorService.getQueueSize());
    }
}