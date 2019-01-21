package com.grayash.crud.manageuser.service;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.grayash.exception.UserPresentException;
import com.grayash.crud.common.util.TrippleDes;
import com.grayash.crud.manageuser.entity.UserEntity;
import com.grayash.crud.manageuser.model.request.AccountStatus;
import com.grayash.crud.manageuser.model.request.ManageUserRequest;
import com.grayash.crud.manageuser.model.request.Verify;
import com.grayash.crud.manageuser.model.response.ManagerUserResponse;
import com.grayash.crud.manageuser.repository.UserRepository;
import com.grayash.crud.manageuser.util.ManageUserUtil;

@Service
public class UserRegistrationService {

    private static final Logger Log = LoggerFactory.getLogger(UserRegistrationService.class);

    @Autowired
    private UserRepository repository;


    @Autowired
    private TrippleDes trippleDes;

    @Autowired
    private OauthTokenService tokenService;

    public ManagerUserResponse registerUser(ManageUserRequest request) throws UserPresentException {
        UserEntity entity;
        ManagerUserResponse response = new ManagerUserResponse();
        UserEntity userEntity = new UserEntity();
        if (Log.isDebugEnabled())
            Log.debug("Registration process start::verifying if user is already present or not");

        List<UserEntity> findAllByUserIdAndEmailIdAndContactNumber =  repository.findByUserIdOrEmailIdOrPhoneNumber(request.getUserId(), request.getEmailId(), ManageUserUtil.unFormatPhoneNumber(request.getContactNumber()));
        if(null!=findAllByUserIdAndEmailIdAndContactNumber && findAllByUserIdAndEmailIdAndContactNumber.size()>0){
            if (Log.isErrorEnabled())
                Log.error("user present. Throwing error");
            throw new UserPresentException();
        }else{

            userEntity.setCustomerId(UUID.randomUUID().toString());
            userEntity.setAccountStatus(AccountStatus.INACTIVE.name());
            userEntity.setAddress(request.getAddress().toUpperCase());
            userEntity.setCountryCode(request.getCountry().toUpperCase());
            userEntity.setFirstName(request.getFirstName().toUpperCase());
            userEntity.setLastName(request.getLastName().toUpperCase());
            userEntity.setUserId(request.getUserId());
            userEntity.setEmailId(request.getEmailId());
            userEntity.setZipCode(request.getZipCode());
            userEntity.setPhoneNumber(ManageUserUtil.unFormatPhoneNumber(request.getContactNumber()));
            try {
                userEntity.setPassword(trippleDes.encrypt(request.getPassword()));
            } catch (Exception e) {
                if (Log.isErrorEnabled())
                    Log.error("Exception::", e);

            }
            userEntity.setPhoneVerify(Verify.UNVERIFIED.name());
            entity = repository.save(userEntity);
            response.setFirstName(entity.getFirstName());
            response.setLastName(entity.getLastName());
            response.setAddress(entity.getAddress());
            response.setAccountStatus(AccountStatus.valueOf(entity.getAccountStatus()));
            response.setCustomerId(entity.getCustomerId());
            response.setEmailId(entity.getEmailId());
            response.setUserId(entity.getUserId());
            response.setPhoneNumber(entity.getPhoneNumber());
            response.setPhoneNumberCountryCode(entity.getCountryCode());
            response.setVerifyStatus(Verify.valueOf(entity.getPhoneVerify()));
            response.setToken(tokenService.getOauthToken(entity.getCustomerId(), entity.getPassword()));
        }

        return response;
    }





}


