package com.karmanno.letmeconvert.repository;

import com.karmanno.letmeconvert.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}
