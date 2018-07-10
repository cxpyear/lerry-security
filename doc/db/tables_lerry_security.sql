CREATE database if NOT EXISTS `lerry-security` default character set utf8 collate utf8_general_ci;
use `lerry-security`;

CREATE TABLE `t_sys_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) COMMENT '编号',
  `name` varchar(50) COMMENT '名称',
  `order_num` int COMMENT '排序',
  `remark` varchar(255) COMMENT '备注',
  `status` int(4) DEFAULT 0 COMMENT '状态  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限组';

-- 角色
CREATE TABLE `t_sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(100) COMMENT '角色编号',
  `name` varchar(100) COMMENT '角色名称',
  `remark` varchar(255) COMMENT '备注',
  `group_id` bigint(20) COMMENT '权限组ID',
  `status` int(4) DEFAULT 0 COMMENT '状态  -1：已删除  0：正常',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- 系统用户
CREATE TABLE `t_sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) COMMENT '密码',
  `realname` varchar(100) COMMENT '真实姓名',
  `email` varchar(100) COMMENT '邮箱',
  `mobile` varchar(100) COMMENT '手机号',
  `status` int(4) DEFAULT 1 COMMENT '状态 -1：注销 0：禁用   1：正常',
  `create_user` varchar(100) COMMENT '创建人',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_user` varchar(100) COMMENT '修改人',
  `modify_time` datetime COMMENT '修改时间',
  `last_login_time` datetime COMMENT '最后一次登陆时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';


-- 菜单
CREATE TABLE `t_sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) COMMENT '菜单编号',
  `name` varchar(50) COMMENT '菜单名称',
  `type` int COMMENT '类型   0：目录   1：菜单   2：按钮',
  `parent_code` varchar(50) COMMENT '父级编号，一级菜单为null',
  `url` varchar(200) COMMENT '组件URL',
  `method` varchar(50) COMMENT '按钮方法访问方式 GET POST PUT DELETE',
  `icon` varchar(50) COMMENT '图标',
  `status` int(4) DEFAULT 1 COMMENT '状态  -1：已删除  0隐藏，1正常',
  `secured` varchar(500) COMMENT '授权',
  `remark` varchar(255) COMMENT '备注',
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- 用户与角色对应关系
CREATE TABLE `t_sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `t_sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `menu_id` bigint COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';


-- 初始数据
INSERT INTO `t_sys_group` (`code`, `name`, `order_num`, `remark`) VALUES ('C000', '系统管理', 1, '系统管理');
INSERT INTO `t_sys_group` (`code`, `name`, `order_num`, `remark`) VALUES ('C001', 'XX集团', 2, '集团总公司');
INSERT INTO `t_sys_group` (`code`, `name`, `order_num`, `remark`) VALUES ('C001001', '上海分公司', 3, '上海分公司');

INSERT INTO `t_sys_role` (`code`, `name`, `remark`, `group_id`) VALUES ('ROLE_ADMIN', '系统管理员', '系统管理员', 1);

INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `icon`, `remark`, `order_num`) VALUES('system_manage','系统管理',0,null,null,null,'系统管理',0);

INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `icon`, `remark`, `order_num`) VALUES('role_manage','角色管理',1,'system_manage','',null,'角色管理',10);
INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `icon`, `remark`, `order_num`) VALUES('role_manage_select','查询',2,'role_manage','',null,'查询按钮',11);
INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `icon`, `remark`, `order_num`) VALUES('role_manage_add','新增',2,'role_manage','',null,'新增按钮',12);
INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `icon`, `remark`, `order_num`) VALUES('role_manage_update','修改',2,'role_manage','',null,'修改按钮',13);

INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `icon`, `remark`, `order_num`) VALUES('user_manage','用户管理',1,'system_manage','',null,'用户管理',20);
INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `method`, `icon`, `remark`, `order_num`) VALUES('user_manage_select','查询',2,'user_manage','/sys/user/**','GET',null,'查询按钮',21);
INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `method`, `icon`, `remark`, `order_num`) VALUES('user_manage_add','新增',2,'user_manage','/sys/user','POST',null,'新增按钮',22);
INSERT INTO t_sys_menu(`code`, `name`, `type`, `parent_code`, `url`, `method`, `icon`, `remark`, `order_num`) VALUES('user_manage_update','修改',2,'user_manage','/sys/user','PUT',null,'修改按钮',23);

INSERT INTO t_sys_role_menu(role_id,menu_id) SELECT 1,id FROM t_sys_menu order by order_num;

INSERT INTO t_sys_user(username,`password`,realname,`status`) VALUES('admin','{bcrypt}$2a$10$QjQVRCpL/LmBayl0VbdFSeDMozXdjxY5kVngHZtFFTyL0ssU/2oxG','系统管理员',1);

INSERT INTO t_sys_user_role(user_id,role_id) VALUES(1,1);