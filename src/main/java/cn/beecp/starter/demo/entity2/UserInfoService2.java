package cn.beecp.starter.demo.entity2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService2 {
    @Autowired
    private UserInfoRepository2 repository;

    public List<UserInfo2> getAllUserInfo() {
        return repository.findAll();
    }

    public List<UserInfo2> testErrorSQL() {
        return repository.testErrorSQL();
    }
}
