package com.example.springbootpractice.repository;

import com.example.springbootpractice.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    @Query("SELECT COUNT(u) FROM User u WHERE u.userId = :userId OR u.email = :email")
    int countByUserIdOrEmail(@Param("userId") String userId, @Param("email") String email);
}
