package cn.itcast.mp;

import cn.itcast.mp.injectors.MySqlInjector;
import cn.itcast.mp.plugins.MyInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.itcast.mp.mapper") //设置mapper接口的扫描包
public class MyBatisPlusConfig {

    @Bean//配置分页插件
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }



    @Bean//注入自定义拦截器
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }


    @Bean//注入自定义sql注入器
    public MySqlInjector mySqlInjector(){
        return new MySqlInjector();
    }






}
