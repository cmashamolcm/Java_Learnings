###Springboot
1. Spring:
    
   Spring is a framework that can help us develop Java enterprise applications 
   easily with configurations and mvc architecture.
   Earlier EJB(Enterprise Java Beans specification was were used for JavaEE applications. But it was heavy.)
   
2. EJB:
   - Enterprise Java Beans
   - This is a specification on how to write JavaEE(now JakartaEE) applications.
   - This addressed the horizontal concerns like transactions, security, persistence etc.
   - Configuration heavy
   - EJB spec will be implemented by application servers like Websphere, JBoss, Glassfish etc.
   - We need to align to the EJB spec and have to deploy our applications in these application servers as containers.
   - To access each part of it, we need to annotate our components as Singleton, Stateless etc.
   - Then from the context of EJBContainer, we have to get the beans and use it with @EJB annotation.
   ```
      Inside an application server;
      html pages ----> contacts servlets for backend operations(@Servlet)
                           |
                           |----->Can run bussiness logic from ejb (@EJB same as autowire and define ejb classes with @Singleton, @Stateless etc.)
      Here, advantage is; 
      We don't have to worry about the creation of beans. But just annotate and use it. 
      AppServer will have single EJB Conatiner and all beans will be there and servlets imports and use it.
      A light weight version of same is in spring as well.
   ```
   
3. Spring:
   - Framework for easy JavaEE app development. Not spec. It is framework.
   - This is also addressing the horizontal concerns. But with light weight containers.
   - It uses POJO instead of heavy EJBeans.
   - It uses light weight container
   - Configs are still there.
   - Supports RESTful, Reactive programming etc.
   - https://www.baeldung.com/spring-bean-vs-ejb
   - **Spring MVC**:
     - MVC = Model View Control
     - Model is data
     - View is jsp pages in src/main/webapp
     - Controller is @Controller with business logic.
     - `DispatcherServlet` is the `front controller` that accepts all requests and diverts to respective controllers to return view filled with data model.
     ```
                   web UI/ any request source<---------------------------------------------------
                     |                                                                           |
                     |1                                                                          |
                     v                                                                           |Return view with data filled to the caller
     ->----------DispatcherServlet (front controller)--------------5------->view resolver------->|
     |   |            |
     |   |3           |2 asks handler to find appropriate controller
     |   |            v
     |   v  -------HandlerMapping------- (identifies which controller to call)
     |   |            
     |   |           
     ^   |ask controller to return the view with data/ model
     |   v
     |   Controller
     |4  |
     |   |prepare and return ModelAndView object
     |   |4
     <---vreturn model and view to dispatcher servlet
     ```
     - Advantage of SpringMVC:
       - Separation of concern (view, data, business logic)
       - Controller with business logic can be reused.
       - Helps to change something in each layer with less effort. (plug and play)
       - Refer `MyController.java` to get sample endpoints with ModelAndView approach.
4. Springboot: (>=Java8)
   - Spring-boot is a way of creating spring based application.
   - Underlying is spring itself.
   - Spring has a lot of configurations, dependencies to solve horizontal concerns(eg: hibernate), we need to deploy it in application server etc.
   - But springboot;
   - `simplified addition of dependencies` (starter)
   - `Less configuration. Autoconfigure depending on added depdency libraries.`(jpa-starter if added, automatically, its configurations/beans are loaded.)
   - `In built server - tomcat`
   - `Helps us to build production ready spring based applications faster.`
   - Springboot helps us to simplify the ModelAndView approach to align more with json/ xml response.
5. Webserver vs application server:
   - Web server: Serves http/ https requests (tomcat)
   - Application server: Serves http + more protocols. It serves business logic to any client. Not only to web.
