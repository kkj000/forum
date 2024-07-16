package myProj.forum.service;

import lombok.RequiredArgsConstructor;
import myProj.forum.domain.Comment;
import myProj.forum.repository.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public Comment save(Comment comment){
        commentMapper.save(comment);
        return comment;
    }

    public void delete(Long id){
        commentMapper.delete(id);
    }

    public List<Comment> findByPostId(Long postId){
        return commentMapper.findByPostId(postId);
        //return Optional.ofNullable(comments);
    }


}
