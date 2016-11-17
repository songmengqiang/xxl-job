
CREATE TABLE XXL_JOB_QRTZ_JOB_DETAILS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    JOB_NAME  varchar2(200) NOT NULL,
    JOB_GROUP varchar2(200) NOT NULL,
    DESCRIPTION varchar2(250) NULL,
    JOB_CLASS_NAME   varchar2(250) NOT NULL,
    IS_DURABLE varchar2(1) NOT NULL,
    IS_NONCONCURRENT varchar2(1) NOT NULL,
    IS_UPDATE_DATA varchar2(1) NOT NULL,
    REQUESTS_RECOVERY varchar2(1) NOT NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_TRIGGERS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    TRIGGER_NAME varchar2(200) NOT NULL,
    TRIGGER_GROUP varchar2(200) NOT NULL,
    JOB_NAME  varchar2(200) NOT NULL,
    JOB_GROUP varchar2(200) NOT NULL,
    DESCRIPTION varchar2(250) NULL,
    NEXT_FIRE_TIME number(13) NULL,
    PREV_FIRE_TIME number(13) NULL,
    PRIORITY INTEGER NULL,
    TRIGGER_STATE varchar2(16) NOT NULL,
    TRIGGER_TYPE varchar2(8) NOT NULL,
    START_TIME number(13) NOT NULL,
    END_TIME number(13) NULL,
    CALENDAR_NAME varchar2(200) NULL,
    MISFIRE_INSTR number(2) NULL,
    JOB_DATA BLOB NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_SIMPLE_TRIGGERS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    TRIGGER_NAME varchar2(200) NOT NULL,
    TRIGGER_GROUP varchar2(200) NOT NULL,
    REPEAT_COUNT number(7) NOT NULL,
    REPEAT_INTERVAL number(12) NOT NULL,
    TIMES_TRIGGERED number(10) NOT NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_CRON_TRIGGERS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    TRIGGER_NAME varchar2(200) NOT NULL,
    TRIGGER_GROUP varchar2(200) NOT NULL,
    CRON_EXPRESSION varchar2(200) NOT NULL,
    TIME_ZONE_ID varchar2(80),
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_SIMPROP_TRIGGERS
  (          
    SCHED_NAME varchar2(120) NOT NULL,
    TRIGGER_NAME varchar2(200) NOT NULL,
    TRIGGER_GROUP varchar2(200) NOT NULL,
    STR_PROP_1 varchar2(512) NULL,
    STR_PROP_2 varchar2(512) NULL,
    STR_PROP_3 varchar2(512) NULL,
    INT_PROP_1 INT NULL,
    INT_PROP_2 INT NULL,
    LONG_PROP_1 INT NULL,
    LONG_PROP_2 INT NULL,
    DEC_PROP_1 NUMERIC(13,4) NULL,
    DEC_PROP_2 NUMERIC(13,4) NULL,
    BOOL_PROP_1 varchar2(1) NULL,
    BOOL_PROP_2 varchar2(1) NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_BLOB_TRIGGERS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    TRIGGER_NAME varchar2(200) NOT NULL,
    TRIGGER_GROUP varchar2(200) NOT NULL,
    BLOB_DATA BLOB NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_CALENDARS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    CALENDAR_NAME  varchar2(200) NOT NULL,
    CALENDAR BLOB NOT NULL,
    PRIMARY KEY (SCHED_NAME,CALENDAR_NAME)
);
-- CREATE TABLE XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS
CREATE TABLE XXL_JOB_QRTZ_PD_TRIGGER_GRPS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    TRIGGER_GROUP  varchar2(200) NOT NULL, 
    PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP)
);

CREATE TABLE XXL_JOB_QRTZ_FIRED_TRIGGERS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    ENTRY_ID varchar2(95) NOT NULL,
    TRIGGER_NAME varchar2(200) NOT NULL,
    TRIGGER_GROUP varchar2(200) NOT NULL,
    INSTANCE_NAME varchar2(200) NOT NULL,
    FIRED_TIME number(13) NOT NULL,
    SCHED_TIME number(13) NOT NULL,
    PRIORITY INTEGER NOT NULL,
    STATE varchar2(16) NOT NULL,
    JOB_NAME varchar2(200) NULL,
    JOB_GROUP varchar2(200) NULL,
    IS_NONCONCURRENT varchar2(1) NULL,
    REQUESTS_RECOVERY varchar2(1) NULL,
    PRIMARY KEY (SCHED_NAME,ENTRY_ID)
);

