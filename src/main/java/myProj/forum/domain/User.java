package myProj.forum.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {

    private Long id;
    private String loginId;
    private String password;
    private List<Post> postList;
    private List<Comment> commentList;
    private int posts;
    private int comments;
    private Date createdAt = new Date();
    private String role = "USER";

}
