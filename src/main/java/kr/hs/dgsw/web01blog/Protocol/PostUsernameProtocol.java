package kr.hs.dgsw.web01blog.Protocol;

import kr.hs.dgsw.web01blog.Domain.Post;

public class PostUsernameProtocol extends Post {
    private String username;

    public PostUsernameProtocol() {
    }

    public PostUsernameProtocol(Post post, String username) {
        super(post);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