CREATE TABLE XXL_JOB_QRTZ_SCHEDULER_STATE
  (
    SCHED_NAME varchar2(120) NOT NULL,
    INSTANCE_NAME varchar2(200) NOT NULL,
    LAST_CHECKIN_TIME number(13) NOT NULL,
    CHECKIN_INTERVAL number(13) NOT NULL,
    PRIMARY KEY (SCHED_NAME,INSTANCE_NAME)
);

CREATE TABLE XXL_JOB_QRTZ_LOCKS
  (
    SCHED_NAME varchar2(120) NOT NULL,
    LOCK_NAME  varchar2(40) NOT NULL, 
    PRIMARY KEY (SCHED_NAME,LOCK_NAME)
);

-- ======================================================

CREATE TABLE XXL_JOB_QRTZ_TRIGGER_INFO (
  id number(18) NOT NULL,
  job_group number(11) NOT NULL,
  job_name varchar2(255) NOT NULL,
  job_cron varchar2(128) NOT NULL,
  job_desc varchar2(255) NOT NULL,
  add_time date DEFAULT NULL,
  update_time date DEFAULT NULL,
  author varchar2(64) DEFAULT NULL,
  alarm_email varchar2(255) DEFAULT NULL,
  executor_handler varchar2(255) DEFAULT NULL,
  executor_param varchar2(255) DEFAULT NULL,
  glue_switch number(11) DEFAULT '0',
  glue_source clob,
  glue_remark varchar2(128) DEFAULT NULL,
  child_jobkey varchar2(255) DEFAULT NULL,
  PRIMARY KEY (id)
); 
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.job_group is '任务组(执行器ID)';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.job_name is '任务名';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.job_cron is '任务执行CRON';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.author is '作者';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.alarm_email is '报警邮件';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.executor_handler is '执行器任务handler';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.executor_param is '执行器任务参数';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.glue_switch is 'GLUE模式开关：0-否，1-是';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.glue_source is 'GLUE源代码';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.glue_remark is 'GLUE备注';
  comment on column XXL_JOB_QRTZ_TRIGGER_INFO.child_jobkey is '子任务Key';

create sequence SEQ_XXL_JOB_QRTZ_TRIGGER_INFO
minvalue 1
maxvalue 999999999999999999
start with 1
increment by 1
cycle;

/*
create or replace trigger TRI_XXL_JOB_QRTZ_TRIGGER_INFO
  before insert on XXL_JOB_QRTZ_TRIGGER_INFO 
  for each row
declare  
begin    
  select SEQ_XXL_JOB_QRTZ_TRIGGER_INFO.nextval into :new.ID from dual;  
end;
*/

-- ============================================

CREATE TABLE XXL_JOB_QRTZ_TRIGGER_LOG (
  id number(18) NOT NULL,
  job_group number(11) NOT NULL,
  job_name varchar2(255) NOT NULL,
  executor_address varchar2(255) DEFAULT NULL,
  executor_handler varchar2(255) DEFAULT NULL,
  executor_param varchar2(255) DEFAULT NULL,
  trigger_time date DEFAULT NULL,
  trigger_status varchar2(255) DEFAULT NULL,
  trigger_msg varchar2(2048) DEFAULT NULL,
  handle_time date DEFAULT NULL,
  handle_status varchar2(255) DEFAULT NULL,
  handle_msg varchar2(2048) DEFAULT NULL,
  PRIMARY KEY (id)
);
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.job_group is '任务组';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.job_name is '任务名';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.executor_address is '执行器地址，本次执行的地址';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.executor_handler is '执行器任务handler';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.executor_param  is 'executor_param';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.trigger_time is '调度-时间';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.trigger_status is '调度-结果';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.trigger_msg is '调度-日志';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.handle_time is '执行-时间';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.handle_status is '执行-状态';
  comment on column XXL_JOB_QRTZ_TRIGGER_LOG.handle_msg is '执行-日志';



