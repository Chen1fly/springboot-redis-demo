package com.lsyonlygoddes.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lsyonlygoddes.entity.Chen;

import java.util.List;


public interface UserService extends IService<Chen> {
    List<Chen> findAll();
}
