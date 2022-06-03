# 1. 下载Nacos

https://github.com/alibaba/nacos/releases/tag/2.1.0

Linux：nacos-server-版本号.tar.gz

Windows：nacos-server-版本号.zip

# 2.配置Nacos

## 2.1 修改数据库配置

Nacos解压后的 conf 目录，打开文件 application.properties

讲下面的配置的注释打开

*spring.datasource.platform=mysql*

*db.num=1*

*db.url.0=jdbc:mysql://127.0.0.1:3306/nacos*

*db.user.0=nacos*

*db.password.0=nacos*

修改mysql相关配置。

## 2.2 执行数据库脚本

在 mysql 中创建数据库 nacos

执行文件 conf**/**nacos-mysql.sql 中的脚本

## 2.3 配置Nacos启动方式

bin 目录下的 startup.sh 或 startup.cmd

（1）如果是单机版，将 export MODE="cluster" 改为 export MODE="standalone"

# 3.代码

nacos-api：各个服务模块的 IService 接口类；

nacos-service：服务

nacos-skeleton：实体类、VO、request、response、enums

nacos-util：工具类

注：如果需要加入中间件（如redis、mq）等，可以在 nacos-demo 下创建对应的 maven-module