6. DI vs IOC:
   - IoC(Inversion of Control) is an architectural concept which says `Objects do not create other objects on which they rely on to do some work. Instead, they get it fom some other source.`
   - Eg: Main movie heros and dups.
   - DI(Dependency Injection) is one implementation of IoC. Here, we pass object to be used through constructor or setter of the user.
   - DI is a design pattern.
   - `Template design pattern` is one another way to implement IoC. Here, we must provide an implementation by sub-classing in order to use the template features.
   - IoC adds runtime polymorphism
7. DI in spring:
   - Life cycle of those classes annotated with `@Component`, `@Bean`  can be managed by spring.
   - Singleton Objects are created for such beans in context at the time of startup itself.
   - Whenever an `@Autowired` through setter or constructor happens, this instance from context is given to it.
   - When we do `@Autowired MyBean bean;`,  spring searches in context for bean with name `mybean`. (Small letter class name).
   - If found, give it to the component using it.
   - Any random class cannot access beans this way. Only other beans can do.
   - `ApplicationContext` vs `BeanFactory`:
   - Both are IoC containers. Container is logical name or real class name for ApplicationContext.
   - BeanFactory is the interface with methods that manages the beans.
   - ApplicationContext is extension of BeanFactory + some more features like load file resources, register listeners, inherit from parent context etc.
   - Recommended to use context than bean factory as spring 5 deprecated a few of of the classes for it.
8. To define a bean:
   - Use `@Component` annotation on class level
   - Use `@Bean` on method level to register returned value as bean.
   - `@Bean` can used in multiple methods of class annotated with `@Configuration`.
   - Scopes: (`@Scope(value=" ")` on bean/ component class). `Scope value must be small letter`
     - `@Scope(value="singleton")`: Creates single object for this component for the entire spring context.
     - Gets created at the initial loading itself.
     - `@Scope(value="prototype")`: Created on demand when everytime we do `context.getBean();`.
     - Not gets created at the time of initial loading.
     - `@Scope(value="request")`: Life of the object will be per request
     - `@Scope(value="session")`: Creates and destroys per session.
   - We can add name/ id to our bean by passing value to `@Component("bean-name")` and with `@Qualifier` while `@Autowire`.
   - This helps us to define beans for same interface with 2 different implementations and use it.
   - Eg: Let Computer be an interface. Laptop(`@Component("lap")`) and Desktop(`@Component("desk")`) and classes from it. 
   - Now, 
   - When we `@Autowire @Qualifier("lap") Computer c;`// will give instance of laptop from context.
   - When we `@Autowire @Qualifier("desk") Computer c;`// will give instance of desktop from context.
   - By default, bean name will be `small letter class name`.
   - `@Autowire`: searches spring container to find an instance (by default search by type. With `@Qualifier`, search by bean name.).
9. Repository:
    - Add springboot-starter-data-jpa, h2 as runtime dependency
    - Map model equivalent to table with `@Enitity`.
    - Make sure to have getters and setters
    - If we want to explicitly specify the table name, `@Table(name="name of table")`
    - `@Id` to map primary key. (must)
    - `@GeneratedValue(strategy=GenerationType.IDENTITY)` to make unique id for primary key on save.
    - `@Column(name="", nullable=true/false. default is true)` to explicitly specify column name
    - `@Reposityory` for jpa repo. Works even if we don"t add this annotation and just extends JpaRepository.
    - But, `@Reposityory` helps to have proper exception handling etc.
    - Add configurations for database in `application.properties`
    - Add `data.sql` in resource folder to have some DML commands. This executes before hibernate init.
    - Make sure to give `spring.jpa.defer-datsource-initialization=true` so that after hibernate init and created tables, data.sql is used.
    - Add `schema.sql` to have DDL if model to table auto-generation is disabled.
    - JpaRepository interface can have `method queries`. Those constructed based on method name.
    - Eg: `findByName` means, find row with given name.
    - But at times, we need to write our own queries.
    - Put `@Query(jpql)` to get the result.
    - Eg: `@Query("from DevModel where name like %?1%") List<DevModel> getAllByNameLike(String param1);`
    - `?1` means 1st parameter of method
    - DevModel is model classname. name is model class attribute name.
