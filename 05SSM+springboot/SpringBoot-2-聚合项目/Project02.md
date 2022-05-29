# 1. 类别--查询列表--数据访问层

关于查询类别的列表，需要执行的SQL语句大致是：

```mysql
select * from pms_category order by sort, id desc
```

为了封装查询列表时各类别数据，应该在`csmall-pojo`的`vo`包中创建`CategoryListItemVO`类，在类中添加最有必要性的属性：

```java
@Data
public class CategoryListItemVO implements Serializable {

    private Long id;
    private String name;
    private Long parentId;
    private Integer depth;
    private String icon;
    private Integer enable;
    private Integer display;

}
```

在`CategoryMapper.java`接口中添加抽象方法：

```
/**
 * 查询类别的列表
 *
 * @return 类别的列表，如果数据表中无数据，则返回长度为0的列表
 */
List<CategoryListItemVO> list();
```

在`CategoryMapper.xml`中配置SQL语句：

```xml
<!-- List<CategoryListItemVO> list(); -->
<select id="list" resultMap="ListResultMap">
    select
        <include refid="ListQueryFields" />
    from
        pms_category
    order by
        sort, id desc
</select>

<sql id="ListQueryFields">
    <if test="true">
        id,
        name,
        parent_id,
        depth,
        icon,
        enable,
        is_display
    </if>
</sql>

<resultMap id="ListResultMap" type="cn.tedu.csmall.pojo.vo.CategoryListItemVO">
    <id column="id" property="id" />
    <result column="name" property="name" />
    <result column="parent_id" property="parentId" />
    <result column="depth" property="depth" />
    <result column="icon" property="icon" />
    <result column="enable" property="enable" />
    <result column="is_display" property="display" />
</resultMap>
```

完成后，在`CategoryMapperTests`中测试：

```java
@Test
@Sql(scripts = {"classpath:truncate.sql", "classpath:insert_data.sql"})
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
void testList() {
    List<?> list = mapper.list();
    log.debug("查询到的数据的数量：{}", list.size());
    for (Object item : list) {
        log.debug("{}", item);
    }
}
```

# 2. 类别--查询列表--业务逻辑层

在`ICategoryService`中添加抽象方法：

```java
/**
 * 查询类别的列表
 *
 * @return 类别的列表，如果数据表中无数据，则返回长度为0的列表
 */
List<CategoryListItemVO> list();
```

在`CategoryServiceImpl`中实现以上抽象方法：

```java
@Override
public List<CategoryListItemVO> list() {
    return categoryMapper.list();
}
```

在`CategoryServiceTests`中测试：

```java
@Test
@Sql(scripts = {"classpath:truncate.sql", "classpath:insert_data.sql"})
@Sql(scripts = {"classpath:truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
void testList() {
    List<?> list = service.list();
    log.debug("查询到的数据的数量：{}", list.size());
    for (Object item : list) {
        log.debug("{}", item);
    }
}
```

# 3. 类别--查询列表--控制器层

在`CategoryController`中添加：

```java
@ApiOperationSupport(order = 40)
@ApiOperation("查询类别列表")
@GetMapping("")
public JsonResult<List<CategoryListItemVO>> list() {
    return JsonResult.ok(categoryService.list());
}
```

# 4. 类别--根据id查询数据--数据访问层

此前在“增加类别”时已经完成根据id查询的功能。

# 5. 类别--根据id查询数据--业务逻辑层

在`ICategoryService`中添加抽象方法：

```java
/**
 * 根据类别id获取类别详情
 *
 * @param id 类别id
 * @return 匹配的类别详情，如果没有匹配的数据，则返回null
 */
CategoryStandardVO getById(Long id);
```

在`CategoryServiceImpl`中实现：

```java
public CategoryStandardVO getById(Long id) {
    CategoryStandardVO queryResult = categoryMapper.getById(id);
    if (queryResult == null) {
        throw new ServiceException(NOT_FOUND, "查询类别详情失败，尝试访问的数据不存在！");
    }
    return queryResult;
}
```

# 6. 类别--根据id查询数据--控制器层

在`CategoryController`中添加：

