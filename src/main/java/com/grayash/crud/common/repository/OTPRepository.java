package com.grayash.crud.common.repository;

import com.grayash.crud.common.entity.OTPEntity;
import com.grayash.crud.common.model.request.FlowType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OTPRepository extends JpaRepository<OTPEntity, Long> {

    OTPEntity findByPhoneNumberAndFlowId(String phoneNumber, String flowId);
}
