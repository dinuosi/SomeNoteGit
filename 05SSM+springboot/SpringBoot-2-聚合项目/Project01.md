# 1. 关于项目

此项目是一个基于“自营”性质的电商平台，此阶段实现的目标主要是后台数据管理，即如何管理可售卖的商品。

# 2. 创建工程

使用Spring Boot的创建向导来创建新的工程，如果在创建过程中可以将工程选择为POM则直接选择，如果无此选项，可以创建出工程后删除`src`目录，此次创建的是整个项目的父级工程，除了使用`pom.xml`进行统一管理以外，此工程并不编写实质运行的代码。在创建过程中，建议勾选的依赖项有：

- Spring Boot Data Elasticsearch
- Spring Boot Data Redis
- Spring Boot Security
- Spring Boot Validation
- Spring Boot Web
- MySQL
- Mybatis Framework
- Lombok

另外，创建时，默认还会添加`spring-boot-starter-test`，以上是创建过程中可勾选的依赖项，后续需要其它依赖项时，可自行添加。

另外，会自动添加`spring-security-test`依赖，此项是不需要的，在`pom.xml`中将其删除即可。

由于当前工程只是父级工程，所以，应该在`pom.xml`中添加：

```xml
<packaging>pom</packaging>
```

父级项目应该统一管理所有子级模块的依赖项的版本，则在`<properties>`中定义各变量，值就是各依赖项的版本号，例如：

```xml
<properties>
    <java.version>1.8</java.version>
    <spring-boot.version>2.5.4</spring-boot.version>
    <mybatis-spring-boot.version>2.2.2</mybatis-spring-boot.version>
    <mysql.version>8.0.26</mysql.version>
    <lombok.version>1.18.20</lombok.version>
</properties>
```

然后，使用`<dependencyManagement>`框住所有依赖项的父级`<dependencies>`，表示这些依赖项只是被当前父级工程管理，并不是实际依赖，并且，各依赖项需要显式的配置版本，使用`${}`使用以上在`<properties>`中定义的变量即可，例如`<version>${spring-boot.version}</version>`。

整个`<dependencyManagement>`节点的代码如下：

```xml
<!-- 依赖管理 -->
<dependencyManagement>
    <!-- 被管理的各依赖项 -->
    <dependencies>
        <!-- Spring Data实现Elasticsearch开发的依赖项 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>
        <!-- Spring Data实现Redis开发的依赖项 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>
        <!-- Spring Boot Security：实现认证和授权 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>
        <!-- Spring Boot Validation：实现请求参数的格式验证 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>
        <!-- Spring Boot Web：集成Spring与Spring MVC -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>${spring-boot.version}</version>
        </dependency>
        <!-- Mybatis集成Spring Boot -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis-spring-boot.version}</version>
        </dependency>
        <!-- MySQL驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>${mysql.version}</version>
        </dependency>
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
            <version>${lombok.version}</version>
        </dependency>
        <!-- Spring Boot测试 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <version>${spring-boot.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

最后，由于当前父级工程不会编写实质运行的代码，也就不需要编译或运行，可以将`pom.xml`中的`<build>`节点全部删除！

# 3. 添加数据库连接池依赖

在Spring Boot中添加了数据库编程的依赖项后，默认已经添加了`Hikari`数据库连接池的，目前，业内也非常流行使用阿里巴巴的Druid连接池，可以自行添加Druid连接池的依赖项，并在后续需要使用的时候显式的指定它，其依赖的代码为：

```xml
<!-- https://mvnrepository.com/artifact/com.alibaba/druid-spring-boot-starter -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.3</version>
</dependency>
```

则在应该先在`pom.xml`的`<properties>`中添加变量：

```xml
<druid-spring-boot.version>1.2.3</druid-spring-boot.version>
```

然后，在`<dependencyManagement>`下的`<dependencies>`下添加依赖：

```xml
<!-- Druid数据库连接池 -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>${druid-spring-boot.version}</version>
</dependency>
```

# 4. 添加Knife4j依赖项

使用同样的做法添加Knife4j的依赖项，此依赖项的原始代码为：

```xml
<!-- https://mvnrepository.com/artifact/com.github.xiaoymin/knife4j-spring-boot-starter -->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <version>2.0.9</version>
</dependency>
```

**注意：请自行调整代码到当前工程的`pom.xml`中。**

# 5. 创建商品管理子模块

在`csmall-server-cgb2112`下创建一个新的Module，参数为：

- Group Id：`cn.tedu`
- Artifact Id：`csmall-product`
- Packaging：`pom`

与`csmall-server-cgb2112`相同，它依然是一个父级工程，本身并不编写代码，所以不需要`src`。

此`csmall-product`的`pom.xml`代码需调整如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>cn.tedu</groupId>
        <artifactId>csmall-server-cgb2112</artifactId>
        <version>1.0.0</version>
    </parent>

    <groupId>cn.tedu</groupId>
    <artifactId>csmall-product</artifactId>
    <version>1.0.0</version>
    <name>csmall-product</name>
    <description>Demo project for Spring Boot</description>

    <packaging>pom</packaging>

</project>
```

# 6. 创建商品管理的实现模块

在`csmall-product`下创建一个新的Module，参数为：

- Group Id：`cn.tedu`
- Artifact Id：`csmall-product-webapi`
- Packaging：`jar`
- Package：`cn.tedu.csmall.product.webapi`

在创建过程，不需要勾选任何依赖项，创建完成后再进行调整。

**注意：请使用IntelliJ IDEA v2021.1.3版本，如果使用的是较低版本，可能需要在创建时补充最后一级文件夹名称！**

当项目创建成功后，需要在`pom.xml`中将父级设置为`csmall-product`，并且，先添加Mybatis、MySQL、Druid、Lombok、Spring Boot Test的依赖项，并删除`<build>`节点。

完整的`pom.xml`代码如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>cn.tedu</groupId>
        <artifactId>csmall-product</artifactId>
        <version>1.0.0</version>
    </parent>

    <groupId>cn.tedu</groupId>
    <artifactId>csmall-product-webapi</artifactId>
    <version>1.0.0</version>
    <name>csmall-product-webapi</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <!-- 依赖项 -->
    <dependencies>
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
        <!-- Spring Boot测试 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
```

当添加了数据库编程的依赖项后，由于尚未配置连接数据库的参数，所以，项目无法启动，也不可以执行集成测试，则此时必须配置连接数据库的参数！

在本项目中，将统一使用`.yml`配置文件，所以，先将`application.properties`重命名为`application.yml`，然后，创建`application-dev.yml`文件，先在`application-dev.yml`中配置连接数据库的参数：

```
# Spring配置
spring:
  # 连接数据库的配置
  datasource:
    # 连接url
    url: jdbc:mysql://localhost:3306/mall_pms?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
    # 数据库用户名
    username: root
    # 数据库密码
    password: root
```

然后，在`application.yml`中激活此Profile配置：

```
# Spring配置
spring:
  # Profile配置
  profiles:
    # 需要加载的Profile配置文件
    active: dev
```

完成后，可以执行测试类中的`contextLoads()`测试方法，此时应该是测试通过的。

为了检查配置的连接参数是否正确，保证后续可以正确的连接到数据库，应该测试尝试获取连接，则先在`application-dev.yml`中设置日志的显示级别（测试过程中将通过日志输出）：

```
# 日志
logging:
  # 显示级别
  level:
    cn.tedu.csmall: trace