create sequence SEQ_XXL_JOB_QRTZ_TRIGGER_LOG
minvalue 1
maxvalue 999999999999999999
start with 1
increment by 1
cycle;

/*

create or replace trigger TRI_XXL_JOB_QRTZ_TRIGGER_LOG
  before insert on XXL_JOB_QRTZ_TRIGGER_LOG 
  for each row
declare  
begin    
  select SEQ_XXL_JOB_QRTZ_TRIGGER_LOG.nextval into :new.ID from dual;  
end;

*/
-- ==================================================

CREATE TABLE XXL_JOB_QRTZ_TRIGGER_LOGGLUE (
  id number(18) NOT NULL,
  job_group number(11) NOT NULL,
  job_name varchar2(255) NOT NULL,
  glue_source clob,
  glue_remark varchar2(128) NOT NULL,
  add_time timestamp NULL,
  update_time timestamp NULL,
  PRIMARY KEY (id)
) ;

create sequence SEQ_XXL_JOB_QRTZ_TRIGGER_LOGL
minvalue 1
maxvalue 999999999999999999
start with 1
increment by 1
cycle;

/*
create or replace trigger TRI_XXL_JOB_QRTZ_TRIGGER_LOGL  
  before insert on XXL_JOB_QRTZ_TRIGGER_LOGGLUE    
  for each row
declare  
begin    
  select SEQ_XXL_JOB_QRTZ_TRIGGER_LOGL.nextval into :new.ID from dual;  
end;
*/ 

-- ==================================================
CREATE TABLE XXL_JOB_QRTZ_TRIGGER_REGISTRY (
  id number(18) NOT NULL,
  registry_group varchar2(255) NOT NULL,
  registry_key varchar2(255) NOT NULL,
  registry_value varchar2(255) NOT NULL,
  update_time date NOT NULL,
  PRIMARY KEY (id)
);

create sequence SEQ_XXL_JOB_QRTZ_TRIGGER_REGIS
minvalue 1
maxvalue 999999999999999999
start with 1
increment by 1
cycle;

/*
create or replace trigger TRI_XXL_JOB_QRTZ_TRIGGER_REGIS  
  before insert on XXL_JOB_QRTZ_TRIGGER_REGISTRY    
  for each row
declare  
begin    
  select SEQ_XXL_JOB_QRTZ_TRIGGER_REGIS.nextval into :new.ID from dual;  
end;
-- */


-- ===================================================
CREATE TABLE XXL_JOB_QRTZ_TRIGGER_GROUP (
  id number(18) NOT NULL,
  app_name varchar2(64) NOT NULL,
  title varchar2(12) NOT NULL,
  grup_order number(4) NOT NULL,
  PRIMARY KEY (id)
) ;

create sequence SEQ_XXL_JOB_QRTZ_TRIGGER_GROUP
minvalue 1
maxvalue 999999999999999999
start with 1
increment by 1
cycle;

/*
create or replace trigger TRI_XXL_JOB_QRTZ_TRIGGER_GROUP  
  before insert on XXL_JOB_QRTZ_TRIGGER_GROUP    
  for each row
declare  
begin    
  select SEQ_XXL_JOB_QRTZ_TRIGGER_GROUP.nextval into :new.ID from dual;  
end;
*/

INSERT INTO XXL_JOB_QRTZ_TRIGGER_GROUP(APP_NAME,TITLE,grup_order) VALUES ('xxl-job-executor-example', '示例执行器','1');

select * from XXL_JOB_QRTZ_TRIGGER_GROUP;

commit;

/*
    表名：XXL_JOB_QRTZ_PAUSED_TRIGGER_GRPS  XXL_JOB_QRTZ_PD_TRIGGER_GRPS
    列明：XXL_JOB_QRTZ_TRIGGER_GROUP.order 换成 grup_order

    varchar => varchar2
    datetime => date

    序列 


    触发器


*/

