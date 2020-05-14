package cn.itcast.mp.mapper;

import cn.itcast.mp.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends MyBaseMapper<User> {
    User findById(Long id);

    //List<User> findAll();
}
