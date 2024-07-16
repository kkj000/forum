package myProj.forum.repository;

import myProj.forum.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);

    Optional<User> findByLoginId(String LoginId);
}