```java
@ApiOperationSupport(order = 40)
@ApiOperation("根据id查询类别详情")
@ApiImplicitParam(name = "id", value = "类别id", required = true, paramType = "path", dataType = "long")
@GetMapping("/{id:[0-9]+}")
public JsonResult<CategoryStandardVO> getById(@PathVariable Long id) {
    return JsonResult.ok(categoryService.getById(id));
}
```

提示：原查询列表的方法的排序序号已从`40`改为更大的值。

另外，应该在以上查询返回的结果类型中各属性上添加`@ApiModelProperty`注解，以完善API文档：

```java
@Data
public class CategoryStandardVO implements Serializable {

    @ApiModelProperty(value = "数据id", position = 1)
    private Long id;

    @ApiModelProperty(value = "类别名称", position = 2)
    private String name;

    @ApiModelProperty(value = "父级类别id，如果是一级分类，则此属性值为0", position = 3)
    private Long parentId;

    @ApiModelProperty(value = "深度，最顶级类别的深度为1，次级为2，以此类推", position = 4)
    private Integer depth;

    @ApiModelProperty(value = "类别关键词", position = 5)
    private String keywords;

    @ApiModelProperty(value = "自定义排序序号", position = 6)
    private Integer sort;

    @ApiModelProperty(value = "图标的URL", position = 7)
    private String icon;

    @ApiModelProperty(value = "是否启用，1=启用，0=禁用", position = 8)
    private Integer enable;

    @ApiModelProperty(value = "是否为父级（是否包含子级），1=是父级，0=不是父级", position = 9)
    private Integer parent;

    @ApiModelProperty(value = "是否显示在导航栏，1=显示，0=不显示", position = 10)
    private Integer display;

}
```

# 7. 关于缓存

缓存数据是将数据存储在更加易于访问的位置，以前提高查询数据的效率！

通常，数据都是存储在数据库中的，并且，数据库服务器与应用程序服务器不是同一台服务器，当需要获取某个数据时，如果能将数据在应用程序服务器中另外保存一份，并从此处获取数据，就会比从数据库服务器获取数据更加高效，另外，数据库中的数据是存储在硬盘上的，如果将“另外保存”的数据存储在比硬盘访问效率更高的存储介质中，例如保存到内存中，也可以非常明显的提高读取数据的效率。

所以，常见的缓存手段是将数据库中的数据存储到应用程序服务器中（可能是存储在硬盘上，也可能是存储在内存中），或者，使用专门的缓存服务器（通常都是基于内存存储的）。

但是，一旦使用了缓存，也就是同一份数据在不同的位置存储了2份甚至多份，就会存在数据不一致的风险，则需要评估此风险！有些数据对于“准确性”要求并不严格，比如某篇文章的点赞数量，火车票在车次列表中显示的余票数量，但是，也有时需要数据是非常精准的，例如在创建订单尝试购买某趟火车的车票时，要求查询到的余票数量必须是精准的！

一般来说，如果要求某个数据必须是精准的，是不会使用缓存的，而是直接从数据库查询数据，或者，在这些场景中，及时同步缓存中的数量，使之始终与数据库中的数据保持一致！

通常，使用缓存来存储的数据具有以下特点：

- 访问频率偏高，甚至非常高
- 数据改变的频率相对较低，或对数据的准确性要求并不严格

# 8. 使用Redis缓存数据

Redis是一个主流的基于内存的NO-SQL数据存储服务。

相关下载：

- Redis 3.2.100 Windows x64 msi 下载链接：https://robinliu.3322.org:8888/download/Redis-x64-3.2.100.msi
- Redis 3.2.100 Windows x64 msi 百度网盘：https://pan.baidu.com/s/1lHPFAyazgGS-6U6str6xtg 密码:cib8
- Redis 3.2.13 Mac/Linux 
  - 下载：http://doc.canglaoshi.org/redis/redis-3.2.13.tar.gz 
  - 下载链接：https://robinliu.3322.org:8888/download/redis-3.2.13.tar.gz
  - 官网下载：https://github.com/redis/redis/archive/3.2.13.tar.gz)

