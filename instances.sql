use echarge;

#-- user --#
insert into user (uid, wx_id, name) values (1, '123', 'abc');
insert into user (uid, wx_id, name) values (2, '456', 'def');
insert into user (uid, wx_id, name) values (3, '789', 'ghi');
insert into user (uid, wx_id, name) values (4, '000', 'jkl');
insert into user (uid, wx_id, name) values (5, '111', 'mno');
insert into user (uid, wx_id, name) values (6, '222', 'pqr');
insert into user (uid, wx_id, name) values (7, '333', 'stu');
insert into user (uid, wx_id, name) values (8, '444', 'vwx');
insert into user (uid, wx_id, name) values (9, '555', 'aaa');
insert into user (uid, wx_id, name) values (10, '666', 'bbb');

#-- commodity --#
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('白色短袖连衣裙荷叶边裙摆宽松韩版休闲纯白清爽优雅连衣裙', 0, 1, 199, '裙子;休闲', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09a.png;https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-09b.png', '超值裙裙！', '2022-02-15 01:02:03', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('纯色纯棉休闲圆领短袖T恤纯白亲肤厚柔软细腻面料纯白短袖套头T恤', 0, 2, 299, 'T恤', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-08b.png', '爆款T恤', '2022-03-15 04:05:06', 1);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('运动连帽拉链卫衣休闲开衫长袖多色运动细绒面料运动上衣', 0, 3, 399, '卫衣', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-17a.png;https://cdn-we-retail.ym.tencent.com/tsr/goods/nz-17a1.png', '帅气卫衣~', '2022-02-01 07:08:09', 2);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('腾讯极光盒子4智能网络电视机顶盒6K千兆网络机顶盒4K高分辨率', 0, 4, 599, '机顶盒', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/dz-3a.png', '极致高清', '2022-04-15 01:01:01', 3);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('带帽午休毯虎年款多功能加厚加大加绒简约多功能午休毯连帽披肩', 0, 5, 99, '披肩', 'https://cdn-we-retail.ym.tencent.com/tsr/goods/muy-3a.png', '休闲百搭', '2022-01-15 02:02:02', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('玉泉求软工课本', 1, 6, 50, '教材;玉泉', '', '最好有笔记！', '2022-04-25 11:12:13', 0);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('紫金港蓝田求取外卖', 1, 7, 3, '外卖;紫金港', '', '18点前送至蓝田，细节私聊', '2022-05-01 17:02:06', 1);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('华家池帮领快递', 1, 8, 5, '快递;华家池', '', '明天前送至×宿舍', '2022-04-29 13:12:28', 2);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('西溪求借书', 1, 9, 10, '借书;西溪', '', '西溪图书馆借一本《三体》，明天前送至×宿舍', '2022-03-07 19:52:43', 3);
insert into commodity (title, type, pub_id, price, tags, figure_urls, description, release_time, state) 
values ('之江求篮球', 1, 10, 60, '篮球;之江', '', '有意者私聊', '2022-04-08 15:22:13', 0);