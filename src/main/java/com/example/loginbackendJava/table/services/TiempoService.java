package com.example.loginbackendJava.table.services;

import com.example.loginbackendJava.table.models.Tiempo;
import com.example.loginbackendJava.table.repositories.TiempoRepository;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TiempoService {
    private final TiempoRepository repository;

    public void addTiempo(Tiempo tiempo) {
        repository.save(tiempo);
    }

    public byte[] writeCSV() throws IOException {

        List<Tiempo> tiempos = repository.findAll();
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(Tiempo.class).withHeader();
        byte[] csvBytes;
        try {
            csvBytes = csvMapper.writer(csvSchema).writeValueAsBytes(tiempos);
        } catch (Exception e) {
            // Manejar excepciones
            csvBytes = new byte[0];
        }
        return csvBytes;
    }

    public List<Tiempo> getListTiempo() {
        return repository.findAll();
    }

}