- Another Redis Desktop Manager 1.5.2 Win  官网下载： https://gitee.com/qishibo/AnotherRedisDesktopManager/attach_files/958501/download/Another-Redis-Desktop-Manager.1.5.2.exe
- Another Redis Desktop Manager 1.5.1 M1 ARM64  官网下载：https://gitee.com/qishibo/AnotherRedisDesktopManager/attach_files/934337/download/Another-Redis-Desktop-Manager-M1-arm64-1.5.1.dmg
- Another Redis Desktop Manager 1.5.2 macOS  官网下载：https://gitee.com/qishibo/AnotherRedisDesktopManager/attach_files/958502/download/Another-Redis-Desktop-Manager.1.5.2.dmg

关于Redis编程，在Java中，有许多成熟的开发框架，例如Redisson、Jedis、lettuce等……Spring提供了`spring-data`的框架，用于统一了API，甚至，在Spring Boot中，还提供了`spring-boot-starter-data-redis`依赖项，集成了`spring-data`对Redis中的数据访问的各依赖项及自动配置，使得在Spring Boot中实现Redis编程更加简单！

当通过Spring系列框架实现Redis编程时，需要使用到`RedisTemplate`对象，在Spring Boot工程中，应该将创建此类型的对象，并交给Spring进行管理，后续，当需要执行Redis数据访问时，调用此对象的方法即可！

则在`csmall-product.webapi`的`config`包下创建`RedisConfiguration`类：

```java
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(
                                    RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate
                = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }

}
```

接下来，可以在测试包下创建`RedisTemplateTests`类，进行简单的测试：

```java
package cn.tedu.csmall.product.webapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    @Test
    public void testSetValue() {
        // 对于普通的字符串值，调用opsForValue()
        // 再调用set()方法实现存入数据
        // 此方法的特点与Map中的put()类似，既是新增数据的方法，也是修改数据的方法
        String key = "category";
        Serializable value = "Phone";
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key, value);
    }

}
```

存入值后，可以通过同样的“值操作器”取出值，例如：

```java
@Test
public void testGetValue() {
    // 因为值是字符串值，需要先调用opsForValue()
    // 再调用get()方法根据key获取值
    String key = "category";
    Serializable value = redisTemplate.opsForValue().get(key);
    System.out.println(value);
}
```

由于值的操作配置序列化器是JSON的序列化器，所以，通过以上方式，还可以将整个对象直接写入到Redis中并读取出来，因为序列化器会将对象转换为JSON格式的字符串，只不过，为了保证反序列化成功，还会在JSON数据中添加数据的类型。

另外，由于默认情况下并不支持Java 8中的日期时间（`LocalDateTime`、`LocalDate`、`LocalTime`），为了保证能够对这些数据类型执行正确的序列化和反序列化，需要在对应的属性上添加注解，例如：

```java
// 使用以下注解时，需要添加Jackson相关依赖
@JsonSerialize(using = LocalDateTimeSerializer.class)
@JsonDeserialize(using = LocalDateTimeDeserializer.class)
private LocalDateTime gmtCreate;
```

接下来，即可通过`RedisTemplate`的`opsForValue()`再调用`set()` / `get()`方法写入 / 读取整个对象，例如：

```java
@Test
public void testSetObjectValue() {
    String key = "category999";
    Category category = new Category();
    category.setId(999L);
    category.setName("礼品");
    category.setEnable(1);
    category.setGmtCreate(LocalDateTime.now());
    redisTemplate.opsForValue().set(key, category);
}

@Test
public void testGetObjectValue() {
    String key = "category999";
    Serializable serializable = redisTemplate.opsForValue().get(key);
    System.out.println("获取到的值：" + serializable);
    System.out.println("获取到的值的类型：" + serializable.getClass().getName());
}
```

由于Redis中的数据存在与数据库中的数据不一致的风险，则可能某些数据是无效的，需要清楚，并且，如果反复向Redis中存入大量不同Key的数据，也可能导致Redis服务器内存不足，则需要清除数据。在向Redis中存入数据时，可以指定数据的TTL（Time To Live：有效生存时间），当此生存时间结束后，对应的数据将被自动清理：

```java
@Test
public void testSetValueDuration() {
    String key = "brand";
    Serializable value = "华为";
    ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
    opsForValue.set(key, value, 15, TimeUnit.SECONDS);
}
```

也可以自行通过指令清理（删除）某些数据，例如：

```java
@Test
public void testDelete() {
    String key = "category";
    redisTemplate.delete(key);
}
```

另外，还有关于列表的数据操作，例如添加元素到Redis中的列表中：

