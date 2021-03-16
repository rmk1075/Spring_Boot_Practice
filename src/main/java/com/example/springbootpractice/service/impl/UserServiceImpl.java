package com.example.springbootpractice.service.impl;

import com.example.springbootpractice.domain.User;
import com.example.springbootpractice.dto.UserDto;
import com.example.springbootpractice.repository.UserRepository;
import com.example.springbootpractice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto getUserInfo(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDto::new).orElse(null);
    }

    /**
     * 사용자 정보 반환
     * 로그인 시 사용
     * @param userId
     * @return
     */
    @Override
    public UserDto getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId);
        return user != null ? new UserDto(user) : null;
    }

    /**
     * 회원가입시 사용
     * 회월가입 성공여부 반환
     * @param userDto
     * @return
     */
    @Override
    public boolean addUser(UserDto userDto) {
        if(userRepository.countByUserIdOrEmail(userDto.getName(), userDto.getEmail()) != 0)
            return false;

        userRepository.save(new User(userDto.getName(), userDto.getEmail(), userDto.getUserId(), userDto.getPassword(), userDto.getCrtnDate(), userDto.getChgDate()));
        return true;
    }
}
