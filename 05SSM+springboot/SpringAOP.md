# Spring AOP

## 1. 关于AOP

AOP指的是面向切面的编程。

在面向对象的编程中，数据的访问流程通常是：

```
客户端 ---(注册)---> 控制器层 ------> 业务逻辑层 ------> 数据访问层

客户端 ---(登录)---> 控制器层 ------> 业务逻辑层 ------> 数据访问层

客户端 ---(改密)---> 控制器层 ------> 业务逻辑层 ------> 数据访问层

客户端 ---(下单)---> 控制器层 ------> 业务逻辑层 ------> 数据访问层
```

可以发现，处理不同的请求时，需要执行的数据操作的流程是相对固定的，如果，随着项目的进一步开发，可能发现无论处理哪种请求（或若干种请求）时都需要执行相同的某个任务，例如，希望统计业务逻辑层的执行耗时，是不可能去修改每个业务方法的代码的，即便是花了大量的时间和精力去写了这些代码，后续也可以不再需要这些代码又需要删除它们，导致代码的维护难度大大增加！

为了解决此问题，就产生了面向切面的编程，它允许在数据处理过程中增加一个切面，并在切面中编写一些代码，无论是处理哪种请求（已经配置了切面的），最终都会执行这些代码。

## 2. 关于Spring AOP

AOP技术并不是Spring框架内置技术，而是一门相对独立的技术，只是Spring框架很好的支持了AOP，并且，由于Spring框架被大量项目所使用，所以，目前主流的AOP实现都是基于Spring AOP的。

## 3. 关于Spring AOP的实现

**【目标】**统计业务逻辑层的执行耗时，即统计每个业务方法的执行耗时。

首先，需要在根级项目中配置管理`spring-boot-starter-aop`的依赖项：

```xml
<!-- Spring Boot AOP -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
    <version>${spring-boot.version}</version>
</dependency>
```

并在`csmall-product-webapi`中添加此依赖项：

```xml
<!-- Spring Boot AOP -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

然后，在项目的根包下（是被组件扫描的）创建`aspect.TimeAspect`实现AOP的应用：

```java
package cn.tedu.csmall.product.webapi.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect // 使得当前类是一个切面类
@Component // 使得Spring可以创建当前类的对象并进行管理
public class TimeAspect {

    // 关于切面方法：
    // -- 访问权限：应该是公有的
    // -- 返回值类型：当使用@Around时，使用Object类型，否则，使用void
    // -- 方法名称：自定义
    // -- 参数列表：当使用@Around时，添加ProceedingJoinPoint类型的参数，否则，为空
    // 关于ProceedingJoinPoint：
    // -- 可称为“连接点”，用于调用切面连接到的目标的方法
    // -- 调用proceed()方法时，相当于执行了切面连接到的目标的方法，例如某个业务方法
    // -- 所以，调用此方法时，切不可捕获异常
    // -- 否则，其它的调用者（例如控制器）将不会知道此异常的出现，就无法向客户端响应错误的结果
    // -- 并且，一定要获取proceed()方法的返回值，它相当于切面连接到的目标的方法的返回值
    // -- proceed()方法的返回值必须作为此切面方法的返回值
    // -- 否则，相当于切面连接到的目标的方法没有返回有效结果
    // 关于execution表达式
    // -- 用于匹配需要切面连接到的目标方法
    // 关于@Around注解
    // -- 是AOP中Advice的一种：“环绕”，用于表示在连接到的目标方法之前和之后
    // -- 在AOP中还有其它Advice，例如@Before / @After / @AfterReturning / @AfterThrowing
    @Around("execution(* cn.tedu.csmall.product.service.*.*(..))")
    public Object serviceMethodTimer(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = pjp.proceed();
        Signature signature = pjp.getSignature();
        Object[] args = pjp.getArgs();

        long end = System.currentTimeMillis();
        System.out.println("方法声明：" + signature);
        System.out.println("参数值：" + Arrays.toString(args));
        System.out.println("执行耗时：" + (end - start) + "毫秒");
        return result;
    }

}
```











