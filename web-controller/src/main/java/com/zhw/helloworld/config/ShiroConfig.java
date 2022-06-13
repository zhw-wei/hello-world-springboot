package com.zhw.helloworld.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 * @date 2022/6/1 22:31
 */
@Slf4j
@Component
public class ShiroConfig {

    @Autowired
    private DataSource dataSource;

    //提供一个 Realm 的实例
    @Bean
    public Realm myRealm(){
        JdbcRealm realm = new JdbcRealm();
        realm.setDataSource(this.dataSource);
        return realm;
    }

    //配置一个 SecurityManager，在 SecurityManager 中配置 Realm
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(this.myRealm());
        return manager;
    }

    //配置一个 ShiroFilterFactoryBean ，在 ShiroFilterFactoryBean 中指定路径拦截规则等
    //配置登录和测试接口
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(this.securityManager());
        shiroFilter.setLoginUrl("/login");//指定登陆页面
        shiroFilter.setSuccessUrl("/success");//指定登陆成功页面
        shiroFilter.setUnauthorizedUrl("/unauthorizedUrl");

        //Map 中配置了路径拦截规则，注意，要有序
        Map<String, String> map = new HashMap<>();
        map.put("/dologin", "anon");//设置登陆接口地址
        map.put("/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(map);

        return shiroFilter;
    }
}
