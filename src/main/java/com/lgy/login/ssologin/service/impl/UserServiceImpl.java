package com.lgy.login.ssologin.service.impl;

import com.lgy.login.ssologin.mapper.UserMapper;
import com.lgy.login.ssologin.pojo.User;
import com.lgy.login.ssologin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public List<Map<Object,Object>> findUserByNameAndPasaWorld(String name, String passWorld) {
        return userMapper.findUserByNameAndPasaWorld(name,passWorld);
    }
}
