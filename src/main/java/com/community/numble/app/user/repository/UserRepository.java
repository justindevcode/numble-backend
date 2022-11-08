package com.community.numble.app.user.repository;


import com.community.numble.app.user.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserBean, Long> {

    UserBean findByUsername(String username);

}
