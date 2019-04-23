package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
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
/*
    @GetMapping("/findpost/{id}")
    @ResponseBody
    public PostUsernameProtocol find(@PathVariable String id){
        return this.postService.FindPost(Long.parseLong(id));
    }
*/

    @GetMapping("/findpost/{id}")
    @ResponseBody
    public ResponseFormat find(@PathVariable String id){
        return new ResponseFormat(ResponseType.POST_GET,this.postService.FindPost(Long.parseLong(id)));
    }
/*
    @PostMapping("/addpost")
    @ResponseBody
    public PostUsernameProtocol add(@RequestBody Post post){
        return this.postService.AddPost(post);
    }
*/

    @PostMapping("/addpost")
    @ResponseBody
    public ResponseFormat add(@RequestBody Post post){
        return new ResponseFormat(ResponseType.POST_ADD,this.postService.AddPost(post));
    }

/*
    @PutMapping("/updatepost/{id}")
    @ResponseBody
    public Post update(@PathVariable String id,@RequestBody Post post){
        return this.postService.UpdatePost(Long.parseLong(id),post);
    }
*/

    @PutMapping("/updatepost/{id}")
    @ResponseBody
    public ResponseFormat update(@PathVariable String id,@RequestBody Post post){
        return new ResponseFormat(ResponseType.POST_UPDATE,this.postService.UpdatePost(Long.parseLong(id),post));
    }

/*
    @DeleteMapping("/deletepost/{id}")
    @ResponseBody
    public boolean delete(@PathVariable Long id){
        return this.postService.DeletePost(id);
    }
*/

    @DeleteMapping("/deletepost/{id}")
    @ResponseBody
    public ResponseFormat delete(@PathVariable Long id){
        return new ResponseFormat(ResponseType.POST_DELETE,this.postService.DeletePost(id));
    }
}
