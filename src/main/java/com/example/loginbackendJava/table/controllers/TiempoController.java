package com.example.loginbackendJava.table.controllers;

import com.example.loginbackendJava.table.models.Tiempo;
import com.example.loginbackendJava.table.services.TiempoService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tiempo")
@CrossOrigin("*")
public class TiempoController {
    private final TiempoService service;

    @GetMapping
    public ResponseEntity<List<Tiempo>> getAllTiempo() {
        return ResponseEntity.ok(service.getListTiempo());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addTiempo(@RequestBody Tiempo tiempo) {
        service.addTiempo(tiempo);
        return "redirect:/index";
    }

    @GetMapping("/exportCSV")
    public void exportCsv(HttpServletResponse response) {
        try {
            service.writeCSV(response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
