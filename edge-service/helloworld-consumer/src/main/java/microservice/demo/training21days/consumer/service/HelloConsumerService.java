package microservice.demo.training21days.consumer.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import microservice.demo.training21days.provider.service.GreetingResponse;
import microservice.demo.training21days.provider.service.HelloService;
import microservice.demo.training21days.provider.service.Person;

@RestSchema(schemaId = "helloConsumer")
@Path("/consumer/v0")  // 这里使用JAX-RS风格开发的consumer服务
public class HelloConsumerService {
  // RPC调用方式需要声明一个provider服务的REST接口代理
  @RpcReference(microserviceName = "provider", schemaId = "hello")
  private HelloService helloService;

  // RestTemplate调用方式需要创建一个 ServiceComb 的 RestTemplate
  private RestTemplate restTemplate = RestTemplateBuilder.create();

  @Path("/hello")
  @GET
  public String sayHello(@QueryParam("name") String name) {
    // RPC 调用方式体验与本地调用相同
    return helloService.sayHello(name);
  }

  @Path("/helloRT")
  @GET
  public String sayHelloRestTemplate(@QueryParam("name") String name) {
    // RestTemplate 使用方式与原生的Spring RestTemplate相同，可以直接参考原生Spring的资料
    // 注意URL不是 http://{IP}:{port} ， 而是 cse://{provider端服务名} ， 其他部分如path/query等与原生调用方式一致
    ResponseEntity<String> responseEntity =
        restTemplate.getForEntity("cse://provider/provider/v0/hello/" + name, String.class);
    return responseEntity.getBody();
  }

  @Path("/greeting")
  @POST
  public GreetingResponse greeting(Person person) {
    return helloService.greeting(person);
  }
}
