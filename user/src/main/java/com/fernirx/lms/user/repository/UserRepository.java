package com.fernirx.lms.user.repository;

import com.fernirx.lms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);

    boolean existsByUsername(String username);

    User getUsersById(Integer id);

    void removeUserById(Integer id);

    List<User> findUsersByIsDelete(Boolean isDelete);
}
