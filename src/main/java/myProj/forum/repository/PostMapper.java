package myProj.forum.repository;

import myProj.forum.domain.Post;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    void save(Post post);

    void updateContents(@Param("id") Long Id, @Param("updateParam") PostUpdateDto updateParam);

    @Update("UPDATE post SET views=#{views} WHERE id=#{id}")
    void updateViews(@Param("id") Long Id, @Param("views") int views);

    @Update("UPDATE post SET likes=#{likes}, dislikes=#{dislikes} WHERE id=#{id}")
    void updateLikes(@Param("id") Long Id, @Param("likes") int likes, @Param("dislikes") int dislikes);


    @Delete("delete from post where id=#{id}")
    void delete(Long id);

    Optional<Post> findById(Long id);

    List<Post> findAll();

    List<Post> findByAuthor(String author);

    @Update("UPDATE post SET views = views + 1 WHERE id = #{postId}")
    void incrementViewCount(Long postId);


}
