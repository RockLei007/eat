
    alter table hera_role_authority 
        drop foreign key FKAE243466DE3FB930;

    alter table hera_role_authority 
        drop foreign key FKAE2434663FE97564;

    alter table hera_user_role 
        drop foreign key FKFE85CB3EDE3FB930;

    alter table hera_user_role 
        drop foreign key FKFE85CB3E836A7D10;
        
    alter table hera_task_schedule 
        drop foreign key FKFEHR7B3FM36A7D10;      

    drop table if exists hera_authority;
    
    create table hera_authority (
        id bigint not null COMMENT '权限编号',
        name varchar(50) not null unique COMMENT '权限缩写',
        remark varchar(60) COMMENT '权限名称',
        parent bigint not null COMMENT '父权限编号', 
        primary key (id)
    ) ENGINE=InnoDB;

    drop table if exists hera_role;
    
    create table hera_role (
        id bigint not null auto_increment COMMENT '角色编号',
        name varchar(80) not null unique COMMENT '角色名称',
        status int(1) not null default '0' COMMENT '角色状态',
        primary key (id)
    ) ENGINE=InnoDB;
    
    drop table if exists hera_role_authority;

    create table hera_role_authority (
        role_id bigint not null,
        authority_id bigint not null
    ) ENGINE=InnoDB;

    drop table if exists hera_user;
    
    create table hera_user (
        id bigint not null auto_increment  COMMENT '用户ID',
        email varchar(80)  COMMENT '电子邮件',
        login_name varchar(25) not null unique  COMMENT '登陆名',
        name varchar(60)  COMMENT '用户名称',
        password varchar(40)  COMMENT '密码',
        status int(1) not null DEFAULT '0'  COMMENT '状态', 
        org_id bigint not null  COMMENT '组织机构ID',
        create_date varchar(25)  COMMENT '创建日期',
        primary key (id)
    ) ENGINE=InnoDB;

    drop table if exists hera_user_info;
    
    create table hera_user_info (
        id bigint not null auto_increment  COMMENT '用户ID',
        last_login_date varchar(25)  COMMENT '最后登陆时间',
        address varchar(255)  COMMENT '住址',
        phone varchar(50)  COMMENT '电话',
        mobile varchar(30)  COMMENT '手机',
        qq varchar(20)  COMMENT 'QQ号',
        msn varchar(20)  COMMENT 'MSN号',
        sex int(1) not null DEFAULT '0'  COMMENT '性别',
        credentials_type bigint  COMMENT '证件类型', 
        identity varchar(50)  COMMENT '证件号码',
        diplomas bigint ,
        political bigint,
        primary key (id)
    ) ENGINE=InnoDB;
    
    drop table if exists hera_user_role;
    
    create table hera_user_role (
        user_id bigint not null,
        role_id bigint not null
    ) ENGINE=InnoDB;
    
    drop table if exists hera_operation_log;
    
    create table hera_operation_log (
	    	id bigint not null auto_increment,
				ip  varchar(20) COMMENT 'IP地址',
				user_name  varchar(30) COMMENT '登陆名',
				key_id  varchar(45) COMMENT '主键',
				key_name  varchar(100) COMMENT '主键的名称',
				datetime  varchar(25) COMMENT '时间',
				class_name  varchar(35) COMMENT '类名',
				method_name  varchar(35) COMMENT '方法名',
				description  varchar(80) COMMENT '描述',
				result  varchar(20) COMMENT '结果',
				org_id bigint COMMENT '组织机构编号',
				primary key (id)
    ) ENGINE=InnoDB;  
    
    drop table if exists hera_menu;
    
    create table hera_menu (
	    	id bigint not null auto_increment,
	    	name varchar(60) COMMENT '菜单名字',
	    	picture varchar(100) COMMENT '图片路径',
	    	path varchar(100) COMMENT '路径',
	    	serial int(2) not null default '1'  COMMENT '排列顺序',
	    	parent bigint not null  COMMENT '父菜单ID',
	    	target varchar(25)  COMMENT '显示的目标',
	    	role varchar(255)  COMMENT '角色ID',
	    	primary key (id)
    ) ENGINE=InnoDB; 
    
    drop table if exists hera_organization;
    
    create table hera_organization (
	    	id bigint not null auto_increment,
	    	name varchar(60)  COMMENT '组织机构名称',
	    	parent bigint not null  COMMENT '父组织机构',
	    	address varchar(250)  COMMENT '地址',
	    	phone varchar(30)  COMMENT '电话',
	    	fax varchar(30)  COMMENT '传真',
	    	symbol varchar(80)  COMMENT '标志',
	    	categories varchar(20)  COMMENT '分类',
	    	create_date varchar(25) COMMENT '创建时间',
	    	end_date varchar(25) COMMENT '结束时间',
	    	weixin varchar(25) COMMENT '微信号',
	    	description varchar(100) COMMENT '说明',
	    	defined1 varchar(25) COMMENT '自定义字段1',
	    	defined2 varchar(50) COMMENT '自定义字段2',
	    	defined3 varchar(100) COMMENT '自定义字段3',
	    	identity varchar(50)  COMMENT '鉴别码',
	    	status int(1)  not null default '0'  COMMENT '状态', 
	    	level int(1) not null default '0'  COMMENT '级别',
	    	primary key (id)
    ) ENGINE=InnoDB;   
    
    drop table if exists hera_task;
    
    create table hera_task(
	    	id bigint not null auto_increment,
	      class_name varchar(100) COMMENT '类的名称',
	    	name varchar(30) COMMENT '任务名字',
	    	description varchar(100) COMMENT '说明',
	    	primary key (id)
    ) ENGINE=InnoDB;
    
    drop table if exists hera_task_schedule;
    
    create table hera_task_schedule(
		  	id bigint not null auto_increment,
		  	task_id bigint not null  COMMENT '任务编号',
		  	task_type int(1) not null default '0'  COMMENT '任务类型,持久,临时',
		  	cron varchar(15)  COMMENT '任务的时间规则',
		  	on_time varchar(25)  COMMENT '启动的时间',
		  	interval_minute int(2)  COMMENT '间隔时间',
		  	count int(3)  COMMENT '需要运行的次数',
				status int(1) not null default '0'  COMMENT '运行状态',
				primary key (id)
    ) ENGINE=InnoDB;

		drop table if exists eat_table;
		
		create table eat_table(
				id bigint not null auto_increment COMMENT '编号',
				org_id bigint not null COMMENT '组织机构ID',
				categories varchar(25) COMMENT '分类',
				name varchar(80) COMMENT '包厢名称，座位名',
				type varchar(25) COMMENT '卡座、包厢、散台',
				discription text COMMENT '详细描述',
				identity varchar(20) COMMENT '验证码',
				reserve int(1) default '0' COMMENT '是否可以预订 0:是 1:否',
				islamic int(1) default '0' COMMENT '是否清真 0:是 1:否',
				state int(1) default '0' COMMENT '状态 0:正常 1:停止',
				primary key (id)
		) ENGINE=InnoDB;
		
		drop table if exists eat_table_reserve;
		  
		create table eat_table_reserve(
				id bigint not null auto_increment COMMENT '编号', 
				table_id bigint not null COMMENT '桌子包厢号',
				amount int(2) COMMENT '人数',
				datetime varchar(25) COMMENT '日期时间',
				begin_time varchar(25) COMMENT '开始时间',
				end_time varchar(25) COMMENT '结束时间',
				user_id bigint COMMENT '微信用户ID',
				org_id bigint not null COMMENT '组织机构ID',
				state int(1) default '0' COMMENT '0:未处理; 1:已确认; 2:取消; 3:结束',
				first int(1) default '0' COMMENT '第一次订餐 0:是 1:否',
				primary key (id)
		) ENGINE=InnoDB;  
		
		drop table if exists eat_customer;
		  
		create table eat_customer(
				id bigint not null auto_increment COMMENT '编号', 
				weixin_id varchar(40) COMMENT '微信ID',
				org_id  bigint not null COMMENT '组织机构ID',
				name varchar(50) COMMENT '用户名',
				phone varchar(20) COMMENT '电话',
				login_name varchar(20)  COMMENT '登陆名',
				create_date varchar(25) COMMENT '创建时间',
				black int(1) default '0' COMMENT '是否为黑名单 0:否 1:是',
				primary key (id)
		) ENGINE=InnoDB;  
		
		drop table if exists eat_reserve_attention;
		  
		create table eat_reserve_attention ( 
				org_id bigint not null COMMENT '组织机构ID',
				user_id bigint not null COMMENT '用户ID',
				user_name varchar(60) not null COMMENT '用户名称',
				state int(1) default '0' COMMENT '是否为激活状态 0：否 1：是',
				primary key (org_id, user_id)
		) ENGINE=InnoDB;  
		
		drop table if exists eat_food;
			
		create table eat_food(
				id bigint not null auto_increment COMMENT '编号',
				name varchar(80) COMMENT '名字',
				categories varchar(20) COMMENT '分类',
				image varchar(100) COMMENT '图片',
				price double(6,2) COMMENT '价格',
				discription text COMMENT '详细说明',
				org_id bigint not null COMMENT '组织机构ID',
				special varchar(100) COMMENT '特色',
				classes varchar(20) COMMENT '菜系',
				type int(0) default '0' COMMENT '菜的类型 0:按份计量 1:按称重计量',
				state int(0) default '0' COMMENT '状态 0:正常 1:停止',  
				primary key (id)
		) ENGINE=InnoDB;  
		
		drop table if exists eat_order_dishes;
		  
		create table eat_order_dishes(
				id bigint not null auto_increment COMMENT '编号',
				table_id bigint not null COMMENT '餐桌包厢ID',
				org_id bigint not null COMMENT '组织机构ID',
				food_array varchar(2000) COMMENT '点菜明细',
				money double(6,2) COMMENT  '点菜金额',
				datetime varchar(25) COMMENT '点菜时间',
				type int(1) default '0' COMMENT '点菜类型 0:现点; 1:预订;',
				reserve_id bigint COMMENT '预约ID',
				state int(1) default '0' COMMENT '状态  0:未处理; 1:已确认; 2:取消; 3:加菜; 4:完成',     
				primary key (id)
		) ENGINE=InnoDB;	
		
		drop table if exists eat_receive_message;
		
		create table eat_receive_message(
				id bigint not null auto_increment COMMENT '编号',
				datetime varchar(25) COMMENT '接收时间',
				content varchar(2000) COMMENT '接收内容',
				primary key (id)
		) ENGINE=InnoDB;
		
		drop table if exists eat_org_rule;
		
		create table eat_org_rule(
				org_id bigint not null COMMENT '组织机构编号',
				rule_id bigint not null COMMENT '规则编号',
				primary key (id)
		) ENGINE=InnoDB;		
    
    drop table if exists eat_help_message;
    
    create table eat_help_message(
				org_id bigint not null COMMENT '组织机构编号',
				content varchar(2000) COMMENT '帮助信息说明',
				primary key (org_id)  
  	) ENGINE=InnoDB;	  
    
    alter table hera_role_authority 
        add constraint FKAE243466DE3FB930 
        foreign key (role_id) 
        references hera_role (id);

    alter table hera_role_authority 
        add constraint FKAE2434663FE97564 
        foreign key (authority_id) 
        references hera_authority (id);

    alter table hera_user_role 
        add constraint FKFE85CB3EDE3FB930 
        foreign key (role_id) 
        references hera_role (id);

    alter table hera_user 
        add constraint FKFE57CB3ADE8FM9C0 
        foreign key (org_id) 
        references hera_organization (id);    
        
    alter table hera_user_role 
        add constraint FKFE85CB3E836A7D10 
        foreign key (user_id) 
        references hera_user (id);
        
    alter table hera_task_schedule 
        add constraint FKFEHR7B3FM36A7D10 
        foreign key (task_id) 
        references hera_task (id);    
