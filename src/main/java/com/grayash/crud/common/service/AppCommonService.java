package com.grayash.crud.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grayash.crud.common.entity.InstalledAppDeviceEntity;
import com.grayash.crud.common.entity.OTPEntity;
import com.grayash.crud.common.exception.CustomerIdNotFoundException;
import com.grayash.crud.common.exception.OTPExpiredException;
import com.grayash.crud.common.exception.OTPNotMatchException;
import com.grayash.crud.common.exception.OtpNotGeneratedException;
import com.grayash.crud.common.model.request.CommonRequest;
import com.grayash.crud.common.model.request.DeviceRegisterRequest;
import com.grayash.crud.common.model.request.OTPRequest;
import com.grayash.crud.common.model.response.DeviceRegistrationResponse;
import com.grayash.crud.common.model.response.OTPResponse;
import com.grayash.crud.common.model.response.OTPStatus;
import com.grayash.crud.common.model.response.Status;
import com.grayash.crud.common.repository.InstalledAppDeviceRepository;
import com.grayash.crud.common.repository.OTPRepository;
import com.grayash.crud.common.util.CodeConstant;
import com.grayash.crud.common.util.CommonUtils;
import com.grayash.crud.manageuser.entity.UserEntity;
import com.grayash.crud.manageuser.repository.UserRepository;

@Service
public class AppCommonService implements CodeConstant {

	private static final Logger Log = LoggerFactory.getLogger(AppCommonService.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private OTPRepository otpRepository;

	@Autowired
	private InstalledAppDeviceRepository deviceRepository;

	public OTPResponse generateOTP(OTPRequest request) {
		OTPResponse response = new OTPResponse();

		OTPEntity otpEntity = new OTPEntity();
		otpEntity.setFlowId(request.getFlowId());
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

	public Status validateOTP(String customerId, String OTP) {
		Status status = new Status();
		List<UserEntity> userDetails = repository.findByCustomerId(customerId);
		/*if (userDetails != null && userDetails.size() == 1) {
			OTPEntity otpDetails = otpRepository.findTopByCustomerIdOrderByUpdateTimeDesc(customerId);
			if (Log.isDebugEnabled())
				Log.debug("for the customer id::" + customerId + " OTP row is::" + otpDetails);
			if (otpDetails == null) {
				throw new OtpNotGeneratedException(customerId);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
				Date date = null;
				Date currentTime = null;
				try {
					date = dateFormat.parse(otpDetails.getUpdateTime());
					dateFormat.setTimeZone((TimeZone.getTimeZone("EST")));
					date = dateFormat.parse(dateFormat.format(date));
					Log.debug("Sql::" + date);
					currentTime = dateFormat.parse(dateFormat.format(new Date()));
					Log.debug("Current Date::" + currentTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				long difference = (currentTime.getTime() - date.getTime()) / (1000 * 60);
				if (Log.isDebugEnabled())
					Log.debug("Current time difference is::" + difference);
				if (difference > 10 || !otpDetails.getStatus().equals(OTPStatus.GENERATED.name())) {
					if (Log.isDebugEnabled())
						Log.debug("OTP is expired::" + difference);
					throw new OTPExpiredException(customerId);
				} else {
					if (OTP.equals(otpDetails.getOTP())) {
						otpDetails.setStatus(OTPStatus.VALIDATED.name());
						otpRepository.save(otpDetails);
						status.setResponseCode(HTTP_OK_STATUS);
					} else {
						otpDetails.setCount(otpDetails.getCount() + 1);
						otpRepository.save(otpDetails);
						OTPNotMatchException ex = new OTPNotMatchException(customerId);
						ex.setOtpErrorCount(otpDetails.getCount());
						throw ex;
					}
				}
			}
		} else {
			throw new CustomerIdNotFoundException(customerId);
		}*/
		return status;
	}

	public DeviceRegistrationResponse registerDevice(DeviceRegisterRequest request) {
		String appId = UUID.randomUUID().toString();
		InstalledAppDeviceEntity deviceEntity = new InstalledAppDeviceEntity();
		deviceEntity.setAppId(appId);
		deviceEntity.setCountryCode(request.getCountryCode());
		deviceEntity.setDeviceType(request.getDeviceType());
		deviceEntity.setIp(request.getIp());
		deviceEntity.setIsp(request.getIsp());
		deviceEntity.setOs(request.getOs());
		deviceEntity.setOsVersion(request.getOsVersion());
		InstalledAppDeviceEntity deviceResponseEntity = deviceRepository.save(deviceEntity);
		DeviceRegistrationResponse response = new DeviceRegistrationResponse();
		response.setAppId(deviceResponseEntity.getAppId());
		response.setResponseCode(HTTP_OK_STATUS);
		response.setHttpCode(HttpStatus.CREATED);
		return response;

	}
}
