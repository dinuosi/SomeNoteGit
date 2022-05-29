# JWT

## 1. 关于JWT

JWT = JSON Web Token，它将通过JSON的格式组织需要记录在“票据”（Token）上的信息，最终通过算法，处理为不易被破解的数据，当客户端向服务器端发起请求后，服务器端可以向客户端响应一段JWT数据，此后，客户端在每次发起请求时，都应该携带此JWT数据，服务器端就可以解析这个JWT数据，从而获取其中的信息，以此识别客户端的身份，甚至从JWT中读取到更多必要的信息。

使用JWT可以解决传统技术中使用Session的不足，例如Session是存在内存中的数据，大量存储可能导致服务器端内存紧张，同时，客户端可能需要Session数据是长期存在，服务器端如果不定期清除一些Session数据就无法缓存内存紧张的压力！另外，在不使用共享Session的做法之前，传统的Session机制也不可以使用在集群架构甚至分布式架构中！

## 2. 使用JWT

在当前工程中创建新的子模块`csmall-passport`，创建时的参数为：

- Group Id：`cn.tedu`
- Artifact Id：`csmall-passport`
- Package：`cn.tedu.csmall.passport`

接下来，需要处理`csmall-passport`的`pom.xml`，主要是修改父工程。

然后，添加`jjwt`的依赖，完整的依赖代码为：

```xml
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```

接下来，可以在`src/test`下创建测试类，测试使用JWT，例如生成JWT数据：

```java
package cn.tedu.csmall.passport;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    // Token中的密钥
    String secretKey = "jfdsfdslkjafdslkjafds";

    @Test
    public void generateJwt() {
        List<String> permissions = new ArrayList<>();
        permissions.add("/product/add");
        permissions.add("/product/delete");

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 9527L);
        claims.put("username", "liucangsong");
        claims.put("permissions", permissions);

        JwtBuilder builder = Jwts.builder();
        // header, claims (payload), signature
        String jwt = builder.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setHeaderParam(Header.CONTENT_TYPE, "HS256")
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        System.out.println(jwt);
    }

}
```

关于生成的JWT数据：

- 不需要明确JWT数据中的内容，其本身也应该是无法直接阅读的
- 反复生成，得到的结果会不同，因为其中包含了不同的过期时间

关于解析JWT：

```java
@Test
public void parseJwt() {
    // 请根据当前测试需求，自行准备jwt数据
    String jwt = "eyJ0eXAiOiJKV1QiLCJjdHkiOiJIUzI1NiIsImFsZyI6IkhTMjU2In0.eyJwZXJtaXNzaW9ucyI6WyIvcHJvZHVjdC9hZGQiLCIvcHJvZHVjdC9kZWxldGUiXSwiaWQiOjk1MjcsImV4cCI6MTY1MTAyNzE2NSwidXNlcm5hbWUiOiJsaXVjYW5nc29uZyJ9.hvHM_IXVhv94_u5lKe_x-1jP8UUTrplp1ag4BxaFEcs";
    JwtParser parser = Jwts.parser();
    Claims claims = parser.setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    Object id = claims.get("id");
    Object username = claims.get("username");
    Object permissions = claims.get("permissions");
    System.out.println("id = " + id);
    System.out.println("username = " + username);
    System.out.println("permissions = " + permissions);
}
```

通过修改测试代码，可以发现：

- `io.jsonwebtoken.SignatureException` >>> JWT数据非法：签名有误
- `io.jsonwebtoken.MalformedJwtException` >>> JWT数据错误
- `io.jsonwebtoken.ExpiredJwtException` >>> JWT数据过期

## 3. 在Spring Boot中使用Security + JWT

在Spring Security中，默认方式是：认证成功后，将用户信息保存在Session中，接下来，将修改为：认证成功后，返回JWT，在后续的请求中，都认证JWT，从而识别用户的身份。

接下来，需要做的：

- 添加`spring-boot-starter-security`依赖
- 完成相关配置，使得Spring Security将从数据库查询管理员信息并完成登录认证
  - 自定义类，实现`UserDetailsService`接口，在此类中重写方法，根据用户名查询用户信息
  - 自定义类，是配置类，继承自`WebSecurityConfigureAdapter`，添加`@Bean`方法，返回`BCryptPasswordEncoder`类型的对象