```java
@Test
public void testSetListValue() {
    // 向Redis中存入列表数据
    // 需要调用RedisTemplate对象得到opsForList()返回对象后再操作
    List<String> categories = new ArrayList<>();
    categories.add("家电");
    categories.add("食品");
    categories.add("衣物");
    String key = "categories";
    // redisTemplate.opsForList().set(key, 0, categories.get(0));
    for (int i = 0; i < categories.size(); i++) {
        redisTemplate.opsForList().rightPush(key, categories.get(i));
    }
}
```

获取列表的长度：

```java
@Test
public void testGetListSize() {
    String key = "categories";
    Long size = redisTemplate.opsForList().size(key);
    System.out.println("size = " + size);
}
```

获取列表中的元素：

```java
@Test
public void testGetListValue() {
    // 在RedisTemplate获取的opsForList()返回的结果对象中
    // 当获取列表值时，默认是分页获取的，所以，每次获取都需要指定获取哪个区间段的值
    // 在指定区间时，start取值为0表示从头开始，end取值为-1表现末尾（直至最后一个元素）
    // 在获取列表值时，会获取包含start至包含end的元素
    // start / end均表示元素在列表中的索引，是从0开始顺序编号的
    String key = "categories";
    long start = 2;
    long end = 4;
    List<Serializable> list = redisTemplate.opsForList().range(key, start, end);
    for (Serializable serializable : list) {
        System.out.println(serializable);
    }
}
```

# 9. 在项目中缓存类别列表

由于类别列表是常用数据（例如每次打开首页都需要，且其它的页面也可能需要），并且这些数据在项目正式上线后一般不会频繁调整，是非常适合缓存的！

这种缓存数据应该是在服务刚刚启动时就需要读取数据库，并将数据写入到Redis的，在后续的访问中，都将优先从Redis中获取数据！

所以，目前需要解决的问题有：

- 从数据库中读取类别列表（已完成）
- 将从数据库中读取到的列表写入到Redis中
- 使得启动服务时就执行写入Redis的操作

先在`csmall-product-webapi`模块的根包下创建`cache`子包，并在其下创建`repo`子包，在其中先创建`ICategoryCacheRepository`接口，在接口中定义必要的抽象方法：

```java
public interface ICategoryCacheRepository {

    /**
     * 清除相关缓存
     */
    void clear();

    /**
     * 将列表数据添加到缓存
     *
     * @param categories 列表数据
     */
    void appendList(List<CategoryListItemVO> categories);

}
```

然后，在`repo`下创建`impl`子包，并在其中创建`CategoryCacheRepositoryImpl`实现：

```java
package cn.tedu.csmall.product.webapi.cache.repo.impl;

import cn.tedu.csmall.pojo.vo.CategoryListItemVO;
import cn.tedu.csmall.product.webapi.cache.repo.ICategoryCacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class CategoryCacheRepositoryImpl implements ICategoryCacheRepository {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 类别列表在Redis中的Key
     */
    public static final String KEY_LIST = "categories";

    @Override
    public void clear() {
        redisTemplate.delete(KEY_LIST);
    }

    @Override
    public void appendList(List<CategoryListItemVO> categories) {
        for (CategoryListItemVO category : categories) {
            redisTemplate.opsForList().rightPush(KEY_LIST, category);
        }
    }

}
```

接下来，应该在“类别”数据的业务逻辑层进行后续的处理，则在`CategoryServiceImpl`中添加：

```java
@Autowired
private ICategoryCacheRepository categoryCacheRepository;

/**
 * 加载类别数据的缓存
 */
private void loadCache() {
    // 1. 从数据库中读取类别列表
    List<CategoryListItemVO> list = categoryMapper.list();
    // 2. 清除缓存的类别数据
    categoryCacheRepository.clear();
    // 3. 将类别数据写入到缓存
    categoryCacheRepository.appendList(list);
}
```

提示：以上方法是私有的，因为后续可能需要复用。

为了使得以上功能是可以被外部调用的，需要在`ICategoryService`接口中定义对外的方法：

```java
/**
 * 初始化缓存
 */
void initCache();
```

并在`CategoryServiceImpl`中，直接调用上面的私有方法来实现：

```java
@Override
public void initCache() {
    loadCache();
}
```

