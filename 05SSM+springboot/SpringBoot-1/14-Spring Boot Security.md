# Spring Boot Security

## 1. 关于Spring Boot Security

Spring Boot Security是在Spring Boot中使用的，基于Spring Security的依赖项，其本质就是Spring Security框架加上了应用于Spring Boot工程的自动配置。

Spring Security是一款主要解决了认证和授权相关处理的安全框架。

认证：验证用户的身份，例如在登录过程中验证用户名与密码是否匹配。

授权：使得用户允许访问服务器端的某些资源，或禁止访问某些资源，例如管理员可以执行一些数据删除操作，而普通用户则不可以删除关键数据。

## 2. 添加依赖项

提示：关于此框架的案例仍使用**boot-demo**。

在项目的`pom.xml`需要添加的依赖代码为：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

当添加此依赖后，启动项目，在访问所有资源时，都是要求先登录的（未退出之前只需要登录1次）。

默认的用户名是`user`，会在启动日志中看到默认的密码，例如：

```
Using generated security password: 276ff4c1-32da-4e23-adad-c926f33e37c6
```

当登录成功后，会返回此前尝试访问的页面。

也可以在`application.properties`中定义用户名和密码：

```
spring.security.user.name=admin
spring.security.user.password=1234
```

当通过以上方式自定义用户名和密码后，启动项目时将不再生成临时密码，原`user`用户也不可用。

## 3. 使用数据库中的用户名和密码

### 3.1. 关于Bcrypt算法

Spring Security推荐使用Bcrypt算法来实现对密码原文的加密处理，在框架中有`BcryptPasswordEncoder`类，此类可以实现加密、判断密码是否匹配等功能。

在密码加密器（PasswordEncoder）中需要关注的方法有：

```java
// 使用原文作为参数，将返回密文
public String encode(CharSequence rawPassword);

// 使用原文作为第1个参数，使用密文作为第2个参数，将返回是否匹配
public boolean matches(CharSequence rawPassword, String encodedPassword)
```

提示：Bcrypt算法对于同一个原文反复做加密处理时，每次得到的密文都是不同的，是因为在加密处理过程中使用了随机盐，所以可以得到不同的密文，同时，由于盐值被保存在密文中了，所以也是可以正常验证的。

简单的使用示例：

```java
package cn.tedu.boot.demo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptTests {

    @Test
    public void testEncode() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        for (int i = 0; i < 10; i++) {
            String encodedPassword = passwordEncoder.encode(rawPassword);
            System.out.println("原密码=" + rawPassword + "，密文=" + encodedPassword);
        }
    }

// 原密码=1234，密文=$2a$10$lQ/BuRDZG6h1GGZLWv/Rx.TjXEJer/668SKVeoFxNs65DMKxeVAmO
// 原密码=1234，密文=$2a$10$jqB0o3.bTzdPLuWVxgfIweZKFXC9qDU15Hzf6vpUSaPifmvdQr4je
// 原密码=1234，密文=$2a$10$NJlBJLl9lp0hAA.UfsvwheborCArK58iJF96te83jbL7J4wq079H6
// 原密码=1234，密文=$2a$10$Jm6fOxg4g4mH.FZ8xXO2OOeTqHAVe6fN/6IATox0NXO1wcW3wq.Q6
// 原密码=1234，密文=$2a$10$uzmvN3IU0yx4cVSBU49jEe2de217l7u2I84NIqiSpMCF2kQefmCZe
// 原密码=1234，密文=$2a$10$EbSsOZUg7F3EMEbgXTHXxuRSbjSxOker.aTjdI1h/zz4SCRNVuDvm
// 原密码=1234，密文=$2a$10$577X/D13svsDuT9cKNSjs.M2JUeqxF7ok9uHqCBYgdqv7pqvlOEM6
// 原密码=1234，密文=$2a$10$WeuikNbKuRCkqr8JrpC75uIa6lYhJ5fuBtWGnGMpJK7MQ8EKLaICa
// 原密码=1234，密文=$2a$10$b25uEZlqDB.koP2iY.RaKueImhdVS9Aeu5fgZmDJglqtpjIXd3UN.
// 原密码=1234，密文=$2a$10$rjTE6LmSC8Z5FBm1TQpmE.OVErbLy1xpkYPlO6pP2T2BoMclr3bTu

    @Test
    public void testMatch() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String[] encodedPasswords = {
                "$2a$10$WeuikNbKuRCkqr8JrpC75uIa6lYhJ5fuBtWGnGMpJK7MQ8EKLaICa",
                "$2a$10$Jm6fOxg4g4mH.FZ8xXO2OOeTqHAVe6fN/6IATox0NXO1wcW3wq.Q6",
                "$2a$10$EbSsOZUg7F3EMEbgXTHXxuRSbjSxOker.aTjdI1h/zz4SCRNVuDvm"
        };
        for (int i = 0; i < encodedPasswords.length; i++) {
            boolean result = passwordEncoder.matches(rawPassword, encodedPasswords[i]);
            System.out.println("原密码=" + rawPassword + "，密文=" + encodedPasswords[i] + "，验证结果=" + result);
        }
    }


}
```

