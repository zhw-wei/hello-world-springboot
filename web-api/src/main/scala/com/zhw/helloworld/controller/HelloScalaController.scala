package com.zhw.helloworld.controller

import com.zhw.helloworld.common.dto.Result
import com.zhw.helloworld.dal.hello.model.Hello
import com.zhw.helloworld.hello.service.HelloService
import io.swagger.annotations.{Api, ApiOperation}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

/**
 * @author: zhaohw
 * @date: 2021.04.30 上午 11:24
 * @description:
 */
@Api(tags = Array("hello scala 接口测试"))
@RestController
@RequestMapping(Array("/hello-scala"))
class HelloScalaController {

  @Autowired
  var helloService: HelloService = _

  @GetMapping(Array("/hello"))
  @ApiOperation("hello测试")
  def hello(@RequestParam id: Int): Result[Hello] = {
    helloService.hello(id)
  }

}
