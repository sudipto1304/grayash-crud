package com.grayash.crud.common.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grayash.crud.common.entity.InstalledAppDeviceEntity;

@Repository
@Transactional
public interface InstalledAppDeviceRepository extends JpaRepository<InstalledAppDeviceEntity, String> {

}
