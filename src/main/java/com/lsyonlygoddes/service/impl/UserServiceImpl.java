package com.lsyonlygoddes.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lsyonlygoddes.entity.Chen;
import com.lsyonlygoddes.mapper.UserMapper;
import com.lsyonlygoddes.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

import java.util.List;


@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Chen> implements UserService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Chen> findAll() {
        //增加缓存
        //先去缓存查找，如果缓存有 直接返回 如果缓存没有 从数据库查询 放入缓存
        String userListJsonStr = redisTemplate.opsForValue().get("UserService.findAll");
        if (StringUtils.isNotBlank(userListJsonStr)){
            List<Chen> chens = JSON.parseArray(userListJsonStr, Chen.class);
            log.info("走了缓存");
            return chens;
        }else {
            List<Chen> chens = baseMapper.selectList(new LambdaQueryWrapper<>());
            redisTemplate.opsForValue().set("UserService.findAll",JSON.toJSONString(chens),2,TimeUnit.HOURS);
            log.info("首次放入缓存");
            return chens;
        }
    }
}
