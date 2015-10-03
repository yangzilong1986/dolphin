﻿/*
USE globalid_db;

DROP TABLE IF EXISTS USER_HISTORY;
DROP TABLE IF EXISTS AUTHENTICATION_HISTORY;
DROP TABLE IF EXISTS TOKEN_HISTORY;
DROP TABLE IF EXISTS DELETE_USER_HISTORY;
*/

CREATE DATABASE IF NOT EXISTS globalid_db;
USE globalid_db;

/* ********************************************************************************************* */
/* USER_HISTORY 테이블 시작 */
DROP TABLE IF EXISTS USER_HISTORY;
CREATE TABLE USER_HISTORY (
	SEQ BIGINT(14) NOT NULL AUTO_INCREMENT COMMENT '생성 값',
	USER_KEY VARCHAR(36) NOT NULL COMMENT 'User key',
	REASON_CODE VARCHAR(3) NOT NULL COMMENT 'Reason Code',
	CONTENT VARCHAR(48) NULL COMMENT 'content',
	REGIST_DATE DATETIME NOT NULL COMMENT 'Regist date',
	PRIMARY KEY (SEQ),
	KEY USER_HISTORY_IDX01 (USER_KEY),
	KEY USER_HISTORY_IDX02 (REASON_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'User history table';
ALTER TABLE USER_HISTORY AUTO_INCREMENT = 1000001;
/* SEQ는 7자리 1000001 부터 사용됨 */
/* USER_HISTORY 테이블 끝 */

/* authentication history 테이블 시작 */
DROP TABLE IF EXISTS AUTHENTICATION_HISTORY;
CREATE TABLE AUTHENTICATION_HISTORY (
	SEQ BIGINT(14) NOT NULL AUTO_INCREMENT COMMENT '생성 값',
	USER_KEY VARCHAR(36) NOT NULL COMMENT 'User key',
	REASON_CODE VARCHAR(3) NOT NULL COMMENT 'Reason Code',
	CONTENT VARCHAR(48) NULL COMMENT 'content',
	REGIST_DATE DATETIME NOT NULL COMMENT 'Regist date',
	PRIMARY KEY (SEQ),
	KEY AUTHENTICATION_HISTORY_IDX01 (USER_KEY),
	KEY AUTHENTICATION_HISTORY_IDX02 (REASON_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'Authentication history table';
ALTER TABLE AUTHENTICATION_HISTORY AUTO_INCREMENT = 1000001;
/* authentication history 테이블 끝 */

/* token history 테이블 시작 */
DROP TABLE IF EXISTS TOKEN_HISTORY;
CREATE TABLE TOKEN_HISTORY (
	SEQ BIGINT(14) NOT NULL AUTO_INCREMENT COMMENT '생성 값',
	OWNER VARCHAR(36) NOT NULL COMMENT 'owner key',
	REASON_CODE VARCHAR(3) NOT NULL COMMENT 'Reason Code',
	REGIST_DATE DATETIME NOT NULL COMMENT 'Regist date',
	PRIMARY KEY (SEQ),
	KEY TOKEN_HISTORY_IDX01 (OWNER),
	KEY TOKEN_HISTORY_IDX02 (REASON_CODE)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'Token history table';
ALTER TABLE TOKEN_HISTORY AUTO_INCREMENT = 1000001;
/* token history 테이블 끝 */

/* delete user history 테이블 시작 */
DROP TABLE IF EXISTS DELETE_USER_HISTORY;
CREATE TABLE DELETE_USER_HISTORY (
	SEQ BIGINT(14) NOT NULL AUTO_INCREMENT COMMENT '생성 값',
	EMAIL_ID VARCHAR(128) NOT NULL COMMENT 'Email format id',
	USER_KEY VARCHAR(36) NOT NULL COMMENT 'User key',
	FIRST_NAME VARCHAR(32) NULL COMMENT 'First name(nick name)',
	CLIENT_ID VARCHAR(36) NOT NULL COMMENT 'Client id',
	REGIST_DATE DATETIME NOT NULL COMMENT 'Regist date',
	PRIMARY KEY (SEQ),
	KEY DELETE_USER_HISTORY_IDX01 (EMAIL_ID),
	UNIQUE KEY DELETE_USER_HISTORY_IDX02 (USER_KEY),
	KEY DELETE_USER_HISTORY_IDX03 (CLIENT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'Delete user history table';
ALTER TABLE DELETE_USER_HISTORY AUTO_INCREMENT = 1000001;
/* delete user history 테이블 끝 */
/* ********************************************************************************************* */