package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.PostUsernameProtocol;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostUsernameProtocol> ListAllPost() {
        List<Post> postList = this.postRepository.findAll();
        List<PostUsernameProtocol> cupList = new ArrayList<>();

        postList.forEach(post -> {
            Optional<User> found = this.userRepository.findById(post.getUserId());
            String username = (!found.isPresent()) ? null : found.get().getName();
            cupList.add(new PostUsernameProtocol(post,username));
        });
        return cupList;
    }

    @Override
    public PostUsernameProtocol FindPost(Long id) {
        Optional<Post> found = this.postRepository.findById(id);
        Post post = new Post();

        if(found.isPresent()){
            post.setId(found.get().getId());
            post.setContent(found.get().getContent());
            post.setUserId(found.get().getUserId());
            post.setFilename(found.get().getFilename());
            post.setFilepath(found.get().getFilepath());
            post.setCreated(found.get().getCreated());
            post.setModified(found.get().getModified());
        }

        Optional<User> user = this.userRepository.findById(post.getUserId());
        String username = (!user.isPresent()) ? null : user.get().getName();

        return new PostUsernameProtocol(post,username);
    }

    @Override
    public PostUsernameProtocol AddPost(Post post) {
        Post add = this.postRepository.save(post);
        Optional<User> user = userRepository.findById(add.getUserId());

        if(user.isPresent()) {
            return new PostUsernameProtocol(add, user.get().getName());
        }else{
            return new PostUsernameProtocol(add, null);
        }
    }

    @Override
    public Post UpdatePost(Long id, Post post) {
        return this.postRepository.findById(id).map(found ->{
            found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
            found.setFilename(Optional.ofNullable(post.getFilename()).orElse(found.getFilename()));
            found.setFilepath(Optional.ofNullable(post.getFilepath()).orElse(found.getFilepath()));
            return this.postRepository.save(found);
        }).orElse(null);
    }

    @Override
    public boolean DeletePost(Long id) {
        Optional<Post> found = this.postRepository.findById(id);

        if(found.isPresent()){
            try{
                this.postRepository.deleteById(id);
            }catch (Exception ex){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
