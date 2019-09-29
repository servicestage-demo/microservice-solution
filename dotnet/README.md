

# payment service
此服务的API为 /v1/payments

### 在本地启动
```sh
cd payment/src
dotnet run
```

访问 http://127.0.0.1:8080/v1/payments 他会返回一串json

# order service
此服务的API为 /v1/orders, 当你调用它时，他会调用 http://127.0.0.1:8080/v1/payments, 并把json透传回来

### 在本地启动
修改 appsettings.Development.json，该文件用于本地开发的配置
把 PaymentServiceEndpoint 改为 http://127.0.0.1:8080
```sh
vim order/src/appsettings.Development.json
```
```json
{
  "ServiceEndpoints": {
    "PaymentServiceEndpoint": "http://127.0.0.1:8080"
  },
  "Logging": {
    "IncludeScopes": false,
    "LogLevel": {
      "Default": "Debug",
      "System": "Information",
      "Microsoft": "Information"
    }
  }
}
```

启动时，要把环境设置为development以读取对应的payment service地址
```sh
cd order/src
export ASPNETCORE_ENVIRONMENT=Developement
dotnet run

```

2个服务都启动后，就可以访问地址  http://127.0.0.1:5000/v1/orders 会看到一串json字符串



# ServiceSage部署
### 确保order配置文件中payment地址正确
给payment service起个名字“payment” 以代替本地127.0.0.1的寻址方式，端口需要保留
```sh
vim order/src/appsettings.json
```
```json
{
  "ServiceEndpoints": {
    "PaymentServiceEndpoint": "http://payment:8080"
  },
 ....
}

```
### 打包payment和order的镜像
首先选择上传到某个swr的region 比如swr.cn-north-1.myhuaweicloud.com
在该region的镜像中心创建仓库，比如demo，具体参考。。。。。。。。。

下面为例子
```sh
cd order/src/
sudo docker build . -t swr.cn-north-1.myhuaweicloud.com/demo/ordernet:1.0
```

```sh
cd payment/src/
sudo docker build . -t swr.cn-north-1.myhuaweicloud.com/demo/paymentnet:1.0
```

### 上传镜像
具体的镜像上传教程请参考。。。。。。。。。

下面为例子
```sh
docker push swr.cn-north-1.myhuaweicloud.com/demo/ordernet:1.0
docker push swr.cn-north-1.myhuaweicloud.com/demo/paymentnet:1.0
```

### 部署

#### 部署payment
1. 创建ServiceComb应用
2. 框架选mesher
3. 运行环境选docker
4. 应用名称，写payment（应用名字要跟order配置文件中的地址保持完全一致，所以名字为payment）
5. 应用版本最好和docker镜像版本保持一致
7. 下一步
8. 选择镜像，选择payment镜像
9. 选择一个微服务引擎，具体引擎创建方法参考。。。。。。
10. 下一步
11. 创建

#### 部署order
1. 创建ServiceComb应用
2. 框架选mesher
3. 运行环境选docker
4. 应用名称，写order或者任意，没有特别要求
5. 应用版本最好和docker镜像版本保持一致
6. 下一步
7. 选择镜像，选择order镜像
8. 点击外网访问，应用端口填写80，此为order的端口，这样外网就可以访问了
9. 选择一个微服务引擎，具体引擎创建方法参考。。。。。。
10. 下一步
11. 创建


等待2个服务全部部署完成
![](file:///C:/Users/t00373999/AppData/Roaming/eSpace_Desktop/UserData/t00373999/imagefiles/F2850C68-2C3D-4A51-909B-0909A1EB1629.png)
点击外网访问地址，即可看到json串，与本地结果一致
