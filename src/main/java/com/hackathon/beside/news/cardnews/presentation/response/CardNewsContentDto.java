package com.hackathon.beside.news.cardnews.presentation.response;

import lombok.Getter;

import java.util.List;

@Getter
public class CardNewsContentDto {

    List<String> contentUrls;

    public CardNewsContentDto(List<String> contentUrls) {
        this.contentUrls = contentUrls;
    }
}
