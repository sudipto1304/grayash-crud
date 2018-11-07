package com.grayash.crud.manageuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grayash.crud.manageuser.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByCustomerId(String customerId);
    List<UserEntity> findByUserIdOrEmailIdOrPhoneNumber(String userId, String emailId, String contactNumber);

}
