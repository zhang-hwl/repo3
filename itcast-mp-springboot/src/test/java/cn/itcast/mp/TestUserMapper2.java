package cn.itcast.mp;

import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserMapper2 {

    @Test
    public void testSelectById(){
        User user  = new User();
        user.setId(2L);
        User user1 = user.selectById();
        System.out.println(user1);
    }

    @Test
    public void testInsert(){
        User user  = new User();
        user.setUserName("liubei");
        user.setPassword("123456");
        user.setAge(21);
        boolean insert = user.insert();
        System.out.println(insert);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(3L);
        user.setAge(32);
        boolean result = user.updateById();
        System.out.println(result);

    }

    @Test
    public void testDelete(){
        User user = new User();
        user.setId(3L);
        boolean result = user.deleteById();
        System.out.println(result);
    }

    @Test
    public void testSelect(){
        User user = new User();

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ge("age",30);//大于等于30的用户返回出来
        List<User> users = user.selectList(wrapper);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }



















}
