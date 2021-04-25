package com.zhw.helloworld.world.service;

import com.zhw.helloworld.config.Result;
import com.zhw.helloworld.dal.world.model.World;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 4:40
 * @description:
 */
public interface WorldService {

    Result<World> world(int id);
}
