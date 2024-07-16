package myProj.forum.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import myProj.forum.domain.Post;
import myProj.forum.repository.PostMapper;
import myProj.forum.repository.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    public Post save(Post post){
        postMapper.save(post);
        return post;
    }

    public void updateContents(Long postId, PostUpdateDto updateDto){
        postMapper.updateContents(postId, updateDto);
    }

    public void updateViews(Long postId, int views){
        postMapper.updateViews(postId, views);
    }

    public void delete(Long postId){
        postMapper.delete(postId);
    }

    public Optional<Post> findById(Long id){
        return postMapper.findById(id);
    }

    public List<Post> findAll(){
        return postMapper.findAll();
    }

    public List<Post> findByAuthor(String author){ return postMapper.findByAuthor(author);}

    public void incrementView(Long postId, HttpServletRequest request){
        Boolean fromLike = (Boolean)request.getSession().getAttribute("post");
        if(fromLike == null || !fromLike){
            postMapper.incrementViewCount(postId);
        }

    }


}