10. Controller vs RestController:
   - `@Controller` annotation is for spring mvc to make handler mapping aware that such a controller endpoint is there.
   - `@RequestMapping` helps to identify the endpoints in controller
   - `@RequestParam(name="expected key of query param")`: `url?devId=1` can be specified in method as `getDev(@RequestParam("devId") int id)`// if no name explicitly given, name of variable become key.
   - `@PathVariable(name="any name given as wild card in request mapping.")`
   - Eg: `@RequestMapping("/dev/{devId}") public String getDev(@PathVariable("devId"") int id)`
   - `@ResponseBody`: This is the main part in REST. By default, response from all endpoints are expected to be a `ModelAndView` or `jsp page name`.
   - But if we want to just return only data, this `@ResponseBody` is a must. Else, it tries to resolve the view and fails.
   - If we have @ResponseBody on our endpoints, we will gte String data etc.
   - It can convert other objects also into json format and send it to the caller.
   - By default, springboot uses `jackson-core` library for this conversion.
   - `Content Negotiation`:
     - Between client and server. `Client says that I can accept this format. Server, can you please send in that format?`
     - Client can ask for data in any format. But server can decide on what all it supports.
     - `Accept` header helps client to send what type it need.
     - `Accept: application/xml`
     - It will be discretion of server to accept it to return `406 - Not Acceptable` error.
     - By default, springboot supports `json`.
     - If we want to support to have xml as return, plug `jackson-dataformat-xml` library. Not, endpoint `can support xml and json`.
     - What if we want only xml (restrict supported return type format in server side)?
     - `@RequestMapping(produces="application/xml")`
     - Here, if client sends request without `Accept: application/xml`, or with `Accept: application/xml`, it returns xml response.
     - If `Accept: application/json`, returns `406 Not Acceptable` error.
     - If produces xml and json and client is not sending Accept header, then json comes.
   - `Sending data from client`:
     - Client can also send data in xml, json etc.
     - Server can decide to accept it or not.
     - `@PostMapping(consumes="application/xml")` make sure only xml request body is accepted.
     - By default, `form-data` is ok format if we are not specifying @RequestBody also, it can map the object properly.
     - `@postMapping` = post method + @RequestMapping
     - `@PostMapping(consumes="application/xml") void save(@RequestBody Model model){}`
     - Here, we expect that client sends body of post call as xml.
     - Otherwise, client will have to explicitly specify in header `Content-Type` as `application/xml`.
     - If content-type header is invalid or 
     - there is no content-type header and client send non-xml format in body, `415-Unsupported MediaType`
   - **produces links to header Accept. Expected Error code: 406-Not acceptable `Server says: Client, I am not interested in sending data in the format you asked for`**. 
   - **consumes links to header Content-Type. Expected error code: 415 - unsupported media-type `Server says: I cannot understand what you are telling me.`**
   ```
   `RestController = Controller + ResponseBody`.
     - Controller: This means, this is a controller and let handler mapper know that I have a few endpoint methods.
     - RequestBody: This endpoint is not giving the view name or ModelAndView to ask ViewResolver.
                    Instead, it is giving data. Which format? json or what ever is specified as `produces` attribute in RequestMapping of that method.
   ```
   - Springboot Data Rest:
     - Avoid controller and directly use repo.
     - This helps in services that need to just take care of persistence and not business logic.
     - `@RepositoryRestResource` and provide `path` and `collectionResourceRel`.