```

然后在测试类中添加测试：

```java
@SpringBootTest
@Slf4j
class CsmallProductWebapiApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetConnection() throws Exception {
        log.debug("获取数据库的连接对象：{}", dataSource.getConnection());
    }

}
```

当测试通过，则表示数据库连接的相关配置是正确的。

# 7. 创建实体类

实体类：通常是与数据表相对应的POJO类，包名通常使用`entity`，也有些使用习惯命名为`domain`。

为了更好的过渡到后期的微服务架构，POJO类应该编写在专门的子模块中，使得其它各服务都可以直接依赖于此POJO模块，则各服务都相当于有完全相同的POJO类型，以便于后续统一的管理数据。

则在根级项目下创建新的子模块，参数如下：

- Group Id：`cn.tedu`
- Artifact Id：`csmall-pojo`
- Package：`cn.tedu.csmall.pojo`
- 版本：`1.0.0`

创建过程不需要勾选任何依赖项，确认以上信息无误后即可直接将子模块创建出来。

创建出工程后，调整`pom.xml`，主要调整内容：

- 将父级项目设置为当前根级工程
- 添加Lombok依赖，只需要这1个依赖
- 删除`<build>`节点
- 删除`test`文件夹
- 删除启动类

在`cn.tedu.csmall.pojo`下创建`entity.Category`类：

```java
package cn.tedu.csmall.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Category implements Serializable {

    private Long id;
    private String name;
    private Long parentId;
    private Integer depth;
    private String keywords;
    private Integer sort;
    private String icon;
    private Integer enable;
    private Integer parent;
    private Integer display;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;

}
```

为了保证当前`csmall-pojo`模块能够被其它模块所使用，应该先在根项目的依赖管理中添加其管理代码：

```xml
<!-- 在properties中添加 -->
<csmall-pojo.version>1.0.0</csmall-pojo.version>
```

```xml
<!-- 在依赖管理中添加 -->
<!-- csmall pojo -->
<dependency>
    <groupId>cn.tedu</groupId>
    <artifactId>csmall-pojo</artifactId>
    <version>${csmall-pojo.version}</version>
</dependency>
```

# 8. 类别--增加--数据访问层

在`csmall-product-webapi`子模块的`pom.xml`中，在依赖项中添加：

```xml
<!-- csmall pojo -->
<dependency>
    <groupId>cn.tedu</groupId>
    <artifactId>csmall-pojo</artifactId>
</dependency>
```

在`cn.tedu.csmall.product.webapi`下创建`config.MybatisConfig`配置类：

```java
@Configuration
@MapperScan("cn.tedu.csmall.product.webapi.mapper") // 如果无此配置，将无法自动装配Mapper对象
public class MybatisConfig {}
```

在`cn.tedu.csmall.product.webapi`下创建`mapper.CategoryMapper`接口：

```java
@Repository // 如果无此注解，Idea可能提示无法装配的错误，但不影响运行
public interface CategoryMapper {
    
    /**
     * 插入类别数据
     * @param category 要插入的类别的数据
     * @return 受影响的行数
     */
    int insert(Category category);
    
}
```

在`application.yml`中添加配置：

```
mybatis:
  mapper-locations: classpath:mapper/*.xml
```

在`src/main/resources`下创建`mapper`文件夹，并在其下复制粘贴得到`CategoryMapper.xml`文件，并在其中配置以上抽象方法映射的SQL语句：

```xml
<!-- 省略前序固定代码 -->
<mapper namespace="cn.tedu.csmall.product.webapi.mapper.CategoryMapper">

    <!-- int insert(Category category); -->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert ...
    </insert>
    
</mapper>
```

最后，在`src/test/java`下的默认包下创建`mapper.CategoryMapperTests`测试类，编写并执行测试：

```java
@SpringBootTest
public class CategoryMapperTests {
    
    @Autowired
    CategoryMapper mapper;
    
    @Test
    void testInsertSuccessfully() {
        // 测试数据
        // 执行测试
        // 断言：返回的受影响的行数一定是1
    }
    
}
```

通常，类别的“名称”应该是唯一的，为保证“名称”是唯一的，在执行插入数据之前应该先检查。

在查询时，表中的`gmt_create`和`gmt_modified`绝大多时候是不需要的，所以，查询的结果中也不需要有`gmtCreate`和`gmtModified`属性，为了避免查询时的浪费，应该另外创建返回结果类型！则在`csmall-pojo`子模块中，在`cn.tedu.csmall.pojo`下创建`vo`包，并在其下创建`CategoryStandardVO`类，此类的属性与`Category`几乎一样，只是不需要有`gmtCreate`和`gmtModified`这2个属性：

```java
package cn.tedu.csmall.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryStandardVO implements Serializable {

    private Long id;
    private String name;
    private Long parentId;
    private Integer depth;
    private String keywords;
    private Integer sort;
    private String icon;
    private Integer enable;
    private Integer parent;
    private Integer display;

}
```

所以，需要开发“根据名称查询类别详情”的功能，其抽象方法可以是：

```java
CategoryStandardVO getByName(String name);
```

客户端尝试增加类别时，需要提交“父级类别id”，当此值不是`0`时，必须是一个有效的类别id值，为保证此值是有效的，还需要开发“根据id查询类别详情”的功能，其抽象方法可以是：

```java
CategoryStandardVO getById(Long id);
```

在类别数据中，还有一个“是否为父级类别”属性，默认应该是“否”，但是，如果此次增加类别时，选中了有效的父级类别，则需要将父级类别的“是否为父级类别”属性更新为“是”（父级类别的此属性原本可能为“否”），则还需要开发“根据id修改<是否为父级类别>”的功能，其抽象方法可以是：

```java
int updateParentById(@Param("id") Long id, @Param("parent") Integer parent);
```

完成后应该及时测试！

以上功能的完整代码为：

**CategoryMapper.java**

```java
package cn.tedu.csmall.product.webapi.mapper;

import cn.tedu.csmall.pojo.entity.Category;
import cn.tedu.csmall.pojo.vo.CategoryStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository // 如果无此注解，Idea可能提示无法装配的错误，但不影响运行
public interface CategoryMapper {

    /**
     * 插入类别数据
     *
     * @param category 要插入的类别的数据
     * @return 受影响的行数
     */
    int insert(Category category);

    /**
     * 根据类别id更新类别”是否为父级类别“
     *
     * @param id     类别id
     * @param parent 是否为父级类别，1=是，-0否
     * @return 受影响的行数
     */
    int updateParentById(@Param("id") Long id, @Param("parent") Integer parent);

    /**
     * 根据类别名称获取类别详情
     *
     * @param name 类别名称
     * @return 匹配的类别详情，如果没有匹配的数据，则返回null
     */
    CategoryStandardVO getByName(String name);

    /**
     * 根据类别id获取类别详情
     *
     * @param id 类别id
     * @return 匹配的类别详情，如果没有匹配的数据，则返回null
     */
    CategoryStandardVO getById(Long id);

}
```

**CategoryMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="cn.tedu.csmall.product.webapi.mapper.CategoryMapper">

    <!-- int insert(Category category); -->
    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into pms_category (
            name, parent_id, depth, keywords,
            sort, icon, enable, is_parent,
            is_display, gmt_create, gmt_modified
        ) values (
            #{name}, #{parentId}, #{depth}, #{keywords},
            #{sort}, #{icon}, #{enable}, #{parent},
            #{display}, #{gmtCreate}, #{gmtModified}
        )
    </insert>

    <!-- int updateParentById(@Param("id") Long id, @Param("parent") Integer parent); -->
    <update id="updateParentById">
        update
            pms_category
        set
            is_parent=#{parent}
        where
            id=#{id}
    </update>

    <!-- Category getByName(String name); -->
    <select id="getByName" resultMap="StandardResultMap">
        select
            <include refid="StandardQueryFields" />
        from
            pms_category
        where
            name=#{name}
    </select>

    <!-- Category getById(Long id); -->
    <select id="getById" resultMap="StandardResultMap">
        select
            <include refid="StandardQueryFields" />
        from
            pms_category
        where
            id=#{id}
    </select>

    <sql id="StandardQueryFields">
        <if test="true">
            id,
            name,
            parent_id,
            depth,
            keywords,
            sort,
            icon,
            enable,
            is_parent,
            is_display
        </if>
    </sql>

    <resultMap id="StandardResultMap" type="cn.tedu.csmall.pojo.vo.CategoryStandardVO">
        <id column="id" property="id" />
        <result column="name" property="name" />
        <result column="parent_id" property="parentId" />
        <result column="depth" property="depth" />
        <result column="keywords" property="keywords" />
        <result column="sort" property="sort" />
        <result column="icon" property="icon" />
        <result column="enable" property="enable" />
        <result column="is_parent" property="parent" />
        <result column="is_display" property="display" />
    </resultMap>

</mapper>
```

