package com.zhw.helloworld.dal.world.dao;

import com.zhw.helloworld.dal.world.model.World;
import com.zhw.helloworld.dal.world.model.WorldCriteria;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorldMapper {
    long countByExample(WorldCriteria example);

    int deleteByExample(WorldCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(World record);

    int insertSelective(World record);

    List<World> selectByExampleWithRowbounds(WorldCriteria example, RowBounds rowBounds);

    List<World> selectByExample(WorldCriteria example);

    World selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") World record, @Param("example") WorldCriteria example);

    int updateByExample(@Param("record") World record, @Param("example") WorldCriteria example);

    int updateByPrimaryKeySelective(World record);

    int updateByPrimaryKey(World record);
}