package cn.itcast.mp;

import cn.itcast.mp.mapper.UserMapper;
import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestUserMapper {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert(){
        User user = new User();
    }

    @Test
    public void testUpdateById(){
       User user = new User();
       user.setId(1L);
       user.setAge(19);

        int result = this.userMapper.updateById(user);
        System.out.println("result=>"+result);

    }

    @Test
    public void testUpdate(){
        User user = new User();

        user.setAge(20);
        user.setPassword("88888888");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","zhangsan");//column数据库中字段名

        //根据条件进行更新
        int result = this.userMapper.update(user, wrapper);

        System.out.println("result=>"+result);
    }

    @Test
    public void testUpdate2(){

        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("age",21).set("password","9999999").eq("user_name","zhangsan");

        //根据条件进行更新
        int result = this.userMapper.update(null, wrapper);

        System.out.println("result=>"+result);
    }

    @Test
    public void testDeleteById(){

        int result = this.userMapper.deleteById(9L);
        System.out.println("result=>"+result);

    }

    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("username","zhangsan");
        map.put("password","123456");

        //根据map去删除数据，多条件之间是and关系
        int result = this.userMapper.deleteByMap(map);
        System.out.println("result=>"+result);
    }


    @Test
    public void testDelete(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","caocao1").eq("password","1234560");

        //根据包装的条件进行删除
        int result = this.userMapper.delete(wrapper);
        System.out.println("result=>"+result);
    }

    @Test
    public void testDelete2(){
       User user = new User();
       user.setPassword("123456");
       user.setUserName("caocao");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        //根据包装的条件进行删除
        int result = this.userMapper.delete(wrapper);
        System.out.println("result=>"+result);
    }

    @Test
    public void testDeleteBatchIds(){

        //根据Id进行批量删除数据
        int result = this.userMapper.deleteBatchIds(Arrays.asList(10L, 11L));

        System.out.println("result=>"+result);
    }


    @Test
    public void testSelectById(){
        User user = this.userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds(){

        //批量查询数据
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(1L, 2L));
        for (User user : users) {
            System.out.println(user);
        }


    }

    @Test
    public void testSelectOne(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name","lisi");
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.gt("age",20); //条件：年龄大于20岁的用户
        //根据条件查询数据的条数
        Integer count = this.userMapper.selectCount(wrapper);

        System.out.println("count=>"+ count);
    }
    @Test
    public void testSelectList(){

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email","itcast");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectPage(){

        Page<User> page = new Page<>(1,1);//查询第一页，一条数据


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置查询条件
        wrapper.like("email","itcast");

        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println(iPage.getTotal());//总条数
        System.out.println(iPage.getPages());//总页

        List<User> records = iPage.getRecords();
        for (User record : records) {
            System.out.println(record);
        }
    }

    /**
     * 测试自定义方法
     */
    @Test
    public void testFindById(){

        User user = this.userMapper.findById(2L);
        System.out.println(user);
    }


    @Test
    public void testAllEq(){
        Map<String,Object> params = new HashMap<>();
        params.put("name","曹操");
        params.put("age","20");
        params.put("password",null);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //
        //wrapper.allEq(params);
        //wrapper.allEq(params,false);//IsNull空的是否作为查询条件
        wrapper.allEq((k,v) ->(k.equals("age") || k.equals("id") || k.equals("name")),params);


        List<User> users = this.userMapper.selectList(wrapper);

        for (User user : users) {
            System.out.println(user);
        }
    }


    @Test
    public void testEq(){

    }


    @Test
    public void testLike(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.likeLeft("name","五");
        List<User> users = this.userMapper.selectList(wrapper);
    }


    @Test
    public void testOrderByAgeDesc(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //按照年龄倒叙排序
        wrapper.orderByDesc("age");
        List<User> users = this.userMapper.selectList(wrapper);
    }

    @Test
    public  void testOr(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","王五").or().eq("age",21);
        List<User> users = this.userMapper.selectList(wrapper);
    }

    @Test
    public  void testSelect(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","王五")
                .or()
                .eq("age",21)
                .select("id","name","age");
        List<User> users = this.userMapper.selectList(wrapper);
    }



    @Test
    public void testFindAll(){

        List<User> users = this.userMapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }



















}
