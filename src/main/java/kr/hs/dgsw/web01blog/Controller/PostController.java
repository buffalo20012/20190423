package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post")
    @ResponseBody
    public List<PostUsernameProtocol> list(){
        return this.postService.ListAllPost();
    }

    @GetMapping("/findpost/{id}")
    @ResponseBody
    public PostUsernameProtocol find(@PathVariable String id){
        return this.postService.FindPost(Long.parseLong(id));
    }

    @PostMapping("/addpost")
    @ResponseBody
    public PostUsernameProtocol add(@RequestBody Post post){
        return this.postService.AddPost(post);
    }

    @PutMapping("/updatepost/{id}")
    @ResponseBody
    public Post update(@PathVariable String id,@RequestBody Post post){
        return this.postService.UpdatePost(Long.parseLong(id),post);
    }

    @DeleteMapping("/deletepost/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id){
        return this.postService.DeletePost(id);
    }
}