**truncate.sql（在`src/test/resources`下）**

```mysql
truncate pms_category;
```

**insert_data.sql（在`src/test/resources`下）**

```mysql
insert into pms_category
(name, parent_id, depth, keywords, sort, icon, enable, is_parent, is_display)
values
('家用电器', 0, 1, '家电', 99, 'http://www.tedu.cn/logo.png', 1, 1, 1),
('手机', 0, 1, '手机', 98, 'http://www.tedu.cn/logo.png', 1, 1, 1),
('家居', 0, 1, '居家生活', 91, 'http://www.tedu.cn/logo.png', 1, 1, 1),
('食品', 0, 1, '吃的喝的', 97, 'http://www.tedu.cn/logo.png', 1, 1, 1),
('电视', 1, 2, '电视', 93, 'http://www.tedu.cn/logo.png', 1, 1, 1),
('空调', 1, 2, '空调', 98, 'http://www.tedu.cn/logo.png', 1, 0, 1),
('洗衣机', 1, 2, '洗衣机', 92, 'http://www.tedu.cn/logo.png', 1, 0, 1),
('教育电视', 5, 3, '教育电视,教育,电视', 98, 'http://www.tedu.cn/logo.png', 1, 0, 1),
('游戏手机', 2, 2, '游戏手机', 95, 'http://www.tedu.cn/logo.png', 1, 0, 1),
('拍照手机', 2, 2, '拍照手机', 91, 'http://www.tedu.cn/logo.png', 1, 0, 1)
;
```

**CategoryMapperTests.java（在`src/test/java`下的`cn.tedu.csmall.product.webapi.mapper`下）**

```java
package cn.tedu.csmall.product.webapi.mapper;

import cn.tedu.csmall.pojo.entity.Category;
import cn.tedu.csmall.pojo.vo.CategoryStandardVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class CategoryMapperTests {

    @Autowired
    CategoryMapper mapper;

    @Test
    @Sql(scripts = {"classpath:truncate.sql"})
    @Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testInsertSuccessfully() {
        // 测试数据
        String name = "电子数码";
        Category category = new Category();
        category.setName(name);
        // 执行测试
        int rows = mapper.insert(category);
        // 断言结果：受影响的行数是1
        Assertions.assertEquals(1, rows);
        Assertions.assertEquals(1, category.getId());
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql", "classpath:insert_data.sql"})
    @Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testUpdateParentByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        Integer parent = 1;
        // 执行测试
        int rows = mapper.updateParentById(id, parent);
        // 断言结果：受影响的行数是1
        Assertions.assertEquals(1, rows);
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql"})
    void testUpdateParentByIdFailBecauseNotExist() {
        // 测试数据
        Long id = 999999999L;
        Integer parent = 1;
        // 执行测试
        int rows = mapper.updateParentById(id, parent);
        // 断言结果：受影响的行数是0（因为没有数据被更新）
        Assertions.assertEquals(0, rows);
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql", "classpath:insert_data.sql"})
    @Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByNameSuccessfully() {
        // 测试数据
        String name = "家用电器";
        // 执行测试
        CategoryStandardVO category = mapper.getByName(name);
        // 断言结果：查询结果不为null
        Assertions.assertNotNull(category);
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql"})
    void testGetByNameFailBecauseNotExist() {
        // 测试数据
        String name = "NOT_EXIST";
        // 执行测试
        CategoryStandardVO category = mapper.getByName(name);
        // 断言结果：查询结果为null（因为没有匹配的数据）
        Assertions.assertNull(category);
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql", "classpath:insert_data.sql"})
    @Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testGetByIdSuccessfully() {
        // 测试数据
        Long id = 1L;
        // 执行测试
        CategoryStandardVO category = mapper.getById(id);
        // 断言结果：查询结果不为null
        Assertions.assertNotNull(category);
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql"})
    void testGetByIdFailBecauseNotExist() {
        // 测试数据
        Long id = 999999999L;
        // 执行测试
        CategoryStandardVO category = mapper.getById(id);
        // 断言结果：查询结果为null（因为没有匹配的数据）
        Assertions.assertNull(category);
    }

}
```

# 9. 类别--增加--业务逻辑层

为保证当前项目在后续可以平衡过渡到微服务架构，需要将业务逻辑层的接口提取出来，放在独立的子模块中，便于其它服务实现RPC通信，所以，在`csmall-product`下创建`csmall-product-service`子模块，用于存储业务逻辑层的接口，此子模块的参数为：

- Group Id：`cn.tedu`
- Artifact Id：`csmall-product-service`
- Package：`cn.tedu.csmall.product.service`
- 父级工程：`cn.tedu.csmall.product`

当项目创建成功后，先调整`pom.xml`文件：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>cn.tedu</groupId>
        <artifactId>csmall-product</artifactId>
        <version>1.0.0</version>
    </parent>

    <groupId>cn.tedu</groupId>
    <artifactId>csmall-product-service</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>csmall-product-service</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!-- csmall pojo -->
        <dependency>
            <groupId>cn.tedu</groupId>
            <artifactId>csmall-pojo</artifactId>
        </dependency>
    </dependencies>

</project>
```

接下来，删除此子模块中不必要的部分，包括：

- test文件夹
- src/main下的resources文件夹
- src/main/java下根包下的启动类

然后，在`cn.tedu.csmall.product.service`下创建`ICategoryService`接口。

需要接口中定义”增加类别“的抽象方法，应该先在`csmall-pojo`的`cn.tedu.csmall.pojo`下创建`dto.CategoryAddNewDTO`类：

```java
@Data
public class CategoryAddNewDTO implements Serializable {

    private String name;
    private Long parentId;
    private String keywords;
    private Integer sort;
    private String icon;
    private Integer enable;
    private Integer display;
    
}
```

回到`csmall-product-service`子模块中，在`ICategoryService`接口中添加抽象方法：

```java
public interface ICategoryService {

    /**
     * 增加类别
     * @param categoryAddNewDTO 封装了增加类别时的参数的对象
     * @return 新增的类别的id
     */
    Long addNew(CategoryAddNewDTO categoryAddNewDTO);

}
```

接下来，应该在`csmall-product-webapi`中实现以上接口，并实现抽象方法，为保证`csmall-product-webapi`能使用到以上接口，应该将`csmall-product-service`先在**根级工程**中进行依赖管理：

```xml
<!-- 在properties中添加 -->
<csmall-product-service.version>1.0.0</csmall-product-service.version>
```

```xml
<!-- 在依赖管理中添加 -->
<!-- csmall-product-service -->
<dependency>
	<groupId>cn.tedu</groupId>
    <artifactId>csmall-product-service</artifactId>
    <version>${csmall-product-service.version}</version>
</dependency>
```

完成后，在`csmall-product-webapi`中添加以上依赖项：

```xml
<!-- csmall-product-service -->
<dependency>
	<groupId>cn.tedu</groupId>
    <artifactId>csmall-product-service</artifactId>
</dependency>
```

再在`csmall-product-webapi`的`cn.tedu.csmall.product.webapi`下创建`service.CategoryServiceImpl`类，添加`@Service`注解，实现`ICategoryService`接口：

```java
@Service
public class CategoryServiceImpl implements ICategoryService {

    // 自动装配CategoryMapper对象
    
