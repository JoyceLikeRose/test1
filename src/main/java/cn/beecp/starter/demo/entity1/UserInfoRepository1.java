package cn.beecp.starter.demo.entity1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository1 extends JpaRepository<UserInfo1, Integer> {
}
