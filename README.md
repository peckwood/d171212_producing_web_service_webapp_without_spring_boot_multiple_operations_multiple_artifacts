Note this project is not inheriting from Spring Boot but still uses `spring-boot-starter-web-services` as dependency

My web.xml over `WebApplicationInitializer` version is not working, so web.xml is commented out.

This project works with client: `d171211_consuming_web_service`

### Configurations

- Some important elements in our configuration of the wsdl
  - namespace 
    - definied in xsd files
    - example: `http://www.ouhaogroup.com/contract-status`, lets use `NAMESPACE_URI`
    - set in 
      - `wsdl11Definition.setTargetNamespace(NAMESPACE_URI), set inside `defaultWsdl11Definition()` of `WebServiceConfig`
      - `@PayloadRoot(namespace = NAMESPACE_URI,` set in `@PayloadRoot` inside `@Endpoint`
  - web service mapping (/ws)
    - set in `onStartup()` of `WebAppInitializer`: `dynamic.addMapping("/ws/*");`
    - `wsdl11Definition.setLocationUri("/ws");`, set inside `defaultWsdl11Definition()` of `WebServiceConfig`
  - port type
    - shows inside `portType` in wsdl url
    - set in `wsdl11Definition.setPortTypeName("contract-status-update-port");` inside `defaultWsdl11Definition()` of `WebServiceConfig`
  - wsdl URL
  - example: http://localhost:8080/d171212_producing_web_service_webapp_without_spring_boot_multiple_operations_multiple_artifacts/ws/contract-status-update.wsdl
  - `/ws` is the mapping set earlier
  - `contract-status-update` is the name of the bean of `defaultWsdl11Definition`
- is composed

### Generating java classes from xsd

- I used org.codehaus.mojo.jaxb2-maven-plugin plugin, only uncomment the goal tag when you need to generating java classes, or it will generate java classes inside target folder and report errors
- Use mvn clean package to generate, the whole process will fail if there are already generated classes in main, that is fine, as the generation should complete successfully
- If the plugin reports some error like `Invocation failed`, it is probablly because it cannot acess the wsdl

> [followed this concretepage guide](https://www.concretepage.com/spring-4/spring-4-soap-web-service-producer-consumer-example-with-tomcat#download)