- 从数据库中读取尝试登录的管理员的信息
  - 添加数据库编程相关依赖
    - `mybatis-spring-boot-starter`
    - `mysql`
    - `druid-spring-boot-starter`
    - `lombok`
  - 必要的配置
    - 在`application.yml`配置连接数据库的信息
    - 通过`@MapperScan`配置接口所在的包
    - 在`application.yml`配置`mybatis.mapper-locations`
  - 实现功能
    - 定义`AdminDetailsVO`类，作用查询的返回结果类型
    - 定义`AdminMapper`接口，并在接口中定义抽象方法
    - 准备`AdminMapper.xml`文件，用于配置以上抽象方法映射的查询
- 不可以让Spring Security完全处理认证过程，应该使用自己的代码接管处理认证成功后的操作
  - 添加`csmall-commons`依赖，使得当前子模块可以使用`JsonResult`，并抛出一些异常并处理异常
  - 自定义DTO类，用于封装客户端提交登录请求时的请求参数
  - 自定义控制器类，接收客户端（页面）提交的登录数据（用户名和密码），并向客户端响应JWT
  - 自定义业务逻辑接口，在其中定义登录的业务方法
  - 自定义业务逻辑类，在其中实现登录的业务方法
    - 使得Spring Security完成认证
      - 需要自动装配`AuthenticationManager`对象
        - 需要使得Spring Security的配置类继承自`WebSecurityConfigurerAdapter`类，重写其中的`authenticationManagerBean()`方法，在方法中调用父类方法以返回对象，并在方法上添加`@Bean`注解
    - 将认证成功的用户信息生成JWT数据，返回
      - 注意：关于权限，会被Spring Security处理为`Collection<GrandtedAuthority>`类型的，如果直接存入到Claims中形成JWT数据，后续解析出来的字符串是不易于还原为原本的类型的，所以，应该将此数据转换为JSON再添加到JWT中
        - 在处理JSON格式转换时，可以使用`fastjson`工具
  - 在Spring Security的配置类中，对登录的请求路径放行
  - 添加Knife4j，形成在线Api文档
    - 需要将相关URL添加到Spring Security白名单中
  - 在控制器中添加一个测试用的请求，用于后续测试处理授权
- 使用过滤器（`Filter`：可以执行在所有其它Web组件之前）拦截所有请求，尝试从中获取JWT数据，如果数据存在，则尝试解析此数据，如果解析成功，将此数据交回给Spring Security
  - 自定义类，继承自`OncePerRequestFilter`类，在其中的方法中对JWT进行处理
  - 注册此过滤器，使之在Spring Security的相关过滤器之前执行

### 3.1. 查询管理员信息

关于验证获取管理员信息的测试数据：

```mysql
truncate ams_admin;
truncate ams_role;
truncate ams_permission;
truncate ams_role_permission;
truncate ams_admin_role;

-- 权限表：插入测试数据
insert into ams_permission (name, value, description) values
('商品-商品管理-读取', '/pms/product/read', '读取商品数据，含列表、详情、查询等'),
('商品-商品管理-编辑', '/pms/product/update', '修改商品数据'),
('商品-商品管理-删除', '/pms/product/delete', '删除商品数据'),
('后台管理-管理员-读取', '/ams/admin/read', '读取管理员数据，含列表、详情、查询等'),
('后台管理-管理员-编辑', '/ams/admin/update', '编辑管理员数据'),
('后台管理-管理员-删除', '/ams/admin/delete', '删除管理员数据');

-- 角色表：插入测试数据
insert into ams_role (name) values
('超级管理员'), ('系统管理员'), ('商品管理员'), ('订单管理员');

-- 角色权限关联表：插入测试数据
insert into ams_role_permission (role_id, permission_id) values
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 6),
(3, 1), (3, 2), (3, 3);

-- 管理员表：插入测试数据
insert into ams_admin (username, password, nickname, email, description, is_enable) values
('root', '$2a$10$Dhl0WTAndoT35jyAuFFYHOSvrqflXcUcqXrjNkUO4dpXmYF6yk2Ki', 'root', 'root@tedu.cn', '最高管理员', 1),
('super_admin', '$2a$10$Dhl0WTAndoT35jyAuFFYHOSvrqflXcUcqXrjNkUO4dpXmYF6yk2Ki', 'administrator', 'admin@tedu.cn', '超级管理员', 1),
('nobody', '$2a$10$Dhl0WTAndoT35jyAuFFYHOSvrqflXcUcqXrjNkUO4dpXmYF6yk2Ki', '无名', 'liucs@tedu.cn', null, 0);

-- 管理员角色关联表：插入测试数据
insert into ams_admin_role (admin_id, role_id) values
(1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (2, 4), (3, 3);
```