最后，需要在服务启动时就执行以上初始化缓存的操作，此操作一般称之为“缓存预热”，则在`csmall-product-webapi`的`cache`包下创建`preload`子包，并在其下创建`CachePreLoad`类：

```java
package cn.tedu.csmall.product.webapi.cache.preload;

import cn.tedu.csmall.product.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CachePreLoad implements ApplicationRunner {

    @Autowired
    private ICategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("缓存预热，准备向缓存中写入【类别】数据……");
        categoryService.initCache();
    }

}
```

提示：当需要启动服务时就会执行某个代码片段，可以自定义组件类，实现`ApplicationRunner`接口，将需要执行的代码片段添加在重写的`run()`方法中即可。

接下来，还需要保证在后续获取数据列表时，能够从缓存中读取数据，则先在`ICategoryCacheRepository`中添加读取列表的功能：

```java
/**
 * 从缓存中读取列表数据
 *
 * @return 列表数据
 */
List<CategoryListItemVO> list();
```

然后，在`CategoryCacheRepositoryImpl`中实现：

```java
@Override
public List<CategoryListItemVO> list() {
    long start = 0;
    long end = -1;
    List<Serializable> list = redisTemplate.opsForList().range(KEY_LIST, start, end);
    List<CategoryListItemVO> categories = new ArrayList<>();
    for (Serializable serializable : list) {
        categories.add((CategoryListItemVO) serializable);
    }
    return categories;
}
```

完成后，当需要读取类别列表时，在`CategoryServiceImpl`中将调整为从缓存中读取：

```java
@Override
public List<CategoryListItemVO> list() {
    // return categoryMapper.list();
    return categoryCacheRepository.list();
}
```

重启项目，则客户端提交请求后都将从缓存中获取类别列表！

为了避免数据库中的数据发生变化，但是缓存中的并没有更新，所以，添加“重建缓存”的功能，先在`ICategoryService`中添加：

```java
/**
 * 重建缓存
 */
void rebuildCache();
```

并在`CategoryServiceImpl`中实现：

```java
@Override
public void rebuildCache() {
    // TODO 添加限制频繁重建缓存的策略
    loadCache();
}
```

并在控制器中添加处理此请求：

```java
@ApiOperationSupport(order = 50)
@ApiOperation("重建缓存")
@PostMapping("/cache/rebuild")
public JsonResult<Void> rebuildCache() {
    categoryService.rebuildCache();
    return JsonResult.ok();
}
```

重启项目，当需要手动更新缓存数据时，就可以向服务器端发送“重建缓存”的请求，则缓存的数据将更新。

但是，绝对不允许频繁的进行“重建缓存”的操作，必须添加一定的限制条件，通常会使用间隔时间进行约束，则需要解决几个问题：

- 必须在每次“重建缓存”时记录时间，作为后续的“重建缓存”时用于判断的“上次重建缓存的时间”，此时间值应该记录在Redis中（其实也可以声明全局的静态成员存储此时间），则需要在`ICategoryCacheRepository`中声明2个新的抽象方法，分别用于写入时间值和读取时间值
- 在`CategoryServiceImpl`业务实现类的`private void loadCache()`方法中，当已经写入缓存数据后，调用“写入时间值”的方法，以记录此次建立/重建缓存的时间
- 在“重建缓存”的方法中，读取记录的时间值，用于与当前时间值进行对比，如果时间差小于一定时间，则抛出异常

**ICategoryCacheRepository**

```java
/**
 * 写入“最近重建缓存”的时间
 */
void setRecentRebuildTime(Long time);

/**
 * 读取“最近重建缓存”的时间
 * @return “最近重建缓存”的时间
 */
Long getRecentRebuildTime();
```

**CategoryCacheRepositoryImpl**

```java
/**
 * 类别的“最近重建缓存时间”Redis中的Key
 */
public static final String KEY_RECENT_REBUILD_TIME = "categories:recent-rebuild-time";

@Override
public void setRecentRebuildTime(Long time) {
    redisTemplate.opsForValue().set(KEY_RECENT_REBUILD_TIME, time);
}

@Override
public Long getRecentRebuildTime() {
    Serializable serializable = redisTemplate.opsForValue().get(KEY_RECENT_REBUILD_TIME);
    if (serializable != null) {
        if (serializable instanceof Long) {
            return (Long) serializable;
        }
    }
    return null;
}
```

