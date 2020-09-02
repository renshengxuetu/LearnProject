package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.AccountDao;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, Integer money) {
        accountDao.decrease(userId, money);
    }

}
