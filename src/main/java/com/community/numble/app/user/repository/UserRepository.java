package com.community.numble.app.user.repository;


import com.community.numble.app.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findUserBy(List<Long> idList);

}
