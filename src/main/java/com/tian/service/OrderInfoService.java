package com.tian.service;

import com.tian.common.other.BusinessException;
import com.tian.dao.entity.OrderInfo;
import com.tian.dao.entity.UserInfo;
import com.tian.dao.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by Administrator on 2018/8/13 0013.
 */
@Service
public class OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
//    @Autowired
//    private UserInfoRemote userInfoRemote;

    /**
     *
     *
     *
     */
//    @TxTransaction(isStart = true)
    @Transactional
    public void testTransaction(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAmount(888L);
        orderInfo.setPayer(1L);
        orderInfo.setPayee(2L);
        orderInfo.setMemo("test data.");


        orderInfoMapper.insertSelective(orderInfo);
        //
        UserInfo userInfo = new UserInfo();
        userInfo.setIdCard("420621198906226011");
//        userInfoRemote.insertUserInfo(userInfo);

        Random random = new Random();
        if(random.nextBoolean()){
            throw new BusinessException(500, "error");
        }

    }


}
