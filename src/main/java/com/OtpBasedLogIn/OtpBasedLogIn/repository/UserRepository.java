package com.OtpBasedLogIn.OtpBasedLogIn.repository;

import com.OtpBasedLogIn.OtpBasedLogIn.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User2,Long> {
   User2 findByEmail(String email);
}
