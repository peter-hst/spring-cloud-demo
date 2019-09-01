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

---
# Ribbon 组件测试
```
- 启动多个member-service实例

- 启动film-serve实例

- RestTemplateClient 测试：
  [访问] (http://film:8401/film/ribbon/1)
  
- LoadServiceInstance 测试：
  [访问] (http://film:8401/film/ribbon/instance)