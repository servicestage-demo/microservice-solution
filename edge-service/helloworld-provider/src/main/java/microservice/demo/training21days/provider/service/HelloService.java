package microservice.demo.training21days.provider.service;

import java.util.Date;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

@RestSchema(schemaId = "hello")        // 该注解声明这是一个REST接口类，CSEJavaSDK会扫描到这个类，根据它的代码生成接口契约
@RequestMapping(path = "/provider/v0") // @RequestMapping是Spring的注解，这里在使用Spring MVC风格开发REST接口
public class HelloService {

  private static final Logger LOGGER = LoggerFactory.getLogger(HelloService.class);

  private DynamicStringProperty sayHelloPrefix = DynamicPropertyFactory
      .getInstance().getStringProperty("hello.sayHelloPrefix", ""
          , notifyConfigRefreshed());

  private Runnable notifyConfigRefreshed() {
    return () -> LOGGER.info("config[hello.sayHelloPrefix] changed to [{}]!", sayHelloPrefix.getValue());
  }

  @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET)
  public String sayHello(@PathVariable(value = "name") String name) {
    return sayHelloPrefix.getValue() + name;
  }

  @PostMapping(path = "/greeting")
  public GreetingResponse greeting(@RequestBody Person person) {
    GreetingResponse greetingResponse = new GreetingResponse();

    if (Gender.MALE.equals(person.getGender())) {
      greetingResponse.setMsg("Hello, Mr." + person.getName());
    } else {
      greetingResponse.setMsg("Hello, Ms." + person.getName());
    }
    greetingResponse.setTimestamp(new Date());

    return greetingResponse;
  }
}
