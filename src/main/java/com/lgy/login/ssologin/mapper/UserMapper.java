package com.lgy.login.ssologin.mapper;

import com.lgy.login.ssologin.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value ="UserMapper")
public interface UserMapper {
    List<Map<Object,Object>> findUserByNameAndPasaWorld(String name, String passWorld);
}
