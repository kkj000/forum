package myProj.forum.service;

import lombok.RequiredArgsConstructor;
import myProj.forum.domain.Post;
import myProj.forum.repository.LikeMapper;
import myProj.forum.repository.PostMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper likeMapper;
    private final PostMapper postMapper;

    public void likePost(Long userId, Long postId){
        Boolean already = likeMapper.isExistLike(userId,postId);
        if(already!=null){
            return;
        }

        likeMapper.insertLike(userId,postId);

        Post post = postMapper.findById(postId).orElseThrow();
        post.setLikes(post.getLikes()+1);
        postMapper.updateLikes(post.getId(),post.getLikes(),post.getDislikes());
    }

    public void dislikePost(Long userId, Long postId){
        Boolean already = likeMapper.isExistDislike(userId,postId);
        if(already!=null){
            return;
        }

        likeMapper.insertDislike(userId,postId);

        Post post = postMapper.findById(postId).orElseThrow();
        post.setDislikes(post.getDislikes()+1);
        postMapper.updateLikes(post.getId(),post.getLikes(),post.getDislikes());
    }
}
