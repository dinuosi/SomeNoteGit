## 1. 关于创建数据库

创建数据库的语法是：

```mysql
CREATE DATABASE 数据库名称;
```

当某个项目规模特别大时，应该根据数据之间的关系，尽可能的拆为多个数据库。

## 2. 关于使用数据库

使用数据库的语法是：

```mysql
USE 数据库名称;
```

提示：以上语法中的分号是可选的。

## 3. 创建数据表

创建数据表的基本语法是：

```mysql
CREATE TABLE 数据表名称 (字段设计列表) CHARSET 字符编码 COMMENT 注释;
```

注意：主流的设计中，数据表的编码强烈建议配置为`utf8mb4`（过低版本的MySQL不支持）。

在设计字段时，基本语法是：

```java
字段名 字段类型 字段约束 comment 注释
```

简单示例：

```mysql
create table ams_admin (
    id bigint unsigned auto_increment,
    username varchar(50) default null unique comment '用户名',
    password char(64) default null comment '密码（密文）',
    nickname varchar(50) default null comment '昵称',
    avatar varchar(255) default null comment '头像URL',
    phone varchar(50) default null unique comment '手机号码',
    email varchar(50) default null unique comment '电子邮箱',
    description varchar(255) default null comment '描述',
    is_enable tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    last_login_ip varchar(50) default null comment '最后登录IP地址（冗余）',
    login_count int unsigned default 0 comment '累计登录次数（冗余）',
    gmt_last_login datetime default null comment '最后登录时间（冗余）',
    gmt_create datetime default null comment '数据创建时间',
    gmt_modified datetime default null comment '数据最后修改时间',
    primary key (id)
) comment '管理员表' charset utf8mb4;
```

关于以上设计：

- 自动编号的`id`应该是`bigint unsigned`类型的，以确保id够用
  - MySQL中的整形类型：`tinyint`、`smallint`、`int`、`bigint`
- 关于`varchar`的使用，必须设置长度，建议设置为比合理的最大值更大一些的值，例如规则为“用户名最多16字符”，则`varchar`中设置的字符长度最少`20`
- 永远不要使用`not null`约束，任何你认为必须的字段，以后都可能不是必须的
- 相对固定长度的字符串类型应该使用`char`，而不是使用`varchar`
  - 并不要求每个数据的长度完全一致
  - `char`的读取效率略高于`varchar`，占用的空间可能比`varchar`少1~2个字节





