package com.hackathon.beside.common.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Summary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String content;
    private int takenTime;
    private LocalDate publishedDate;
    @OneToMany(mappedBy = "summary")
    private List<SummaryUsersMapping> summaryUsersMappings;
}
