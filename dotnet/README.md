
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
