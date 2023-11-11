package com.hackathon.beside.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class NewsContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private News news;
}
