/* データベース作成 */
create database cadb;


/* ユーザ作成 */
CREATE USER causer INDENTIFIED BY 'axiz';
/* ユーザ削除 権限も同時に削除できる */
drop user causer@localhost;


/* 権限付与 */
GRANT ALL PRIVILEGES ON *.* TO causer IDENTIFIED BY 'cajava' WITH GRANT OPTION;
/* 参考
GRANT ALL PRIVILEGES ON *.* TO causer@"%" IDENTIFIED BY 'cajava' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO causer@localhost IDENTIFIED BY 'cajava' WITH GRANT OPTION;
*/

/* データ確認 */
select host,user,password from mysql.user;
show databases;

/* データベース変更 */
use cadb;


/* テーブル作成 */
/* ユーザテーブル */
create table users(
userid int primary key  auto_increment,
username varchar(12),
tel varchar(12),
pass varchar(12)
);


/* 自動採番の初期値設定 */
alter table users auto_increment=101;

/* 確認 */
show table status where name = 'users';

SET NAMES CP932;
/* users登録 */
INSERT INTO users(username,tel,pass)VALUES('鎌田','09011111111','axiz');
INSERT INTO users(username,tel,pass)VALUES('山形','09022222222','bxiz');
INSERT INTO users(username,tel,pass)VALUES('松本','09033333333','cxiz');
INSERT INTO users(username,tel,pass)VALUES('蓮見','09044444444','dxiz');
INSERT INTO users(username,tel,pass)VALUES('野田','09055555555','exiz');
INSERT INTO users(username,tel,pass)VALUES('植竹','09066666666','fxiz');
INSERT INTO users(username,tel,pass)VALUES('小柴','09077777777','gxiz');
INSERT INTO users(username,tel,pass)VALUES('神山','09088888888','hxiz');
INSERT INTO users(username,tel,pass)VALUES('鎌田','09099999999','ixiz');
INSERT INTO users(username,tel,pass)VALUES('鎌田','09000000000','jxiz');
