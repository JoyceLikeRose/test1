package cn.beecp.starter.demo.entity2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRepository2 extends JpaRepository<UserInfo2, Integer> {

    @Query(value = "select * from UUUUU", nativeQuery = true)
    public List<UserInfo2> testErrorSQL();

}
