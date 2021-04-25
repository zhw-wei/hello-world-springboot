package com.zhw.helloworld.world.service.impl;

import com.zhw.helloworld.config.Result;
import com.zhw.helloworld.dal.world.dao.WorldMapper;
import com.zhw.helloworld.dal.world.model.World;
import com.zhw.helloworld.world.service.WorldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 4:41
 * @description:
 */
@Slf4j
@Service
public class WorldServiceImpl implements WorldService {
    @Autowired
    private WorldMapper worldMapper;

    @Override
    public Result<World> world(int id) {
        Optional<World> world = Optional.ofNullable(this.worldMapper.selectByPrimaryKey(id));

        return Result.Success.QUERY(
                world.orElseGet(() -> {
                    World w = new World();
                    w.setId(-1);
                    w.setCode("world");
                    w.setName("world");
                    return w;
                }));
    }
}
