package com.weylandyutani.starter.controller;

import com.weylandyutani.starter.dto.CommandRequest;
import com.weylandyutani.starter.service.CommandExecutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/starter/commands")
public class CommandController {

    @Autowired
    private CommandExecutorService executorService;

    @PostMapping
    public ResponseEntity<String> submitCommand(@Valid @RequestBody CommandRequest request) {
        executorService.execute(request);
        return ResponseEntity.ok("Команда принята к исполнению");
    }
}
