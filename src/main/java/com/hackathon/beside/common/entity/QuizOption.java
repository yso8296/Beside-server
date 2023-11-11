package com.hackathon.beside.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class QuizOption {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private boolean isAnswer;
    @OneToMany(mappedBy = "quizOption")
    private List<QuizOptionUserMapping> quizOptionsUserMappings;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}
