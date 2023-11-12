package com.hackathon.beside.common.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class QuizOptionUserMapping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_option_id")
    private QuizOption quizOption;

    public QuizOptionUserMapping(User user, QuizOption quizOption) {
        this.user = user;
        this.quizOption = quizOption;
    }
}