**CategoryServiceImpl**

```java
/**
 * 重建缓存的间隔时间，以分钟为单位
 */
public static final Integer REBUILD_INTERVAL_TIME_IN_MINUTE = 1;

@Override
public void rebuildCache() {
    // 读取“最近重建缓存”的时间
    Long recentRebuildTime = categoryCacheRepository.getRecentRebuildTime();
    if (recentRebuildTime == null) {
        recentRebuildTime = 0L;
    }
    // 与当前时间对比，判断时间差是否大于xx分钟
    long currentTimeMillis = System.currentTimeMillis();
    if (currentTimeMillis - recentRebuildTime > REBUILD_INTERVAL_TIME_IN_MINUTE * 60 * 1000) {
        // 是：两次重建缓存的间隔时间较长，允许重建缓存
        loadCache();
    } else {
        // 否：两次重建缓存的间隔时间太短，则抛出异常
        throw new ServiceException(NOT_ACCEPTABLE, "重建缓存失败，重建缓存必须间隔至少" + REBUILD_INTERVAL_TIME_IN_MINUTE + "分钟！");
    }
}

/**
 * 加载类别数据的缓存
 */
private void loadCache() {
    // 1. 从数据库中读取类别列表
    List<CategoryListItemVO> list = categoryMapper.list();
    // 2. 清除缓存的类别数据
    categoryCacheRepository.clear();
    // 3. 将类别数据写入到缓存
    categoryCacheRepository.appendList(list);
    // 4. 写入“最近重建缓存”的时间
    categoryCacheRepository.setRecentRebuildTime(System.currentTimeMillis());
}
```

# 10. 关于缓存每个“类别”数据

当缓存了“类别”列表后，很难直接通过缓存中的“列表”实现“根据id获取某个类别的详情”，为了便于实现各种情景下的数据访问，还应该再次缓存每个“类别”数据。

为了便于后续的操作，在缓存每个“类别”数据时，使用的Key必须是有规律的，并且，还应该将每个“类别”的id作为Key的一部分，才能实现“根据id获取某个类别的详情”。

暂定Key的规则是`categories:id`，例如`categories:15`。

要实现此目标，需要：

- 从数据库中可以获取所有“类别”详情（未实现，此前实现的读取`CategoryListItemVO`，此时需要一次性读出所有`CategoryStandardVO`）
- 将某个id对应的“类别”详情数据写入到缓存中
- 根据id从缓存中读取“类别”详情
- 获取所有在缓存中的“类别”数据的Key(s)
- 在“类别”的业务实现类中，将原本从数据库中读取数据改为从缓存中读取数据
- 在重建缓存时，需要先清除各个“类别”详情数据，并添加各“类别”详情数据到缓存中

关于一次性读出所有`CategoryStandardVO`，先在`CategoryMapper.java`接口添加：

```java
List<CategoryStandardVO> listDetails();
```

然后，在`CategoryMapper.xml`中配置SQL：

```java
<!-- List<CategoryStandardVO> listDetails(); -->
<select id="listDetails" resultMap="StandardResultMap">
    select
        <include refid="StandardQueryFields" />
    from
        pms_category
    order by
        sort, id desc
</select>
```

调整Key的常量：

```java
/**
     * 在Redis缓存的所有”类别“数据的Key共有的前缀
     */
    public static final String KEY_PREFIX = "csmall:category:";
    /**
     * ”类别“列表在Redis中的Key，即：csmall:category:list
     */
    public static final String KEY_LIST = KEY_PREFIX + "list";
    /**
     * 各”类别“在Redis中的Key的前缀，例如：csmall:category:item:15
     */
    public static final String KEY_PREFIX_ITEM = KEY_PREFIX + "item:";
    /**
     * 类别的“最近重建缓存时间”Redis中的Key，即：csmall:category:recent-rebuild-time
     */
    public static final String KEY_RECENT_REBUILD_TIME = KEY_PREFIX + "recent-rebuild-time";
```

然后，在`ICategoryCacheRepository`中添加抽象方法：