    @Override
    public Long addNew(CategoryAddNewDTO categoryAddNewDTO) {
        // 从参数中获取尝试增加的类别的名称
        // 调用mapper对象的getByName()方法执行查询，并获取返回值
        // 判断返回值是否不为null
        // 是：存在与名称匹配的数据，则名称已经被占用，抛出异常（暂时抛出RuntimeException）
        
        // 从参数中获取父类类别的id
        // 判断父级类别的id是否为0
        // -- 是：当前尝试增加的类别是一级分类，则深度为1
        // -- 否：当前深度增加的类别不是一级分类，需要根据父类类别来决定当前类别的深度
        // -- 基于父级类别id,调用mapper对象的getById()方法执行查询，查询父级类别
        // -- 判断查询结果是否为null
        // -- -- 是：父级类别不存在，抛出异常（暂时抛出RuntimeException）
        // -- -- 否：父级类别存在，从查询结果中获取父类级别的depth，将其增加1，得到当前尝试增加的类别的深度
        
        // 创建Category类型的对象
        // 补全Category对象的属性值：depth：以上处理的结果
        // 补全Category对象的属性值：parent：默认为0
        // 补全Category对象的属性值：gmtCreate / gmtModified：当前时间（LocalDateTime.now()）
        // 补全Category对象的属性值：name / parentId / keywords / sort / icon / enable / display：来自参数categoryAddNewDTO
        // 调用mapper对象的insert()方法执行插入数据，并获取返回值
        // 判断返回的受影响行数是否不为1
        // 是：抛出异常（暂时抛出RuntimeException）
        
        // 判断，父级类别不为0，且父级类别中的parent值为0
        // >> 基于父级别的id，调用mapper的updateParentById()，将父级类别的“parent：是否为父级类别”更新为1，并获取返回值
        // >> 判断返回的受影响行数是否不为1
        // >> 是：抛出异常（暂时抛出RuntimeException）
        
        // 返回新增的类别的id，即调用insert()时的参数中的id属性值
        return null;
    }
    
}
```

具体的实现为：

```java
package cn.tedu.csmall.product.webapi.service;

import cn.tedu.csmall.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.pojo.entity.Category;
import cn.tedu.csmall.pojo.vo.CategoryStandardVO;
import cn.tedu.csmall.product.service.ICategoryService;
import cn.tedu.csmall.product.webapi.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Long addNew(CategoryAddNewDTO categoryAddNewDTO) {
        // 日志
        log.debug("增加类别，参数：{}", categoryAddNewDTO);
        // 从参数中获取尝试增加的类别的名称
        String name = categoryAddNewDTO.getName();
        // 调用mapper对象的getByName()方法执行查询，并获取返回值
        CategoryStandardVO checkNameQueryResult = categoryMapper.getByName(name);
        // 判断返回值是否不为null
        if (checkNameQueryResult != null) {
            // 是：存在与名称匹配的数据，则名称已经被占用，抛出异常（暂时抛出RuntimeException）
            log.warn("增加类别失败，尝试增加的类别名称【{}】已经存在！", name);
            throw new RuntimeException("增加类别失败，尝试增加的类别名称已经存在！");
        }

        // 从参数中获取父类类别的id
        Long parentId = categoryAddNewDTO.getParentId();
        // 默认类别深度为1
        Integer depth = 1;
        // 声明父级类别
        CategoryStandardVO parentCategory = null;
        // 判断父级类别的id是否为0
        if (parentId != 0) {
            // 当前尝试增加的类别不是一级分类，需要根据父类类别来决定当前类别的深度
            // 基于父级类别id,调用mapper对象的getById()方法执行查询，查询父级类别
            parentCategory = categoryMapper.getById(parentId);
            // 判断查询结果是否为null
            if (parentCategory == null) {
                // 是：父级类别不存在，抛出异常（暂时抛出RuntimeException）
                log.warn("增加类别失败，选定的父级类别不存在！");
                throw new RuntimeException("增加类别失败，选定的父级类别不存在！");
            } else {
                // 否：父级类别存在，从查询结果中获取父类级别的depth，将其增加1，得到当前尝试增加的类别的深度
                depth = parentCategory.getDepth() + 1;
            }
        }

        // 创建Category类型的对象
        Category category = new Category();
        // 补全Category对象的属性值：name / parentId / keywords / sort / icon / enable / display：来自参数categoryAddNewDTO
        BeanUtils.copyProperties(categoryAddNewDTO, category);
        // 补全Category对象的属性值：depth：以上处理的结果
        category.setDepth(depth);
        // 补全Category对象的属性值：parent：默认为0
        category.setParent(0);
        // 补全Category对象的属性值：gmtCreate / gmtModified：当前时间（LocalDateTime.now()）
        LocalDateTime now = LocalDateTime.now();
        category.setGmtCreate(now);
        category.setGmtModified(now);
        // 调用mapper对象的insert()方法执行插入数据，并获取返回值
        log.debug("增加类别，即将插入类别数据：{}", category);
        int rows = categoryMapper.insert(category);
        // 判断返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出异常（暂时抛出RuntimeException）
            log.warn("增加类别失败，插入类别数据时出现未知错误！");
            throw new RuntimeException("增加类别失败，服务器忙，请稍后再次尝试！");
        }

        // 判断，父级类别不为0，且父级类别中的parent值为0
        if (parentId != 0 && parentCategory.getParent() == 0) {
            // 基于父级别的id，调用mapper的updateParentById()，将父级类别的“parent：是否为父级类别”更新为1，并获取返回值
            log.debug("增加类别，将父级类别的“是否为父级类别”设置为：是");
            rows = categoryMapper.updateParentById(parentId, 1);
            // 判断返回的受影响行数是否不为1
            if (rows != 1) {
                // 是：抛出异常（暂时抛出RuntimeException）
                log.warn("增加类别失败，更新父级类别数据时出现未知错误！");
                throw new RuntimeException("增加类别失败，服务器忙，请稍后再次尝试！");
            }
        }

        // 返回新增的类别的id，即调用insert()时的参数中的id属性值
        log.debug("增加类别，完成，将返回新增的类别的id：{}", category.getId());
        return category.getId();
    }

}
```

**注意：以上代码并未完全完成，还需要进行其它调整，例如异常等。**

完成后，应该在`src/test/java`的根包下创建`service.CategoryServiceTests`测试类，编写并执行测试：

```java
package cn.tedu.csmall.product.webapi.service;

