package com.tian.dao.mapper;

import com.tian.dao.entity.OrderInfo;

/**
 * Created by tianxiong on 2019/8/26.
 */
public interface OrderInfoMapper {
    void insertSelective(OrderInfo orderInfo);
}
