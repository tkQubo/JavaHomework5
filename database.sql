/* �f�[�^�x�[�X�쐬 */
create database cadb;


/* ���[�U�쐬 */
CREATE USER causer INDENTIFIED BY 'axiz';
/* ���[�U�폜 �����������ɍ폜�ł��� */
drop user causer@localhost;


/* �����t�^ */
GRANT ALL PRIVILEGES ON *.* TO causer IDENTIFIED BY 'cajava' WITH GRANT OPTION;
/* �Q�l
GRANT ALL PRIVILEGES ON *.* TO causer@"%" IDENTIFIED BY 'cajava' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO causer@localhost IDENTIFIED BY 'cajava' WITH GRANT OPTION;
*/

/* �f�[�^�m�F */
select host,user,password from mysql.user;
show databases;

/* �f�[�^�x�[�X�ύX */
use cadb;


/* �e�[�u���쐬 */
/* ���[�U�e�[�u�� */
create table users(
userid int primary key  auto_increment,
username varchar(12),
tel varchar(12),
pass varchar(12)
);


/* �����̔Ԃ̏����l�ݒ� */
alter table users auto_increment=101;

/* �m�F */
show table status where name = 'users';

SET NAMES CP932;
/* users�o�^ */
INSERT INTO users(username,tel,pass)VALUES('���c','09011111111','axiz');
INSERT INTO users(username,tel,pass)VALUES('�R�`','09022222222','bxiz');
INSERT INTO users(username,tel,pass)VALUES('���{','09033333333','cxiz');
INSERT INTO users(username,tel,pass)VALUES('�@��','09044444444','dxiz');
INSERT INTO users(username,tel,pass)VALUES('��c','09055555555','exiz');
INSERT INTO users(username,tel,pass)VALUES('�A�|','09066666666','fxiz');
INSERT INTO users(username,tel,pass)VALUES('����','09077777777','gxiz');
INSERT INTO users(username,tel,pass)VALUES('�_�R','09088888888','hxiz');
INSERT INTO users(username,tel,pass)VALUES('���c','09099999999','ixiz');
INSERT INTO users(username,tel,pass)VALUES('���c','09000000000','jxiz');
