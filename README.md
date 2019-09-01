# Spring Cloud Demo Project

## core-api 
```
基础工具类，VO通用对象等
```

## eureka-service
```
Eureka Server 服务注册中心
```

## member-service
```
会员服务提供者
```

## film-service
```
影片服务提供者
```

## message-service
```
消息服务提供者，Email/SMS 异步消息提供
```
## asset-service
```
资源服务提供者，上传视频/图片/文件到七牛云存储
```
## Feign组件测试
```
- film-service 去消费 member-service 服务
- film-service 配置feign依赖包
- film-service 开启 @EnableFeignClients 注解
- film-service 引入 MemberFeignClient-Api接口
- 注意：GET请求多个参数使用map封装的问题
```