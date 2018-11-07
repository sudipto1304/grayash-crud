package com.grayash.crud.common.service;


import com.grayash.crud.common.entity.ErrorMsgEntity;
import com.grayash.crud.common.repository.ErrorMsgRepository;
import com.grayash.crud.common.util.ErrorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
public class StartUpService extends ErrorMsg {
    private static final Logger LOG = LoggerFactory.getLogger(StartUpService.class);

    @Autowired
    private ErrorMsgRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void loadErrorMsg() {
        List<ErrorMsgEntity> errorMsgList = repository.findAll();
        if(null!=errorMsgList && errorMsgList.size()>0){
            errorMsgList.forEach(e->this.putErrorMsg(e.getMsgCode(), e.getMsgText()));
        }
        if(LOG.isDebugEnabled())
            LOG.debug("Error Msg from DB::"+errorMsgList);
    }
}
