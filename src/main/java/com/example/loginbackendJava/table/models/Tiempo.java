package com.example.loginbackendJava.table.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "t_tiempo")
@AllArgsConstructor
@NoArgsConstructor
public class Tiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long codigo;
    @Column(length = 8)
    private String clave;
    private int t1;
    private int t2;
    private int t3;
    private int t4;
    private int t5;
    private int t6;
    private int t7;
    private Date fecha;

}
