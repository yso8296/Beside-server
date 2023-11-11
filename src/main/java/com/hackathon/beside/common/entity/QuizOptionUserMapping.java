package com.hackathon.beside.common.entity;

import jakarta.persistence.*;

@Entity
public class QuizOptionUserMapping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_option_id")
    private QuizOption quizOption;
}
