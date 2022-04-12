package com.example.StudentEnrolmentSystem.repository;

import com.example.StudentEnrolmentSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Transactional
    @Modifying
    @Query(value = "update user set status=\"INACTIVE\" where user_id=?1", nativeQuery = true)
    void updateUserStatus(String userId);
}
