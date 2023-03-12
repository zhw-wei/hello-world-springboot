package com.zhw.helloworld.dal.hello.dao;

import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.dal.hello.model.HelloCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloMapper {
    long countByExample(HelloCriteria example);

    int deleteByExample(HelloCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hello record);

    int insertSelective(Hello record);

    List<Hello> selectByExampleWithRowbounds(HelloCriteria example, RowBounds rowBounds);

    List<Hello> selectByExample(HelloCriteria example);

    Hello selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hello record, @Param("example") HelloCriteria example);

    int updateByExample(@Param("record") Hello record, @Param("example") HelloCriteria example);

    int updateByPrimaryKeySelective(Hello record);

    int updateByPrimaryKey(Hello record);
}