import cn.tedu.csmall.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.product.service.ICategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    ICategoryService service;

    @Test
    @Sql(scripts = {"classpath:truncate.sql"})
    @Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewSuccessfully() {
        // 测试数据
        String name = "家用电器";
        Long parentId = 0L;
        String keywords = "家电,电视,冰箱,洗衣机";
        Integer sort = 88;
        String icon = "http://www.tedu.cn/logo.png";
        Integer enable = 1;
        Integer display = 1;
        CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
        categoryAddNewDTO.setName(name);
        categoryAddNewDTO.setParentId(parentId);
        categoryAddNewDTO.setKeywords(keywords);
        categoryAddNewDTO.setSort(sort);
        categoryAddNewDTO.setIcon(icon);
        categoryAddNewDTO.setEnable(enable);
        categoryAddNewDTO.setDisplay(display);
        // 执行测试，断言不会抛出异常
        Assertions.assertDoesNotThrow(() -> {
            Long id = service.addNew(categoryAddNewDTO);
            // 断言新增的数据的id为1
            Assertions.assertEquals(1, id);
        });
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql", "classpath:insert_data.sql"})
    @Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void testAddNewFailBecauseNameConflict() {
        // 测试数据
        String name = "家用电器";
        CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
        categoryAddNewDTO.setName(name);
        // 执行测试，断言将抛出异常
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.addNew(categoryAddNewDTO);
        });
    }

    @Test
    @Sql(scripts = {"classpath:truncate.sql"})
    void testAddNewFailBecauseParentNotExist() {
        // 测试数据
        String name = "家用电器";
        Long parentId = 999999999L;
        CategoryAddNewDTO categoryAddNewDTO = new CategoryAddNewDTO();
        categoryAddNewDTO.setName(name);
        categoryAddNewDTO.setParentId(parentId);
        // 执行测试，断言将抛出异常
        Assertions.assertThrows(RuntimeException.class, () -> {
            service.addNew(categoryAddNewDTO);
        });
    }

}
```

# 10. 事务（Transaction）

事务是数据库中的用于保证一系列的写操作要么全部成功，要么全部失败的机制！

在事务中涉及几个概念：

- 开启事务（BEGIN）
- 提交事务（COMMIT）
- 回滚事务（ROLLBACK）

在基于Spring JDBC的编程中，当需要某个业务方法是事务性的，只需要在此方法的声明上添加`@Transactional`即可！

关于Spring JDBC管理事务的机制，大致是：

```
开启事务：Begin
try {
	你的业务方法
} catch (RuntimeException e) {
	回滚事务：Rollback
}
提示事务：Commit
```

注意事项：

- 它是基于接口代理模式的，所以，`@Transactional`只针对接口中声明的方法是有效的
  - 例如，你在`CategoryServiceImpl`类中另外声明了某方法，此方法在`ICategoryService`上是没有的，则此方法上添加`@Transactional`也是无效的
- 它是基于接口代理模式的，所以，内部调用时，被调用方法的`@Transactional`是无效的
  - 例如在接口中声明了`a()`、`b()`这2个方法，均添加了`@Transactional`注解，且`a()`方法的内部调用了`b()`方法，则此次调用时，`b()`方法上的`@Transactional`是无效的
- 默认情况下，将根据`RuntimeException`进行回滚
  - 你也可以配置`@Transactional`注解的`rollbackFor`属性，则根据其它异常类型进行回滚，还可以配置`noRollbackFor`属性，指定出现某些异常时不回滚
- 关于`@Transcational`注解，可以添加在接口上、接口中的抽象方法上、接口的实现类上、接口的实现类的重写的方法上，如果是添加在接口或实现类上，将使得接口中定义的所有方法都是事务性的，如果是添加接口中抽象方法上或接口的实现类重写的方法上，只会使得当前方法是事务性的，如果同时添加`@Transcational`注解，并配置了不同的参数，则以方法上的注解参数为准
  - **注意：强烈推荐将`@Transational`添加在接口上，或接口中的抽象方法上，而不添加在实现类上**
- 通常，某个业务方法中涉及1次以上的写操作（增、删、改操作，例如2次插入，或1次修改+1次删除，甚至2次插入+2次删除等），就必须使得此业务方法是事务性的
  - 其实，如果某个业务方法涉及多次查询，也可以是事务性的，则会使用同一个数据库连接，在多次查询时，会一定程度上提高查询效率

在当前项目中，为了能在`csmall-product-service`中能使用`@Transactional`注解，必须添加`spring-jdbc`依赖，在其它子模块中，此依赖都是添加`mybatis-spring-boot-starter`时同时依赖进来的！为保证所有子模块使用的各依赖项的版本是统一的，在`csmall-product-service`中也应该添加` mybatis-spring-boot-starter`依赖，但是，其子级依赖项是不需要的（甚至不能添加进来，避免某些自动配置导致程序运行错误），则应该将不需要的子级依赖项去除！所以，在`csmall-product-service`中需要添加的依赖项代码为：

```xml
<!-- Mybatis集成Spring Boot -->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-autoconfigure</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.zaxxer</groupId>
            <artifactId>HikariCP</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

完成后，在`ICategoryService`接口上添加`@Transactional`注解，并且，在实现类上将不再使用此注解。

# 11. 关于异常

目前项目中抛出的异常还是`RuntimeException`，是不合适的，需要自定义异常类，例如`ServiceException`，但是，为了后续向微服务架构过渡，这个异常并不能直接定义在`csmall-product-webapi`，而应该定义在一个共有的子模块中！

则在根级项目下，创建新的子模块，创建参数为：

- Group Id：`cn.tedu`
- Artifact Id：`csmall-commons`
- Package：`cn.tedu.csmall.commons`

当子模块创建出来后，删除不必要的部分：

- `src/test`
- `src/main/resources`
- `src/main/java`下的根包下的启动类

然后，在此子模块的根包下创建`response.ServiceCode`枚举，用于穷举可能的业务状态码：

```java
package cn.tedu.csmall.commons.response;

public enum ServiceCode {

    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    NOT_ACCEPTABLE(406),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500);

    private Integer value;

    ServiceCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
```

然后，在根包下创建`exception.ServiceException`业务异常类，并且，此类必须添加带`ServiceCode`和`String message`的构造方法，且还需要添加对于`ServiceCode`的Getter方法：

```java
package cn.tedu.csmall.commons.exception;

import cn.tedu.csmall.commons.response.ServiceCode;

public class ServiceException extends RuntimeException {

    private ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode, String message) {
        super(message);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode() {
        return serviceCode;
    }

}
```

以上全部完成后，应该在根级工程的`pom.xml`中管理此子模块：

```xml
<!-- 添加在properties中 -->
<csmall-commons.version>1.0.0</csmall-commons.version>
```

```xml
<!-- 添加在依赖管理中 -->
<!-- csmall-commons -->
<dependency>
    <groupId>cn.tedu</groupId>
    <artifactId>csmall-commons</artifactId>
    <version>${csmall-commons.version}</version>
</dependency>
```

并且，在`csmall-product-webapi`中添加以上`csmall-commons`依赖。

接下来，需要调整`CategoryServiceImpl`中抛出的异常。

提示，在此操作中，应该善用“静态导入”的做法，可以使得调用枚举值的语法更加简洁，例如：



提示，在此操作中，应该善用“静态导入”的做法，可以使得调用枚举值的语法更加简洁，例如：

```java
import static cn.tedu.csmall.commons.response.ServiceCode.*;
```

```java
throw new ServiceException(CONFLICT, "增加类别失败，尝试增加的类别名称已经存在！");
```

调整完后的代码如下：

```java
package cn.tedu.csmall.product.webapi.service;

import cn.tedu.csmall.commons.exception.ServiceException;
import cn.tedu.csmall.commons.response.ServiceCode;
import cn.tedu.csmall.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.pojo.entity.Category;
import cn.tedu.csmall.pojo.vo.CategoryStandardVO;
import cn.tedu.csmall.product.service.ICategoryService;
import cn.tedu.csmall.product.webapi.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static cn.tedu.csmall.commons.response.ServiceCode.*;

@Service
@Slf4j
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Long addNew(CategoryAddNewDTO categoryAddNewDTO) {
        // …………此处省略此次未修改的代码…………
        // 判断返回值是否不为null
        if (checkNameQueryResult != null) {
            // 是：存在与名称匹配的数据，则名称已经被占用，抛出异常（暂时抛出RuntimeException）
            log.warn("增加类别失败，尝试增加的类别名称【{}】已经存在！", name);
            throw new ServiceException(CONFLICT, "增加类别失败，尝试增加的类别名称已经存在！");
        }

        // …………此处省略此次未修改的代码…………
        // 判断父级类别的id是否为0
        if (parentId != 0) {
            // …………此处省略此次未修改的代码…………
            // 判断查询结果是否为null
            if (parentCategory == null) {
                // 是：父级类别不存在，抛出异常（暂时抛出RuntimeException）
                log.warn("增加类别失败，选定的父级类别不存在！");
                throw new ServiceException(NOT_FOUND, "增加类别失败，选定的父级类别不存在！");
            } else {
                // …………此处省略此次未修改的代码…………
            }
        }

        // …………此处省略此次未修改的代码…………
        // 判断返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出异常（暂时抛出RuntimeException）
            log.warn("增加类别失败，插入类别数据时出现未知错误！");
            throw new ServiceException(INTERNAL_SERVER_ERROR, "增加类别失败，服务器忙，请稍后再次尝试！");
        }

        // 判断，父级类别不为0，且父级类别中的parent值为0
        if (parentId != 0 && parentCategory.getParent() == 0) {
            // …………此处省略此次未修改的代码…………
            // 判断返回的受影响行数是否不为1
            if (rows != 1) {
                // 是：抛出异常（暂时抛出RuntimeException）
                log.warn("增加类别失败，更新父级类别数据时出现未知错误！");
                throw new ServiceException(INTERNAL_SERVER_ERROR, "增加类别失败，服务器忙，请稍后再次尝试！");
            }
        }

        // 返回新增的类别的id，即调用insert()时的参数中的id属性值
        log.debug("增加类别，完成，将返回新增的类别的id：{}", category.getId());
        return category.getId();
    }

}
```

