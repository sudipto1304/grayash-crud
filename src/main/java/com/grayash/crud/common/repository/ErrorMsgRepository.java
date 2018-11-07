package com.grayash.crud.common.repository;

import com.grayash.crud.common.entity.ErrorMsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface ErrorMsgRepository extends JpaRepository<ErrorMsgEntity, String> {
}
