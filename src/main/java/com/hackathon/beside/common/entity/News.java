package com.hackathon.beside.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private Integer takenTime;
    private LocalDate publishedDate;

    @OneToMany(mappedBy = "news")
    public List<NewsContent> newsContents;
}