为了便于后续的操作都使用了统一的密码处理机制，先在`cn.tedu.boot.demo.config`下创建`SecurityConfiguration`类，并在此类中使用`@Bean`方法返回`BcryptPasswordEncoder`对象：

```java
@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
```

接下来，调整`GlobalPasswordEncoder`，使得该类是通过自动装配的`PasswordEncoder`处理加密的：

```java
@Component
public class GlobalPasswordEncoder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 执行加密
     *
     * @param rawPassword 明文密码（原文）
     * @return 密文密码（加密后的结果）
     */
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
```

接下来，应该在`AdminServiceImpl`中自动装配以上`GlobalPasswordEncoder`的对象，并在原加密的代码处调整为使用装配的对象来调用方法进行加密处理！最后，执行测试，并在数据表中保留至少1个有效管理员信息。

### 3.2. 实现：根据用户名查询管理员登录时所需的详情

为了保证在处理登录认证时能获取到必要的管理员，且不包含非必要信息，应该先梳理所需要信息，并将它们封装在自定义类，此类将作为查询的返回结果类型！

则在`cn.tedu.boot.demo.vo`包中先创建`PermissionSimpleVO`类，声明查询时涉及的权限信息中的属性：

```java
@Data
public class PermissionSimpleVO implements Serializable {
    private Long id;
    private String name;
    private String value;
}
```

然后，再创建`AdminLoginVO`类，在类中声明必要属性：

```java
@Data
public class AdminLoginVO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Integer isEnable;
    private List<PermissionSimpleVO> permissions;
}
```

在编写代码之前，仍应分析需要执行的SQL语句：

```mysql
select id, username, password, is_enable from ams_admin where username=?
```

然后 ，在`AdminMapper`接口中添加抽象方法：

```java
AdminLoginVO getLoginInfoByUsername(String username);
```

并在`AdminMapper.xml`中配置以上抽象方法映射的SQL语句：

```xml
<select id="getLoginInfoByUsername" resultMap="LoginResultMap">
    select 
        ams_admin.id,
        ams_admin.username,
        ams_admin.password,
        ams_admin.is_enable,

        ams_permission.id AS permission_id,
        ams_permission.name AS permission_name,
        ams_permission.value AS permission_value
    from 
        ams_admin
    left join ams_admin_role 
        on ams_admin.id=ams_admin_role.admin_id
    left join ams_role_permission 
        on ams_admin_role.role_id=ams_role_permission.role_id
    left join ams_permission 
        on ams_role_permission.permission_id=ams_permission.id
    where 
        username=#{username}
</select>

<resultMap id="LoginResultMap" type="cn.tedu.boot.demo.vo.AdminLoginVO">
    <id column="id" property="id" />
    <result column="username" property="username" />
    <result column="password" property="password" />
    <result column="is_enable" property="isEnable" />
    <collection property="permissions" ofType="cn.tedu.boot.demo.vo.PermissionSimpleVO">
        <id column="permission_id" property="id" />
        <result column="permission_name" property="name" />
        <result column="permission_value" property="value" />
    </collection>
</resultMap>
```

完成后，在`AdminMapperTests`中进行测试：

```java
@Test
// 脚本：清空，插入
// 脚本（测试之后）：清空
public void testGetLoginInfoByUsernameSuccessfully() {
    // 测试数据
    String username = "admin001";
    // 执行测试
    AdminLoginVO admin = mapper.getLoginInfoByUsername(username);
    // 断言查询结果不为null
    Assertions.assertNotNull(admin);
}

@Test
// 脚本：清空
public void testGetLoginInfoByUsernameFailBecauseNotExist() {
    // 测试数据
    String username = "admin001";
    // 执行测试
    AdminLoginVO admin = mapper.getLoginInfoByUsername(username);
    // 断言查询结果为null
    Assertions.assertNull(admin);
}
```

