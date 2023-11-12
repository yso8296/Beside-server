package com.hackathon.beside.fcm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestPushMessage {

    MORNING_ALARM("비사이드", "오늘은 경제 기초 시리즈(1)-가계부에 대해 공부를 할 시간이예요. 카드뉴스를 확인해보세요:)"),
    LUNCH_ALARM("비사이드", "오늘은 한 주 동안 공부한 카드뉴스를 필기노트를 통해 복습하는 날이에요:)"),
    DINNER_ALARM("비사이드", "오늘은 한 주 동안 공부한 내용을 퀴즈를 통해 점검하는 날이에요. 퀴즈를 풀고 랭킹을 확인해요:)")
    ;

    String title;
    String body;
}
