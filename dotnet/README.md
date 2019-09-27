# payment service
Is the provider side, tha API is /v1/payments

## To run on local

cd src
dotnet run

check http://127.0.0.1:8080/v1/payments

# order service
Is the consumer side, tha API is /v1/orders, it calls http://127.0.0.1/v1/payments, then response to client

## To run on local
edit appsettings.Development.json change PaymentServiceEndpoint to http://127.0.0.1:8080
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

cd src
export ASPNETCORE_ENVIRONMENT=Developement
dotnet run
check http://127.0.0.1:5000/v1/orders# microservice-solutioin
