package com.java.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * 使用 Jedis 测试 Redis 事务
 */
public class TestTX {

    public static void main(String[] args){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","zhangsan");
        jsonObject.put("age",13);
        Jedis jedis = new Jedis("192.168.3.30", 6379);
        Transaction multi = jedis.multi();
        try{
            jedis.select(1);
            jedis.flushDB();
            multi.set("user1", jsonObject.toJSONString());
            multi.set("user2", jsonObject.toJSONString());
            int i = 1 / 0;      // 使代码执行到 catch 中，执行放弃事务的操作
            multi.exec();
        }catch (Exception e){
            multi.discard();
        }finally {
            System.out.println(jedis.get("user1"));  // 如果 Transaction 对象没有执行 exec 或者 discard 命令，在之后的 Jedis 对象是无法执行任何命令
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
    }

}