# 12. 类别--增加--控制器

需要先在`csmall-product-webapi`的`pom.xml`添加WEB开发的依赖：

```xml
<!-- Spring Boot Web：集成Spring与Spring MVC -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

然后，在`csmall-product-webapi`下添加控制器，简单的处理请求，如下：

```java
package cn.tedu.csmall.product.webapi.controller;

import cn.tedu.csmall.pojo.dto.CategoryAddNewDTO;
import cn.tedu.csmall.product.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    // http://localhost:8080/categories/add-new?name=JiaDian&parentId=0
    @RequestMapping("/add-new")
    public String addNew(CategoryAddNewDTO categoryAddNewDTO) {
        Long id = categoryService.addNew(categoryAddNewDTO);
        return "" + id;
    }

}
```

完成后，启动整个项目（运行启动类的`main()`方法），在浏览器中通过 http://localhost:8080/categories/add-new?name=JiaDian&parentId=0 进行测试访问，将可以看到新增的类别的id，需要注意，目前还没有处理异常，所以，使用相同的参数反复访问会出现500错误。

目前，需要解决的问题有：

- 响应JSON格式的数据
- 统一处理异常
- 添加在线API文档
- 验证请求参数

关于响应JSON格式的数据，需要定义一个作为统一响应结果的类型，由于后续的微服务器，各服务应该都使用这个类进行响应，所以，这个类应该定义在`csmall-commons`中！则在`csmall-commons`的根包下的`response`包下创建`JsonResult`类：

```java
package cn.tedu.csmall.commons.response;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {

    private Integer state; // 业务返回码
    private String message; // 消息
    private T data; // 数据

    private JsonResult() { }

    public static JsonResult<Void> ok() {
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.state = ServiceCode.OK.getValue();
        jsonResult.data = data;
        return jsonResult;
    }
    
    public static JsonResult<Void> fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    public static JsonResult<Void> fail(ServiceCode serviceCode, String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.state = serviceCode.getValue();
        jsonResult.message = message;
        return jsonResult;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
```

接下来，应该将控制器的处理请求的方法的返回值改为以上类型，同时，因为本次允许向客户端响应新增的类别的ID号，为了便于后续可能的调整（例如响应更多属性的值），并且为了后续能够在API文档中对此值进行描述，应该将此ID值封装起来！所以，先在`csmall-pojo`下的根包下的`vo`包下创建`CategoryAddNewVO`类：

```java
@Data
@Accessors(chain = true)
public class CategoryAddNewVO implements Serializable {
    
    private Long id;
    
}
```

然后，调整`CategoryController`中处理请求的方法的返回值：

```java
// http://localhost:8080/categories/add-new?name=JiaDian&parentId=0
@RequestMapping("/add-new")
public JsonResult<CategoryAddNewVO> addNew(CategoryAddNewDTO categoryAddNewDTO) {
    Long id = categoryService.addNew(categoryAddNewDTO);
    return JsonResult.ok(new CategoryAddNewVO().setId(id));
}
```

完成后，重启项目，再次访问（注意修改请求参数），当正确的添加类别后，响应的结果例如：

```
{"state":200,"message":null,"data":{"id":5}}
```

为了避免响应为`null`的属性，还应该在`application.yml`中添加配置：

```
spring:
  jackson:
    default-property-inclusion: non_null
```

提示：根据已有的配置，以上代码可能需要调整，例如，当已经存在`spring:`的一级配置时，则应该将以上代码的`spring:`去除，并将剩余的代码配置在已有的`spring:`的子级。

配置完成后，再次重启项目，并尝试访问，响应的JSON中将不再包含为`null`的部分。

接下来，需要统一处理异常，为了使得后续所有的服务（子模块）都可以使用同样的处理异常的代码，应该将统一处理异常的类创建在`csmall-commons`下，则需要在`csmall-commons`中添加`spring-webmvc`的依赖，很显然，我们并不会直接添加此依赖，应该是添加`spring-boot-starter-web`依赖项，再排除不需要的子级依赖项，所以添加的依赖代码为：

```xml
<!-- Spring Boot Web：集成Spring与Spring MVC -->
<!-- 由于需要统一处理异常，则需要spring-web / spring-webmvc相关依赖项 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-json</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

然后，在`csmall-commons`的根包下的`exception`子包中创建`handler.GlobalExceptionHandler`类，用于统一处理异常：

```java
package cn.tedu.csmall.commons.exception.handler;

import cn.tedu.csmall.commons.exception.ServiceException;
import cn.tedu.csmall.commons.response.JsonResult;
import cn.tedu.csmall.commons.response.ServiceCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleServiceException(ServiceException e) {
        return JsonResult.fail(e);
    }

    @ExceptionHandler(Throwable.class)
    public JsonResult<Void> handleThrowable(Throwable e) {
        e.printStackTrace();
        return JsonResult.fail(ServiceCode.INTERNAL_SERVER_ERROR, "服务器忙，请稍后再次尝试！");
    }

}
```

为了保证以上处理异常的类能够被组件扫描到，应该在`csmall-commons`的根包下创建`config.CsmallCommonsConfiguration`配置类，在此类中使用`@ComponentScan`注解扫描以上处理异常的类所在的包：

```java
package cn.tedu.csmall.commons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("cn.tedu.csmall.commons.exception.handler")
public class CsmallCommonsConfiguration {
}
```

最后，在需要使用以上处理异常的子模块中导入以上配置类即可，例如在`csmall-product-webapi`子模块的启动类上使用`@Import`导入以上配置类：

```java
@Import({CsmallCommonsConfiguration.class})
@SpringBootApplication
public class CsmallProductWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsmallProductWebapiApplication.class, args);
    }

}
```

完成后，再次重启项目，即可看到能够处理异常！

接下来，添加在线API文档，则在`csmall-product-webapi`子模块的`pom.xml`中添加`knife4j-spring-boot-starter`的依赖项，并且从前序项目中复制`Knife4jConfiguration`到此子模块中，注意，在此配置类中一定要修改配置的包名为当前项目中控制器类所在的包！

然后，在`application.yml`中添加配置：

```
# knife4j配置
knife4j:
  # 开启增强模式
  enable: true
```

其实，Knife4j主要是用于开发过程中的，在生产环境中应该屏蔽，可以通过`knife4j.production`进行控制。

完成后，启动项目，可以通过 http://localhost:8080/doc.html 访问在线API文档。

首先，可以看到`addNew`的请求有7种，这是因为在控制器类中使用`@RequestMapping`配置请求路径导致的，需要将其改为`@PostMapping`，例如：

```java
@PostMapping("/add-new")
public JsonResult<CategoryAddNewVO> addNew(CategoryAddNewDTO categoryAddNewDTO) {
    Long id = categoryService.addNew(categoryAddNewDTO);
    return JsonResult.ok(new CategoryAddNewVO().setId(id));
}
```

然后，在控制器类上通过`@Api`注解来配置模块名称，在处理请求的方法上通过`@ApiOperation`和`@ApiOperationSupport`配置业务功能的名称、顺序：

```java
@Api(tags = "1. 类别管理模块")
@RestController
@RequestMapping("/categories")
public class CategoryController {

    public CategoryController() {
        System.out.println("CategoryController.CategoryController");
    }

    @Autowired
    private ICategoryService categoryService;

    @ApiOperationSupport(order = 10)
    @ApiOperation("增加类别")
    @PostMapping("/add-new")
    public JsonResult<CategoryAddNewVO> addNew(CategoryAddNewDTO categoryAddNewDTO) {
        Long id = categoryService.addNew(categoryAddNewDTO);
        return JsonResult.ok(new CategoryAddNewVO().setId(id));
    }

}
```

然后，在`CategoryAddNewDTO`中的各属性之前添加`@ApiModelProperty`属性对请求参数进行说明，但是，`CategoryAddNewDTO`类是在`csmall-pojo`子模块中的，此模块并没有添加`knife4j-spring-boot-starter`的依赖，则无法使用注解进行配置，所以需要先添加依赖项：

```xml
<!-- Knife4j在线API文档 -->
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-ui</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-annotations</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-core</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.javassist</groupId>
            <artifactId>javassist</artifactId>
        </exclusion>
        <exclusion>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-bean-validators</artifactId>
        </exclusion>
        <exclusion>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
        </exclusion>
        <exclusion>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-spring-webmvc</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-annotations</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

然后再配置各请求参数的说明：

```java
package cn.tedu.csmall.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryAddNewDTO implements Serializable {

    @ApiModelProperty(value = "类别名称", example = "家电", required = true)
    private String name;

    @ApiModelProperty(value = "父级类别id，如果是一级分类，则此属性值为0", example = "10", required = true)
    private Long parentId;

    @ApiModelProperty(value = "类别关键词", example = "家电,家用电器", required = true)
    private String keywords;

    @ApiModelProperty(value = "自定义排序序号", example = "1", required = true)
    private Integer sort;

    @ApiModelProperty(value = "图标的URL", example = "http://www.tedu.cn/logo.png", required = true)
    private String icon;

    @ApiModelProperty(value = "是否启用，1=启用，0=禁用", example = "1", required = true)
    private Integer enable;

    @ApiModelProperty(value = "是否显示在导航栏，1=显示，0=不显示", example = "1", required = true)
    private Integer display;

}
```

以上是针对请求参数添加的说明，其实，还应该对响应的数据也添加说明！例如，在`JsonResult`中：

```java
public class JsonResult<T> implements Serializable {

    @ApiModelProperty(value = "业务状态码", position = 1)
    private Integer state;

    @ApiModelProperty(value = "消息", position = 2)
    private String message;

    @ApiModelProperty(value = "数据", position = 3)
    private T data;
 
    // ………… 省略其它原有代码 …………
}
```

提示：需要将`csmall-pojo`中关于`knife4j-spring-boot-starter`的依赖代码复制到`csmall-commons`模块中。

由于响应时的结果类型是`JsonResult<CategoryAddNewVO>`，还需要在`CategoryAddNewVO`中继续添加说明：

```java
@Data
@Accessors(chain = true)
public class CategoryAddNewVO implements Serializable {

    @ApiModelProperty(value = "新增加的类别的id", position = 1)
    private Long id;

}
```

完成后，关于在线API文档可以暂时结束，此框架仍有一些不足，不是核心问题可以不必过多关注。

接下来，需要对请求参数进行验证，首先，需要在`csmall-product-webapi`中添加`spring-boot-starter-validation`依赖项。

当添加依赖项后，需要：

- 对被检查参数添加注解，配置检查规则及检查失败时文本描述等
- 在被检查的参数前添加`@Valid` / `@Validated`注解
- 处理`BindException`异常

先在`CategoryAddNewDTO`中添加注解，则此类在`csmall-pojo`中，所以，需要先在此模块中也添加依赖项：

```xml
<!-- Spring Boot Validation：实现请求参数的格式验证 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.apache.tomcat.embed</groupId>
            <artifactId>tomcat-embed-el</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.jboss.logging</groupId>
            <artifactId>jboss-logging</artifactId>
        </exclusion>
        <exclusion>
            <groupId>com.fasterxml</groupId>
            <artifactId>classmate</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

配置检查规则及检查失败时文本描述等，例如：

```java
package cn.tedu.csmall.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
public class CategoryAddNewDTO implements Serializable {

    @ApiModelProperty(value = "类别名称", example = "家电", required = true)
    @NotNull(message = "增加类别失败，必须提交类别名称！")
    @Pattern(regexp = ".{2,16}", message = "增加类别失败，类别名称必须是2~16字符！")
    private String name;

    @ApiModelProperty(value = "父级类别id，如果是一级分类，则此属性值为0", example = "10", required = true)
    @NotNull(message = "增加类别失败，必须提交父级类别！")
    private Long parentId;

    @ApiModelProperty(value = "类别关键词", example = "家电,家用电器", required = true)
    @NotNull(message = "增加类别失败，必须提交类别关键词！")
    private String keywords;

    @ApiModelProperty(value = "自定义排序序号", example = "1", required = true)
    @NotNull(message = "增加类别失败，必须提交自定义排序序号！")
    @Range(max = 99, message = "增加类别失败，自定义排序序号必须是0~99之间的值！")
    private Integer sort;

    @ApiModelProperty(value = "图标的URL", example = "http://www.tedu.cn/logo.png", required = true)
    @NotNull(message = "增加类别失败，必须提交图标！")
    private String icon;

    @ApiModelProperty(value = "是否启用，1=启用，0=禁用", example = "1", required = true)
    @NotNull(message = "增加类别失败，必须选择是否启用！")
    @Range(max = 1)
    private Integer enable;

    @ApiModelProperty(value = "是否显示在导航栏，1=显示，0=不显示", example = "1", required = true)
    @NotNull(message = "增加类别失败，必须选择是否显示在导航栏！")
    @Range(max = 1)
    private Integer display;

}
```

并且，需要在`GlobalExceptionHandler`中添加新的处理异常的方法，用于处理`BindException`，例如：

```java
@ExceptionHandler(BindException.class)
public JsonResult<Void> handleBindException(BindException e) {
    String message = e.getBindingResult().getFieldError().getDefaultMessage();
    return JsonResult.fail(ServiceCode.BAD_REQUEST, message);
}
```

# 13. 使用Mybatis拦截器自动更新时间

Mybatis拦截器是Mybatis框架中的一种组件，可用于拦截SQL语句，并进行必要的干预。

每个Mybatis拦截器都必须实现`Interceptor`接口，并且在拦截器类上使用`@Intercepts`注解来配置此拦截器应用于哪里，在拦截器内部，主要靠`intercept()`方法实现拦截，并进行必要的处理。

```java
package cn.tedu.csmall.product.webapi.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Intercepts(@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
))
public class SqlInterceptor implements Interceptor {
    /**
     * 自动添加的创建时间字段
     */
    private static final String FIELD_CREATE = "gmt_create";
    /**
     * 自动更新时间的字段
     */
    private static final String FIELD_MODIFIED = "gmt_modified";
    /**
     * SQL语句类型：其它（暂无实际用途）
     */
    private static final int SQL_TYPE_OTHER = 0;
    /**
     * SQL语句类型：INSERT
     */
    private static final int SQL_TYPE_INSERT = 1;
    /**
     * SQL语句类型：UPDATE
     */
    private static final int SQL_TYPE_UPDATE = 2;
    /**
     * 查找SQL类型的正则表达式：INSERT
     */
    private static final String SQL_TYPE_PATTERN_INSERT = "^insert\\s";
    /**
     * 查找SQL类型的正则表达式：UPDATE
     */
    private static final String SQL_TYPE_PATTERN_UPDATE = "^update\\s";
    /**
     * 查询SQL语句片段的正则表达式：gmt_modified片段
     */
    private static final String SQL_STATEMENT_PATTERN_MODIFIED = ",\\s*" + FIELD_MODIFIED + "\\s*=";
    /**
     * 查询SQL语句片段的正则表达式：gmt_create片段
     */
    private static final String SQL_STATEMENT_PATTERN_CREATE = ",\\s*" + FIELD_CREATE + "\\s*[,)]?";
    /**
     * 查询SQL语句片段的正则表达式：WHERE子句
     */
    private static final String SQL_STATEMENT_PATTERN_WHERE = "\\s+where\\s+";
    /**
     * 查询SQL语句片段的正则表达式：VALUES子句
     */
    private static final String SQL_STATEMENT_PATTERN_VALUES = "\\)\\s*values?\\s*\\(";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 日志
        log.debug("准备拦截SQL语句……");
        // 获取boundSql，即：封装了即将执行的SQL语句及相关数据的对象
        BoundSql boundSql = getBoundSql(invocation);
        // 从boundSql中获取SQL语句
        String sql = getSql(boundSql);
        // 日志
        log.debug("原SQL语句：{}", sql);
        // 准备新SQL语句
        String newSql = null;
        // 判断原SQL类型
        switch (getOriginalSqlType(sql)) {
            case SQL_TYPE_INSERT:
                // 日志
                log.debug("原SQL语句是【INSERT】语句，准备补充更新时间……");
                // 准备新SQL语句
                newSql = appendCreateTimeField(sql, LocalDateTime.now());
                break;
            case SQL_TYPE_UPDATE:
                // 日志
                log.debug("原SQL语句是【UPDATE】语句，准备补充更新时间……");
                // 准备新SQL语句
                newSql = appendModifiedTimeField(sql, LocalDateTime.now());
                break;
        }
        // 应用新SQL
        if (newSql != null) {
            // 日志
            log.debug("新SQL语句：{}", newSql);
            reflectAttributeValue(boundSql, "sql", newSql);
        }

        // 执行调用，即拦截器放行，执行后续部分
        return invocation.proceed();
    }

    public String appendModifiedTimeField(String sqlStatement, LocalDateTime dateTime) {
        Pattern gmtPattern = Pattern.compile(SQL_STATEMENT_PATTERN_MODIFIED, Pattern.CASE_INSENSITIVE);
        if (gmtPattern.matcher(sqlStatement).find()) {
            log.debug("原SQL语句中已经包含gmt_modified，将不补充添加时间字段");
            return null;
        }
        StringBuilder sql = new StringBuilder(sqlStatement);
        Pattern whereClausePattern = Pattern.compile(SQL_STATEMENT_PATTERN_WHERE, Pattern.CASE_INSENSITIVE);
        Matcher whereClauseMatcher = whereClausePattern.matcher(sql);
        // 查找 where 子句的位置
        if (whereClauseMatcher.find()) {
            int start = whereClauseMatcher.start();
            int end = whereClauseMatcher.end();
            String clause = whereClauseMatcher.group();
            log.debug("在原SQL语句 {} 到 {} 找到 {}", start, end, clause);
            String newSetClause = ", " + FIELD_MODIFIED + "='" + dateTime + "'";
            sql.insert(start, newSetClause);
            log.debug("在原SQL语句 {} 插入 {}", start, newSetClause);
            log.debug("生成SQL: {}", sql);
            return sql.toString();
        }
        return null;
    }

    public String appendCreateTimeField(String sqlStatement, LocalDateTime dateTime) {
        // 如果 SQL 中已经包含 gmt_create 就不在添加这两个字段了
        Pattern gmtPattern = Pattern.compile(SQL_STATEMENT_PATTERN_CREATE, Pattern.CASE_INSENSITIVE);
        if (gmtPattern.matcher(sqlStatement).find()) {
            log.debug("已经包含 gmt_create 不再添加 时间字段");
            return null;
        }
        // INSERT into table (xx, xx, xx) values (?,?,?)
        // 查找 ) values ( 的位置
        StringBuilder sql = new StringBuilder(sqlStatement);
        Pattern valuesClausePattern = Pattern.compile(SQL_STATEMENT_PATTERN_VALUES, Pattern.CASE_INSENSITIVE);
        Matcher valuesClauseMatcher = valuesClausePattern.matcher(sql);
        // 查找 ") values " 的位置
        if (valuesClauseMatcher.find()) {
            int start = valuesClauseMatcher.start();
            int end = valuesClauseMatcher.end();
            String str = valuesClauseMatcher.group();
            log.debug("找到value字符串：{} 的位置 {}, {}", str, start, end);
            // 插入字段列表
            String fieldNames = ", " + FIELD_CREATE + ", " + FIELD_MODIFIED;
            sql.insert(start, fieldNames);
            log.debug("插入字段列表{}", fieldNames);
            // 定义查找参数值位置的 正则表达 “)”
            Pattern paramPositionPattern = Pattern.compile("\\)");
            Matcher paramPositionMatcher = paramPositionPattern.matcher(sql);
            // 从 ) values ( 的后面位置 end 开始查找 结束括号的位置
            String param = ", '" + dateTime + "', '" + dateTime + "'";
            int position = end + fieldNames.length();
            while (paramPositionMatcher.find(position)) {
                start = paramPositionMatcher.start();
                end = paramPositionMatcher.end();
                str = paramPositionMatcher.group();
                log.debug("找到参数值插入位置 {}, {}, {}", str, start, end);
                sql.insert(start, param);
                log.debug("在 {} 插入参数值 {}", start, param);
                position = end + param.length();
            }
            if (position == end) {
                log.warn("没有找到插入数据的位置！");
                return null;
            }
        } else {
            log.warn("没有找到 ) values (");
            return null;
        }
        log.debug("生成SQL: {}", sql);
        return sql.toString();
    }


    @Override
    public Object plugin(Object target) {
        // 本方法的代码是相对固定的
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
        // 无须执行操作
    }

    /**
     * <p>获取BoundSql对象，此部分代码相对固定</p>
     *
     * <p>注意：根据拦截类型不同，获取BoundSql的步骤并不相同，此处并未穷举所有方式！</p>
     *
     * @param invocation 调用对象
     * @return 绑定SQL的对象
     */
    private BoundSql getBoundSql(Invocation invocation) {
        Object invocationTarget = invocation.getTarget();
        if (invocationTarget instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocationTarget;
            return statementHandler.getBoundSql();
        } else {
            throw new RuntimeException("获取StatementHandler失败！请检查拦截器配置！");
        }
    }

    /**
     * 从BoundSql对象中获取SQL语句
     *
     * @param boundSql BoundSql对象
     * @return 将BoundSql对象中封装的SQL语句进行转换小写、去除多余空白后的SQL语句
     */
    private String getSql(BoundSql boundSql) {
        return boundSql.getSql().toLowerCase().replaceAll("\\s+", " ").trim();
    }

    /**
     * <p>通过反射，设置某个对象的某个属性的值</p>
     *
     * @param object         需要设置值的对象
     * @param attributeName  需要设置值的属性名称
     * @param attributeValue 新的值
     * @throws NoSuchFieldException   无此字段异常
     * @throws IllegalAccessException 非法访问异常
     */
    private void reflectAttributeValue(Object object, String attributeName, String attributeValue) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(attributeName);
        field.setAccessible(true);
        field.set(object, attributeValue);
    }

    /**
     * 获取原SQL语句类型
     *
     * @param sql 原SQL语句
     * @return SQL语句类型
     */
    private int getOriginalSqlType(String sql) {
        Pattern pattern;
        pattern = Pattern.compile(SQL_TYPE_PATTERN_INSERT, Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(sql).find()) {
            return SQL_TYPE_INSERT;
        }
        pattern = Pattern.compile(SQL_TYPE_PATTERN_UPDATE, Pattern.CASE_INSENSITIVE);
        if (pattern.matcher(sql).find()) {
            return SQL_TYPE_UPDATE;
        }
        return SQL_TYPE_OTHER;
    }

}
```

以上只是定义了拦截器及其拦截点，并没有真正的将其应用，所以，还必须添加配置：

```java
package cn.tedu.csmall.product.webapi.config;

import cn.tedu.csmall.product.webapi.interceptor.SqlInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MybatisInterceptorConfiguration {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @PostConstruct
    public void addInterceptor() {
        SqlInterceptor sqlInterceptor = new SqlInterceptor();
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            sqlSessionFactory.getConfiguration().addInterceptor(sqlInterceptor);
        }
    }

}
```





















