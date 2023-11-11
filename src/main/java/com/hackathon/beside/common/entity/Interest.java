package com.hackathon.beside.common.entity;

import com.hackathon.beside.common.enums.InterestEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Interest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public static Interest toEntity(String name) {
        long interestId = InterestEnum.convertToId(name);
        return Interest.builder()
                .id(interestId)
                .name(name)
                .build();
    }
}