**pom.xml**

```xml
<!-- Mybatis集成Spring Boot -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
</dependency>
<!-- MySQL驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
<!-- Druid数据库连接池 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
</dependency>
<!-- Lombok -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

**application-dev.yml**

```
# Spring配置
spring:
  # 连接数据库的配置
  datasource:
    # 连接url
    url: jdbc:mysql://localhost:3306/mall_ams?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    # 数据库用户名
    username: root
    # 数据库密码
    password: root
```

**application.yml**

```
# Spring配置
spring:
  # Profile配置
  profiles:
    # 需要加载的Profile配置文件
    active: dev

# Mybatis配置
mybatis:
  # 配置SQL语句的XML文件的位置
  mapper-locations: classpath:mapper/*.xml
```

**MybatisConfig.java**

```java
package cn.tedu.csmall.passport.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.tedu.csmall.passport.mapper") // 如果无此配置，将无法自动装配Mapper对象
public class MybatisConfig {

}
```

**AdminDetailsVO.java**

```java
package cn.tedu.csmall.passport.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminDetailsVO implements Serializable {

    private Long id;
    private String username;
    private String password;
    private List<String> permissions;

}
```

**AdminMapper.java**

```java
package cn.tedu.csmall.passport.mapper;

import cn.tedu.csmall.passport.pojo.vo.AdminDetailsVO;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    AdminDetailsVO getDetailsByUsername(String username);

}
```

**AdminMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.tedu.csmall.passport.mapper.AdminMapper">

    <!-- AdminDetailsVO getDetailsByUsername(String username); -->
    <select id="getDetailsByUsername" resultMap="DetailsResultMap">
        select
            ams_admin.id,
            ams_admin.username,
            ams_admin.password,
            ams_permission.value
        from ams_admin
        left join ams_admin_role on ams_admin.id=ams_admin_role.admin_id
        left join ams_role_permission on ams_admin_role.role_id=ams_role_permission.role_id
        left join ams_permission on ams_role_permission.permission_id=ams_permission.id
        where
            username=#{username}
    </select>

    <resultMap id="DetailsResultMap" type="cn.tedu.csmall.passport.pojo.vo.AdminDetailsVO">
        <id column="id" property="id" />
        <result column="username" property="username" />
        <result column="password" property="password" />
        <collection property="permissions" ofType="java.lang.String">
            <constructor>
                <arg column="value" />
            </constructor>
        </collection>
    </resultMap>

</mapper>
```

以上代码可以实现根据管理员的用户名查询管理员的信息详情。

### 3.2. 使Spring Security通过数据库的数据实现登录认证

**UserDetailsServiceImpl.java**

```java
package cn.tedu.csmall.passport.security;

import cn.tedu.csmall.passport.mapper.AdminMapper;
import cn.tedu.csmall.passport.pojo.vo.AdminDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("根据用户名（{}）查找管理员信息……", s);
        AdminDetailsVO admin = adminMapper.getDetailsByUsername(s);
        log.debug("查找到的管理员信息为：{}", admin);
        if (admin == null) {
            log.error("登录失败，用户名不存在！");
            throw new BadCredentialsException("=== 用户名不存在 ===");
        }

        UserDetails userDetails = User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .disabled(false)
                .credentialsExpired(false)
                .authorities(admin.getPermissions().toArray(new String[] {}))
                .build();
        log.debug("将向Spring Security返回尝试登录的用户信息：{}", userDetails);
        return userDetails;
    }

}
```

**WebSecurityConfiguration.java**

```java
package cn.tedu.csmall.passport.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
```

以上完成后，启动当前服务，在浏览中输出任何此服务的URL，均会重定向到登录页面，则可以尝试各种输入来观察是否可以通过数据库中的数据进行登录认证！

提示：在没有设计页面的情况，当登录后会跳转此前访问的URL，则页面会显示404错误，是正常的表现！

### 3.3. 使用自己的代码接管处理认证的操作

添加依赖：

```xml
<!-- csmall-commons -->
<dependency>
    <groupId>cn.tedu</groupId>
    <artifactId>csmall-commons</artifactId>
</dependency>
```

**WebSecurityConfiguration.java**

```java
package cn.tedu.csmall.passport.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        String[] urls = {
          "/admin/login"
        };
        http.authorizeRequests()
            .antMatchers(urls).permitAll()
            .anyRequest().authenticated();
    }
    
}
```

**AdminLoginDTO.java**

```java
package cn.tedu.csmall.passport.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLoginDTO implements Serializable {

    private String username;
    private String password;

}
```

**IAdminService.java**

```java
package cn.tedu.csmall.passport.service;

import cn.tedu.csmall.passport.pojo.dto.AdminLoginDTO;

public interface IAdminService {

    String login(AdminLoginDTO adminLoginDTO);

}
```

**AdminServiceImpl.java**

```java
package cn.tedu.csmall.passport.service.impl;

import cn.tedu.csmall.passport.pojo.dto.AdminLoginDTO;
import cn.tedu.csmall.passport.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(AdminLoginDTO adminLoginDTO) {
        // 创建被认证数据
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                        adminLoginDTO.getUsername(), adminLoginDTO.getPassword());
        // 执行认证，如果认证失败，将抛出异常
        authenticationManager.authenticate(authentication);

        // 如果程序可执行到此处，则表示认证成功
        String jwt = "hahaha"; // 临时
        return jwt;
    }

}
```

**AdminController.java**

```java
package cn.tedu.csmall.passport.controller;

import cn.tedu.csmall.commons.response.JsonResult;
import cn.tedu.csmall.passport.pojo.dto.AdminLoginDTO;
import cn.tedu.csmall.passport.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @RequestMapping("/login")
    public JsonResult<String> login(AdminLoginDTO adminLoginDTO) {
        return JsonResult.ok(adminService.login(adminLoginDTO));
    }

}
```

完成后，启动服务，可通过 http://localhost:8080/admin/login?username=root&password=123456 在浏览器中测试访问。

### 3.4. 解析JWT数据，向Spring Security中存入登录的用户信息

**JwtAuthenticationFilter.java**

```java
package cn.tedu.csmall.passport.security;

import cn.tedu.csmall.commons.response.JsonResult;
import cn.tedu.csmall.commons.response.ServiceCode;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Token中的密钥
    private String secretKey = "jfdsfdslkjafdslkjafds";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 客户端在使用JWT时，应该将其添加在请求头中名为Authentication属性中，这是我们在设置的服务器端的约定，客户端必须遵守
        // 从请求头中获取名为Authentication的数据
        String jwt = request.getHeader("Authentication");
        log.debug("从请求头中获取Authentication：{}", jwt);
        // 判断是否获取到了数据
        if (!StringUtils.hasText(jwt)) {
            // 不存在有效的JWT数据，则直接放行
            // 因为某些请求本身也不要求登录，没有JWT数据是正常表现
            // 继续执行后续的过滤器链
            log.debug("没有获取到JWT数据，直接放行……");
            filterChain.doFilter(request, response);
            return; // 必须
        }

        // 如果代码可以执行到此处，表示从请求头中获取到了疑似有效的JWT
        log.debug("准备解析JWT数据……");
        String username = null;
        String permissionsString = null;
        try {
            JwtParser parser = Jwts.parser();
            Claims claims = parser.setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
            username = claims.get("username").toString();
            permissionsString = claims.get("permissions").toString();
            log.debug("从JWT中解析得到username：{}", username);
            log.debug("从JWT中解析得到permissions：{}", permissionsString);
        } catch (SignatureException e) {
            log.error("解析失败，JWT数据签名有误！");
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.FORBIDDEN, "JWT数据签名有误！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (MalformedJwtException e) {
            log.error("解析失败，JWT数据格式有误！");
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.FORBIDDEN, "JWT数据格式有误！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (ExpiredJwtException e) {
            log.error("解析失败，JWT数据已经过期！");
            JsonResult<Void> jsonResult = JsonResult.fail(ServiceCode.FORBIDDEN, "JWT数据已经过期！");
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println(jsonResultString);
            return;
        }

        // 将解析成功的JWT放回到Spring Security中
        List<SimpleGrantedAuthority> authorities
                = JSON.parseArray(permissionsString, SimpleGrantedAuthority.class);
        log.debug("将JSON格式的权限信息转换为集合：{}", authorities);
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("成功将JWT中的信息存入到Spring Security上下文，后续Spring Security将可以自动处理这些数据");
        // 放行，继续执行后续的过滤器链
        filterChain.doFilter(request, response);
    }

}
```

**WebSecurityConfiguration.java**

```java
package cn.tedu.csmall.passport.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        String[] urls = {
                "/admin/login",
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

        // 注册过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
```

后续，当其它的服务也需要实现认证和授权时，将以上验证JWT的相关代码进行复用即可。







