package com.hackathon.beside.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Question {

    @Id
    @GeneratedValue
    private Long id;
    private String explanation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Quiz quiz;
}
