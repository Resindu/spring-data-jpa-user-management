package com.example.springdatajpausermanagement.dao;

import com.example.springdatajpausermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //JPQL
    @Query("select u.firstName,u.email from User u where u.id=?1")
    String getFirstNameByEmail(String email);


    //Native query
    @Query(
            value = "SELECT * FROM User_tbl u where u.firstName=?1",
            nativeQuery = true
    )
    User getUserFirstNameNative(String firstName);

    //Native named params
    @Query(
            value = "SELECT * FROM user_tbl u where u.first_name=:firstName",
            nativeQuery = true
    )
    User getUserFirstNameNativeNamedParam(@Param("firstName") String firstName);


    @Modifying
    @Transactional
    @Query(
            value = "update user_tbl set first_name = ?1 where email = ?2",
            nativeQuery = true
    )
    int updateUserNameByEmail(String firstName,String email);



}
