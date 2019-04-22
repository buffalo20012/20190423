package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;

import java.util.List;

public interface UserService {
    List<User> ListAll();

    User FindUser(Long id);

    User AddUser(User user);

    User UpdateUser(Long id, User user);

    boolean DeleteUser(Long id);
}
