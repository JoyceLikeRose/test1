package cn.beecp.starter.demo;

import cn.beecp.boot.EnableDataSourceMonitor;
import cn.beecp.boot.EnableMultiDataSource;
import cn.beecp.starter.demo.entity1.UserInfo1;
import cn.beecp.starter.demo.entity1.UserInfoService1;
import cn.beecp.starter.demo.entity2.UserInfo2;
import cn.beecp.starter.demo.entity2.UserInfoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableMultiDataSource
@EnableDataSourceMonitor
@SpringBootApplication
@RestController
public class MutilDsApplication {

    @Autowired
    private UserInfoService1 service1;
    @Autowired
    private UserInfoService2 service2;

    public static void main(String[] args) {
        SpringApplication.run(MutilDsApplication.class, args);
    }

    @RequestMapping("/getAllUser1")
    public List<UserInfo1> getAllUserInfo1() throws Exception {
        return service1.getAllUserInfo();
    }

    @RequestMapping("/getAllUser2")
    public List<UserInfo2> getAllUserInfo2() throws Exception {
        return service2.getAllUserInfo();
    }

    @RequestMapping("/testErrorSQL")
    public List<UserInfo2> testErrorSQL() throws Exception {
        return service2.testErrorSQL();
    }
}