11. REST and maturity models:
    - REST = Representational State Transfer
    - It is an architectural style
    - Helps to send the state of a resource from one place to another.
    - Eg: from database to web UI with the help of REST client.
    - Underlying protocol: HTTP
    - `URI`:
      - path variable: uri/1, uri/asha - This points to the resource
      - query param/ request param: uri?id=1, uri?name=asha&age=20 - This is kind of where condition to get a resource. `Conditional fetch`
    - Request Headers:
      - `Request headers`(from client. eg: host, Accept etc.)
      - `Response headers`(from server. eg: content-disposition, content-type, eTag)
      - `Representation header`: metadata of data we transfer. Eg: content-type, content-length, content-location ect.
      - `Custom headers`: Our own headers.
      - Content-Type - what type of content client sends in request body (415)
      - Content-Disposition - indicates if inline or attachment mostly in case of files so that browser can decide on download or to add `<a>`. Eg: `Content-Disposition: "atatchment; abc.text"` so that file can be downloaded as abc.txt.
      - Accept - what type of response client would like to get from server.(406)
      - Authorization
      - cache
      - Use `server.max-http-header-size` to set maximum header size for a request. It is for server. Default for tomcat is 10MB from springboot 2.x 
    - Response Code:
      - 2xx - success
        - 200 - ok, 201 - created, 204 - success but no content
        - 202 - accepted. Uses mostly for huge file upload etc.
      - 3xx - redirect error or others
        - 303 - redirect
      - 4xx - client side problem
        - 400 - bad request, 401 - unauthorized, 403 - Forbidden, 404 - resource not found, 405 - method not allowed, 406 - not acceptable, 409 - already exists/ conflict, 415-Unsupported media-type
        - 429 - too many requests
      - 5xx - server side problem
        - 500 - internal server error
        - 503 - service unavailable
        - 502 - resource crunch
    - Methods/ HTTP verbs:
      - GET
      - POST - create/ update
      - PUT/id - update/ create if not there. Need full resource body. If id is missing, creates new one.
      - PATCH/id - partial update. No need of to send entire resource
        - `Why we need patch?`: When concurrent update happens, to avoid data loss.
      - DELETE
    - PUT, POST, PATCH idempotency:
      - idempotency: If we try one operation once or n times, result will be same.
      - PUT takes entire object and try to update as a whole if existing and creates if not present.
      - If we try n times also, the response resource will be same. First time, if not existing, creates and responds back with it. Next time own, record is there and hence do update. But still result will be same as request body doesn't change.
      - PATCH may or may not be idempotent. Eg: patch updates email id. n requests do the same. But if a patch updates updatedDate, it can be non-idempotent.
      - POST creates new resource everytime. So, it is not idempotent.
      - `POST not idempotent`
      - `PUT, DELETE, [GET, HEAD, OPTIONS, TRACE - these are not changing resource]` are.
      - `PATCH` may or may not.
      - Anything can be idempotent or not. It is controlled by underlying code. But this is a standard.
      - `POST` can have `path params`(when we want to create a sub-resource /order/1/address to create address for order id 1) but no `query params`.
      - `GET` - request body unwanted. Can give path param or query param or without also. Avoid path parameter if we want to fetch all.
      - Use `Pagination` with GET to avoid large data. Better to use `query param` with it to have limit, page ect.
      - `PUT` can do upsert. Put with id, update. Without id, create 
      - `PATCH` can help us to send partial data. Eg: update user basic profile.
    - Pagination:
      - To get batch of data instead of fetch all at once.
      - 2 ways:
        - request with limit and offset
        - with response to url to get next page
    - Asynchronous API:
      - For long running process, we need to wait for response
      - Eg: huge file upload
      - `Return 202 - accepted` and another api to poll with some id. When status is success/ upload completed, return 303 - redirect to GET /id of resource
      - We can decide in server side itself, max-file-size, max-request-size etc.
      - Mutipart support is enabled by default in springboot 2.x
      - In download api, we should specify at least as `Content-Type: application/octect-steam` and `Content-Disposition: attachment or inlie etc.`
      - https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
      - Redirect: 303. 301 - moved permanently
      - I below code, response comes as url given in setLocation of header. Status code visible will be 200. But page loaded will be the one in header.location as we specified 3xx as response code.
      ```
          @GetMapping("/redirect")
          public ResponseEntity navigate(){
               var header = new HttpHeaders();
               header.setLocation(URI.create("https://stackoverflow.com/questions/29085295/spring-mvc-restcontroller-and-redirect"));
               var result =  new ResponseEntity(header, HttpStatus.MOVED_PERMANENTLY);
               return result;
          }
      ```
    - API rate limit:
      - To make sure too many hits are restricted within a given time.
      - 429 - too many requests
      - Queue requests or apply rate limit to avoid getting overwelmed.
      - Or fallback with hystrix etc.
    - **ETAG**:
      - Entity tag
      - http response header
      - For http caching
      - Conditional GET: with If-None-Match, If-Match headers
      - No change, 304 - Not modified
      - This helps to avoid sending heavy response body everytime even without change
      - This helps to save bandwidth by not sending data if no change happened to resource.
      - We need to add filter to have eTag.
      - Shall and deep eTag implementations can be there.
      - Add `@Bean` as
      ```
         @Bean
         public ShallowEtagHeaderFilter eTagFilter(){
               return new ShallowEtagHeaderFilter();// this checks on hash of response and sends 304 if same hash code comes in response with no body.
         }
       //To have this working, 1st API call gets eTag in response header.
      Take that and add it as
      If-None-Match or If-Match header in upcoming requests to get 304 on no change.
      Eg:
      curl --location --request GET 'http://localhost:8080/etag' \header 'If-None-Match: "0628155dbf834441bb58f8ab9347a9718"'
      ```[README.md](..%2F..%2F..%2F..%2F..%2FDownloads%2Ftr-backend-challenge-simplified-1.1.4%202%2FREADME.md)
      - Refer: https://javadeveloperzone.com/spring-boot/spring-boot-etag-header-example/
