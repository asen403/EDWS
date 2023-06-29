CREATE DATABASE IF NOT EXISTS nqdj DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_general_ci;

create table user
(
    id          int primary key auto_increment comment '主键自增',
    username    varchar(255) not null comment '用户名',
    password    varchar(255) not null comment '用户密码',
    avatar      varchar(255) comment '头像',
    phone       varchar(11) comment '手机号码',
    email       varchar(50) comment '邮箱地址',
    age         int comment '年龄',
    sex         varchar(10) comment '性别',
    address     varchar(255) comment '地址',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '更新时间'
) comment '用户表';

create table project
(
    id          int primary key auto_increment comment '主键自增',
    title       varchar(255) not null comment '项目标题',
    image       varchar(255) not null comment '图片地址',
    duration    varchar(10) comment '时长',
    price       int comment '价格',
    count       int comment '消费次数',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '更新时间'
) comment '项目表';

create table physio
(
    id          int primary key auto_increment comment '主键自增',
    name        varchar(255) not null comment '理疗师名称',
    avatar      varchar(255) not null comment '理疗师头像',
    collects    varchar(10) comment '收藏数',
    likes       int comment '点赞数',
    nums        int comment '消费次数',
    create_time datetime default current_timestamp comment '创建时间',
    update_time datetime default current_timestamp on update current_timestamp comment '更新时间'
) comment '理疗师表';

create table project_physio(
    id int primary key auto_increment comment '主键',
    project_id int not null comment '项目id',
    physio_id int not null comment '理疗师id'
)comment '项目理疗师关联表';