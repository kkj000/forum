package myProj.forum.repository;

import myProj.forum.domain.Post;
import myProj.forum.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class PostRepositoryTest {

    @Autowired
    private PostService postRepository;

    @Test
    public void InsertPost(){
        Post post = new Post();
        post.setTitle("Test_title");
        post.setContent("Test_Content");
        post.setAuthor("Test_Author");

        Post saved = postRepository.save(post);
        assertThat(saved).isEqualTo(post);
    }

    @Test
    public void testFindById() {
        Post post = new Post();
        post.setTitle("Test_Title");
        post.setContent("Test_Content");
        post.setAuthor("Test_Author");
        postRepository.save(post);

        Post fetchedPost = postRepository.findById(post.getId()).orElseThrow();
        assertThat(fetchedPost).isNotNull();
        assertThat(fetchedPost.getTitle()).isEqualTo("Test_Title");
    }
}
