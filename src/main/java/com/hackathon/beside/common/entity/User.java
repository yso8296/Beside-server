package com.hackathon.beside.common.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String password;
    private String nickname;
    private Long readNewsCount;
    private Long enteredQuizCount;
    private Float correctRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id")
    private Interest interest;

    @OneToMany(mappedBy = "user")
    private List<TermsUsersMapping> termsUsersMappings;
    @OneToMany(mappedBy = "user")
    private List<QuizOptionUserMapping> quizOptionsUserMappings;
    @OneToMany(mappedBy = "user")
    private List<QuizUsersMapping> quizUsersMappings;
    @OneToMany(mappedBy = "user")
    private List<SummaryUsersMapping> summaryUsersMappings;
    @OneToMany(mappedBy = "user")
    private List<NewsUsersMapping> newsUsersMappings;
}
