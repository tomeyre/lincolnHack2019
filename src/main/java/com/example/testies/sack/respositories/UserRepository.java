package com.example.testies.sack.respositories;

import com.example.testies.sack.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM USERS WHERE USERNAME = ?",
            nativeQuery = true)
    UserEntity findUserByUsername(String username);

    @Query(value = "SELECT * FROM USERS WHERE USER_ID = ?",
            nativeQuery = true)
    UserEntity findByUserId(Integer userId);
}
