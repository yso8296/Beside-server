package com.hackathon.beside.ranking;

import com.hackathon.beside.common.entity.QuizUsersMapping;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.*;

@RequiredArgsConstructor
@Service
public class RankService {

    private final UserRepository userRepository;

    public TotalRankDto getUserRank(Long userId, String interest) {
        // 1. 모든 유저 정보 조회
        // 2. interestId 이용해서 관심 주제 일치하는 유저 추출
        // 3. 각 유저마다 quizUserMapping 조회해서 맞은 개수 추출
        // 4. 점수로 순위 계산, 점수 같으면 시간 짧은 순

        List<User> users = userRepository.findAll();  // 1
        List<User> interestUsers = new ArrayList<>();

        for (User user : users) { // 2
            if (user.getInterest().getName().equals(interest)) {
                interestUsers.add(user);
            }
        }

        Map<Integer, User> map = new HashMap<>();
        for (User interestUser : interestUsers) {
            List<QuizUsersMapping> quizUsersMappings = interestUser.getQuizUsersMappings();
            int score = 0;
            for (QuizUsersMapping quizUsersMapping : quizUsersMappings) {
                score += quizUsersMapping.getCorrectCount() * 10;
            }
            map.put(score, interestUser);
        }

        Map<Integer, User> keyDescendingMap = new TreeMap<>(Collections.reverseOrder());  // 3
        keyDescendingMap.putAll(map);

        List<MyRankDto> userRanks = new ArrayList<>();
        MyRankDto myRanking = new MyRankDto();
        int cnt = 1;
        int check = 0;
        Random random = new Random();

        for (int key : keyDescendingMap.keySet()) {
            User user = keyDescendingMap.get(key);

            // 랜덤값 추가
            float randomValue = (float) (random.nextInt(50) + 1) / 10.0f; // 0.1부터 5.0까지의 랜덤값

            if (user.getId() == userId) {
                myRanking = MyRankDto.toMyRankDto(user, cnt, (float) key + randomValue);
                check++;
            } else if (userRanks.size() < 3) {
                userRanks.add(MyRankDto.toMyRankDto(user, cnt, (float) key + randomValue));
                check++;
            }
            cnt++;

            if(check == 4) break;
        }



        return TotalRankDto.toTotalRankDto(myRanking, userRanks);
    }

    public TotalSchoolDto getSchoolRank(Long userId, String interest) {

        List<User> users = userRepository.findAll();  // 1
        List<User> interestUsers = new ArrayList<>();

        for (User user : users) { // 2
            if (user.getInterest().getName().equals(interest)) {
                interestUsers.add(user);
            }
        }

        Map<String, Integer> map = new HashMap<>();
        for (User interestUser : interestUsers) {
            boolean containsValue = map.containsValue(interestUser.getSchool().getName());
            if (!containsValue) {
                map.put(interestUser.getSchool().getName(), 0);
            }
        }

        for (User interestUser : interestUsers) {
            List<QuizUsersMapping> quizUsersMappings = interestUser.getQuizUsersMappings();
            int score = 0;
            for (QuizUsersMapping quizUsersMapping : quizUsersMappings) {
                score += quizUsersMapping.getCorrectCount() * 10;
            }
            map.put(interestUser.getSchool().getName(), map.get(interestUser.getSchool().getName() + score));
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // 내림차순 정렬
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<SchoolRankDto> userRanks = new ArrayList<>();
        SchoolRankDto myRanking = new SchoolRankDto();
        int cnt = 1;
        int check = 0;
        Random random = new Random();
        User user = userRepository.findById(userId).get();
        for (Map.Entry<String, Integer> stringIntegerEntry : entryList) {
            // 랜덤값 추가
            float randomValue = (float) (random.nextInt(50) + 1) / 10.0f; // 0.1부터 5.0까지의 랜덤값

            if (user.getSchool().getName().equals(stringIntegerEntry.getKey())) {
                myRanking = SchoolRankDto.toSchoolRankDto(cnt, user.getSchool().getName(), (float) stringIntegerEntry.getValue().floatValue() + randomValue);
                check++;
            } else if (userRanks.size() < 3) {
                userRanks.add(SchoolRankDto.toSchoolRankDto(cnt, stringIntegerEntry.getKey(), (float) stringIntegerEntry.getValue().floatValue() + randomValue));
                check++;
            }
            cnt++;

            if (check == 4) break;
        }
        return TotalSchoolDto.toTotalRankDto(myRanking, userRanks);
    }
}
