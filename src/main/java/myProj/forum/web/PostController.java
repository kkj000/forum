package myProj.forum.web;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myProj.forum.domain.Comment;
import myProj.forum.domain.File;
import myProj.forum.domain.Post;
import myProj.forum.domain.UploadFile;
import myProj.forum.repository.PostUpdateDto;
import myProj.forum.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final LikeService likeService;
    private final CommentService commentService;
    private final FileService fileService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping
    public String posts(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts",posts);
        return "post/posts";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, Model model, HttpServletRequest request){
        postService.incrementView(postId, request);
        Post post = postService.findById(postId).orElseThrow();
        List<Comment> comments = commentService.findByPostId(postId);
        model.addAttribute("post",post);
        model.addAttribute("comments", comments);

        File file = fileService.findByPostId(postId);
        if(file != null){
            log.info("image : " + file.getStoredFilename());
            model.addAttribute("imageUrl", "/images/"+ file.getStoredFilename());
        }
        return "post/post";
    }

    @PostMapping("/{postId}/comment")
    public String comment(@PathVariable Long postId, @ModelAttribute Comment comment, Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        comment.setUserId(customUserDetails.getUserId());
        comment.setAuthor(customUserDetails.getUsername());
        comment.setPostId(postId);
        commentService.save(comment);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("deleteComment/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
        commentService.delete(commentId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/add")
    public String addPostForm(){
        return "post/addPostForm";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post,
                          @RequestParam("images") MultipartFile multipartFile,
                          RedirectAttributes redirectAttributes,
                          Authentication authentication) throws IOException {

        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        post.setAuthor(userDetails.getUsername());
        post.setUserId(userDetails.getUserId());
        Post savedPost = postService.save(post);

        if(!multipartFile.isEmpty()) {
            UploadFile uploadFile = fileService.storeFile(multipartFile);
            fileService.save(savedPost.getId(), uploadFile);
        }
        redirectAttributes.addAttribute("postId",savedPost.getId());
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/edit/{postId}")
    public String editPostForm(@PathVariable Long postId, Model model){
        Post post = postService.findById(postId).orElseThrow();
        model.addAttribute("post",post);
        return "post/editPostForm";}

    @PostMapping("/edit/{postId}")
    public String editPost(@PathVariable Long postId, @ModelAttribute PostUpdateDto updateDto, RedirectAttributes redirectAttributes){
        postService.updateContents(postId,updateDto);
        redirectAttributes.addAttribute("postId",postId);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long postId){
        postService.delete(postId);
        return "redirect:/posts";
    }

    @GetMapping("/{postId}/like")
    public String like(@PathVariable Long postId, Authentication authentication, HttpServletRequest request){
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        likeService.likePost(userId,postId);
        request.getSession().setAttribute("post",true);
        return "redirect:/posts/{postId}";
    }

    @GetMapping("/{postId}/dislike")
    public String dislike(@PathVariable Long postId, Authentication authentication, HttpServletRequest request){
        CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        likeService.dislikePost(userId,postId);
        request.getSession().setAttribute("post",true);
        return "redirect:/posts/{postId}";
    }



}
