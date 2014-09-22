insert into hera_organization(id,name,parent,Address,phone,fax,status,level) values(1,'网站管理员',0,'','','',0,0);
insert into hera_organization(id,name,parent,Address,phone,fax,status,level) values(2,'网站用户',0,'','','',0,0);

insert into hera_user (id,email,login_name,name,password,org_id) values(1,'admin@springside.org.cn' ,'admin','Admin', '21232f297a57a5a743894a0e4a801fc3', 1);
insert into hera_user (id,email,login_name,name,password,org_id) values(2,'user@springside.org.cn'  ,'user', 'User',  'ee11cbb19052e40b07aac0ca060c23ee', 2);
insert into hera_user (id,email,login_name,name,password,org_id) values(3,'jack@springside.org.cn'  ,'user2','Jack',  '7e58d63b60197ceb55a1c487989a3720', 2);
insert into hera_user (id,email,login_name,name,password,org_id) values(4,'kate@springside.org.cn'  ,'user3','Kate',  '92877af70a45fd6a2ed7fe81e1236b78', 2);
insert into hera_user (id,email,login_name,name,password,org_id) values(5,'sawyer@springside.org.cn','user4','Sawyer','3f02ebe3d7929b091e3d8ccfde2f3bc6', 2);
insert into hera_user (id,email,login_name,name,password,org_id) values(6,'ben@springside.org.cn'   ,'user5','Ben',   '0a791842f52a0acfbb3a783378c066b8', 2);

insert into hera_user_info(id,sex) values(1,0); 
insert into hera_user_info(id,sex) values(2,0); 
insert into hera_user_info(id,sex) values(3,0); 
insert into hera_user_info(id,sex) values(4,0); 
insert into hera_user_info(id,sex) values(5,0); 
insert into hera_user_info(id,sex) values(6,0); 

insert into hera_role (ID,NAME) values(1,'管理员');
insert into hera_role (ID,NAME) values(2,'用户');

insert into hera_authority values ('1', '1', '系统管理', '0');
insert into hera_authority values ('100', 'view_user', '浏览用户', '1');
insert into hera_authority values ('101', 'change_user', '新增修改用户', '1');
insert into hera_authority values ('102', 'view_role', '浏览角色', '1');
insert into hera_authority values ('103', 'change_role', '新增修改角色', '1');
insert into hera_authority values ('104', 'view_menu', '浏览菜单', '1');
insert into hera_authority values ('105', 'change_menu', '新增修改菜单', '1');
insert into hera_authority values ('106', 'view_org', '浏览组织机构', '1');
insert into hera_authority values ('107', 'change_org', '新增修改组织机构', '1');
insert into hera_authority values ('108', 'view_log', '查看操作日志', '1');
insert into hera_authority values ('2', '2', '点菜管理', '0');
insert into hera_authority values ('201', 'view_food', '查看菜品', '2');
insert into hera_authority values ('202', 'change_food', '修改菜品', '2');
insert into hera_authority values ('203', 'change_dishes', '点菜业务操作', '2');
insert into hera_authority values ('204', 'view_table', '查看餐桌包厢', '2');
insert into hera_authority values ('205', 'change_table', '修改餐桌包厢', '2');
insert into hera_authority values ('206', 'view_receive', '查看接收的信息', '2');
insert into hera_authority values ('207', 'view_reserve', '查看订餐信息', '2');
insert into hera_authority values ('208', 'view_attention', '查看邮件通知人信息', '2');
insert into hera_authority values ('209', 'change_attention', '修改邮件通知人信息', '2');
insert into hera_authority values ('210', 'view_customer', '查看顾客信息', '2');
insert into hera_authority values ('211', 'change_customer', '修改顾客信息', '2');
insert into hera_authority values ('212', 'view_help', '查看帮助信息', '2');
insert into hera_authority values ('213', 'change_help', '修改帮助信息', '2');

insert into hera_user_role values(1,1);
insert into hera_user_role values(1,2);
insert into hera_user_role values(2,2);
insert into hera_user_role values(3,2);
insert into hera_user_role values(4,2);
insert into hera_user_role values(5,2);
insert into hera_user_role values(6,2);

insert into hera_role_authority values(1,100);
insert into hera_role_authority values(1,101);
insert into hera_role_authority values(1,102);
insert into hera_role_authority values(1,103);
insert into hera_role_authority values(1,104);
insert into hera_role_authority values(1,105);
insert into hera_role_authority values(1,106);
insert into hera_role_authority values(1,107);
insert into hera_role_authority values(1,108);
insert into hera_role_authority values(2,100);
insert into hera_role_authority values(2,102);
insert into hera_role_authority values(2,104);

insert into hera_menu values ('1', '系统管理', '', '', '1', '0', 'main', '1,2');
insert into hera_menu values ('2', '用户管理', '', '/system/user.action', '1', '1', 'main', '1');
insert into hera_menu values ('3', '角色管理', '', '/system/role.action', '1', '1', 'main', '1');
insert into hera_menu values ('4', '菜单管理', '', '/system/menu.action', '1', '1', 'main', '1');
insert into hera_menu values ('5', '组织机构管理', '', '/system/organization.action', '1', '1', 'main', '1');
insert into hera_menu values ('6', '日志管理', '', '/system/log.action', '1', '1', 'main', '1');

insert into hera_task values (1, 'com.heracles.framework.taskjob.InitTaskJob', '定时任务初始化', '将所有定时任务的状态调整为初始等待状态，系统启动2分钟后将自动执行');
insert into hera_task values (2, 'com.heracles.framework.taskjob.RunKeepOnTaskJob', '自动运行的任务', '系统启动后3分钟将启动所有自动运行任务');
insert into hera_task values (3, 'com.heracles.framework.taskjob.RunOnceOnlyTaskJob', '临时运行的任务', '系统每隔3分钟检查一次临时运行的任务');
insert into hera_task values (10, 'com.heracles.framework.taskjob.ClearLogJob', '日志清理任务', '根据约定的时间清理操作日志');
insert into hera_task values (11, 'com.heracles.framework.taskjob.ClearStopScheduleJob', '清理运行完成的临时任务', '默认清理三天前已经运行完成的临时任务');
insert into hera_task values (12, 'com.heracles.framework.taskjob.ClearDishesJob', '清理点菜单的垃圾信息', '默认清理1小时前的信息');

insert into hera_task_schedule values (1, 10, 0, '0 0 3 1 * ?', '', 0, 0, 0);
insert into hera_task_schedule values (2, 11, 0, '0 0 3 * * ?', '', 0, 0, 0);
insert into hera_task_schedule values (3, 12, 0, '0 0/5 * * * ?', '', 0, 0, 0);