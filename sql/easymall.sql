/*
Navicat MySQL Data Transfer

Source Server         : mysql3306
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : easymall

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-09-18 18:38:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `orderitem`
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
`order_id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`product_id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' ,
`buynum`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`order_id`, `product_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
BEGIN;
INSERT INTO `orderitem` VALUES ('48588d45-3cb1-490e-8ddd-12dd64953015', '05e20c1a-0401-4c0a-82ab-6fb0f37db397', '2'), ('73febd6f-5ca2-42e1-9f65-1f95ad0d639b', '05e20c1a-0401-4c0a-82ab-6fb0f37db397', '4'), ('73febd6f-5ca2-42e1-9f65-1f95ad0d639b', '09f47493-214d-44bc-927d-6ce0bf89a057', '1'), ('73febd6f-5ca2-42e1-9f65-1f95ad0d639b', '103e5414-0da2-4fba-b92f-0ba876e08939', '3'), ('73febd6f-5ca2-42e1-9f65-1f95ad0d639b', '6746c459-b284-4256-bbc6-1df60ba4a0a2', '7'), ('7c665640-2ff7-4e5d-97b7-242f88c82073', '05e20c1a-0401-4c0a-82ab-6fb0f37db397', '1'), ('89ecbf5f-21bb-4d3a-bc54-2cce7e1ffb3b', '05e20c1a-0401-4c0a-82ab-6fb0f37db397', '2');
COMMIT;

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
`id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`money`  double NULL DEFAULT NULL ,
`receiverinfo`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`paystate`  int(11) NULL DEFAULT NULL ,
`ordertime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`user_id`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `user_id` (`user_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of orders
-- ----------------------------
BEGIN;
INSERT INTO `orders` VALUES ('48588d45-3cb1-490e-8ddd-12dd64953015', '1800', 'xx省xx市xx路xx号', '0', '2017-09-07 19:34:21', '17'), ('73febd6f-5ca2-42e1-9f65-1f95ad0d639b', '12000', 'xx省xx市xx路xx号', '1', '2017-09-14 09:48:06', '15'), ('7c665640-2ff7-4e5d-97b7-242f88c82073', '900', 'xx省xx市xx路xx号', '1', '2017-09-12 18:38:35', '15'), ('89ecbf5f-21bb-4d3a-bc54-2cce7e1ffb3b', '1800', 'xx省xx市xx路xx号', '0', '2017-09-12 19:34:10', '15');
COMMIT;

-- ----------------------------
-- Table structure for `products`
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
`id`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`price`  double NULL DEFAULT NULL ,
`category`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`imgurl`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`pnum`  int(11) NULL DEFAULT NULL ,
`description`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of products
-- ----------------------------
BEGIN;
INSERT INTO `products` VALUES ('05e20c1a-0401-4c0a-82ab-6fb0f37db397', '海滩别墅', '900', '电子数码', '/WEB-INF/upload/5/e/d/5/4/5/e/b/5f0d34dc-157f-49ba-ad39-1b28927ba6ae_1005714.jpg', '284', '面朝大海, 侧面有山, 安享晚年之首选!!!'), ('09f47493-214d-44bc-927d-6ce0bf89a057', 'iPhone7Plus 3200G 黑色手机', '1000', '电子数码', '/WEB-INF/upload/5/2/3/4/7/8/d/c/1838eaa6-6459-420f-b8e2-6ea9f43c4b5e_dfd259ab-bcc7-43f6-a9d5-62872ff5671e.jpg', '2549', '爱上爱疯, 你疯...我疯...大家一起疯....'), ('103e5414-0da2-4fba-b92f-0ba876e08939', '滑雪套装', '2000', '日用百货', '/WEB-INF/upload/4/d/2/a/2/3/1/8/b3c3fc7a-222c-49be-9491-f466553d2284_386718.jpg', '221', '滑雪必备套转, 你, 值得你拥有~~~'), ('17c3f20e-ef86-4857-9293-f29e52954a95', '打印机', '180', '电子数码', '/WEB-INF/upload/7/c/b/f/7/d/2/9/5e229aef-063f-4d0d-91df-2d4aa7167670_6f84843a-1d1e-49c7-b4ce-c035d7790171.jpg', '1508', '一个神奇的打印机...,可以打印人民币, 你敢用吗??'), ('26128d47-423b-4220-8047-544ff899db50', 'MacBook Air 13.3英寸笔记本', '12', '电子数码', '/WEB-INF/upload/2/1/0/3/f/0/3/5/2fb0b43b-4dbe-440b-899b-13c02a9f5475_22d124c9-df52-4cd4-88b3-691005f1cafe.jpg', '1233', '三坑笔记本, 从此亮瞎你的眼....'), ('2ad0d041-8c5f-4b70-a0ef-1ca2fd476dba', '战神主机', '5888', '电子数码', '/WEB-INF/upload/b/4/1/b/d/9/8/8/e41cd642-2fc2-4cb2-b20a-f8a78405eee2_e9dd0d91-40c1-4db5-a888-244e825e9ce4.jpg', '78', '主机中的战斗机. 欧耶~~~~'), ('36b9407f-746a-4956-988e-557122bc74d0', 'banana', '11', '日用百货', '/WEB-INF/upload/e/5/0/9/c/7/6/0/60746822-144c-4e98-8aaa-fca07e142a63_banana.jpg', '453', '国产大banana,好吃又好用'), ('38a4a0f0-7c33-4e78-aa9e-1a3f7f193683', '绿色健康大黄瓜', '1000', '电子数码', '/WEB-INF/upload/8/6/b/0/5/9/e/f/1d25320d-e1b2-42bc-b890-981d58391cf0_6f84843a-1d1e-49c7-b4ce-c035d7790171.jpg', '4865', '买了我的瓜, 忘了那个他/它/她...'), ('3da04a08-a570-4945-91b5-cd0d63ace7b4', '2017011002', '10000', '电子数码', '/WEB-INF/upload/9/f/f/a/1/6/d/0/49617712-4018-4c0e-9e7a-5ebc4ff79ad1_6f84843a-1d1e-49c7-b4ce-c035d7790171.jpg', '654', '一款可以打印女朋友的打印机...程序员必备!!!'), ('3f36ac54-5da0-4cd8-9991-2ee86cc348c2', '金士顿8G内存条', '800', '电子数码', '/WEB-INF/upload/2/2/b/7/f/2/f/4/06402c91-aa25-45d5-b0c3-3ac276a7cd05_244c59c6-bf0a-451b-81e6-18f8bb257e5f.jpg', '8447', '8G内存条，速度拿货, 数量有限(赠4G种子, 你懂的....)！'), ('59622587-958e-43cb-b657-49619f60713e', '防楼上噪音1000W震动反击神器', '88', '日用百货', '/WEB-INF/upload/6/5/a/4/2/9/e/8/cb68faa0-0033-4517-bff0-5fb2f1f1019a_671434fae6cd7b89a26ce25e072442a7d8330efa.jpg', '532', '晚上开启一分钟, 楼上安静10小时, 再也不怕楼上制造噪音了....'), ('6746c459-b284-4256-bbc6-1df60ba4a0a2', '后悔药', '200', '日用百货', '/WEB-INF/upload/2/6/4/a/a/5/2/3/ee6c796a-6333-4cd5-a06e-271d876aac8c_589577.jpg', '4758', '都说世上没有后悔药,原本我也深信不疑'), ('6c28bc1a-9c9b-4be3-b1cf-0068565e64e4', '兔子', '59', '家用电器', '/WEB-INF/upload/1/f/b/5/3/4/e/4/61a8cdff-52f7-4fce-bdb5-570426022082_preview.jpg', '52', '我们的征途是星辰大海!'), ('70ee3179-3e76-4a3d-bd30-55d740f022dc', '极品水母', '998', '日用百货', '/WEB-INF/upload/e/6/f/d/3/f/6/1/d2370fcb-dc8f-4405-9bf2-76e798a91567_Jellyfish.jpg', '243', '居家旅行，必备之选!!!!'), ('77feb539-a575-487b-8500-df38520f3239', '战地1 豪华版 Origin平台正版激活码', '780', '图书杂志', '/WEB-INF/upload/5/e/e/6/c/4/9/b/67c1a752-9020-4372-bcda-3375ef01c878_preview.jpg', '103', '想办法干他一炮....'), ('a0390f80-bed7-4a92-9954-5e22e64cbe17', 'neutrogena防晒乳', '100', '电子数码', '/WEB-INF/upload/5/0/6/f/4/4/b/f/40ca42aa-8298-430a-9fa9-88d6156d7b18_c987f2c1-4123-4d87-83bd-fe2fb221e272.jpg', '1349', '带你变成白富美, 走上人生癫疯~~~'), ('a08b13e9-c16a-4657-94ee-3b9bee2bd9c6', '华为荣耀8', '5000', '电子数码', '/WEB-INF/upload/4/a/d/8/8/c/4/0/236ac480-db3a-4e6b-bc7f-c379a30c2c2c_301fb535-938a-4103-a2f5-f3f9af9ba9c6.jpg', '2545', '支持国产, 从你我做起!!!'), ('a7184417-5aa2-4de0-8237-a4c0f53972a1', '三坑手机', '5000', '电子数码', '/WEB-INF/upload/3/6/c/0/7/2/1/3/741c8c70-cdd1-43a9-8cde-aa6a787129ca_738f47e2-9605-46aa-b647-fc8dca814074.jpg', '524', '三坑手机, 专业坑人三十年, 从未被超越...!!!'), ('b1f9c947-4f72-4245-b09d-8c5a8c311ae1', '地中海7日游', '5000', '日用百货', '/WEB-INF/upload/6/5/5/c/5/4/1/9/d437c381-59af-49ee-80c6-2b01e0b06105_1017530.jpg', '2345', '放松身心，寻找艳遇'), ('bf45940e-ac72-454f-b67f-83dd288d11f9', '反恐精英套装', '10000', '服装服饰', '/WEB-INF/upload/1/4/2/e/7/3/6/4/09af74da-3829-45c5-9517-380d2cc74f6a_preview.jpg', '644', '专业反恐服装，带你装x带你飞...biu~biu~biu~~'), ('c0e7b4f3-e1ad-47d6-8c0d-f1c58b820ca8', '灵魂出体出窍术', '1000', '电子数码', '/WEB-INF/upload/c/4/b/7/b/5/8/f/5adda796-66af-4c6e-a9e5-49a52a3c44a5_371cee6d-d81b-42b7-a11f-3ad36dc0e537.jpg', '42', '30天学会灵魂出窍术, 如果学不会, 请登录我们的网站, 三打溜点不坑你坑谁点卡姆'), ('c2952779-e9e0-4eda-8e0a-41a61f1afc66', '苍#null', '998', '床上用品', '/WEB-INF/upload/e/d/b/1/f/0/6/7/bcff4ee1-cc7f-4b30-a29c-017f76a21bf8_1.jpg', '874', '苍老师同款'), ('c2978733-5af8-473b-adbc-05073126164b', '宠物猫', '30000', '日用百货', '/WEB-INF/upload/3/5/5/4/c/3/a/b/943de853-0e1b-4d51-9524-991607024d3b_IMG_0928.JPG', '245', '只有一只哦，快来抢购吧'), ('c766ec19-4645-4e6b-9ddf-73a0f4aa5f6c', '脑残片', '999', '日用百货', '/WEB-INF/upload/f/e/f/8/2/e/3/c/82c1698f-38a2-4340-9df7-83fadaefff4b_howardmouth.jpg', '562', '脑残认识的福音, 脑残片雷人上市, 你...需要吗?'), ('d73ab7ed-9f78-4775-a93b-4d355b2d5fc0', '死亡海体验套餐', '4444', '日用百货', '/WEB-INF/upload/c/8/1/0/4/6/3/b/28139e28-7390-45a7-82c8-03e673486e60_Desert.jpg', '624', '体验死亡的感觉，你值得拥有!'), ('d7f7cce4-b268-41a7-9429-21fa69b64159', '爱眼滤蓝光LED背光液晶显示器', '20000', '家用电器', '/WEB-INF/upload/1/8/e/9/b/2/7/2/95e01470-8e6f-40dc-a76b-087d804bb0cf_bae0a60a-521d-48ef-bea6-0854b89d7be0.jpg', '424', '极致影院体验, 从这里开始~~~'), ('d8cb845e-37f6-4515-9fc1-dea07719ee06', '防分手神器：大砖头', '0', '日用百货', '/WEB-INF/upload/6/b/8/3/a/4/9/8/3fb77001-cd6c-4e4f-94a1-21a9c0563778_Hydrangeas.jpg', '2345', '你再说一个分手试试。。。。'), ('ff838641-feb5-42a1-b061-042b9113a95c', '烈性炸药', '5899', '电子数码', '/WEB-INF/upload/c/7/4/1/4/2/3/2/a99e691b-88d4-43a2-ac12-82ec54db123d_738f47e2-9605-46aa-b647-fc8dca814074.jpg', '435', '充电会自动引爆, 再也不用担心自杀shi不了了');
COMMIT;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`username`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`password`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`nickname`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`role`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=18

;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', '炒鸡管理员', 'admin@tedu.cn', 'admin'), ('2', '张飞', '202cb962ac59075b964b07152d234b70', '管理员', 'admin@tedu.cn', 'admin'), ('3', 'qwe', '202cb962ac59075b964b07152d234b70', 'asd', 'qwe@qwe.qwe', 'admin'), ('4', '赵云', '202cb962ac59075b964b07152d234b70', '子龙', 'zy@shuguo.com', 'admin'), ('13', 'y', '22c276a05aa7c90566ae2175bcc2a9b0', 'y', 'y', 'user'), ('15', 'wer', '22c276a05aa7c90566ae2175bcc2a9b0', 'wer', 'qwe@qwe.qwe', 'user'), ('16', '伊利丹', '202cb962ac59075b964b07152d234b70', '大恶魔', 'qwe@qwe.qwe', 'user'), ('17', '玛法里奥', '202cb962ac59075b964b07152d234b70', '泰兰德我的挚爱', 'qwe@qwe.qwe', 'user');
COMMIT;

-- ----------------------------
-- Auto increment value for `user`
-- ----------------------------
ALTER TABLE `user` AUTO_INCREMENT=18;
