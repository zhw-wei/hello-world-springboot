package com.zhw.helloworld.controller

import com.zhw.helloworld.common.dto.Result
import com.zhw.helloworld.dal.hello.model.Hello
import com.zhw.helloworld.hello.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

/**
 * @author: zhaohw
 * @date: 2021.04.30 上午 11:24
 * @description:
 */
@RestController
@RequestMapping(Array("/hello-scala"))
class HelloScalaController {

  @Autowired
  var helloService: HelloService = _

  @GetMapping(Array("/hello"))
  def hello(@RequestParam id: Int): Result[Hello] = {
    helloService.hello(id)
  }

}
