package myProj.forum.repository;

import myProj.forum.domain.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void save(Comment comment);

    void delete(Long id);

    List<Comment> findByPostId(Long postId);
}
