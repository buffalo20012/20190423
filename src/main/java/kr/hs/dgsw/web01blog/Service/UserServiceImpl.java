package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> ListAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User FindUser(Long id) {
        User user = new User();
        Optional<User> found = userRepository.findById(id);

        if (found.isPresent()) {
            user.setEmail(found.get().getEmail());
            user.setId(found.get().getId());
            user.setAccount(found.get().getAccount());
            user.setName(found.get().getName());
            user.setPhone(found.get().getPhone());
            user.setCreated(found.get().getCreated());
            user.setUpdated(found.get().getUpdated());
            user.setProfilePath(found.get().getProfilePath());
        }

        return user;
    }

    @Override
    public User AddUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User UpdateUser(Long id, User user) {
        return this.userRepository.findById(id).map(found ->{
            found.setProfilePath(Optional.ofNullable(user.getProfilePath()).orElse(found.getProfilePath()));
            found.setPhone(Optional.ofNullable(user.getPhone()).orElse(found.getPhone()));
            found.setName(Optional.ofNullable(user.getName()).orElse(found.getName()));
            found.setPassword(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
            found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
            found.setAccount(Optional.ofNullable(user.getAccount()).orElse(found.getAccount()));
            return this.userRepository.save(found);
        }).orElse(null);
    }

    @Override
    public boolean DeleteUser(Long id) {
        Optional<User> found = this.userRepository.findById(id);

        if(found.isPresent()) {
            try {
                this.userRepository.deleteById(id);
            } catch (Exception e) {
                return false;
            }
        }else{
            return false;
        }
        return true;
    }
}
