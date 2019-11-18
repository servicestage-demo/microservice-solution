package microservice.demo.training21days.provider.service;

// 定义RPC调用方式所使用的代理接口
public interface HelloService {
  // 方法名称与provider服务契约中的 operationId 保持一致
  // 参数顺序与provider服务契约中定义的顺序保持一致
  String sayHello(String name);

  GreetingResponse greeting(Person person);
}