当测试获取数据成功时，应该可以获取以下数据：

```
AdminLoginVO(
	id=1, 
	username=admin001, 
	password=123456, 
	isEnable=0, 
	permissions=[
		PermissionSimpleVO(id=1, name=商品-商品管理-读取, value=/pms/product/read), 
		PermissionSimpleVO(id=2, name=商品-商品管理-编辑, value=/pms/product/update), 
		PermissionSimpleVO(id=3, name=商品-商品管理-删除, value=/pms/product/delete), 
		PermissionSimpleVO(id=4, name=后台管理-管理员-读取, value=/ams/admin/read), 
		PermissionSimpleVO(id=5, name=后台管理-管理员-编辑, value=/ams/admin/update), 
		PermissionSimpleVO(id=6, name=后台管理-管理员-删除, value=/ams/admin/delete)
	])
```











管理员			管理员与角色				角色			角色与权限				权限

```msyql
truncate ams_admin;
truncate ams_role;
truncate ams_permission;
truncate ams_admin_role;
truncate ams_role_permission;
insert into ams_admin (username, password) values ('user001', '1234');
insert into ams_role (name) values ('商品管理员'), ('积分管理员');
insert into ams_admin_role (admin_id, role_id) values (1, 1), (1, 2);
insert into ams_permission (name, value) values ('创建商品', '/product/create'), ('删除商品', '/product/delete'), ('修改库存', '/product/stock/update'), ('查看积分', '/reward-point/show'), ('扣减积分', '/reward-point/reduce'), ('查看订单', '/order/show');
insert into ams_role_permission (role_id, permission_id) values (1, 1), (1, 2), (1, 3), (2, 4), (2, 5), (1, 6), (2, 6);

select 
	ams_admin.id,
	ams_admin.username,
	ams_admin.password,
	ams_admin.is_enable,
	
	ams_permission.id,
	ams_permission.name,
	ams_permission.value
from 
	ams_admin
left join ams_admin_role 
	on ams_admin.id=ams_admin_role.admin_id
left join ams_role_permission 
	on ams_admin_role.role_id=ams_role_permission.role_id
left join ams_permission 
	on ams_role_permission.permission_id=ams_permission.id
where 
	username='user001';
```

### 3.3. 关于UserDetailsService

在Spring Security中，定义了`UserDetailsService`接口，此接口中有抽象方法：

```java
UserDetails loadUserByUsername(String username);
```

Spring Security会在处理登录认证时自动根据尝试登录的用户名调用此接口实现类的此方法，并获得`UserDetails`对象，此对象应该包含用户的密码、权限等信息，接下来，Spring Security会自动判断密码正确，如果不正确，将返回错误信息，如果正确，会将此用户信息（包含权限）保存下来（默认保存在Session中）。

则在`cn.tedu.boot.demo`下创建`security`包，并在其下创建`UserDetailsServiceImpl`类，实现`UserDetailsService`接口：

```java
package cn.tedu.boot.demo.security;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AdminLoginVO admin = adminMapper.getLoginInfoByUsername(s);
        if (admin == null) {
            throw new BadCredentialsException("登录失败，用户名不存在！！！！！");
        }
        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .disabled(admin.getIsEnable() == 0)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .authorities("权限待定")
                .build();
    }

}
```

完成后，Spring Boot Security会自动使用以上类来实验登录过程中的认证！所以，可以直接启动项目进行测试。

**注意：在数据表中必须存在有效的管理员信息，且必须保证数据表中的密码是经过Bcrypt算法处理后的密文，且允许登录的账号的`is_enable`字段的值必须是`1`。**

### 3.4. 关于授权

在处理认证过程中，需要向响应的`UserDetails`对象中转入权限数据！则将`UserDetailsServiceImpl`中的`loadUserByUsername()`方法内部调整为：

```java
@Override
public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    log.debug("尝试登录，用户名={}", s);
    AdminLoginVO admin = adminMapper.getLoginInfoByUsername(s);
    log.debug("根据用户名查询的结果为：{}", admin);
    if (admin == null) {
        log.debug("登录失败，用户名不存在！！！！！");
        throw new BadCredentialsException("登录失败，用户名不存在！！！！！");
    }

    // --------- 以下是处理授权的相关代码 ---------
    // 将用户的权限信息转换为Spring Security要求的List<GrantedAuthority>类型的数据
    // ----------------------------------------
    List<PermissionSimpleVO> permissions = admin.getPermissions();
    log.debug("此用户的权限信息为：{}", permissions);
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (PermissionSimpleVO permission : permissions) {
        String permissionValue = permission.getValue();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
        authorities.add(authority);
    }

    return User.builder()
            .username(admin.getUsername())
            .password(admin.getPassword())
            .disabled(admin.getIsEnable() == 0)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .authorities(authorities) // 将以上得到的用户权限信息封装到UserDetails对象中
            .build();
}
```

