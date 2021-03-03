package com.example.springbootpractice.repository;

import com.example.springbootpractice.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