```mysql
-- 数据库：mall_pms

-- 相册表：创建数据表
drop table if exists pms_album;
create table pms_album
(
    id           bigint unsigned auto_increment comment '记录id',
    name         varchar(50)      default null comment '相册名称',
    description  varchar(255)     default null comment '相册简介',
    sort         tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '相册' charset utf8mb4;

-- 相册表：为相册名称字段添加索引
create index idx_album_name on pms_album (name);

-- 图片表：创建数据表
drop table if exists pms_picture;
create table pms_picture
(
    id           bigint unsigned auto_increment comment '记录id',
    album_id     bigint unsigned   default null comment '相册id',
    url          varchar(255)      default null comment '图片url',
    description  varchar(255)      default null comment '图片简介',
    width        smallint unsigned default null comment '图片宽度，单位：px',
    height       smallint unsigned default null comment '图片高度，单位：px',
    is_cover     tinyint unsigned  default 0 comment '是否为封面图片，1=是，0=否',
    sort         tinyint unsigned  default 0 comment '自定义排序序号',
    gmt_create   datetime          default null comment '数据创建时间',
    gmt_modified datetime          default null comment '数据最后修改时间',
    primary key (id)
) comment '图片' charset utf8mb4;

-- 品牌表：创建数据表
drop table if exists pms_brand;
create table pms_brand
(
    id                     bigint unsigned auto_increment comment '记录id',
    name                   varchar(50)      default null comment '品牌名称',
    pinyin                 varchar(50)      default null comment '品牌名称的拼音',
    logo                   varchar(255)     default null comment '品牌logo的URL',
    description            varchar(255)     default null comment '品牌简介',
    keywords               varchar(255)     default null comment '关键词列表，各关键词使用英文的逗号分隔',
    sort                   tinyint unsigned default 0 comment '自定义排序序号',
    sales                  int unsigned     default 0 comment '销量（冗余）',
    product_count          int unsigned     default 0 comment '商品种类数量总和（冗余）',
    comment_count          int unsigned     default 0 comment '买家评论数量总和（冗余）',
    positive_comment_count int unsigned     default 0 comment '买家好评数量总和（冗余）',
    enable                 tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    gmt_create             datetime         default null comment '数据创建时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '品牌' charset utf8mb4;

-- 品牌表：为品牌名称字段添加索引
create index idx_brand_name on pms_brand (name);

-- 类别表：创建数据表
drop table if exists pms_category;
create table pms_category
(
    id           bigint unsigned auto_increment comment '记录id',
    name         varchar(50)      default null comment '类别名称',
    parent_id    bigint unsigned  default 0 comment '父级类别id，如果无父级，则为0',
    depth        tinyint unsigned default 1 comment '深度，最顶级类别的深度为1，次级为2，以此类推',
    keywords     varchar(255)     default null comment '关键词列表，各关键词使用英文的逗号分隔',
    sort         tinyint unsigned default 0 comment '自定义排序序号',
    icon         varchar(255)     default null comment '图标图片的URL',
    enable       tinyint unsigned default 0 comment '是否启用，1=启用，0=未启用',
    is_parent    tinyint unsigned default 0 comment '是否为父级（是否包含子级），1=是父级，0=不是父级',
    is_display   tinyint unsigned default 0 comment '是否显示在导航栏中，1=启用，0=未启用',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '类别' charset utf8mb4;

-- 类别表：为类别名称字段添加索引
create index idx_category_name on pms_category (name);

-- 品牌类别关联表：创建数据表
drop table if exists pms_brand_category;
create table pms_brand_category
(
    id           bigint unsigned auto_increment comment '记录id',
    brand_id     bigint unsigned default null comment '品牌id',
    category_id  bigint unsigned default null comment '类别id',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '品牌与类别关联' charset utf8mb4;

-- 属性表：创建数据表
drop table if exists pms_attribute;
create table pms_attribute
(
    id                 bigint unsigned auto_increment comment '记录id',
    template_id        bigint unsigned  default null comment '所属属性模版id',
    name               varchar(50)      default null comment '属性名称',
    description        varchar(255)     default null comment '简介（某些属性名称可能相同，通过简介补充描述）',
    type               tinyint unsigned default 0 comment '属性类型，1=销售属性，0=非销售属性',
    input_type         tinyint unsigned default 0 comment '输入类型，0=手动录入，1=单选，2=多选，3=单选（下拉列表），4=多选（下拉列表）',
    value_list         varchar(255)     default null comment '备选值列表',
    unit               varchar(50)      default null comment '计量单位',
    sort               tinyint unsigned default 0 comment '自定义排序序号',
    is_allow_customize tinyint unsigned default 0 comment '是否允许自定义，1=允许，0=禁止',
    gmt_create         datetime         default null comment '数据创建时间',
    gmt_modified       datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '属性' charset utf8mb4;

-- 属性模版表：创建数据表
drop table if exists pms_attribute_template;
create table pms_attribute_template
(
    id           bigint unsigned auto_increment comment '记录id',
    name         varchar(50)      default null comment '属性模版名称',
    pinyin       varchar(50)      default null comment '属性模版名称的拼音',
    keywords     varchar(255)     default null comment '关键词列表，各关键词使用英文的逗号分隔',
    sort         tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create   datetime         default null comment '数据创建时间',
    gmt_modified datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment '属性模版' charset utf8mb4;

-- 属性模版表：为属性模版名称字段添加索引
create index idx_attribute_template_name on pms_attribute_template (name);

-- 类别与属性模版关联表：创建数据表
drop table if exists pms_category_attribute_template;
create table pms_category_attribute_template
(
    id                    bigint unsigned auto_increment comment '记录id',
    category_id           bigint unsigned default null comment '类别id',
    attribute_template_id bigint unsigned default null comment '属性模版id',
    gmt_create            datetime        default null comment '数据创建时间',
    gmt_modified          datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment '类别与属性模版关联' charset utf8mb4;

-- SPU（Standard Product Unit）表：创建数据表
drop table if exists pms_spu;
create table pms_spu
(
    id                     bigint unsigned not null comment '记录id',
    name                   varchar(50)      default null comment 'SPU名称',
    type_number            varchar(50)      default null comment 'SPU编号',
    title                  varchar(255)     default null comment '标题',
    description            varchar(255)     default null comment '简介',
    list_price             decimal(10, 2)   default null comment '价格（显示在列表中）',
    stock                  int unsigned     default 0 comment '当前库存（冗余）',
    stock_threshold        int unsigned     default 0 comment '库存预警阈值（冗余）',
    unit                   varchar(50)      default null comment '计件单位',
    brand_id               bigint unsigned  default null comment '品牌id',
    brand_name             varchar(50)      default null comment '品牌名称（冗余）',
    category_id            bigint unsigned  default null comment '类别id',
    category_name          varchar(50)      default null comment '类别名称（冗余）',
    attribute_template_id  bigint unsigned  default null comment '属性模版id',
    album_id               bigint unsigned  default null comment '相册id',
    pictures               varchar(500)     default null comment '组图URLs，使用JSON数组表示',
    keywords               varchar(255)     default null comment '关键词列表，各关键词使用英文的逗号分隔',
    tags                   varchar(255)     default null comment '标签列表，各标签使用英文的逗号分隔，原则上最多3个',
    sales                  int unsigned     default 0 comment '销量（冗余）',
    comment_count          int unsigned     default 0 comment '买家评论数量总和（冗余）',
    positive_comment_count int unsigned     default 0 comment '买家好评数量总和（冗余）',
    sort                   tinyint unsigned default 0 comment '自定义排序序号',
    is_deleted             tinyint unsigned default 0 comment '是否标记为删除，1=已删除，0=未删除',
    is_published           tinyint unsigned default 0 comment '是否上架（发布），1=已上架，0=未上架（下架）',
    is_new_arrival         tinyint unsigned default 0 comment '是否新品，1=新品，0=非新品',
    is_recommend           tinyint unsigned default 0 comment '是否推荐，1=推荐，0=不推荐',
    is_checked             tinyint unsigned default 0 comment '是否已审核，1=已审核，0=未审核',
    check_user             varchar(50)      default null comment '审核人（冗余）',
    gmt_check              datetime         default null comment '审核通过时间（冗余）',
    gmt_create             datetime         default null comment '数据创建时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment 'SPU（Standard Product Unit）' charset utf8mb4;

-- SPU详情表：创建数据表
drop table if exists pms_spu_detail;
create table pms_spu_detail
(
    id           bigint unsigned auto_increment comment '记录id',
    spu_id       bigint unsigned default null comment 'SPU id',
    detail       text            default null comment 'SPU详情，应该使用HTML富文本，通常内容是若干张图片',
    gmt_create   datetime        default null comment '数据创建时间',
    gmt_modified datetime        default null comment '数据最后修改时间',
    primary key (id)
) comment 'SPU详情' charset utf8mb4;

-- SKU（Stock Keeping Unit）表：创建数据表
drop table if exists pms_sku;
create table pms_sku
(
    id                     bigint unsigned not null comment '记录id',
    spu_id                 bigint unsigned  default null comment 'SPU id',
    title                  varchar(255)     default null comment '标题',
    bar_code               varchar(255)     default null comment '条型码',
    attribute_template_id  bigint unsigned  default null comment '属性模版id',
    specifications         varchar(2500)    default null comment '全部属性，使用JSON格式表示（冗余）',
    album_id               bigint unsigned  default null comment '相册id',
    pictures               varchar(500)     default null comment '组图URLs，使用JSON格式表示',
    price                  decimal(10, 2)   default null comment '单价',
    stock                  int unsigned     default 0 comment '当前库存',
    stock_threshold        int unsigned     default 0 comment '库存预警阈值',
    sales                  int unsigned     default 0 comment '销量（冗余）',
    comment_count          int unsigned     default 0 comment '买家评论数量总和（冗余）',
    positive_comment_count int unsigned     default 0 comment '买家好评数量总和（冗余）',
    sort                   tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create             datetime         default null comment '数据创建时间',
    gmt_modified           datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment 'SKU（Stock Keeping Unit）' charset utf8mb4;

-- SKU规格参数表（存储各SKU的属性与值，即规格参数）：创建数据表
drop table if exists pms_sku_specification;
create table pms_sku_specification
(
    id              bigint unsigned auto_increment comment '记录id',
    sku_id          bigint unsigned  default null comment 'SKU id',
    attribute_id    bigint unsigned  default null comment '属性id',
    attribute_name  varchar(50)      default null comment '属性名称',
    attribute_value varchar(50)      default null comment '属性值',
    unit            varchar(10)      default null comment '自动补充的计量单位',
    sort            tinyint unsigned default 0 comment '自定义排序序号',
    gmt_create      datetime         default null comment '数据创建时间',
    gmt_modified    datetime         default null comment '数据最后修改时间',
    primary key (id)
) comment 'SKU数据' charset utf8mb4;
```









## 附：关于密码加密

在基于Maven的项目中添加以下依赖：

```xml
<!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.15</version>
</dependency>
```



```java
public class DigestTests {

    @Test
    public void digest() {
        String rawPassword = "123456" + "fdh98geljgdfskj";
        String encodedPassword = DigestUtils.md5Hex(rawPassword);
        System.out.println("原密码：" + rawPassword);
        System.out.println("加密后的密码：" + encodedPassword);
    }
}
```

