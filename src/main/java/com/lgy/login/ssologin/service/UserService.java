package com.lgy.login.ssologin.service;

import com.lgy.login.ssologin.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<Object,Object>> findUserByNameAndPasaWorld(String name, String passWorld);

}
