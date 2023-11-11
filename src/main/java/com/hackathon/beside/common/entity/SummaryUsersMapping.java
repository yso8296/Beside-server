package com.hackathon.beside.common.entity;

import jakarta.persistence.*;

@Entity
public class SummaryUsersMapping {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "summary_id")
    private Summary summary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
