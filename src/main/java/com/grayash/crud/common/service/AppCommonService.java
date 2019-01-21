package com.grayash.crud.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grayash.crud.common.entity.ErrorMsgEntity;
import com.grayash.crud.common.entity.OTPEntity;
import com.grayash.crud.common.model.request.OTPRequest;
import com.grayash.crud.common.model.request.ValidateOTPRequest;
import com.grayash.crud.common.model.response.ErrorMessageResponse;
import com.grayash.crud.common.model.response.OTPResponse;
import com.grayash.crud.common.model.response.OTPStatus;
import com.grayash.crud.common.model.response.ValidateOTPResponse;
import com.grayash.crud.common.repository.ErrorMsgRepository;
import com.grayash.crud.common.repository.OTPRepository;
import com.grayash.crud.common.util.CodeConstant;
import com.grayash.crud.common.util.CommonUtils;
import com.grayash.crud.common.util.ErrorMsg;
import com.grayash.crud.manageuser.repository.UserRepository;

@Service
public class AppCommonService implements CodeConstant {

	private static final Logger Log = LoggerFactory.getLogger(AppCommonService.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private OTPRepository otpRepository;
	
	@Autowired
    private ErrorMsgRepository errorRepository;
	



	public OTPResponse generateOTP(OTPRequest request) {
		OTPResponse response = new OTPResponse();
		OTPEntity otpEntity = otpRepository.findByPhoneNumberAndFlowId(request.getPhoneNumber(), request.getFlowId().name());
		if(otpEntity==null) {
			otpEntity = new OTPEntity();
		}
		otpEntity.setFlowId(request.getFlowId().name());
		otpEntity.setOTP(String.valueOf(CommonUtils.getOTP()));
		otpEntity.setPhoneNumber(request.getPhoneNumber());
		otpEntity.setStatus(OTPStatus.GENERATED.name());
		otpEntity.setPhoneNumberCountry(request.getCountryCode());
		OTPEntity otpEntityData = otpRepository.save(otpEntity);
		response.setPhoneNumber(otpEntity.getPhoneNumber());
		response.setPhoneNumberCountryCode(otpEntityData.getPhoneNumberCountry());
		response.setOtpStatus(OTPStatus.valueOf(otpEntityData.getStatus()));
		return response;
	}

	public ValidateOTPResponse validateOTP(ValidateOTPRequest request) {
		OTPEntity otpEntity = otpRepository.findByPhoneNumberAndFlowId(request.getPhoneNumber(), request.getFlowType().name());
		ValidateOTPResponse response = new ValidateOTPResponse();
		if(otpEntity!=null) {
			response.setAttemptCount(otpEntity.getCount());
			response.setCountryCode(otpEntity.getPhoneNumberCountry());
			response.setOtp(otpEntity.getOTP());
			response.setPhoneNumber(otpEntity.getPhoneNumber());
		}
		return response;
	}
	
	public ErrorMessageResponse getErrorMessage(String msgCode) {
		return new ErrorMessageResponse(msgCode, ErrorMsg.getErrorMsg(msgCode));
	}

	public List<ErrorMessageResponse> getAllErrorMessage() {
		Map<String, String> errorMsg =  ErrorMsg.getAllErrorMsg();
		List<ErrorMessageResponse> response = new ArrayList<>();
		errorMsg.forEach((k,v)->response.add(new ErrorMessageResponse(k,v)));
		return response;
	}
	
	
	public void refreshCache() {
		List<ErrorMsgEntity> errorMsgList = errorRepository.findAll();
		if(null!=errorMsgList && errorMsgList.size()>0){
            errorMsgList.forEach(e->ErrorMsg.putErrorMsg(e.getMsgCode(), e.getMsgText()));
        }
		
	}
	
}