```java
/**
 * 将某个“类别”详情数据写入到缓存中
 *
 * @param category “类别”详情数据
 */
void setCategory(CategoryStandardVO category);

/**
 * 根据id从缓存中读取“类别”详情
 *
 * @param id 尝试读取的”类别“的id
 * @return 匹配的“类别”详情，如果没有匹配的数据，则返回null
 */
CategoryStandardVO getCategoryById(Long id);

/**
 * 获取缓存中所有”类别“数据的Key
 * @return
 */
Set<String> getKeys();
```

并在`CategoryCacheRepository`中实现：

```java
@Override
public void setCategory(CategoryStandardVO category) {
    String key = KEY_PREFIX_ITEM + category.getId();
    log.debug("处理【类别】缓存：向缓存中写入Key为【{}】的数据：{}", key, category);
    redisTemplate.opsForValue().set(key, category);
}

@Override
public CategoryStandardVO getCategoryById(Long id) {
    CategoryStandardVO category = null;
    String key = KEY_PREFIX_ITEM + id;
    Serializable serializable = redisTemplate.opsForValue().get(key);
    if (serializable != null) {
        if (serializable instanceof CategoryStandardVO) {
            category = (CategoryStandardVO) serializable;
        }
    }
    log.debug("处理【类别】缓存：从缓存中读取详情数据：id={}，结果={}", key, category);
    return category;
}

@Override
public Set<String> getKeys() {
    Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
    log.debug("处理【类别】缓存：从缓存中读取所有Key：{}", keys);
    return keys;
}

// ===== 以下是调整原实现代码 =====
@Override
public void clear() {
    Set<String> keys = getKeys();
    for (String key : keys) {
        log.debug("处理【类别】缓存：清除数据，Key={}", key);
        redisTemplate.delete(key);
    }
}
```

接下来，在`CategoryServiceImpl`中修改原有方法的实现细节：

```java
/**
 * 加载类别数据的缓存
 */
private void loadCache() {
    // 1. 从数据库中读取类别列表
    // 忽略原有代码，增加以下代码
    List<CategoryStandardVO> listDetails = categoryMapper.listDetails();
    // 2. 清除缓存的类别数据
    // 不变
    // 3. 将类别数据写入到缓存
    // 忽略原有代码，增加以下代码
    for (CategoryStandardVO category : listDetails) {
        categoryCacheRepository.setCategory(category);
    }
    // 4. 写入“最近重建缓存”的时间
    // 不变
}

@Override
public CategoryStandardVO getById(Long id) {
    // 改为通过缓存获取数据
    // CategoryStandardVO queryResult = categoryMapper.getById(id);
    CategoryStandardVO queryResult = categoryCacheRepository.getCategoryById(id);
    // 剩余代码不变
}
```

完成后，重启项目，在缓存预热也会写入每个“类别”的缓存，并且，通过ID查询时，也是从缓存中获取的数据。

# 12. 关于缓存穿透

当客户端请求某个数据时，还可以使用另一种策略：优先从缓存中获取数据，如果命中缓存，则返回缓存中的数据，如果未命中，则尝试从数据库中查询，如果仍无此数据，则返回`null`或抛出异常，如果数据库中存在此数据，则将数据写入到缓存，并返回。

以商品数据为例，这种数据其实是可以入在缓存中的，因为热门商品被访问的频率相对较高，除了库存、销量等数据以外，其它数据基本上短期内不会发生变化，则使用缓存是相对合理的！但是，这种商品数据什么时候写入到缓存？如果是刚刚创建时（将数据写入到数据库时）就同步也写入缓存，但是某商品并不是热销商品，长期存在于缓存中会形成浪费！对于这种数据，通常会采取第1次访问时再写入到缓存的机制。

所以，可以将`CategoryServiceImpl`中的策略调整为（暂时使用“类别”数据演示此做法）：

```java
@Override
public CategoryStandardVO getById(Long id) {
    // 暂定策略：优先从缓存中获取数据
    // 如果命中缓存，则返回缓存中的数据
    // 如果未命中，则尝试从数据库中查询，如果仍无此数据，则返回null或抛出异常
    // 如果数据库中存在此数据，则将数据写入到缓存，并返回
    // CategoryStandardVO queryResult = categoryMapper.getById(id);
    CategoryStandardVO queryResult = categoryCacheRepository.getCategoryById(id);
    if (queryResult == null) {
        CategoryStandardVO categoryInDB = categoryMapper.getById(id);
        if (categoryInDB == null) {
            throw new ServiceException(NOT_FOUND, "查询类别详情失败，尝试访问的数据不存在！");
        } else {
            categoryCacheRepository.setCategory(categoryInDB, 1);
            return categoryInDB;
        }
    } else {
        return queryResult;
    }
}
```

