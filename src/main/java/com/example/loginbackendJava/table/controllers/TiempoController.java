package com.example.loginbackendJava.table.controllers;

import com.example.loginbackendJava.table.models.Tiempo;
import com.example.loginbackendJava.table.services.TiempoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public void addTiempo(@RequestBody Tiempo tiempo) {
        service.addTiempo(tiempo);
    }

    @GetMapping("/exportCSV")
    public ResponseEntity<byte[]> exportCsv() {
        try {
            byte[] csvBytes = service.writeCSV();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "datos.csv");
            headers.setContentLength(csvBytes.length);
            return new ResponseEntity<>(csvBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
