# microservice-solution
ServiceStage微服务方案样例集合

## 多语言支持

### .Net
通过ServiceStage部署，可以将dotnet应用变为云原生应用，需要符合以下条件：
- 使用docker进行打包
- 使用linux与dotnet core生态
- 支持标准http_proxy环境变量以进行代理设置

#### 本地开发
参考例子[dotnet](dotnet)

#### ServiceStage部署
参考官网

## 微服务API网关

### ServiceStage端到端部署EdgeService网关服务
通过ServiceStage，将源码构建为EdgeService微服务网关，并将后端服务的API开放到外网。此demo需要以下前提条件：
- 在华为云上拥有CSE引擎实例、CCE集群、EIP各一个
- 了解maven构建、docker镜像打包的基本知识

参考例子[edge-service](edge-service)