经过以上调整后，将会优先从缓存中获取数据，但是，如果使用一个不存在数据的id值尝试获取数据，就会反复查询数据库，则可能对数据库产生一定的冲击！这种现象称之为：缓存穿透。

关于缓存穿透，最直接的解决方法是：如果未命中缓存，将从数据库中查询数据，即使数据库中也没有匹配的数据，仍会向缓存中写入此id对应的空数据或可以标识为“无”意义的数据，后续，当尝试从缓存中获取数据时，应该先判断获取到的结果是不是空或“无”，再决定后续的处理过程，另外，写入的空或“无”数据应该是在一定时间范围内有限的，过了这段时间将失效！

使用这种策略解决缓存穿透时，需要：

- 添加向缓存中写入`null`数据的方法
- 在业务中，需要增加判断`hasKey()`的结果

则在`ICategoryCacheRepository`中添加：

```java
/**
 * 写入某个id对应的”类别“的空值，通常用于解决缓存穿透的问题
 *
 * @param id 尝试从缓存中获取的数据的id
 */
void setEmptyValue(Long id);

/**
 * 判断某个id对应的Key是否存在
 *
 * @param id 类别id
 * @return 此id对应的Key是否存在
 */
boolean hasKey(Long id);
```

在`CategoryCacheRepositoryImpl`中实现：

```java
@Override
public void setEmptyValue(Long id) {
    String key = KEY_PREFIX_ITEM + id;
    log.debug("处理【类别】缓存：向缓存中写入Key为【{}】的【null】数据：{}", key);
    redisTemplate.opsForValue().set(key, null, 1, TimeUnit.MINUTES);
}

@Override
public boolean hasKey(Long id) {
    String key = KEY_PREFIX_ITEM + id;
    Boolean result = redisTemplate.hasKey(key);
    log.debug("处理【类别】缓存：判断是否存在Key={}的缓存数据，结果为：{}", key, result);
    return result;
}
```

在`CategoryServiceImpl`中调整`getById()`的业务逻辑：

```java
// 根据id判断缓存中是否存在此Key
// 是：存在，则从缓存中获取数据，并判断是否为null
// -- 是：缓存中数据为null，抛出异常
// -- 否：缓存中存在有效数据，则返回
// 否：不存在，表示缓存中无此数据，则查数据库，判断是否存在此数据
// -- 是：存在，则将此数据写入缓存，并返回
// -- 否：不存在，则向缓存中写入空值，并抛出异常
```

或者：

```java
// 根据id从缓存中获取数据，并判断是否不为null
// -- 是：缓存中存在有效数据，则返回

// 判断此Key是否存在
// -- 是：Key存在，却是null，抛出异常

// 查数据库，判断是否存在此数据
// -- 是：存在，则将此数据写入缓存，并返回
// -- 否：不存在，则向缓存中写入空值，并抛出异常
```

最终实现为：

```java
@Override
public CategoryStandardVO getById(Long id) {
    // 根据id从缓存中获取数据，并判断是否不为null
    CategoryStandardVO category = categoryCacheRepository.getCategoryById(id);
    if (category != null) {
        // 是：缓存中存在有效数据，则返回
        return category;
    }

    // 判断此Key是否存在
    boolean hasKey = categoryCacheRepository.hasKey(id);
    // 是：Key存在，却是null，抛出异常
    if (hasKey) {
        throw new ServiceException(NOT_FOUND, "获取类别详情失败，尝试访问的数据不存在！");
    }

    // 查数据库，判断是否存在此数据
    CategoryStandardVO categoryInDB = categoryMapper.getById(id);
    if (categoryInDB != null) {
        // 是：存在，则将此数据写入缓存，并返回
        categoryCacheRepository.setCategory(categoryInDB);
        return categoryInDB;
    } else {
        // 否：不存在，则向缓存中写入空值，并抛出异常
        categoryCacheRepository.setEmptyValue(id);
        throw new ServiceException(NOT_FOUND, "获取类别详情失败，尝试访问的数据不存在！");
    }
}
```















