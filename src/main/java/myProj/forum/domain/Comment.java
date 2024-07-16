package myProj.forum.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Long id;
    private Long postId;
    private Long userId;
    private String author;
    private String content;
    private Date createdAt = new Date();
}
