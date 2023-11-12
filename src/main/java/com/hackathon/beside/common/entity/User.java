package com.hackathon.beside.common.entity;

import com.hackathon.beside.common.enums.UserAuthority;
import com.hackathon.beside.user.JoinForm;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.hackathon.beside.common.enums.UserAuthority.NORMAL_USER;

@Getter
@Setter
@Table(name = "users")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    private UserAuthority authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interest_id")
    private Interest interest;
    @OneToMany(mappedBy = "user")
    private List<QuizOptionUserMapping> quizOptionsUserMappings;
    @OneToMany(mappedBy = "user")
    private List<QuizUsersMapping> quizUsersMappings;
    @OneToMany(mappedBy = "user")
    private List<SummaryUsersMapping> summaryUsersMappings;
    @OneToMany(mappedBy = "user")
    private List<NewsUsersMapping> newsUsersMappings;

    public static User toEntity(JoinForm form) {
        return User.builder()
                .account(form.getAccount())
                .password(form.getPassword())
                .nickname(form.getNickname())
                .authority(NORMAL_USER)
                .build();
    }
}
