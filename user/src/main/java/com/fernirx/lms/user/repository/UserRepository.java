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
     * Find user by username and fetch role eagerly
     */
    @Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.username = :username")
    Optional<User> findByUsernameWithRole(@Param("username") String username);

    /**
     * Find user by username
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a {@link User} by the given email address.
     *
     * @param email the email address to search for (case-insensitive)
     * @return an {@link Optional} containing the {@link User} if found,
     *         or {@link Optional#empty()} if no user exists with the given email
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if username exists
     */
    boolean existsByUsername(String username);

    /**
     * Find all users by deletion status
     */
    List<User> findByIsDeleted(Boolean isDeleted);

    /**
     * Find active user by ID (not deleted)
     */
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.isDeleted = false")
    Optional<User> findActiveById(@Param("id") Long id);

    /**
     * Soft delete user
     */
    @Modifying
    @Query("UPDATE User u SET u.isDeleted = true WHERE u.id = :id")
    void softDeleteById(@Param("id") Long id);

    /**
     * Restore deleted user
     */
    @Modifying
    @Query("UPDATE User u SET u.isDeleted = false WHERE u.id = :id")
    void restoreById(@Param("id") Long id);
}