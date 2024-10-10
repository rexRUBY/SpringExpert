package org.example.expert.domain.User;

import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
public class UserDataGenerationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void generateUserData() {
        int numberOfUsers = 1000000;
        Set<String> nicknames = new HashSet<>();

        for (int i = 0; i < numberOfUsers; i++) {
            String nickname;
            do {
                nickname = "user_" + UUID.randomUUID().toString().substring(0, 8);
            } while (nicknames.contains(nickname));

            nicknames.add(nickname);
            User user = new User();
            user.setNickname(nickname);
            userRepository.save(user);

            if (i % 10000 == 0) {
                System.out.println("유저 생성: " + i);
            }
        }
    }
}
