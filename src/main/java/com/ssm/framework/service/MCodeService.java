package com.ssm.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.MCodeMapper;
import com.ssm.framework.entity.MCode;

@Service
public class MCodeService {
    @Autowired
    private MCodeMapper mCodeMapper;

    public List<MCode> iFindDepatmentName() {
        return mCodeMapper.iFindDepatmentName();
    }
}

