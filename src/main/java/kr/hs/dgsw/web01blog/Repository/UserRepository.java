package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
