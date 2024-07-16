package myProj.forum.repository;

import lombok.Data;

@Data
public class PostUpdateDto {
    private String title;
    private String content;
    private int views;
    private int likes;
    private int dislikes;
}
