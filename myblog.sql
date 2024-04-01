/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 11/08/2023 12:04:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `content` varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(510) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (15, 'chen', '阿里云开源通义千问70亿参数模型，免费可商用；韩国学会称LK-99不是常温超导体；Meta开源文本音乐生成AI工具|极客头条', '资讯', '2023-08-06 10:11:38', '<h3>国内要闻</h3><h4>阿里云开源通义千问 70 亿参数模型，免费可商用</h4><div>通义千问开源！8月3日，阿里云开源通义千问 70 亿参数模型，包括通用模型 Qwen-7B 和对话模型 Qwen-7B-Chat，两款模型均已上线魔搭社区，开源、免费、可商用。此举让阿里云成为国内首个加入大模型开源行列的大型科技企业。</div><h4>消息称“腾讯混元大模型”开始应用内测，多个业务线接入测试</h4><div>腾讯自研的“腾讯混元大模型”已经进入应用内测阶段，多位腾讯员工表示，近期收到了内部邮件邀请，可以通过网页或者小程序体验混元大模型。据了解，腾讯内部多个业务已经接入混元大模型进行测试。报道称，混元助手项目组的一号位张正友是腾讯史上最高职级拥有者，早年曾在微软研究院就职，带领视觉团队从事大量学术研究，贡献颇丰。张正友于 2018 年加入腾讯，创建了腾讯机器人实验室 Robotics X，致力推进人机协作的下一代机器人研究。2021 年 1 月 8 日，腾讯宣布张正友成为腾讯首位 17 级研究员 / 杰出科学家。（36氪）<br></div><img src=\"http://127.0.0.1:6660/api/file/blog/2023701011331783.jpg\">', 'http://127.0.0.1:6660/api/file/blog/2023701011331783.jpg', '通义千问开源！8月3日，阿里云开源通义千问 70 亿参数模型，包括通用模型 Qwen-7B 和对话模型 Qwen-7B-Chat，两款模型均已上线魔搭社区，开源、免费、可商用。此举让阿里云成为国内首个加入大模型开源行列的大型科技企业。');
INSERT INTO `blog` VALUES (16, 'chen', '美团百度陆续启动 2024 届校招；“邪恶版 ChatGPT ”降临，为黑客而生；英伟达特供芯片暴涨到 50 万元每片|极客头条', '资讯', '2023-08-06 16:45:36', '<h3>国内要闻</h3><h4>美团百度打头阵，大厂陆续启动 2024 届校招</h4><div>7 月 31 日，美团启动 2024 届校园招聘。此次招聘面向毕业时间在 2023 年 11 月至 2024 年 10 月的海内外院校应届毕业生，预计招募 6000 人，覆盖技术、产品、商业分析、运营、金融、供应链、职能、市场营销、设计、销售、客服和支持等 10 大类职位、100 余种岗位。百度也于本月启动了 2024 届校招，岗位覆盖技术、产品、政企解决方案和服务类、专业服务和管理支持类四大类别。目前启动校招的互联网公司还有小红书、新浪及微博、网易游戏雷火、米哈游、大疆等，但未公布具体招聘规模。（界面新闻）<br></div><h4>阿里达摩院推出 AI 框架 HQTrack，可实现视频高质量跟踪</h4><div>据 GitHub 页面显示，阿里达摩院近日宣布与大连理工大学合作，推出一款名为 HQTrack 的 AI 框架。该框架主要由视频多目标分割器（VMOS）和掩模优化器（MR）组成，旨在实现对视频中任何目标的高质量跟踪。研究团队表示，HQTrack 基于开源框架 DeAOT、HQ-SAM，在此之上进行了一定改进，实现了在视频中分割各目标的可能性。研究团队表示，该 AI 框架可以应用于人脸识别、智能驾驶、监控追踪等领域，并有望在未来商业应用中落地。（品玩）</div><h4>华为最早今年重启 5G 移动芯片生产</h4><div>近期，有消息透露，华为正在与中国最大的芯片制造商中芯国际合作开发一款支持 5G 的芯片，该芯片可在几个月内投入量产。华为计划重新启动芯片的更多细节尚不清楚，但几乎可以肯定它正在利用 Chiplet 技术。（日经亚洲）</div><img src=\"http://127.0.0.1:6660/api/file/blog/20237016452352615.png\">', 'http://127.0.0.1:6660/api/file/blog/20237016452352615.png', '7 月 31 日，美团启动 2024 届校园招聘。此次招聘面向毕业时间在 2023 年 11 月至 2024 年 10 月的海内外院校应届毕业生，预计招募 6000 人，覆盖技术、产品、商业分析、运营、金融、供应链、职能、市场营销、设计、销售、客服和支持等 10 大类职位、100 余种岗位。百度也于本月启动了 2024 届校招，岗位覆盖技术、产品、政企解决方案和服务类、专业服务和管理支持类四大类别。目前启动校招的互联网公司还有小红书、新浪及微博、网易游戏雷火、米哈游、大疆等，但未公布具体招聘规模。（界面新闻）<br>');
INSERT INTO `blog` VALUES (19, 'chen', 'Windows “上云”后，Linux 有望“捡漏”成最大赢家？', '资讯', '2023-08-10 20:45:22', '<div>今年，凭借 OpenAI 市值飞升的微软，无疑是 AI 行业的领军者。然而微软 2023 年第四财季财报，却揭示了其辉煌背后的危机：卖不动的 Windows 和硬件设备、“道阻且难”的 AI 商业化之路。<br><br>正如 CEO 萨提亚 · 纳德拉所说，当前 AI 仍是微软加码的重点，但实际上对于 Windows，微软也一直有个明确的长期发展目标：将 Windows 迁移到云端。<br></div><h4>在云端完整体验 Windows 10/Windows 11</h4><div>将 Windows 迁移到云端”这项长期计划的曝光，源自于一份微软内部文件，是近期与美国联邦贸易委员会（FTC）在进行 Xbox 争夺战时披露的。<br><br>在这份文件中的“现代生活战略和优先级”中，微软将“Windows 11 迁移到云端”的战略视为一个长期机会：</div><img src=\"http://localhost:6660/api/file/blog/20237420452269836.png\"><div>其中提到的 Windows 365，是微软在 Microsoft Inspire 2021 合作伙伴大会上正式发布的新云服务，允许企业在云端体验 Windows 10 或 Windows 11 系统。<br><br>“我们以 Windows 365 创造出了一个全新的类别：Cloud PC。”当时，萨提亚 · 纳德拉对于 Windows 365 的解释是：“如同以 SaaS 方式将应用程序带到云端一样，我们现在把操作系统也带到了云上，这将为组织提供一种更加灵活、安全的工作方式，让员工无论置身何地，都能保持连接，并更好地释放生产力。”<br><br>简单来说，Windows 365 并不是一款可以在本地安装的传统桌面操作系统，但它可在任何现代 Web 浏览器上运行，并允许用户从各种设备访问 Cloud PC。值得一提的是，这个“设备”不仅可以是 Windows 设备，还可以是 iPad、Android 手机等几乎任何可以连接到 Internet 的设备。<br><br>不仅如此，Microsoft 365 总经理 Wangui McKelvey 还表示，Windows 365 提供即时启动体验，即用户可以从云端将 App、工具、数据和设置，传输到 Mac、iPad 、Linux 设备和 Android 设备上：“你可以从上次中断的地方继续，因为即使切换设备，Cloud PC 的状态仍然保持不变。”<br><br>也就是说，只要设备能连接到 Internet、具有 Web 浏览器，用户就可以通过 Windows 365 设备访问 Cloud PC，在云端完整体验 Windows 10 或 Windows 11，包括应用、数据和各项设置。<br></div>', 'http://localhost:6660/api/file/blog/20237420452269836.png', '今年，凭借 OpenAI 市值飞升的微软，无疑是 AI 行业的领军者。然而微软 2023 年第四财季财报，却揭示了其辉煌背后的危机：卖不动的 Windows 和硬件设备、“道阻且难”的 AI 商业化之路。<br><br>正如 CEO 萨提亚 · 纳德拉所说，当前 AI 仍是微软加码的重点，但实际上对于 Windows，微软也一直有个明确的长期发展目标：将 Windows 迁移到云端。<br>');

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `musician` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duration` int NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `word` varchar(20000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (2, 'chen', '2023-08-07 13:25:16', 'Whatever It Takes', 'http://127.0.0.1:6660/api/file/musicImg/202387132442719170.jpg', 'Image Dargon', 201, 'http://127.0.0.1:6660/api/file/music/20238713257770748.mp3', '[{\"en\":\"\",\"cn\":\"暂无歌词\",\"time\":0},{\"en\":\"\",\"cn\":\"暂无歌词\",\"time\":5},{\"en\":\"\",\"cn\":\"暂无歌词\",\"time\":10}]');
INSERT INTO `music` VALUES (6, 'chen', '2023-08-09 18:44:11', '再飞行', 'http://localhost:6660/api/file/musicImg/202389183711789779.jpg', '逃跑计划', 91, 'http://localhost:6660/api/file/music/202389183728814842.mp3', '[{\"en\":\"\",\"cn\":\"作词 : 刘北\",\"time\":\"0\"},{\"en\":\"\",\"cn\":\"作曲 : 格非\",\"time\":\"12\"},{\"en\":\"\",\"cn\":\"眼前重复的风景\",\"time\":\"24\"},{\"en\":\"\",\"cn\":\"渐渐模糊了约定\",\"time\":\"27\"},{\"en\":\"\",\"cn\":\"星空下流浪的你\",\"time\":\"30\"},{\"en\":\"\",\"cn\":\"仍然秘密的距离\",\"time\":\"33\"},{\"en\":\"\",\"cn\":\"温度消失的瞬间\",\"time\":\"36\"},{\"en\":\"\",\"cn\":\"无法触摸的明天\",\"time\":\"40\"},{\"en\":\"\",\"cn\":\"没有引力的世界\",\"time\":\"42\"},{\"en\":\"\",\"cn\":\"没有脚印的光年\",\"time\":\"45\"},{\"en\":\"\",\"cn\":\"还在等着你出现\",\"time\":\"48\"},{\"en\":\"\",\"cn\":\"日日夜夜自转的行星\",\"time\":\"52\"},{\"en\":\"\",\"cn\":\"到处遮满别人的背影\",\"time\":\"55\"},{\"en\":\"\",\"cn\":\"让风吹散混乱的呼吸\",\"time\":\"58\"},{\"en\":\"\",\"cn\":\"快快清醒 yeyeye\",\"time\":\"61\"},{\"en\":\"\",\"cn\":\"静静照亮原来的自己\",\"time\":\"64\"},{\"en\":\"\",\"cn\":\"天空撒满忽然的光明\",\"time\":\"67\"},{\"en\":\"\",\"cn\":\"眼中只有绚烂的天际\",\"time\":\"70\"},{\"en\":\"\",\"cn\":\"再飞行\",\"time\":\"73\"}]');
INSERT INTO `music` VALUES (7, 'chen', '2023-08-09 19:26:35', 'Traveling Light', 'http://localhost:6660/api/file/musicImg/20238919223249710.jpg', 'Joel Hanson / Sara Groves', 208, 'http://localhost:6660/api/file/music/20238919228651068.mp3', '[{\"en\":\"\",\"cn\":\"作词 : Steve Hindalong/Marcus Byrd/Allison M Mellon\",\"time\":\"0\"},{\"en\":\"\",\"cn\":\"作曲 : Brown Bannister/Marc Byrd/Steve Hindalong\",\"time\":\"8\"},{\"en\":\"I was doubling over the load on my shoulders\",\"cn\":\"曾经在我肩头有千钧重担 让我不堪重负\",\"time\":\"16\"},{\"en\":\"Was a weight I carried with me everyday\",\"cn\":\"每天每日我都觉得沉重不堪难以解脱\",\"time\":\"20\"},{\"en\":\"Crossing miles of frustrations and rivers of raging\",\"cn\":\"穿过满路荆棘挫折 趟过无数狂怒激流\",\"time\":\"24\"},{\"en\":\"Picking up stones I found along the way\",\"cn\":\"不忘一路俯拾寻找圆润美丽的卵石\",\"time\":\"29\"},{\"en\":\"I staggered and I stumbled down\",\"cn\":\"我一路蹒跚 坎坷前行\",\"time\":\"32\"},{\"en\":\"Pathways of trouble\",\"cn\":\"走过这条烦恼不断的旅途\",\"time\":\"35\"},{\"en\":\"I was hauling those souvenirs of misery\",\"cn\":\"所有痛苦不幸的回忆让我步履蹒跚\",\"time\":\"37\"},{\"en\":\"And with each step taken my back was breaking\",\"cn\":\"每一步都仿似将要压弯我的脊梁\",\"time\":\"41\"},{\"en\":\"\'Til I found the One who took it all from me\",\"cn\":\"直到遇到他 我如释重负\",\"time\":\"45\"},{\"en\":\"Down by the riverside\",\"cn\":\"漫步河畔\",\"time\":\"49\"},{\"en\":\"(Down by the riverside)\",\"cn\":\"（漫步河畔）\",\"time\":\"52\"},{\"en\":\"I laid my burdens down\",\"cn\":\"我卸下满身重负\",\"time\":\"53\"},{\"en\":\"Now I\'m traveling light\",\"cn\":\"如今我将轻装前行\",\"time\":\"56\"},{\"en\":\"My spirit lifted high\",\"cn\":\"满心舒畅一身轻盈\",\"time\":\"57\"},{\"en\":\"(I found my freedom now)\",\"cn\":\"（如今的我终于自由）\",\"time\":\"61\"},{\"en\":\"I found my freedom now\",\"cn\":\"如今的我终于自由\",\"time\":\"62\"},{\"en\":\"And I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"64\"},{\"en\":\"Through the darkest alleys and loneliest valleys\",\"cn\":\"走过晦暗无光的衢巷 穿越幽暗深邃的幽谷\",\"time\":\"83\"},{\"en\":\"I was dragging those heavy chains of doubt and fear\",\"cn\":\"所有不安和恐惧化作枷锁让我踉跄难行\",\"time\":\"87\"},{\"en\":\"Then with the one word spoken the locks were broken\",\"cn\":\"你一句简单的言语便破除了我全部枷锁\",\"time\":\"91\"},{\"en\":\"Now he\'s leading me to places\",\"cn\":\"如今他正引领着我前往\",\"time\":\"95\"},{\"en\":\"Where there are no tears\",\"cn\":\"那永不伤心落泪的天堂\",\"time\":\"97\"},{\"en\":\"Down by the riverside\",\"cn\":\"漫步河畔\",\"time\":\"99\"},{\"en\":\"(Down by the riverside)\",\"cn\":\"（漫步河畔）\",\"time\":\"102\"},{\"en\":\"I laid my burdens down\",\"cn\":\"我卸下满身重负\",\"time\":\"103\"},{\"en\":\"Now I\'m traveling light\",\"cn\":\"如今我将轻装前行\",\"time\":\"106\"},{\"en\":\"My spirit lifted high\",\"cn\":\"满心舒畅一身轻盈\",\"time\":\"108\"},{\"en\":\"(I found my freedom now)\",\"cn\":\"（如今的我终于自由）\",\"time\":\"111\"},{\"en\":\"I found my freedom now\",\"cn\":\"如今的我终于自由\",\"time\":\"124\"},{\"en\":\"And I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"131\"},{\"en\":\"(Down by the riverside)\",\"cn\":\"漫步河畔\",\"time\":\"133\"},{\"en\":\"I laid my burdens down\",\"cn\":\"我卸下满身重负\",\"time\":\"137\"},{\"en\":\"Now I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"139\"},{\"en\":\"My spirit lifted high\",\"cn\":\"满心舒畅一身轻盈\",\"time\":\"141\"},{\"en\":\"I found my freedom now\",\"cn\":\"如今的我终于自由\",\"time\":\"145\"},{\"en\":\"And I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"148\"},{\"en\":\"Down by the riverside\",\"cn\":\"漫步河畔\",\"time\":\"149\"},{\"en\":\"I laid my burdens down\",\"cn\":\"我卸下满身重负\",\"time\":\"153\"},{\"en\":\"Now I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"156\"},{\"en\":\"My spirit lifted high\",\"cn\":\"满心舒畅一身轻盈\",\"time\":\"158\"},{\"en\":\"I found my freedom now\",\"cn\":\"如今的我终于自由\",\"time\":\"162\"},{\"en\":\"And I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"164\"},{\"en\":\"Down by the riverside\",\"cn\":\"漫步河畔\",\"time\":\"166\"},{\"en\":\"I laid my burdens down\",\"cn\":\"我卸下满身重负\",\"time\":\"170\"},{\"en\":\"Now I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"173\"},{\"en\":\"My spirit lifted high\",\"cn\":\"满心舒畅一身轻盈\",\"time\":\"174\"},{\"en\":\"I found my freedom now\",\"cn\":\"我卸下满身重负\",\"time\":\"179\"},{\"en\":\"And I\'m traveling light\",\"cn\":\"如今我将轻装前行\",\"time\":\"181\"},{\"en\":\"(I\'m traveling light)\",\"cn\":\"（我将轻装前行）\",\"time\":\"183\"},{\"en\":\"I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"184\"},{\"en\":\"I found my freedom now\",\"cn\":\"如今的我终于自由\",\"time\":\"187\"},{\"en\":\"I\'m traveling light\",\"cn\":\"我将轻装前行\",\"time\":\"192\"}]');

-- ----------------------------
-- Table structure for photo_height
-- ----------------------------
DROP TABLE IF EXISTS `photo_height`;
CREATE TABLE `photo_height`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photo_height
-- ----------------------------
INSERT INTO `photo_height` VALUES (1, 'chen', '2023-08-08 14:41:58', 'http://localhost:6660/api/file/photoHeight/202388143947551381.jpg');
INSERT INTO `photo_height` VALUES (2, 'chen', '2023-08-08 21:13:25', 'http://localhost:6660/api/file/photoHeight/202388211321993284.jpg');
INSERT INTO `photo_height` VALUES (3, 'chen', '2023-08-08 21:13:55', 'http://localhost:6660/api/file/photoHeight/202388211352743865.jpg');
INSERT INTO `photo_height` VALUES (4, 'chen', '2023-08-08 21:14:03', 'http://localhost:6660/api/file/photoHeight/202388211359579048.jpg');
INSERT INTO `photo_height` VALUES (5, 'chen', '2023-08-09 09:42:21', 'http://localhost:6660/api/file/photoHeight/20238994215003075.jpg');
INSERT INTO `photo_height` VALUES (6, 'chen', '2023-08-09 09:42:32', 'http://localhost:6660/api/file/photoHeight/20238994228385540.jpg');
INSERT INTO `photo_height` VALUES (7, 'chen', '2023-08-09 09:42:42', 'http://localhost:6660/api/file/photoHeight/20238994240155895.jpg');
INSERT INTO `photo_height` VALUES (8, 'chen', '2023-08-09 09:42:52', 'http://localhost:6660/api/file/photoHeight/20238994250827322.jpg');
INSERT INTO `photo_height` VALUES (9, 'chen', '2023-08-09 09:43:01', 'http://localhost:6660/api/file/photoHeight/20238994259260081.jpg');
INSERT INTO `photo_height` VALUES (10, 'chen', '2023-08-09 09:43:10', 'http://localhost:6660/api/file/photoHeight/2023899437101738.jpg');
INSERT INTO `photo_height` VALUES (11, 'chen', '2023-08-09 09:43:16', 'http://localhost:6660/api/file/photoHeight/20238994314097878.jpg');

-- ----------------------------
-- Table structure for photo_width
-- ----------------------------
DROP TABLE IF EXISTS `photo_width`;
CREATE TABLE `photo_width`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of photo_width
-- ----------------------------
INSERT INTO `photo_width` VALUES (2, 'chen', '2023-08-08 14:37:15', 'http://localhost:6660/api/file/photoWidth/202388143249230304.jpg');
INSERT INTO `photo_width` VALUES (3, 'chen', '2023-08-08 21:06:34', 'http://localhost:6660/api/file/photoWidth/20238821629195791.jpg');
INSERT INTO `photo_width` VALUES (4, 'chen', '2023-08-08 21:06:55', 'http://localhost:6660/api/file/photoWidth/20238821649700016.png');
INSERT INTO `photo_width` VALUES (5, 'chen', '2023-08-08 21:07:21', 'http://localhost:6660/api/file/photoWidth/20238821718901733.jpg');
INSERT INTO `photo_width` VALUES (6, 'chen', '2023-08-08 21:07:54', 'http://localhost:6660/api/file/photoWidth/20238821750068687.jpg');
INSERT INTO `photo_width` VALUES (7, 'chen', '2023-08-08 21:18:24', 'http://localhost:6660/api/file/photoWidth/202388211816069509.jpg');
INSERT INTO `photo_width` VALUES (8, 'chen', '2023-08-08 21:18:43', 'http://localhost:6660/api/file/photoWidth/202388211838862379.jpg');
INSERT INTO `photo_width` VALUES (9, 'chen', '2023-08-08 21:18:56', 'http://localhost:6660/api/file/photoWidth/202388211850639815.jpg');
INSERT INTO `photo_width` VALUES (10, 'chen', '2023-08-08 21:19:08', 'http://localhost:6660/api/file/photoWidth/20238821194697136.jpg');
INSERT INTO `photo_width` VALUES (11, 'chen', '2023-08-08 21:19:47', 'http://localhost:6660/api/file/photoWidth/202388211941589642.jpg');
INSERT INTO `photo_width` VALUES (12, 'chen', '2023-08-09 10:03:00', 'http://localhost:6660/api/file/photoWidth/20238910257625194.jpg');
INSERT INTO `photo_width` VALUES (13, 'chen', '2023-08-09 10:03:10', 'http://localhost:6660/api/file/photoWidth/2023891038319611.jpg');
INSERT INTO `photo_width` VALUES (14, 'chen', '2023-08-09 10:03:16', 'http://localhost:6660/api/file/photoWidth/20238910314367089.png');
INSERT INTO `photo_width` VALUES (15, 'chen', '2023-08-09 10:03:24', 'http://localhost:6660/api/file/photoWidth/20238910321213571.jpg');
INSERT INTO `photo_width` VALUES (16, 'chen', '2023-08-09 10:03:33', 'http://localhost:6660/api/file/photoWidth/20238910331546628.jpg');
INSERT INTO `photo_width` VALUES (17, 'chen', '2023-08-09 10:03:42', 'http://localhost:6660/api/file/photoWidth/20238910341818719.jpg');
INSERT INTO `photo_width` VALUES (18, 'chen', '2023-08-09 10:03:56', 'http://localhost:6660/api/file/photoWidth/20238910353256164.jpg');
INSERT INTO `photo_width` VALUES (19, 'chen', '2023-08-09 10:04:05', 'http://localhost:6660/api/file/photoWidth/2023891043017917.jpg');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, '1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'chen', 'hbaiqiao@163.com', '19854803394');

-- ----------------------------
-- Table structure for vlog
-- ----------------------------
DROP TABLE IF EXISTS `vlog`;
CREATE TABLE `vlog`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duration` int NULL DEFAULT NULL,
  `src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vlog
-- ----------------------------
INSERT INTO `vlog` VALUES (2, '第一个视频', 'chen', '2023-08-07 18:33:47', 'http://localhost:6660/api/file/vlogImg/202387183249035893.jpg', 19, 'http://localhost:6660/api/file/vlog/202387183337029735.mp4', '视频发布功能测试');
INSERT INTO `vlog` VALUES (6, '2023年元旦', 'chen', '2023-08-09 21:42:03', 'http://localhost:6660/api/file/vlogImg/202389214046103061.jpg', 13, 'http://localhost:6660/api/file/vlog/202389214135549736.mp4', '珠海长隆');
INSERT INTO `vlog` VALUES (7, '长隆海洋乐园', 'chen', '2023-08-09 21:44:52', 'http://localhost:6660/api/file/vlogImg/202389214418190837.jpg', 25, 'http://localhost:6660/api/file/vlog/202389214446947496.mp4', 'hhhhhhhhhhhhhhhh');
INSERT INTO `vlog` VALUES (8, '横琴', 'chen', '2023-08-09 21:45:58', 'http://localhost:6660/api/file/vlogImg/202389214536524163.jpg', 17, 'http://localhost:6660/api/file/vlog/202389214551298540.mp4', '2023');

SET FOREIGN_KEY_CHECKS = 1;
