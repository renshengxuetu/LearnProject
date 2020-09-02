package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {

    void decrease(@Param("userId")Long userId, @Param("money")Integer money);

}
