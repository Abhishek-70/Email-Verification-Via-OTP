package com.OtpBasedLogIn.OtpBasedLogIn.service.Impl;

import com.OtpBasedLogIn.OtpBasedLogIn.entity.User2;
import com.OtpBasedLogIn.OtpBasedLogIn.repository.UserRepository;
import com.OtpBasedLogIn.OtpBasedLogIn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    private Long generateRandomId() {
        // Generate a random number, you can customize this as needed
        return ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
    }


    @Override
    public User2 saveUser(User2 user2) {
        user2.setUid(generateRandomId());
        User2 savedUser = userRepository.save(user2);
        return savedUser;
    }
}
