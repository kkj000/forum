package myProj.forum.repository;

import myProj.forum.domain.File;
import myProj.forum.domain.Post;
import myProj.forum.domain.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

@Mapper
public interface FileMapper {

    void save(File file);

    File findByPostId(Long postId);
}
