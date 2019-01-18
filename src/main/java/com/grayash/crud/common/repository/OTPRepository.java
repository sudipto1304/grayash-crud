package com.grayash.crud.common.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grayash.crud.common.entity.OTPEntity;

@Repository
@Transactional
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {

    OTPEntity findByPhoneNumberAndFlowId(String phoneNumber, String flowId);
}
