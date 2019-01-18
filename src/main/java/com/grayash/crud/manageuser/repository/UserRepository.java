package com.grayash.crud.manageuser.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grayash.crud.manageuser.entity.UserEntity;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByCustomerId(String customerId);
    List<UserEntity> findByUserIdOrEmailIdOrPhoneNumber(String userId, String emailId, String contactNumber);

}
