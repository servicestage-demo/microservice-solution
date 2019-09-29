
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
```sh
cd order/src/
 sudo docker build . -t swr.cn-north-1.myhuaweicloud.com/hwstaff_xxx/ordernet
```

```sh
cd payment/src/
 sudo docker build . -t swr.cn-north-1.myhuaweicloud.com/hwstaff_xxx/paymentnet
```

### 上传镜像
```sh
docker push swr.cn-north-1.myhuaweicloud.com/hwstaff_xxx/ordernet
docker push swr.cn-north-1.myhuaweicloud.com/hwstaff_xxx/paymentnet
```

### 部署
注意在部署payment时，应用名字要跟order配置文件中的地址保持完全一致，所以名字为payment
