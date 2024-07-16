package myProj.forum.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper {

    @Insert("INSERT INTO likes (user_id, post_id) VALUES (#{userId}, #{postId})")
    void insertLike(@Param("userId") Long userId, @Param("postId") Long postId);

    @Select("SELECT * FROM likes WHERE user_id=#{userId} AND post_id=#{postId}")
    Boolean isExistLike(@Param("userId") Long userId, @Param("postId") Long postId);

    @Insert("INSERT INTO dislikes (user_id, post_id) VALUES (#{userId}, #{postId})")
    void insertDislike(@Param("userId") Long userId, @Param("postId") Long postId);

    @Select("SELECT * FROM dislikes WHERE user_id=#{userId} AND post_id=#{postId}")
    Boolean isExistDislike(@Param("userId") Long userId, @Param("postId") Long postId);
}
