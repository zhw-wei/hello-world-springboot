### 说明
本项目是练手的springboot项目，会集成很多数据库、中间件，也会写单元测试代码

#### 项目结构
web-api: api接口，项目入口，各种配置
web-dal: mysql数据库操作入口，xml自动生成
web-service: 业务代码
web-common: 公共配置、公共类（多个service时）

#### 项目依赖
数据库：
1. mysql

中间件：

#### 单元测试代码
1. 单元测试代码只能放在web-api中

#### 配置相关
打包后项目启动命令
```shell script
java -jar xxx.jar --spring.config.location=/path/to/application.yml --server.port=8081 > /dev/null 2>&1 &
```
1. spring.config.location: 指定配置文件地址，覆盖jar包中的配置文件
2. server.port: 指定端口号，命令行指定的配置内容优先级高于配置文件中的内容（配置文件中也可以有server.port）