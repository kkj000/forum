package myProj.forum.service;

import lombok.RequiredArgsConstructor;
import myProj.forum.domain.User;
import myProj.forum.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.save(user);
        return user;
    }

    public User findByLoginId(String loginId){
        return userMapper.findByLoginId(loginId).orElseThrow();
    }
}
