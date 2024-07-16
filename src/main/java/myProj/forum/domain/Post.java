package myProj.forum.domain;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Post {

    private Long id;
    private Long userId;
    private String author;
    private String title;
    private String content;
    private Date createdAt = new Date();
    private int views;
    private int likes;
    private int dislikes;
    private List<Comment> commentList;

    public void incrementView(){
        views += 1;
    }

}
