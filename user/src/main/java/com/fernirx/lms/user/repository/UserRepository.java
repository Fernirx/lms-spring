package com.fernirx.lms.user.repository;

import com.fernirx.lms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by username
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);

    /**
     * Find all users by deletion status
     */
    List<User> findByIsDelete(Boolean isDelete);

    /**
     * Find active user by ID (not deleted)
     */
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isDelete = false")
    Optional<User> findActiveById(@Param("id") Long id);

    /**
     * Soft delete user
     */
    @Modifying
    @Query("UPDATE User u SET u.isDelete = true WHERE u.id = :id")
    void softDeleteById(@Param("id") Long id);

    /**
     * Restore deleted user
     */
    @Modifying
    @Query("UPDATE User u SET u.isDelete = false WHERE u.id = :id")
    void restoreById(@Param("id") Long id);
}