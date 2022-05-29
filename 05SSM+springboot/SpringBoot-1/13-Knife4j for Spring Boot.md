# Knife4j -- 在线API文档框架

Knife4j是国人开发一个基于Swagger2的在线API文档的框架，它可以扫描控制器所在的包，并解析每一个控制器及其内部的处理请求的方法，生成在线API文档，为前后端的开发人员的沟通提供便利。

在`pom.xml`中添加依赖：

```xml
<!-- https://mvnrepository.com/artifact/com.github.xiaoymin/knife4j-spring-boot-starter -->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.9</version>
</dependency>
```

然后，需要在`application.properties`中添加配置：

```
knife4j.enable=true
```

并且，需要在`cn.tedu.boot.demo.config`下创建`Knife4jConfiguration`配置类：

```java
/**
 * Knife4j（Swagger2）的配置
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    /**
     * 【重要】指定Controller包路径
     */
    private String basePackage = "cn.tedu.boot.demo.controller";
    /**
     * 分组名称
     */
    private String groupName = "xxx";
    /**
     * 主机名
     */
    private String host = "xxx";
    /**
     * 标题
     */
    private String title = "xxx";
    /**
     * 简介
     */
    private String description = "xxx";
    /**
     * 服务条款URL
     */
    private String termsOfServiceUrl = "http://www.apache.org/licenses/LICENSE-2.0";
    /**
     * 联系人
     */
    private String contactName = "xxx";
    /**
     * 联系网址
     */
    private String contactUrl = "xxx";
    /**
     * 联系邮箱
     */
    private String contactEmail = "xxx";
    /**
     * 版本号
     */
    private String version = "1.0.0";

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean
    public Docket docket() {
        String groupName = "1.0.0";
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host(host)
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(termsOfServiceUrl)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .version(version)
                .build();
    }

}
```

完成后，启动项目，通过 http://localhost:8080/doc.html 即可访问在线API文档。

在开发实践中，每个处理请求的方法应该限定为某1种请求方式，如果允许多种请求方式，则在API文档的菜单中会有多项。

在API文档中，菜单中的各名称默认是根据控制器类名、方法名转换得到的，通常，应该通过配置改为更加易于阅读理解的名称：

- `@Api`：是添加在控制器类上的注解，通过此注解的`tags`属性可以修改原本显示控制器类名称的位置的文本，通常，建议在配置的`tags`值上添加序号，例如：`"1. 管理员模块"`、`"2. 商品模块"`，则框架会根据值进行排序
- `@ApiOperation`：是添加在控制器类中处理请求的方法上的注解，用于配置此方法处理的请求在API文档中显示的文本
- `@ApiOperationSupport`：是添加在控制器类中处理请求的方法上的注解，通过配置其`order`属性可以指定各方法在API文档中的显示顺序
- `@ApiModelProperty`：是添加在POJO类的属性上的注解，用于对请求参数或响应结果中的某个属性进行说明，主要通过其`value`属性配置描述文本，并可通过`example`属性配置示例值，还可在响应结果时通过`position`属性指定顺序
- `@ApiImplicitParam`：是添加在控制器类中处理请求的方法上的注解，也可以作为`@ApiImplicitParams`注解的参数值，主要用于配置非封装的参数，主要配置`name`、`value`、`example`、`required`、`dataType`属性
- `@ApiImplicitParams`：是添加在控制器类中处理请求的方法上的注解，当方法有多个非封装的参数时，在方法上添加此注解，并在注解内部通过`@ApiImplicitParam`数组配置多个参数

提示：以上`@ApiImplicitParams`、`@ApiImplicitParam`和`@ApiModelProperty`可以组合使用。

配置示例--控制器类：

```java
@Api(tags = "1. 管理员模块")
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperationSupport(order = 10)
    @ApiOperation("增加管理员")
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(AdminAddNewDTO adminAddNewDTO) {
        throw new RuntimeException("此功能尚未实现");
    }

    @ApiOperationSupport(order = 40)
    @ApiOperation("根据id查询管理员详情")
    @ApiImplicitParam(name = "id", value = "管理员id", example = "1",
            required = true, dataType = "long")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<Admin> getById(@PathVariable Long id) {
        throw new RuntimeException("此功能尚未实现");
    }

    @ApiOperationSupport(order = 41)
    @ApiOperation("根据角色类型查询管理员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", example = "1",
                required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", example = "1",
                dataType = "int")
    })
    @GetMapping("/list-by-role")
    public JsonResult<Admin> listByRole(Long roleId, Integer page) {
        throw new RuntimeException("此功能尚未实现");
    }

}
```

配置示例--封装的POJO类：

```java
@Data
@Accessors(chain = true)
public class AdminAddNewDTO implements Serializable {

    @ApiModelProperty(value = "用户名", example = "admin001")
    private String username;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "昵称", example = "管理员1号")
    private String nickname;

}
```