关于Spring Security的配置类：

```java
package cn.tedu.boot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 添加此注解才可以在控制器方法上配置权限
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 提示：本方法中的配置可以一次性写完，每写一段都可以调用 and() 方法再拼接下一段配置

        // 关闭跨域
        http.csrf().disable();

        // authorizeRequests()：需要对请求进行认证和授权
        // antMatchers()：匹配某些路径，取值可以是字符串数组表示的URL，每个URL必须使用 / 作为第1个字符
        // permitAll()：许可所有（不需要认证授权即可访问），此方法之前必须是先匹配了路径的
        // anyRequest()：任何请求（除开以前已经配置的请求）
        // authenticated()：已经认证的（要求已经登录）
        String[] urls = {
                "/favicon.ico",
                "/doc.html",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs"
        };
        http.authorizeRequests()
                .antMatchers(urls).permitAll()
                .anyRequest().authenticated();

        // 当需要登录时，启用表单登录
        http.formLogin();
    }

}
```

当需要控制权限时，可以在控制器类中处理请求的方法上添加注解，并配置需要哪个权限值，例如：

```java
@GetMapping("/{id:[0-9]+}")
@PreAuthorize("hasAuthority('/ams/admin/read')") // 要求权限中具有 /ams/admin/read 这一条才允许访问此资源
public JsonResult<Admin> getById(@PathVariable Long id) {
    throw new RuntimeException("此功能尚未实现");
}
```

提示：以上做法可适用于通过Knife4j的调试功能发出的GET请求，如果需要测试POST请求，可能会出现403错误，则应该通过浏览器的调试面板中的“网络”来观察当前的Session ID，例如`JSESSIONID=07C874111475B2CD1A41E64EA268059E`，然后，在Knife4j的API文档中，在“文档管理”的“全局参数设置”中添加名为`Cookie`的参数，值为例如`JSESSIONID=07C874111475B2CD1A41E64EA268059E`，参数类型为`header`，则通过Knife4j进行调试时，会自动携带此信息，需要注意的是，如果重新登录、服务器端重启等原因导致Session ID发生变化，在Knife4j调试之前也应该更换Knife4j中配置的Session ID。

提示：为了保证较好的演示效果，请事先准备好数据表中的数据。

当某个已经登录的用户无权限访问资源时，将响应**403（Forbidden）**错误。



```
第1次响应时
Set-Cookie
JSESSIONID=FDED4F9C1FA8020FA16FF7BF4D07587B

Set-Cookie
JSESSIONID=07C874111475B2CD1A41E64EA268059E

后续请求时
Cookie
JSESSIONID=FDED4F9C1FA8020FA16FF7BF4D07587B
```





## 附：关于Session

HTTP协议是一种无状态协议，同一个用户在同一台设备上多次对同一个服务器端进行访问时，默认在服务器端并不保存此用户的相关信息，所以，无论访问多少次，服务器端都无法识别用户的身份！

为了解决此问题，最简单直接的方式是使用Session（会话），当某个客户端第1次向服务器端发送请求后，服务器端会在服务器端的内存中保存此用户的信息，并且此信息会关联到一个唯一的Session ID，当服务器端进行响应时，会将此Session ID响应到客户端，后续，客户端应该在每次请求时都携带此Session ID，则服务器可以根据后续请求头中的Session ID对应到此前保存的数据，从而识别用户的身份！

关于使用Session保存的数据，通常是：

- 用户身份的唯一标识，例如用户的id
- 高频率使用的数据，例如用户的权限
  - 可能存在数据不一致的风险
- 不便于使用其它存储技术进行处理的数据

关于Session消失的机制：

- 超时，如果某客户端长时间未向服务器端发起任何请求，则在服务器端上，此客户端对应的Session数据会被清除，常见的设置值是15分钟或30分钟
- 更换客户端（包括更换浏览器、关开浏览器），也会无法访问此前的Session数据，并且，此前的Session数据将会根据超时机制被清理
- 服务器端设备关机或重启
- 服务器端程序调用了清除Session数据的方法，例如调用了`HttpSession`对象的`invalidate()`方法









