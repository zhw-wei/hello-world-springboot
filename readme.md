### 说明
本项目是练手的springboot项目，会集成很多数据库、中间件，也会写单元测试代码

#### 项目结构
* web-api: api接口，项目入口，各种配置
* web-dal: mysql数据库操作入口，mapper.xml使用插件自动生成
* web-service: 业务代码
* web-common: 公共配置、公共类（多个service时）
* web-mongo: mongo数据库操作入口

#### 语言
* java
* scala
* sql

#### 项目依赖
数据库：
1. mysql
2. redis
3. mongodb

中间件：
1. rabbitmq：消息中间件
2. canal: MySQL 数据库增量日志解析，提供增量数据订阅和消费
3. zookeeper: 分布式应用程序协调服务
4. spark: 专为大规模数据处理而设计的快速通用的计算引擎

#### 测试代码
1. 单元测试代码只能放在web-api中
2. 测试包括单元测试和普通测试代码

#### 压力测试
1. jmeter

#### 配置相关
打包后项目启动命令
```shell script
java -jar xxx.jar --spring.config.location=/path/to/application.yml --server.port=8081 > /dev/null 2>&1 &
```
1. spring.config.location: 指定配置文件地址，覆盖jar包中的配置文件
2. server.port: 指定端口号，命令行指定的配置内容优先级高于配置文件中的内容
    * 配置文件中也可以有server.port
    * 命令行中的配置参数优先级高于配置文件
    * application.yml是本地配置，server.port可以直接配置在yml文件中，无需命令行中指定
    * server.port是yml中配置的一个举例 