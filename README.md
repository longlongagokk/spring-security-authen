# 项目说明

1，开发环境  
* jdk:1.8  
* mysql:5.7
* redis:4.0.3(或者使用内部缓存ehcache)
* springcloud:Greenwich.SR2 (springsecurity 为webflux版本，请注意，跟servlet不兼容。) 

2，运行步骤
>1，新建数据库，并将项目根目录下的user.sql执行。  
>2，修改vitily-microservice-cluster\vitily-cluster-config\src\main\resources下的
provider-dev.properties文件中datasource0对象的相关配置。  
>3，如果使用redis，将provider-dev.properties中spring.redis对象相关属性修改掉；如果使用
ehcache，将erver.cacheFrame的值改为ehcache  
>3，按顺序启动  
>>vitily-microservice-cluster\的vitily-cluster-eureka 项目  
>>vitily-microservice-cluster\的vitily-cluster-config 项目  
>>vitily-microservice-cluster\的vitily-cluster-gateway 项目  
>>vitily-microservice-member\的vitily-member-service-api 项目  

3，测试运行
>在浏览器中输入
>>http://localhost:8100/order/get/10：返回无权限  
>>http://localhost:8100/mem/get/10：返回无权限  
>>http://localhost:8100/auth/get/10：返回找不到资源：因为我们配置了/aut/**允许匿名用户访问   
>>http://localhost:8100/auth/getToken?password=e10adc3949ba59abbe56e057f20f883e&userName=13724256427：返回了正确的用户信息以及token、所属角色组[1]  

>>在postman中将http头设置  
Authorization：Bearer 8908e17db2ee44aafd7a134432d72462|13724256427  
并输入  
>>>http://localhost:8100/order/get/10：返回404，说明此时已经有权限访问了但是找不到资源。  
>>>http://localhost:8100/mem/get/10：返回无权限，说明该用户所属角色并没有该资源的权限。  

>>我们换个用户登陆  
>>http://localhost:8100/auth/getToken?userName=13658745145&password=e10adc3949ba59abbe56e057f20f883e  

>>在postman中将http头设置  
Authorization：Bearer 0820bc284dff4fcbdca723204b303b19|13658745145  
并输入  
>>>http://localhost:8100/order/get/10：返回无权限，说明该用户所属角色没有该资源权限。  
>>>http://localhost:8100/mem/get/10：返回了正确的数据，说明该用户对应角色确实有该资源。  

4，代码模块说明
>1，vitily-common-tools：工具模块（一些公用工具）  
>2，vitily-common-jdbc：数据库操作模块（作为数据操作层基类存在）  
>3，vitily-common-cache：缓存模块  
>4，vitily-cluster-eureka：注册中心  
>5，vitily-cluster-config：配置中心  
>6，vitily-cluster-gateway：网关、负载均衡以及鉴权中心  
>7，vitily-member-service：业务逻辑、数据操作层，可以并到vitily-member-service-api中  
>8，vitily-member-service-api：会员中心，接口暴露应用。  

5，鉴权流程说明
>1，资源是是放在tb_mem_resources中的数据。  
>2，角色拥有的资源放在tb_group_has_resources表中，对应资源id表示拥有某个资源  
>3，vitily-member-service-api启动的时候将角色拥有的资源放到缓存中（角色、资源管理可以使用另外一个
微服务去操作，此处仅做示例）。代码在vitily-member-service-api\src\main\java\com.vitily.member.api\conf\StartupController.java
类中。  
>4，当一个请求从外部访问gateway（vitily-cluster-gateway）的时候，gateway会使用过滤器来判断用户
是否登陆、未登录表示为匿名（anonymous）访问，仅允许访问/auth/**下的资源。  
>5，假如用户已经登录，则使用用户对应的角色拥有的权限url来匹配请求的路径，匹配到则返回下一个filter，没有匹配到则返回无权限。

6，鉴权不足之处
>1，资源管理没有与授权分开  
>2，授权返回的token太简单，容易别识破  
>3，没有做刷新授权机制。  
>4，每次访问都需要将角色资源就加载一遍，没做到一次加载，动态更新的功能  




