package com.hackathon.beside.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.hackathon.beside.fcm.RequestPushMessage.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationScheduler {

//    private final UserRepository userRepository;
//
//    private FirebaseMessaging instance;
//
//    @PostConstruct
//    public void firebaseSetting() throws IOException {
//        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("firebase/knu-cse-hackathon-firebase-adminsdk-g0gvw-850377a4d0.json").getInputStream())
//                .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
//        FirebaseOptions secondaryAppConfig = FirebaseOptions.builder()
//                .setCredentials(googleCredentials)
//                .build();
//        FirebaseApp app = FirebaseApp.initializeApp(secondaryAppConfig);
//        this.instance = FirebaseMessaging.getInstance(app);
//    }
//
//    @Scheduled(fixedRate = 500000)
//    public void pushMorningDietAlarm() throws FirebaseMessagingException {
//        log.info("비사이드");
//
//        // 모든 사용자의 FCM 토큰 가져오기
//        List<String> allUserFCMTokens = getAllUserFCMTokens();
//
//        // 알림 전송
//        pushAlarm(MORNING_ALARM, allUserFCMTokens);
//    }
//
//    @Scheduled(fixedRate = 600000)
//    public void pushDietAlarm() throws FirebaseMessagingException {
//        log.info("비사이드");
//
//        // 모든 사용자의 FCM 토큰 가져오기
//        List<String> allUserFCMTokens = getAllUserFCMTokens();
//
//        // 알림 전송
//        pushAlarm(DINNER_ALARM, allUserFCMTokens);
//    }
//
//    @Scheduled(fixedRate = 300000)
//    public void pushMornigDietAlarm() throws FirebaseMessagingException {
//        log.info("비사이드");
//
//        // 모든 사용자의 FCM 토큰 가져오기
//        List<String> allUserFCMTokens = getAllUserFCMTokens();
//
//        // 알림 전송
//        pushAlarm(LUNCH_ALARM, allUserFCMTokens);
//    }
//
//    private void pushAlarm(RequestPushMessage data, List<String> userFCMTokens) throws FirebaseMessagingException {
//        for (String userFCMToken : userFCMTokens) {
//            Message message = getMessage(data, userFCMToken);
//            sendMessage(message);
//        }
//    }
//
//    private Message getMessage(RequestPushMessage data, String userFCMToken) {
//        Notification notification = Notification.builder()
//                .setTitle(data.getTitle())
//                .setBody(data.getBody())
//                .build();
//
//        Message.Builder builder = Message.builder()
//                .setToken(userFCMToken)
//                .setNotification(notification);
//
//        return builder.build();
//    }
//
//    public String sendMessage(Message message) throws FirebaseMessagingException {
//        return this.instance.send(message);
//    }
//
//    // 사용자의 FCM 토큰을 데이터베이스에서 가져오는 메서드 (예시)
//    private List<String> getAllUserFCMTokens() {
//        // 여기에서 데이터베이스 또는 다른 저장소에서 모든 사용자의 FCM 토큰을 조회하고 리스트로 반환하는 로직을 작성
//        // 예시: return userDao.getAllUserFCMTokens();
//        List<String> tokens = new ArrayList<>();
//        List<User> users = userRepository.findAll();
//        List<User> filteredUser = users.stream()
//                .filter(user ->
//                        (user.getFcmToken() != null &&
//                                !user.getFcmToken().trim().isEmpty()))
//                .toList();
//
//        for (User user : filteredUser) {
//            System.out.println("fcm token: " + user.getFcmToken());
//            tokens.add(user.getFcmToken());
//        }
//        return tokens;
//    }

}
