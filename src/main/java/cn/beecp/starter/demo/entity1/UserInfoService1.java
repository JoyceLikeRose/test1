package cn.beecp.starter.demo.entity1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService1 {
    @Autowired
    private UserInfoRepository1 repository;

    public List<UserInfo1> getAllUserInfo() {
        return repository.findAll();
    }
}
