package myProj.forum.domain;

import lombok.Data;

@Data
public class File {

    private Long id;

    private Long postId;

    private String originalFilename;
    private String storedFilename;

}
