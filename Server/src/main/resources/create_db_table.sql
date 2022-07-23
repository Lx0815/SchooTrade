# CREATE DATABASE school_trade CHARACTER SET utf8mb4;
#role_id        INT         default 20  not null comment '账号角色，指向 enums.pk_id',
# USE school_trade;



/*=====================================================
  description : 购物车详情表，存储了每一种商品的数量及总金额等信息
  =====================================================*/
DROP TABLE IF EXISTS cart_detail;
CREATE TABLE `cart_detail`  (
                                `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                `goods_id` INT NOT NULL COMMENT '商品 id',
                                `goods_count` INT NOT NULL COMMENT '商品数量',
                                `goods_total_price` DECIMAL(10, 2) NOT NULL COMMENT '商品总价格',
                                `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 枚举表，存储了系统中所需要使用的一些常量。
                    根据 id 的取值范围进行区分：

                    10-19 为user_detail状态
                    20-29为user_的role_id
                    30-39为goods状态
                    40-49为order状态
                    50-149为goods.type
                    150-249为tags
  =====================================================*/
DROP TABLE IF EXISTS enums;
CREATE TABLE `enums`  (
                          `pk_id` INT NOT NULL COMMENT 'id，10-19 为user状态，20-29为user的role，30-39为goods状态，40-49为order状态，50-149为goods.type，150-249为tags',
                          `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '名称',
                          `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '描述',
                          `create_time` DATETIME NOT NULL COMMENT '创建时间',
                          `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                          PRIMARY KEY (`pk_id`)
);



/*=====================================================
  description : enums 表的初始化数据
  =====================================================*/
INSERT INTO enums (pk_id, name, description, create_time, update_time)
VALUES (10, '正常', '用户状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (11, '已封禁', '用户状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    /*======================================================================*/
       (20, '普通用户', '用户角色', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (21, '管理员用户', '用户角色', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    /*======================================================================*/
       (30, '已上架', '商品状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (31, '已下架', '商品状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    /*======================================================================*/
       (40, '未支付', '订单状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (41, '未收货', '订单状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (42, '已收货', '订单状态', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    /*======================================================================*/
       (50, '饰品', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (51, '数码产品', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (52, '箱包', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (53, '零食', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (54, '服饰', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (55, '鞋类', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (56, '个护清洁', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (57, '美妆', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (58, '特产', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (59, '医药保健', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (60, '图书', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (61, '安装维修', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (62, '学习资料', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (63, '其他', '商品类型', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    /*======================================================================*/
       (150, '99新', '商品标签', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (151, '9成新', '商品标签', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
       (152, '8成新', '商品标签', '2022-07-17 12:00:00', '2022-07-17 12:00:00');



/*=====================================================
  description : 商品表，存储用户发布的商品信息
  =====================================================*/
DROP TABLE IF EXISTS goods;
CREATE TABLE `goods`  (
                          `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                          `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '商品名称',
                          `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '商品描述',
                          `price` DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品价格',
                          `seller` INT NOT NULL COMMENT '指向 user.pk_id ',
                          `transaction_address` INT NOT NULL COMMENT '指向 transaction_addr.pk_id',
                          `status` INT NOT NULL COMMENT '指向 enums.pk_id',
                          `sell_time` DATETIME NULL DEFAULT NULL COMMENT '售出时间',
                          `goods_count` INT NOT NULL DEFAULT 1 COMMENT '商品库存',
                          `create_time` DATETIME NOT NULL COMMENT '创建时间',
                          `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                          PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 商品图片表，存储用户发布的商品图片
  =====================================================*/
DROP TABLE IF EXISTS goods_images;
CREATE TABLE `goods_images`  (
                                 `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                 `goods_id` INT NOT NULL COMMENT '指向 goods.pk_id',
                                 `image_path` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '图片路径',
                                 `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                 PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 商品视频表，存储用户发布的商品视频
  =====================================================*/
DROP TABLE IF EXISTS goods_video;
CREATE TABLE `goods_video`  (
                                `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                `goods_id` INT NOT NULL COMMENT '指向 goods.pk_id',
                                `video_path` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '视频路径',
                                `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 订单表，存储一笔交易的订单概况
  =====================================================*/
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
                          `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                          `buyer` INT NOT NULL COMMENT '买家，指向 user.pk_id',
                          `order_status` INT NOT NULL DEFAULT 40 COMMENT '订单状态，指向 enums',
                          `order_detail_count` INT NOT NULL DEFAULT 0 COMMENT '订单详情的数量',
                          `order_total_price` DECIMAL(10, 2) NOT NULL COMMENT '订单总金额',
                          `pay_time` DATETIME NULL DEFAULT NULL COMMENT '付款时间',
                          `finish_time` DATETIME NULL DEFAULT NULL COMMENT '交易完成时间',
                          `create_time` DATETIME NOT NULL COMMENT '创建时间',
                          `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                          PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 订单详情表，存储了订单交易商品的详细信息
  =====================================================*/
DROP TABLE IF EXISTS order_detail;
CREATE TABLE `order_detail`  (
                                 `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                 `bill_num` CHAR(32) NOT NULL DEFAULT '' COMMENT '订单编号',
                                 `goods_id` INT NOT NULL COMMENT '商品 id',
                                 `goods_count` INT NOT NULL DEFAULT 0 COMMENT '商品数量',
                                 `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                 PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 购物车表，存储了购物车中商品种类的数量
  =====================================================*/
DROP TABLE IF EXISTS shopping_cart;
CREATE TABLE `shopping_cart`  (
                                  `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                  `cart_detail_count` INT NOT NULL DEFAULT 0 COMMENT '购物车项的数量',
                                  `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                  `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                  PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 交易地址表，存储用户常用交易地址
  =====================================================*/
DROP TABLE IF EXISTS transaction_address;
CREATE TABLE `transaction_address`  (
                                        `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                        `address_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '交易地址',
                                        `user_id` INT NOT NULL COMMENT '地址所属的用户 id',
                                        `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                        `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                        PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 用户基本信息表，即暴露在外，其余用户可以直接看见的
  =====================================================*/
DROP TABLE IF EXISTS user;
CREATE TABLE `user`  (
                         `pk_id` INT AUTO_INCREMENT COMMENT '用户 id，由 java 工具类 主键 生成',
                         `headshot` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '头像图片所在路径',
                         `nick_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '昵称',
                         `gender` TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '性别，1 为 男，0 为 女。默认为 0',
                         `role_id` INT NOT NULL DEFAULT 20 COMMENT '账号角色，指向 enums.pk_id',
                         `create_time` DATETIME NOT NULL COMMENT '账号创建时间',
                         `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次修改时间',
                         PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;


/*=====================================================
  description : 用户基本信息表数据初始化
  =====================================================*/
INSERT INTO user (headshot, nick_name, gender, role_id, create_time, update_time)
VALUES
    ('', '李华', 1, 21, '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    ('', '张三', 1, 20, '2022-07-17 12:00:00', '2022-07-17 12:00:00');


/*=====================================================
  description : 用户详细信息表，只有自己和管理员可见
  =====================================================*/
DROP TABLE IF EXISTS user_detail;
CREATE TABLE `user_detail`  (
                                `pk_id` INT AUTO_INCREMENT COMMENT '主键 id，和 user.pk_id 完全相同',
                                `school_card_id` CHAR(10) NOT NULL COMMENT '校园卡 id，即登录的账号',
                                `real_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '真实姓名',
                                `password` CHAR(32) NOT NULL COMMENT '密码，采用 sha256 + MD5 加密',
                                `status` INT NOT NULL DEFAULT 10 COMMENT '账号状态。指向enums，10 表示正常，11表示已封禁，',
                                `grade` year NOT NULL COMMENT '所在年级，单位为 级',
                                `major_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '专业名称',
                                `class_num` CHAR(2) NOT NULL DEFAULT 00 COMMENT '班级序号',
                                `create_time` DATETIME NOT NULL COMMENT '账号创建时间',
                                `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                PRIMARY KEY (`pk_id`),
                                UNIQUE INDEX `school_card_id_unique`(`school_card_id`) USING BTREE COMMENT '校园卡卡号的唯一索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 用户详细信息表数据初始化
  =====================================================*/
INSERT INTO user_detail (school_card_id, real_name, password, status, grade, major_name, class_num, create_time, update_time)
VALUES
    (7210764207, '李华', 123456, 10, '2021', '软件工程', '2', '2022-07-17 12:00:00', '2022-07-17 12:00:00'),
    (7210764208, '张三', 123456, 10, '2021', '软件工程', '2', '2022-07-17 12:00:00', '2022-07-17 12:00:00');


/*=====================================================
  description : 操作日志概况表，记录了此次操作的大致描述，以及操作得 service 和 user 相关信息
  =====================================================*/
DROP TABLE IF EXISTS user_operate_log;
CREATE TABLE `user_operate_log`  (
                                     `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                     `user_id` INT NOT NULL COMMENT '操作用户的 id',
                                     `service_name` VARCHAR(255) NOT NULL COMMENT '此次操作的service方法的全限定名',
                                     `description` VARCHAR(255) NOT NULL COMMENT '操作描述',
                                     `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                     `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                     PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 操作日志详情表，记录了每一次操作所在的数据库，表，字段，字段类型，操作前后值得变化等
  =====================================================*/
DROP TABLE IF EXISTS user_operate_log_detail;
CREATE TABLE `user_operate_log_detail`  (
                                            `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                            `database_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '操作的数据库名',
                                            `table_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '操作的表名',
                                            `field_name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '操作的字段名',
                                            `old_value` VARCHAR(255) NULL DEFAULT NULL COMMENT '操作前的值',
                                            `new_value` VARCHAR(255) NULL DEFAULT NULL COMMENT '操作后的值',
                                            `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '此次操作描述',
                                            `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                            `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                            PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 商品表和标签表的中间表
  =====================================================*/
DROP TABLE IF EXISTS goods_tags;
CREATE TABLE `goods_tags`  (
                               `pk_id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
                               `goods_id` INT NOT NULL COMMENT '商品 id',
                               `tags_id` INT NOT NULL COMMENT '指向 enums.id 的 150-249',
                               PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4;



/*=====================================================
  description : 用户发布的求购商品信息
  =====================================================*/
DROP TABLE IF EXISTS goods_to_buy;
CREATE TABLE `goods_to_buy`  (
                                 `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                 `name` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '求购商品名称',
                                 `description` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '求购商品描述',
                                 `price` DECIMAL(10, 2) NOT NULL DEFAULT 0.00 COMMENT '求购商品预期价格',
                                 `type` INT NOT NULL COMMENT '求购商品类别，指向 enums.pk_id 的50-149',
                                 `count` INT NOT NULL DEFAULT 1 COMMENT '求购商品数量',
                                 `status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '信息是否被删除，1表示被删除，0表示未被删除',
                                 `transaction_address` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '期望的交易地址',
                                 `user_id` INT NOT NULL COMMENT '发布求购信息的用户 id',
                                 `image` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '发布求购商品的示例图',
                                 `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                 `update_time` DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次修改时间',
                                 PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户发布的求购商品信息，';



/*=====================================================
  description : 求购商品信息下的留言表
  =====================================================*/
DROP TABLE IF EXISTS goods_to_buy_remark;
CREATE TABLE `goods_to_buy_remark`  (
                                        `pk_id` INT AUTO_INCREMENT COMMENT '主键',
                                        `content` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '留言内容',
                                        `user_id` INT NOT NULL COMMENT '留言用户',
                                        `goods_to_buy_id` INT NOT NULL COMMENT '求购信息的 id',
                                        `status` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '1 表示已删除， 0表示正常',
                                        PRIMARY KEY (`pk_id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '求购商品信息下的留言表';