12. Component vs Controller vs RestController vs Service vs Repository:
    - Component - stereotype for any spring component. Scanned by @ComponentScan. All others are derived from component.
      - Component is kind of bean itself. But this annotation is in class level and @Bean is in method level.
    - Controller - indication to handler mapping to identify endpoints
      - @RequestMapping has only effect inside a controller as Dispatcher servlet/ handler mapping scans that only to get end points.
      - If a class itself is annotated with @RequestMapping, then even if @Controller is not there, handler registers the endpoint. But not recommended to use.
    - RestController - controller + ResponseBody(expect data instead of view name)
    - Service - Business logic
    - Repository - persistence layer.
      - Advantage of this annotation is that, it adds an advisor to the component to convert SQL exceptions to spring's unified unchecked exception.
      - PersistenceExceptionTranslationPostProcessor helps here.
      - `persistence exception translation`
    - `Why should we use these annoations?`
    - Currently and in future also, spring may come up with some special treatments for each layer and this annotations helps to get it to our beans by default.
    - `@Scheduledjob(fixedRate=2000)` helps to create quartz jobs and run in evey 2000ms. Need @EnableScheduling to start it working. (https://spring.io/guides/gs/scheduling-tasks/)
    - `@EnableScheduling` will ensure that background task executors are created so that we can schedule our jobs.
    - `@Configuration`: tags a class a source of beans
    - `@EnableAutoConfiguration`: tells springboot to start adding beans based on settings from xml, dependencies in pom etc.
    - There is a jar `spring-boot-autoconfigure` having file called `spring.factories` in `META_INF`. This is used to identify the autoconfigure.
    - Similarly, if we want to create a library with auto-configurable beans, add `spring.factories` in `META_INF`
    - From springboot 3.x, it is changed to `META_INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`
    - We define META-INF folder in `resource` folder of project src.
    - Refer `custom-auto-config` project for reference. Used in `doctor-service` project.
    - Eg: If spring-webmvc is there, add DispatcherServlet
    - `@ComponentScan`: to tell spring to look for components, configurations, services etc in given package.
    - ***@SpringbootApplication***: @Configuration, @AutoConfiguration, @ComponentScan