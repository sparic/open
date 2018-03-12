<!-- 增加权限数据 -->
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/employee","查询员工列表","employee:query");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/employee","更新员工信息","employee:update");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/employee/*","禁用员工信息","employee:delete");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/train","查询课程列表","train:query");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/train","新增课程信息","train:insert");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/train","更新课程信息","train:update");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/train/sendMail","发送培训邮件接口","train:send");
insert into D_PERMISSION(URL,DESCRIPTION,PATTERN) values ("admin/employee/export","导出员工信息","employee:export");

<!-- 增加权限的关系表数据 -->
insert into D_ROLE_PERMISSION(RID,PID) values (1,62);
insert into D_ROLE_PERMISSION(RID,PID) values (1,63);
insert into D_ROLE_PERMISSION(RID,PID) values (1,64);
insert into D_ROLE_PERMISSION(RID,PID) values (1,65);
insert into D_ROLE_PERMISSION(RID,PID) values (1,66);
insert into D_ROLE_PERMISSION(RID,PID) values (1,67);
insert into D_ROLE_PERMISSION(RID,PID) values (1,68);
insert into D_ROLE_PERMISSION(RID,PID) values (1,69);

<!-- 更新用户表 -->
ALTER TABLE D_USER ADD `BIZ_ID` varchar(255) DEFAULT '' COMMENT '业务主键(时间戳md5)';
ALTER TABLE D_USER ADD `SALES_MAN` varchar(255) DEFAULT NULL COMMENT '销售负责人';
ALTER TABLE D_USER ADD `TYPE` tinyint(4) DEFAULT NULL COMMENT '类别(1-诺亚，2-人形)';
ALTER TABLE D_USER ADD `COMPANY` varchar(50) DEFAULT NULL COMMENT '公司名称',