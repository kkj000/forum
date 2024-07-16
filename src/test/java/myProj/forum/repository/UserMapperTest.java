package myProj.forum.repository;

import myProj.forum.domain.User;
import myProj.forum.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    private UserService userService;

    @Test
    public void findByLoginIdTest(){
        User user = new User();
        user.setLoginId("testId");
        user.setPassword("testPassword");

        userService.save(user);

        User fetchedUser = userService.findByLoginId(user.getLoginId());

        Assertions.assertThat(fetchedUser.getLoginId()).isEqualTo("testId");

    }
}
