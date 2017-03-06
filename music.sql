/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : music

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2017-03-06 18:05:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for album_base
-- ----------------------------
DROP TABLE IF EXISTS `album_base`;
CREATE TABLE `album_base` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `singerName` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `createCompany` varchar(255) DEFAULT NULL,
  `introduction` text,
  `songNum` int(11) DEFAULT NULL,
  `singerId` varchar(255) DEFAULT NULL,
  `tagId` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `album_singer` (`singerId`) USING BTREE,
  KEY `album_tag` (`tagId`) USING BTREE,
  CONSTRAINT `album_singer` FOREIGN KEY (`singerId`) REFERENCES `singer` (`id`),
  CONSTRAINT `album_tag` FOREIGN KEY (`tagId`) REFERENCES `tag_album` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album_base
-- ----------------------------
INSERT INTO `album_base` VALUES ('07b0f39a7d4d4de1be8bfc64b35cc41e', 'The Best Damn Thing: Deluxe Edition', 'image/album/18526770928757404.jpg', null, 'Avril Lavigne', '2007-06-26 00:00:00', 'RCA Records Label', '《美丽坏东西》（英语：The Best Damn Thing）是加拿大唱作歌手艾薇儿·拉维尼的第三张录音室专辑，于2007年4月17日由RCA唱片发行。这张专辑与她上一张专辑《酷到骨子里》（Under My Skin）相比风格更加多样，多样许多评论者认为这是艾薇儿最商业化的作品。这张专辑由不同的制作人参与制作，包括Matt Beckley、Rob Cavallo、Dr. Luke以及艾薇儿自己作为执行制作人。', '0', '7ca6a02f51324867b8d4bd6db72620a5', '2', '1');
INSERT INTO `album_base` VALUES ('0fdf0e00289948dea521498544c6ae5a', 'THEME SONGS COLLECTION', 'image/album/6657542906651932.jpg', null, '澤野弘之', '2012-01-25 00:00:00', 'Aniplex', null, '0', '7288ccbbd92246d3a28db7b67cc1b2df', '4', '1');
INSERT INTO `album_base` VALUES ('1', '湫兮如风', 'image/album/1419469524541522.jpg', '电影《大鱼海棠》片尾曲', '徐佳莹', '2016-07-13 00:00:00', '霍尔果斯彩条屋影业', '动画电影《大鱼海棠》片尾曲《湫兮如风》，由徐佳莹演唱，吉田潔作曲，梁旋填词。“湫兮如风”一词出自宋玉《高唐赋》，原文“湫兮如风，凄兮如雨”描绘“凉风习习,细雨清凄”的景象。而在此歌曲中，暗指电影主人公之一“湫”的命运。', '2', '6', '1', '1');
INSERT INTO `album_base` VALUES ('10', '逆战', 'image/album/101155069761055.jpg', '枪战网游《逆战》主题曲', '张杰', '2012-03-21 00:00:00', '天娱传媒', '驾驶机甲，翻天覆地，张杰代言腾讯自研FPS大作《逆战》 腾讯游戏首款自研FPS枪战网游《逆战》，力邀玩家喜爱的明星张杰为游戏代言，并创作主题曲《逆战》。歌中讲述一名暴风少年，驾驶机甲，在烈火重重的战场上，奋勇拼搏，逆势而战，捍卫世界和平。让我们跟随其鲜明的节奏，为实现心中的梦想，一起Fighting吧！ ', '2', '7', '1', '1');
INSERT INTO `album_base` VALUES ('11', '着魔', 'image/album/849922488315442.jpg', '游戏《恶魔法则》主题曲', '张杰', '2010-04-06 00:00:00', null, '《着魔》由著名网络作家、《恶魔法则》原作者“跳舞”作词，国内著名的音乐制作人包胡尔查、谭伊哲倾力打造，曲风恢弘大气中点缀优柔婉转，通过张杰的演绎更加准确的再现了游戏的气势和魔幻风格。据报道，《着魔》将于4月6日在媒体上首播。 \r\n在沉寂一段时间后，张杰以献唱《恶魔法则》主题曲的方式再次成为舆论的焦点。据张杰本人透露，《着魔》将与自己以往凄美柔肠的风格截然不同，要带给歌迷一种截然不同的听觉享受。 ', '1', '7', '1', '1');
INSERT INTO `album_base` VALUES ('1906891f5c544d68be95b75d79ca2509', 'Purpose', 'image/album/3285340746015046.jpg', null, 'Justin Bieber', '2015-11-13 00:00:00', 'Island Records', 'Justin Bieber睽违3年的全新专辑【Purpose】邀来黄金制作团队操刀，加上电音人气王Skrillex、Diplo及嘻哈乐坛王牌制作Rick Rubin，呈现多采多姿的流行音乐风格。首波单曲\"What Do You Mean?\"踩着时下最夯的电子元素，空降99国iTunes单曲榜冠军、YouTube两亿四千万人次疯狂点播，更为Justin夺下生涯首支英美双料冠军单曲；第二波主攻单曲“Sorry”狂扫39国iTunes单曲榜冠军、登上美国单曲榜亚军；暖身单曲“I’ll Show You”展现Justin的成熟韵味，闯进41国iTunes Top 10；由全英音乐奖最佳男歌手Ed Sheeran跨刀创作的\"Love Yourself\"；合作全美流行单曲榜季军\"As Long As You Love Me\"的嘻哈歌手Big Sean再次合作\"No Pressure\"；拥有5张全美专辑榜冠军专辑的嘻哈鬼才Nas助阵\"We Are\"；新世代酷女声Halsey站台\"The Feeling\"。另外，专辑也收录了Justin Bieber为Skrillex及Diplo连手策动的电音组合Jack ?友情跨刀献唱英国金榜季军曲\"Where Are ? Now\"。', '0', '0422a500fb46411c8f58d295f6c14064', '2', '1');
INSERT INTO `album_base` VALUES ('1c60e872591043478159ec8d10c5e845', '拾伍 重唱集', 'image/album/4457420139017765.jpg', null, '羽·泉', '2013-07-20 00:00:00', '巨匠文化', '被重新唤醒的旋律', '0', 'b88aa86cb91141ff86a77f0e2757d667', '1', '1');
INSERT INTO `album_base` VALUES ('2', '追梦痴子心', 'image/album/27487790708865.jpg', null, 'GALA', '2011-03-24 00:00:00', '东乐', '仍然是嬉笑自嘲，仍然不靠谱，早已经不是少年，还是满腔少年心气。不赶紧攒钱买房，还一心奔跑在路上，最初的一点梦想都在手心化成了糖浆，还紧握住不放，被视作痴人也不在意，怀着一颗赤子之心一路奔跑。六年时光的打磨并不能使所有人都向生活俯首称臣，这张《追梦痴子心》', '12', '1', '1', '1');
INSERT INTO `album_base` VALUES ('2f46fc1a958741759391a950b431f267', '下完这场雨', 'image/album/1d681f9b515446e0a71ebe974987c2dd.jpeg', '', '后弦', '2016-10-10 00:00:00', '代亚文化', '专辑首波重磅主打《下完这场雨》是一首汇集后弦复古情怀的倾情力作。后弦把歌曲故事年代拉回民国，空间跨越上海滩和长城脚下，可谓怀旧风满满。他表示写过很多中国风，而这回是首次涉猎怀旧年代风，所以也用一种更磁性与怀旧的声线来演绎。歌曲以致敬弘一法师李叔同经典名曲《送别》为线索，用熟悉的旋律贯穿于音乐中，展开了一场与《送别》有关的雨中绝恋凄美故事。旋律部分相当抓人上口，却不失淡淡怀旧情怀，想必又将是后弦笔下一首经典作品。\r\n下完这场雨，打湿那年陈旧事。怀旧能否感动当下？耳边弦音，等你答案。', '1', '4', '1', '1');
INSERT INTO `album_base` VALUES ('3', 'It Won\'t Be Soon Before Long', 'image/album/2544269906854087.jpg', null, 'Maroon 5', '2008-07-06 00:00:00', 'Universal Music Digital Bulgaria', '在这些流行音乐届大腕级音乐人的打造之下，《It Won\'t Be Soon Before Long》处处透出的都是走在时尚前沿的声音。无论是开场曲《If I Never See Your Face Again》和《Little of Your Time》，还是《Little Of Your Time》和《Wake Up Call》，或是《Can\'t Stop》都很有去年红透美国的流行小天王Justin Timberlake的气质，在Adam Levine独特的嗓音之下铺垫的是时尚的舞曲元素以及以及复古的Funk，R&B节奏。《Won\'t Go Home Without You》有着煽情的歌词，明显是为刚刚失恋的小青年们写的。此后的《Nothing Lasts Forever》，《Goodnight Goodnight》，《Better That We Break》和《Back At Your Door》都是旋律非常上口的慢版情歌，跟前半部分几首动感的快歌刚好互补。\r\n可以看的出来，Maroon 5是一直非常聪明的乐队，他们很清楚现在的歌迷们需要什么。这张《It Won\'t Be Soon Before Long》很好的迎合了当今流行乐坛歌迷们的口味。', '19', '2', '2', '1');
INSERT INTO `album_base` VALUES ('4', 'Songs About Jane', 'image/album/6647647302165577.jpg', null, 'Maroon 5', '2007-04-30 00:00:00', 'Universal Music Digital Bulgaria', '美国流行组合 Maroon 5--《Songs About Jane》\\\\nmaroon 5 融合了红色的狂野魅力、神秘热情、奔放烈爱，再掺入摇滚力道、灵魂旋律及放克节奏，这样的红流泻着新灵魂乐性感风情的放克摇滚，这样的红在流行歌坛注入了一股新的颜色。\\\\n2003年，一首充满张力的摇滚劲曲\\\\\"Harder to Breathe\\\\\"悄悄地进占了全球各地的电台，这首挟带着戏剧性内容和唱腔而让人一听就上瘾的歌曲，为来自洛杉矶的这支五人团体Maroon 5打开了进军世界的大门。\\\\nMaroon 5的成功决非一夕得来的侥幸，这两年来他们巡回全美在超过两百场的演唱会中表演，并为许多知名艺人暖场，这些现场演出让Maroon 5一点一滴地累积起人气，原本在2002年就已发行的\\\\\"Harder To Breathe\\\\\"因此开始口耳相传地慢慢走红，到了2003年夏天更成为歌迷的最爱之一，打入全美单曲榜第18名，而收录\\\\\"Harder To Breathe\\\\\"的专辑《Songs About Jane》也连带获得全美专辑榜第七名的佳绩。2004年由同一张专辑所选出的单曲\\\\\"This Love\\\\\"不但延续了\\\\\"Harder To Breathe\\\\\"的气势，还更上一层楼地获得全美单曲榜第五名，而Maroon 5在欧洲以及亚洲各国也都创下杰出的成绩，俨然已成为全球最受欢迎的团体之一。', '12', '2', '2', '1');
INSERT INTO `album_base` VALUES ('5', 'Maybe I\'m Dreaming', 'image/album/6667438511838692.jpg', null, 'Owl City', '2010-01-25 00:00:00', 'Digital Distribution Estonia', '2008年3月18日，Owl City第二张专辑《Maybe I’m Dreaming》问世。梦幻的标题，简洁的封面，如同糖衣包裹的声线和旋律，在这个春暖花开的季节再次捎来令人欣喜的清新气息。曲风是Electronica和Pop的完美融合，似乎又有一点Indie Rock的味道。饶有趣味的歌词有如抒情诗一般的迷人魅力，又颇具美学的魔力。你会惊叹于冰冷的键盘上怎能滑落如此圆润的音符，电子元素的巧妙运用使得整张专辑饱满而不失利落，跃动着生机盎然的温情。Adam Young在他的MySpace上写到，由于失眠的缘故，大部分歌曲都是在凌晨的时候制作出来的。清晨的第一滴露水和青草的芳香，已无形地渗透到Owl City的音乐里，自然灵动的氛围贯穿其中。《The Saltwater Room》和《Air Traffic》后半段出现的女声好像奶油一样，甜甜的与曲子的整体气质都很搭。此外《Rainbow Veins》和《Dear Vienna》也都是重点推荐的曲目。让四月的好心情伴随好音乐蔓延。\r\n他的声音象少女倾诉心事一样温文细气,但对旋律节奏的掌握却十分到位.Of June这张EP就以欢快的电子旋律征服了不少听众,象宝石般闪烁明亮.而首张专辑Maybe I\'m Dreaming在演绎方面Owl City显然更加投入,从精致的和声到深情的合唱,Owl City的个人魅力也越加凸现.甜蜜的声线清新的电子音乐,这貌试简单的配搭却被Owl City演绎得绚丽多彩,尽管夏日未到但已仿佛感受到那份清爽的惬意!', '12', '3', '2', '1');
INSERT INTO `album_base` VALUES ('6', 'The Christmas Song', 'image/album/2543170396784645.jpg', null, 'Owl City', '2008-12-23 00:00:00', 'Self-Released', null, '1', '3', '2', '1');
INSERT INTO `album_base` VALUES ('63cb796e54194a4b8f8f44f047dc9710', '娃娃脸', 'image/album/36283883728991.jpg', null, '后弦', '2009-12-11 00:00:00', '无线星空', '创作达人后弦，携带他的最新EP《娃娃脸》重回媒体和歌迷的视野，掀起一阵”幻想系“音乐热浪。后弦从《娃娃脸》的童年回味到《花甲》遥远传说的跨度，足以让听者佩服他天马行空的幻想能力。后弦又首次携手同盟女歌手SARA，为自己的年度对唱情歌《你还欠我一个拥抱》种下激情“曲种”，给市场带来偌大的音乐冲击力。 所有人都觉得后弦具有很好的企划头脑,会给自己准确定位,其实这是后弦对音乐的一种良好态度,也是为了寻找一个最真实音乐人生。后弦的作品也往往能让人留恋往返,留下无穷猜想。', '0', '4', '1', '1');
INSERT INTO `album_base` VALUES ('6cf40bca30114703a8a9a1564f355b5d', 'a horse\'s dream 梦马', 'image/album/18521273370353427.jpg', '电影《宾虚》中国推广曲', '尚雯婕', '2016-10-01 00:00:00', '黑方金圆', '命运造物，使人受炼狱苦，解脱不得。躲避！逃离！挣扎！呐喊！抗争！心中存“恨”，蛰伏于光影之间 ；韬光养晦，君子复仇，在梦境与现实之间赢得了什么又失去了什么……', '0', '24b8d4d63ed943ef995c2183ba025c49', '1', '1');
INSERT INTO `album_base` VALUES ('6d6eab70fa854753ae1236d1b5ac6251', '2016 GRAMMY Nominees', 'image/album/16640008975136296.jpg', '2016格莱美提名', 'Bruno Mars', '2016-01-22 00:00:00', '环球唱片', '2016年度第58届格莱美音乐奖的提名刚刚揭晓，美国嘻哈音乐人肯德里克·拉马尔(Kendrick Lamar)获得了包括“年度最佳专辑”和“年度最佳歌曲”等在内的11项提名，是本届格莱美奖获得提名最多的音乐人。泰勒·斯威夫特(Taylor Swift)和今年人气爆棚的The Weeknd各获七项提名紧随其后。此外，超级制作人马克思·马丁(Max Martin)和加拿大说唱音乐人德雷克(Drake)也都摘得多个奖项的提名。', '0', 'c0f54aa5c6eb4c85a8e62b9eadb4df98', '2', '1');
INSERT INTO `album_base` VALUES ('7', '很有爱', 'image/album/59373627905249.jpg', null, '后弦', '2012-02-09 00:00:00', '天浩盛世', '2012后弦「很有爱」全创作积累专辑，是幻想系王子后弦签约进天浩盛世后，所交出的一张漂亮成绩单，2首全新单曲加上11首创作单曲的经典收录，相信在龙年一开始就带给歌迷「很有爱」的惊喜感受！\r\n从清新甜美的「没有这首歌」开场，落入华丽古典的R&B情歌「信条」、转到朗朗上口的专辑同名歌曲「很有爱」、再游走到学院风强烈的「白色恋习曲」、充满中国风韵味的「苏州城外的微笑」、最后转回英式摇滚的「花甲」…这13首高潮迭起的歌曲，构成了专辑丰富且多元的画面感、也凸显后弦这位幻想系王子游走在旋律之中的多情痴狂与戏剧情感，不在拘泥于传统中国风R&B中，进而呈现的是他企图玩转「跨界」的肆无忌惮，成就出这张跳跃在音符上的全创作专辑！\r\n因为融合了多种的音乐元素在其中，因此这张专辑与以往最大的不同就是要尝试「跨界」的音乐概念，跨界CROSSOVER理念，源于横跨多种领域的创作形式，在新专辑中，后弦把自己的幻想系概念彻底融入亚洲舞曲、美系POP、清新田园、学院摇滚等不同「界」的音乐元素中，构成一种跨界融合的全新风格，这标志着后弦式音乐的突破自我，全新尝试又一步，彷彿走进后弦的一座幻想城堡般，回到最纯真的童年在乐园中肆意玩乐、好奇探索、发现美丽、或是有如坐在花台上的优雅浪漫、谈笑风生，一首一首歌曲接踵而来，万花筒般的迎面绽放、目不暇给，聆听完专辑后的首首惊奇！绝对不容轻易放过！', '13', '4', '1', '1');
INSERT INTO `album_base` VALUES ('8', '9公主', 'image/album/666304046444415.jpg', null, '后弦', '2006-09-01 00:00:00', '东升文化', null, '6', '4', '1', '1');
INSERT INTO `album_base` VALUES ('8eb6dd0c468f416192198fbf9b757858', '南山南', 'image/album/6648746813825382.jpg', null, '马頔', '2014-09-26 00:00:00', '摩登天空', '马頔单曲《南山南》正式版首发 诗歌才情与旋律天赋完美融汇', '0', '92ddb9c6c0134a0dab6ca130b76a3fda', '1', '1');
INSERT INTO `album_base` VALUES ('9', '骄傲的少年', 'image/album/1391981724588577.jpg', '动画《那年那兔那些事儿》第二季片尾曲', '南征北战', '2016-04-01 00:00:00', '翼下之风动漫', '奔跑吧，骄傲的少年，献给每一个爱国的人。', '1', '5', '1', '1');
INSERT INTO `album_base` VALUES ('9214d61795fc47c886336e38637a3758', '画风', 'image/album/1417270500276190.jpg', null, '后弦', '2016-06-22 00:00:00', '通力时代', '后弦近日推出全新中国风力作《画风》，后弦表示这首作品是从杜牧唐诗《山行》有感而发，把深秋的香山一段动人际遇融入曲中。“画风”包括双重含义，一层为诗意的香山画风很美，另一层则是爱情缥缈稍纵即逝，留住爱就像把风画在纸上一样难。', '0', '4', '1', '1');
INSERT INTO `album_base` VALUES ('98b79d16e6744676bc951b5813573972', 'Fearless', 'image/album/7945071024087485.jpg', null, 'Taylor Swift', '2009-03-08 00:00:00', 'Universal Music Ireland Ltd.', '2006年冬天，乡村女歌手泰勒史薇芙特(Taylor Swift)推出她的首支单曲，以当红乡村巨星提姆·麦克罗(Tim McGraw)的名字当作标题，引起广大的注意，也打进了乡村排行前十名。另人惊奇的是,Taylor Swift不但是歌曲的作者,她首张同名专辑里面的所有歌曲也都是她亲自参与谱写的,而当时他才十五岁。', '0', '0986ba72bc3a40618678c8f61a21d361', '2', '1');
INSERT INTO `album_base` VALUES ('990090629cd949f593e40b1dfc0c305b', 'ノイタミナ FAN BEST', 'image/album/7823025232375393.jpg', 'ノイタミナ 10周年纪念 精选专辑', 'EGOIST', '2015-04-15 00:00:00', 'Aniplex', '2005年4月からスタートしたフジテレビのアニメ枠“ノイタミナ”の10周年記念ファン・リクエスト・アルバム。“ノイタミナ”作品を彩ってきたオープニング&エンディング・テーマの数々から、ファン投票によって厳選された楽曲を収録するベスト盤。', '0', '10300f1269e9438099154dcc05a53823', '4', '1');
INSERT INTO `album_base` VALUES ('a842acf1cc894174872fe8dcd1aaef3d', '初音ミク 5thバースデーベスト ～impacts～', 'image/album/888405395266415.jpg', null, '初音ミク', '2012-08-01 00:00:00', null, null, '0', '354169e500304711a138f308c1a22897', '4', '1');
INSERT INTO `album_base` VALUES ('a94ef65ad45448568ff0ebb216a5f80d', '恋爱サーキュレーション', 'image/album/642114790633458.jpg', null, '花澤香菜', '2010-01-27 00:00:00', 'アニプレックス', null, '0', '5dcd4d10774e443589916de44b956c47', '4', '1');
INSERT INTO `album_base` VALUES ('b87afd3f20604733b06f3ab7d9aeea9f', 'I Really Like You', 'image/album/7878000813226177.jpg', null, 'Carly Rae Jepsen', '2015-03-02 00:00:00', 'Interscope Records', '加拿大热门女歌手Carly Rae Jepsen回归新单曲《I Really Like You》于3月2日发行。', '0', '9d63ea982ebe422c9660080379c6959e', '2', '1');
INSERT INTO `album_base` VALUES ('c9019d0672564068902d627adf686f30', 'Encore', 'image/album/556352883666539.jpg', null, '久石譲', '2002-10-02 00:00:00', 'Universal', null, '0', 'a98ceefbe51b43e588e2deac7377c524', '4', '1');
INSERT INTO `album_base` VALUES ('e3a511d2a7db4b809199175414aa8b19', '回蔚', 'image/album/85761906972218.jpg', null, '莫文蔚', '2009-06-26 00:00:00', '数字发行', '专辑取名《回蔚》，是希望借此让大家“回味”美好的往昔。这张专辑以翻唱老歌为主，除了《打起手鼓唱起歌》外，还重新演绎了“金嗓子”周璇的《月圆花好》，王洛宾的《在那遥远的地方》、《半个月亮爬上来》和邓丽君的《风从哪里来》等歌曲。  第一次唱民歌，莫文蔚说是在2002年，就是这首《打起手鼓唱起歌》。', '0', 'e6cb866b51394a5d82a96c86a58e0527', '1', '1');
INSERT INTO `album_base` VALUES ('f04ee298f71d4151b46633e69ca3aee1', '梵高先生', 'image/album/2323268069553116.jpg', null, '李志', '2005-09-15 00:00:00', '李志', null, '0', 'a899a4687b984a66874646751f3b1010', '1', '1');

-- ----------------------------
-- Table structure for album_record
-- ----------------------------
DROP TABLE IF EXISTS `album_record`;
CREATE TABLE `album_record` (
  `id` varchar(255) NOT NULL,
  `shareNum` int(11) DEFAULT '0',
  `commentNum` int(11) DEFAULT '0',
  `playNum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of album_record
-- ----------------------------
INSERT INTO `album_record` VALUES ('07b0f39a7d4d4de1be8bfc64b35cc41e', '0', '0', '0');
INSERT INTO `album_record` VALUES ('0fdf0e00289948dea521498544c6ae5a', '0', '0', '0');
INSERT INTO `album_record` VALUES ('1', '0', '2', '6');
INSERT INTO `album_record` VALUES ('10', '0', '0', '0');
INSERT INTO `album_record` VALUES ('11', '0', '0', '0');
INSERT INTO `album_record` VALUES ('1906891f5c544d68be95b75d79ca2509', '0', '0', '0');
INSERT INTO `album_record` VALUES ('1c60e872591043478159ec8d10c5e845', '0', '0', '0');
INSERT INTO `album_record` VALUES ('2', '0', '0', '2');
INSERT INTO `album_record` VALUES ('2f46fc1a958741759391a950b431f267', '0', '0', '0');
INSERT INTO `album_record` VALUES ('3', '0', '0', '3');
INSERT INTO `album_record` VALUES ('4', '0', '0', '1');
INSERT INTO `album_record` VALUES ('5', '0', '0', '1');
INSERT INTO `album_record` VALUES ('6', '0', '0', '1');
INSERT INTO `album_record` VALUES ('63cb796e54194a4b8f8f44f047dc9710', '0', '0', '0');
INSERT INTO `album_record` VALUES ('6cf40bca30114703a8a9a1564f355b5d', '0', '0', '0');
INSERT INTO `album_record` VALUES ('6d6eab70fa854753ae1236d1b5ac6251', '0', '0', '0');
INSERT INTO `album_record` VALUES ('7', '0', '0', '0');
INSERT INTO `album_record` VALUES ('8', '0', '0', '0');
INSERT INTO `album_record` VALUES ('8eb6dd0c468f416192198fbf9b757858', '0', '0', '0');
INSERT INTO `album_record` VALUES ('9', '0', '0', '0');
INSERT INTO `album_record` VALUES ('9214d61795fc47c886336e38637a3758', '0', '0', '0');
INSERT INTO `album_record` VALUES ('98b79d16e6744676bc951b5813573972', '0', '0', '0');
INSERT INTO `album_record` VALUES ('990090629cd949f593e40b1dfc0c305b', '0', '0', '0');
INSERT INTO `album_record` VALUES ('a842acf1cc894174872fe8dcd1aaef3d', '0', '0', '0');
INSERT INTO `album_record` VALUES ('a94ef65ad45448568ff0ebb216a5f80d', '0', '0', '0');
INSERT INTO `album_record` VALUES ('b87afd3f20604733b06f3ab7d9aeea9f', '0', '0', '0');
INSERT INTO `album_record` VALUES ('c9019d0672564068902d627adf686f30', '0', '0', '0');
INSERT INTO `album_record` VALUES ('e3a511d2a7db4b809199175414aa8b19', '0', '0', '0');
INSERT INTO `album_record` VALUES ('f04ee298f71d4151b46633e69ca3aee1', '0', '0', '0');

-- ----------------------------
-- Table structure for at
-- ----------------------------
DROP TABLE IF EXISTS `at`;
CREATE TABLE `at` (
  `id` varchar(255) NOT NULL,
  `creatorName` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `sendUserId` varchar(255) DEFAULT NULL,
  `creatorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `atCI_user` (`creatorId`),
  KEY `atSI_user` (`sendUserId`),
  CONSTRAINT `atCI_user` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `atSI_user` FOREIGN KEY (`sendUserId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of at
-- ----------------------------

-- ----------------------------
-- Table structure for classify_first
-- ----------------------------
DROP TABLE IF EXISTS `classify_first`;
CREATE TABLE `classify_first` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classify_first
-- ----------------------------
INSERT INTO `classify_first` VALUES ('1000', '华语', '1');
INSERT INTO `classify_first` VALUES ('2000', '欧美', '1');
INSERT INTO `classify_first` VALUES ('3000', '其他', '1');

-- ----------------------------
-- Table structure for classify_second
-- ----------------------------
DROP TABLE IF EXISTS `classify_second`;
CREATE TABLE `classify_second` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `firstClassifyId` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cs_cf` (`firstClassifyId`),
  CONSTRAINT `cs_cf` FOREIGN KEY (`firstClassifyId`) REFERENCES `classify_first` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classify_second
-- ----------------------------
INSERT INTO `classify_second` VALUES ('1001', '华语男歌手', '1000', '1');
INSERT INTO `classify_second` VALUES ('1002', '华语女歌手', '1000', '1');
INSERT INTO `classify_second` VALUES ('1003', '华语组合/乐队', '1000', '1');
INSERT INTO `classify_second` VALUES ('2001', '欧美男歌手', '2000', '1');
INSERT INTO `classify_second` VALUES ('2002', '欧美女歌手', '2000', '1');
INSERT INTO `classify_second` VALUES ('2003', '欧美组合/乐队', '2000', '1');
INSERT INTO `classify_second` VALUES ('3001', '其他男歌手', '3000', '1');
INSERT INTO `classify_second` VALUES ('3002', '其他女歌手', '3000', '1');
INSERT INTO `classify_second` VALUES ('3003', '其他组合/乐队', '3000', '1');

-- ----------------------------
-- Table structure for comment_album
-- ----------------------------
DROP TABLE IF EXISTS `comment_album`;
CREATE TABLE `comment_album` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `albumId` varchar(255) NOT NULL,
  `creatorId` varchar(255) NOT NULL,
  `agreeNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commentCI_user` (`creatorId`),
  KEY `comment_album_ibfk_2` (`albumId`),
  CONSTRAINT `comment_album_ibfk_1` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `comment_album_ibfk_2` FOREIGN KEY (`albumId`) REFERENCES `album_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_album
-- ----------------------------
INSERT INTO `comment_album` VALUES ('0ca1f7d6b18b43d5bea734042139bdbc', '<img class=\"emoji\" data-name=\":flushed:\" alt=\"\" src=\"svg/1f633.svg\">', '2016-11-03 18:27:38', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_album` VALUES ('1', '时间大家说过', '2016-07-13 19:17:45', null, '1', '1', '1');
INSERT INTO `comment_album` VALUES ('10', '大苏打', '2016-07-20 14:58:52', null, '1', '5', '1');
INSERT INTO `comment_album` VALUES ('11', '鬼地方', '2016-06-28 14:58:55', null, '1', '4', '1');
INSERT INTO `comment_album` VALUES ('2', '啊好多咯', '2016-07-08 19:18:19', null, '1', '2', '100');
INSERT INTO `comment_album` VALUES ('3', '阿加莎', '2016-07-25 19:18:23', null, '1', '1', '1');
INSERT INTO `comment_album` VALUES ('32d88fe6a5b549038fe6dfa76618bdb1', '<img class=\"emoji\" data-name=\":stuck_out_tongue_winking_eye:\" alt=\"\" src=\"svg/1f61c.svg\">', '2016-09-10 21:19:04', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_album` VALUES ('4', '打开手机和点卡', '2016-07-31 19:18:28', null, '1', '3', '2');
INSERT INTO `comment_album` VALUES ('48d9f06b485c4685be05349f59e721dd', '<img class=\"emoji\" data-name=\":wink:\" alt=\"\" src=\"svg/1f609.svg\">', '2017-02-28 10:18:11', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_album` VALUES ('5', '啊哦我们系', '2016-07-15 19:18:31', null, '1', '1', '100');
INSERT INTO `comment_album` VALUES ('6', '囧啊睡觉的', '2016-07-07 14:58:33', null, '1', '5', '1');
INSERT INTO `comment_album` VALUES ('7', '和大家开始', '2016-07-05 14:58:38', null, '1', '4', '15');
INSERT INTO `comment_album` VALUES ('8', 'asda是', '2016-07-31 14:58:43', null, '1', '3', '30');
INSERT INTO `comment_album` VALUES ('8168eac2793b4c1294b2a25f4fc03142', '<img class=\"emoji\" data-name=\":thermometer_face:\" alt=\"\" src=\"svg/1f912.svg\">', '2016-09-10 21:17:06', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_album` VALUES ('9', '大神', '2016-08-07 14:58:47', null, '1', '2', '32');

-- ----------------------------
-- Table structure for comment_song
-- ----------------------------
DROP TABLE IF EXISTS `comment_song`;
CREATE TABLE `comment_song` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `songId` varchar(255) NOT NULL,
  `creatorId` varchar(255) NOT NULL,
  `agreeNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commentCI_user` (`creatorId`),
  KEY `comment_song_ibfk_1` (`songId`),
  CONSTRAINT `comment_song_ibfk_1` FOREIGN KEY (`songId`) REFERENCES `song_base` (`id`),
  CONSTRAINT `comment_song_ibfk_2` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_song
-- ----------------------------
INSERT INTO `comment_song` VALUES ('1', '时间大家说过', '2016-07-13 19:17:45', null, '1', '1', '1');
INSERT INTO `comment_song` VALUES ('10', '大苏打', '2016-07-20 14:58:52', null, '1', '5', '1');
INSERT INTO `comment_song` VALUES ('11', '鬼地方', '2016-06-28 14:58:55', null, '1', '4', '1');
INSERT INTO `comment_song` VALUES ('2', '啊好多咯', '2016-07-08 19:18:19', null, '1', '2', '100');
INSERT INTO `comment_song` VALUES ('3', '阿加莎', '2016-07-25 19:18:23', null, '1', '1', '1');
INSERT INTO `comment_song` VALUES ('30d4f2b3fcc44cbf934b70fbfc79e4e9', '<img class=\"emoji\" data-name=\":sleeping:\" alt=\"\" src=\"svg/1f634.svg\">', '2016-09-10 21:19:47', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_song` VALUES ('4', '打开手机和点卡', '2016-07-31 19:18:28', null, '1', '3', '2');
INSERT INTO `comment_song` VALUES ('5', '啊角度来说卡了', '2016-07-15 19:18:31', null, '1', '1', '100');
INSERT INTO `comment_song` VALUES ('6', '囧啊睡觉的', '2016-07-07 14:58:33', null, '1', '5', '1');
INSERT INTO `comment_song` VALUES ('7', '和大家开始', '2016-07-05 14:58:38', null, '1', '4', '15');
INSERT INTO `comment_song` VALUES ('8', 'asda是', '2016-07-31 14:58:43', null, '1', '3', '30');
INSERT INTO `comment_song` VALUES ('9', '大神', '2016-08-07 14:58:47', null, '1', '2', '32');
INSERT INTO `comment_song` VALUES ('a14d010d24b5413cbd4cf75f79e76b3f', '<img class=\"emoji\" data-name=\":kissing_heart:\" alt=\"\" src=\"svg/1f618.svg\">', '2016-11-03 17:16:18', '0', '1', '1527188383414715966829442506b666', '1');

-- ----------------------------
-- Table structure for comment_songlist
-- ----------------------------
DROP TABLE IF EXISTS `comment_songlist`;
CREATE TABLE `comment_songlist` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `songListId` varchar(255) NOT NULL,
  `creatorId` varchar(255) NOT NULL,
  `agreeNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commentCI_user` (`creatorId`),
  KEY `comment_songlist_ibfk_2` (`songListId`),
  CONSTRAINT `comment_songlist_ibfk_1` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `comment_songlist_ibfk_2` FOREIGN KEY (`songListId`) REFERENCES `songlist_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_songlist
-- ----------------------------
INSERT INTO `comment_songlist` VALUES ('1', '时间大家说过', '2016-07-13 19:17:45', null, '1', '1', '1');
INSERT INTO `comment_songlist` VALUES ('10', '大苏打', '2016-07-20 14:58:52', null, '1', '5', '1');
INSERT INTO `comment_songlist` VALUES ('11', '鬼地方', '2016-06-28 14:58:55', null, '1', '4', '1');
INSERT INTO `comment_songlist` VALUES ('2', '啊好多咯', '2016-07-08 19:18:19', null, '1', '2', '100');
INSERT INTO `comment_songlist` VALUES ('3', '阿加莎', '2016-07-25 19:18:23', null, '1', '1', '1');
INSERT INTO `comment_songlist` VALUES ('3d73b20a71024c599910aae83fdd239b', '<img class=\"emoji\" data-name=\":scream:\" alt=\"\" src=\"svg/1f631.svg\">', '2016-09-10 21:19:23', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songlist` VALUES ('4', '打开手机和点卡', '2016-07-31 19:18:28', null, '1', '3', '2');
INSERT INTO `comment_songlist` VALUES ('5', '啊角度来说卡了', '2016-07-15 19:18:31', null, '1', '1', '100');
INSERT INTO `comment_songlist` VALUES ('6', '囧啊睡觉的', '2016-07-07 14:58:33', null, '1', '5', '1');
INSERT INTO `comment_songlist` VALUES ('7', '和大家开始', '2016-07-05 14:58:38', null, '1', '4', '15');
INSERT INTO `comment_songlist` VALUES ('8', 'asda是', '2016-07-31 14:58:43', null, '1', '3', '30');
INSERT INTO `comment_songlist` VALUES ('9', '大神', '2016-08-07 14:58:47', null, '1', '2', '32');

-- ----------------------------
-- Table structure for comment_songmenu
-- ----------------------------
DROP TABLE IF EXISTS `comment_songmenu`;
CREATE TABLE `comment_songmenu` (
  `id` varchar(255) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `songMenuId` varchar(255) NOT NULL,
  `creatorId` varchar(255) NOT NULL,
  `agreeNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `commentCI_user` (`creatorId`),
  KEY `commentSI_user` (`songMenuId`),
  CONSTRAINT `commentCI_user` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `commentSI_user` FOREIGN KEY (`songMenuId`) REFERENCES `songmenu_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment_songmenu
-- ----------------------------
INSERT INTO `comment_songmenu` VALUES ('1', '时间大家说过', '2016-07-13 19:17:45', '1', '1', '1', '1');
INSERT INTO `comment_songmenu` VALUES ('10', '大苏打', '2016-07-20 14:58:52', '1', '1', '5', '1');
INSERT INTO `comment_songmenu` VALUES ('11', '鬼地方', '2016-06-28 14:58:55', '1', '1', '4', '1');
INSERT INTO `comment_songmenu` VALUES ('2', '啊好多咯', '2016-07-08 19:18:19', '1', '1', '2', '101');
INSERT INTO `comment_songmenu` VALUES ('265545fe8d3540bf8e9502c7b187d5cf', 'asda<img class=\"emoji\" data-name=\":thinking:\" alt=\"\" src=\"svg/1f914.svg\">', '2016-09-10 20:35:48', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('3', '阿加莎', '2016-07-25 19:18:23', '1', '1', '1', '1');
INSERT INTO `comment_songmenu` VALUES ('4', '打开手机和点卡', '2016-07-31 19:18:28', '1', '1', '3', '2');
INSERT INTO `comment_songmenu` VALUES ('41c7ec5b10144af4849652c6c4518ea2', '<img class=\"emoji\" data-name=\":blush:\" alt=\"\" src=\"svg/1f60a.svg\">', '2016-09-11 16:13:37', '0', '1', '1527188383414715966829442506b666', '2');
INSERT INTO `comment_songmenu` VALUES ('4509806bd5cb48798d5f72ef68e39e41', '<img class=\"emoji\" data-name=\":no_mouth:\" alt=\"\" src=\"svg/1f636.svg\">', '2017-01-28 10:35:54', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('5', '啊角度来说卡了', '2016-07-15 19:18:31', '1', '1', '1', '101');
INSERT INTO `comment_songmenu` VALUES ('6', '囧啊睡觉的', '2016-07-07 14:58:33', '1', '1', '5', '1');
INSERT INTO `comment_songmenu` VALUES ('64a640954ff7443cbb556cce72ddfd20', '<img class=\"emoji\" data-name=\":astonished:\" alt=\"\" src=\"svg/1f632.svg\">', '2017-01-28 10:37:20', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('64ac5c621cb84238a55e97bb571f649c', '<img class=\"emoji\" data-name=\":confused:\" alt=\"\" src=\"svg/1f615.svg\">', '2017-01-28 10:35:12', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('7', '和大家开始', '2016-07-05 14:58:38', '1', '1', '4', '16');
INSERT INTO `comment_songmenu` VALUES ('7e94303f8cf7462099f47c7837057311', '的吗上班', '2016-09-10 20:35:40', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('8', 'asda是', '2016-07-31 14:58:43', '1', '1', '3', '31');
INSERT INTO `comment_songmenu` VALUES ('86d19cf4c5e841ceaf3ddee9cd94bad8', '<img class=\"emoji\" data-name=\":sleeping:\" alt=\"\" src=\"svg/1f634.svg\">', '2017-01-28 10:33:51', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('8f03452bf59a47b388e9408d922719e1', '肯德基哦', '2016-09-10 16:44:03', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('9', '大神', '2016-08-07 14:58:47', '1', '1', '2', '35');
INSERT INTO `comment_songmenu` VALUES ('938911ec4b5b4c3b9155ef6f9813120d', '<img class=\"emoji\" data-name=\":hugging:\" alt=\"\" src=\"svg/1f917.svg\">', '2017-01-28 10:33:28', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('9aa515fae29347d3b4910580ce7bd7dc', '<img class=\"emoji\" data-name=\":relaxed:\" alt=\"\" src=\"svg/263a.svg\">', '2016-09-11 16:16:14', '0', '1', '1527188383414715966829442506b666', '3');
INSERT INTO `comment_songmenu` VALUES ('a04f23415b4c425d966bdfe149e4c2fa', 'undefined', '2016-09-10 20:15:05', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('aeade101cc864d279ba71367ad8af68e', '<img class=\"emoji\" data-name=\":grinning:\" alt=\"\" src=\"svg/1f600.svg\">', '2017-01-28 10:28:41', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('b9f5245a321c4c8396310d110d4ec0b5', '<img class=\"emoji\" data-name=\":grin:\" alt=\"\" src=\"svg/1f601.svg\">', '2017-01-28 10:28:56', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('bf75c1eb3c9b489fb421066a0d6f5952', '<img class=\"emoji\" data-name=\":disappointed:\" alt=\"\" src=\"svg/1f61e.svg\">', '2016-11-18 22:05:31', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('c132a02788aa467ea6c82fae7bb8166a', '就卡刷点卡<img class=\"emoji\" data-name=\":disappointed:\" alt=\"\" src=\"svg/1f61e.svg\">', '2016-09-10 20:35:07', '0', '2', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('c654490df8c14436ad584a626be395de', '<img class=\"emoji\" data-name=\":heart_eyes:\" alt=\"\" src=\"svg/1f60d.svg\">', '2016-09-10 20:45:02', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('caf879d4707d41b489f2bec1b0f20fe0', '<img class=\"emoji\" data-name=\":joy:\" alt=\"\" src=\"svg/1f602.svg\"><img class=\"emoji\" data-name=\":rolling_eyes:\" alt=\"\" src=\"svg/1f644.svg\"><img class=\"emoji\" data-name=\":rolling_eyes:\" alt=\"\" src=\"svg/1f644.svg\">', '2016-12-27 12:30:25', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('d6ff4546d80446dab8338e2c247d5ca0', '啊谁家的咯', '2016-09-10 16:43:30', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('e0d0346f27bd493e94716a2d18ebc8cc', '肯德基哈考试的', '2016-09-10 16:42:31', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('e492087eb6974321b4893b68873b56e1', '<img class=\"emoji\" data-name=\":relaxed:\" alt=\"\" src=\"svg/263a.svg\">', '2017-01-28 10:29:32', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('e4c0b2fbd638428897f2b1105acd2951', '杀开多久啊老师', '2016-09-10 16:39:48', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('e5015729ff204ef79593fac2acc811c3', '<img class=\"emoji\" data-name=\":thinking:\" alt=\"\" src=\"svg/1f914.svg\">', '2016-11-03 16:56:22', '0', '1', '1527188383414715966829442506b666', '7');
INSERT INTO `comment_songmenu` VALUES ('e99f18086ea3471c83e575933612a50f', '<img class=\"emoji\" data-name=\":frowning2:\" alt=\"\" src=\"svg/2639.svg\"><img class=\"emoji\" data-name=\":money_mouth:\" alt=\"\" src=\"svg/1f911.svg\"><img class=\"emoji\" data-name=\":hushed:\" alt=\"\" src=\"svg/1f62f.svg\">', '2016-09-10 20:25:44', '0', '1', '1527188383414715966829442506b666', '0');
INSERT INTO `comment_songmenu` VALUES ('f389d1d07dfd4e40bc5e2598b57463f5', 'undefined', '2016-09-10 20:11:23', '0', '1', '1527188383414715966829442506b666', '1');
INSERT INTO `comment_songmenu` VALUES ('f5e5daf91b1745c3aeb393e37bd41a8f', '萨达是快乐的消除心中<img class=\"emoji\" data-name=\":smile:\" alt=\"\" src=\"svg/1f604.svg\">阿三大萨达个凤凰剧院<img class=\"emoji\" data-name=\":kissing_smiling_eyes:\" alt=\"\" src=\"svg/1f619.svg\">', '2016-09-10 20:33:13', '0', '1', '1527188383414715966829442506b666', '1');

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` varchar(255) NOT NULL,
  `creatorName` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `sendUserId` varchar(255) DEFAULT NULL,
  `creatorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `informationCI_user` (`creatorId`),
  KEY `informationSI_user` (`sendUserId`),
  CONSTRAINT `informationCI_user` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `informationSI_user` FOREIGN KEY (`sendUserId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------

-- ----------------------------
-- Table structure for privatemessage
-- ----------------------------
DROP TABLE IF EXISTS `privatemessage`;
CREATE TABLE `privatemessage` (
  `id` varchar(255) NOT NULL,
  `creatorName` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `isChecked` tinyint(1) DEFAULT NULL,
  `sendUserId` varchar(255) DEFAULT NULL,
  `creatorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `privateMessageCI_user` (`creatorId`),
  KEY `privateMessageSI_user` (`sendUserId`),
  CONSTRAINT `privateMessageCI_user` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `privateMessageSI_user` FOREIGN KEY (`sendUserId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privatemessage
-- ----------------------------

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `introduction` text,
  `userId` varchar(255) DEFAULT NULL,
  `collectionNum` int(11) DEFAULT NULL,
  `classifyId` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `singer_user` (`userId`),
  KEY `singer_cs` (`classifyId`),
  CONSTRAINT `singer_cs` FOREIGN KEY (`classifyId`) REFERENCES `classify_second` (`id`),
  CONSTRAINT `singer_user` FOREIGN KEY (`userId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES ('0422a500fb46411c8f58d295f6c14064', 'Justin Bieber', 'image/singer/2538772353415190.jpg', '贾斯汀.比伯;Justin Drew Bieber', '加拿大少年歌手Justin Bieber，早期因为在YouTube翻唱其他艺人的歌曲而出名，随后被经纪人挖掘并被Usher培养进入美国音乐歌坛。他是YouTube观看量最多的艺人及首位19岁前拥有五张冠军专辑、在首张专辑发行前就有4首TOP40单曲的歌手。2011《人物》杂志公布的年度好莱坞最富有的年轻人。在“福布斯全球百位巨星排行榜”上贾斯汀连续2年名列第三，男歌手权利排行第一，推特粉丝全球第一。Billboard与VH1评选的当今最具影响童星。', null, '0', '2001', '1');
INSERT INTO `singer` VALUES ('0986ba72bc3a40618678c8f61a21d361', 'Taylor Swift', 'image/singer/7923080789957002.jpg', '泰勒·斯威夫特', '泰勒·斯威夫特（Taylor Swift），美国乡村音乐著名创作女歌手。1989年出生于美国宾州。2006年与独立唱片公司Big Machine签约并发行首张个人专辑《Taylor Swift》。第二张专辑《Fearless》在2008年11月11日发行，在Billboard排行榜上到达了第一的位置，首支单曲《Love Story》在2008年9月正式发行，成为了第二畅销的乡村单曲，在公告牌最热100中最高排到第四。该专辑也卖出了乡村音乐最高的销售量约60万张，包括其他种类的音乐，也是美国女歌手公开销售最多的专辑。Taylor曾获得美国乡村音乐协会奖年度最佳专辑奖、格莱美年度专辑奖等荣誉。', null, '0', '2002', '1');
INSERT INTO `singer` VALUES ('1', 'GALA', 'image/singer/3229265650823140.jpg', '', 'GALA是由4个80年代出生的青年组成，是一支只用吉他来创造美妙旋律的乐队。成员有苏朵、苏依拉、石亮、于政。他们的一首《YOUNG FOR YOU》是大家都较为熟知的歌曲。2012年4月8日，获得第十二届音乐风云榜年度盛典最佳摇滚乐队。GALA是一支只用吉他来创造美妙旋律的乐队。这是四个对音乐充满热情又执迷不悟的小伙子。他们身上带着年轻人毫不掩饰的冲动和桀骜不驯的锋芒。随着年龄的增长，他们在音乐里加入了更多的情绪和手法，也不断尝试着音乐的各种可能性。', '5', '100', '1003', '1');
INSERT INTO `singer` VALUES ('10300f1269e9438099154dcc05a53823', 'EGOIST', 'image/singer/924689278984279.jpg', 'エゴイスト', 'EGOIST是在2011年10月TV动画「罪恶王冠」中设定的拥有超高人气及超大影响力的的网络歌手，在剧中演唱OP/ED以及插曲。而实际上这位歌手则是由supercell的ryo担任制作、并由ryo选出的chelly担任演唱的角色。', null, '0', '3003', '1');
INSERT INTO `singer` VALUES ('1b8536e4c8974d009bfe53980b0267b8', '张惠妹', 'image/singer/1475749049047228eb37d45858d4c8f9.jpeg', 'aMEI;阿密特', '中国台湾著名女歌手，亚洲流行歌坛重量级天后，台湾原住民歌手。张惠妹于华人世界名气极高，于海外（尤美、日等地）也有一定的知名度，已囊括多项华语圈重要的音乐奖项。台湾首位接受美国CNN专访的歌手（并且在2011年第二度接受此媒体《Talk Asia》节目专访），也是首位登上《时代》杂志亚洲版封面、且入选亚洲二十大风云人物及登上亚洲周刊封面的歌手。阿妹在1996年三月被著名台湾音乐人陈志远、陈复明发现并签约丰华唱片。并在同年7月在带自己入行的恩师张雨生的《两伊战争－红色热情》专辑中与张雨生男女对唱《最爱的人伤我最深》。同年12月13日，张惠妹在张雨生的协助下发行第一张个人专辑《姐妹》。1998年 举办第一场个人大型户外售票演唱会“妹力四射”，创下台湾歌手有史以来最快举行大型演唱会的纪录。2009年发行的《阿密特》专辑至今仍是金曲奖史上获得最大成功的作品，曾于第21届时擒下包括专辑、制作人、歌手等六项大奖。代表作品：《姐妹》、《我可以抱你吗》、《记得》、《火》、《如果你也听说》、《掉了》、《我最亲爱的》。', null, '0', '1002', '1');
INSERT INTO `singer` VALUES ('2', 'Maroon 5', 'image/singer/210006720922693.jpg', '魔力红;褐红五人组', 'Maroon5，中文译名魔力红乐队，来自LA的摇滚乐队，确切一些说是新灵魂摇滚。maroon 融合了红色的狂野魅力，神秘热情，奔放烈爱再掺入摇滚力道，灵魂旋律及放克节奏，这样的红流泻着新灵魂乐性感风情的放克摇滚这样的红，在流行歌坛注入了一股新的颜色。', null, '1', '2003', '1');
INSERT INTO `singer` VALUES ('24b8d4d63ed943ef995c2183ba025c49', '尚雯婕', 'image/singer/837827860390612.jpg', 'Laure Shang', '内地著名女歌手，原创电子唱作人。多次受邀出席巴黎、纽约时装周，有华语“潮流女王”、“个性天后”之称。2006年“超级女声”冠军， 2007年6月，尚雯婕签约华谊兄弟音乐，同时发行了首张个人EP大碟《梦之浮桥》。该EP名称源自尚雯婕为好莱坞电影《面纱》配唱的中文主题曲。10月22日，发行个人首张专辑《在梵高的星空下》。还曾两度献唱冯小刚导演电影《非诚勿扰》、《唐山大地震》主题曲。2008年3月1日，音乐生涯里的第一场演唱会——尚佳分享·尚雯婕2008北京演唱会。2013年参加节目《我是歌手》，将个人实力发挥的淋漓尽致，再次吸引了大家的注意，获得了广泛的好评。代表作品：《最终信仰》，《战》，《候鸟》，《连卡佛小姐》。', null, '0', '1002', '1');
INSERT INTO `singer` VALUES ('3', 'Owl City', 'image/singer/174822348833527.jpg', '亚当·扬;Adam Young;杨亚当;杨雅丹', '猫头鹰之城乐队（Owl City）是一支来自美国明尼苏达州的电子音乐乐队。乐队仅由一名成员组成：亚当·扬（Adam·Young）。亚当·扬于2007年创建了该乐队，并且担任乐队的主唱、编曲、创作、混音合成工作。Owl City的音乐飘逸、灵动，既有梦幻的神秘色彩，又有电子音乐的动感节奏。Ocean Eyes专辑中的主打歌曲《Fireflies》一经推出就登上了各大音乐电台以及音乐榜单的冠军宝座，受到乐迷的热烈追捧。', null, '3', '2001', '1');
INSERT INTO `singer` VALUES ('354169e500304711a138f308c1a22897', '初音ミク', 'image/singer/3251255899603164.jpg', '初音未来;はつね ミク;Hatsune Miku', '初音未来（初音ミク），通常简称为“初音”。初音未来（初音ミク，中文界部分人简称为“初音”）可指CRYPTON FUTURE MEDIA以Yamaha的VOCALOID 2语音合成引擎为基础开发贩售的虚拟女性歌手软件角色主唱系列的第一作；亦可指该音乐软件的拟人化产物，是此软件的角色，也衍生出动漫和游戏人物。', null, '0', '3002', '1');
INSERT INTO `singer` VALUES ('4', '后弦', 'image/singer/3295236355687273.jpg', '邓文彬', '内地青年全能音乐人、歌手。1997年就读广西大学，求学时间接触原创音乐，开始积累大量原创及舞台表演经验。大学时代发起组织了B2B和声组合获广西最受欢迎学生创作组合，HBH四人青春组合，短路乐队（担任鼓手），连续三年获广西大学校园十大歌手称号。1999年开始，接触MIDI编曲，开始创作了大量原创歌曲。2004年底，艺名正式取为后弦。2005年10月，首张专辑《古玩》隆重上市。随后发行的 EP 《九公主》、《东方不败》、 EP《娃娃脸》以及最新专辑《很有爱》纷纷得了骄人的成绩，其包办词曲的创作能力也得到了肯定。代表作品：《西厢》《九公主》《单车恋人》《娃娃脸》。', '4', '532', '1001', '1');
INSERT INTO `singer` VALUES ('5', '南征北战', 'image/singer/1695446930051837.jpg', null, '南征北战成立于于2007年，目前共有3位成员，分别是汀洋，醉人，中国黑人。 各个成员擅长风格各异，包括电子、Hardcore Rap、 Funk、 Gangsta Rap、 Rap Metal、 Pop Rap、 Old School等等，每个成员说唱功力十分了得，从快嘴弹舌到硬核唱腔，无所不及。 他们是集作词、作曲、编曲、混音为一体的全能音乐团体，被誉为中国版的“林肯公园”。', '3', '134', '1003', '1');
INSERT INTO `singer` VALUES ('5dcd4d10774e443589916de44b956c47', '花澤香菜', 'image/singer/3272146612372963.jpg', '花泽香菜;はなざわ かな;Hanazawa Kana', '日本的女性艺人、声优、演员。儿童演员出身，2006年开始声优工作， 2012年2月宣布以个人歌手名义出道，唱片公司为Aniplex，4月25日推出首张单曲《星空☆ディスティネーション》。', null, '0', '3002', '1');
INSERT INTO `singer` VALUES ('6', '徐佳莹', 'image/singer/181419418599618.jpg', 'LALA', '台湾女歌手，在《超级星光大道》第三届比赛中表现优异，以自创曲《身骑白马》拿下25分满分的成绩，是超级星光大道开播最快得到满分的参赛者，并拿下当届总冠军。2009年5月29日发行个人首张创作专辑《徐佳莹LaLa 首张创作专辑》，最新专辑《理想人生》于2012年6月6日发行。', '2', '734', '1002', '1');
INSERT INTO `singer` VALUES ('7', '张杰', 'image/singer/668503069713228.jpg', 'Jason Zhang', '华语歌坛新生代领军人物，偶像与实力兼具的超人气天王。2004年出道至今，已发行9张高品质唱片，唱片销量称冠内地群雄。2008年以来举办过9场爆满的个人演唱会，在各大权威音乐奖项中先后21次获得“最受欢迎男歌手”称号，2012年度中国TOP排行榜内地最佳男歌手，2010年在韩国M-net亚洲音乐大赏(MAMA)上获得“亚洲之星”（Best Asian Artist）大奖，影响力触及海外。2010年作为歌手代表受邀担任博鳌亚洲论坛表演嘉宾。2012年在北京人民大会堂开启了巡演的历程，同年捐资200万作为北斗星空爱心基金创始基金，并启动“张杰音乐梦想教室”公益项目，自2012年10月份起已陆续在全国各地建立了六所“张杰音乐梦想教室”。', '1', '83', '1001', '1');
INSERT INTO `singer` VALUES ('7288ccbbd92246d3a28db7b67cc1b2df', '澤野弘之', 'image/singer/1382086122014735.jpg', '泽野弘之;さわの ひろゆき;Sawano Hiroyuki', '日本作曲家。出生于东京都。以电视剧、动画、电影、电影音乐为中心进行作曲和编曲工作，同时也为许多艺人写作曲子。所属事务所为Legendoor。代表作包括电视剧《医龙-Team Medical Dragon-》系列配乐、动画《机动战士高达 UC》、《罪恶王冠》、《进击的巨人》、《KILL la KILL》、《ALDNOAH.ZERO》、《青之驱魔师》、《终结的炽天使》、《甲铁城的卡巴内里》系列配乐等。', null, '0', '3001', '1');
INSERT INTO `singer` VALUES ('7ca6a02f51324867b8d4bd6db72620a5', 'Avril Lavigne', 'image/singer/733374255761178.jpg', '艾薇儿', '艾薇儿·拉维尼（Avril Lavigne），加拿大著名流行女歌手、摇滚小天后、歌曲创作者及演员。 2002年并以歌曲《Skater Boy》出名。其后的专辑《Let go》与《The Best Damn Thing》在数个国家的音乐排行榜上达到最前列。2006年入选《加拿大商业杂志》在好莱坞最有影响力的加拿大人（排行第17位）。2010年担任温哥华冬奥会闭幕式嘉宾，同年为迪士尼电影《爱丽丝梦游仙境》献唱主题曲《Alice》。2011年3月8日发布新专辑《Goodbye Lullaby》。Avril对音乐、时尚、个性以及性感的定义被年青人所普遍接受和模仿，是乐坛的领军人物。其积极向上，充满乐观的精神一直被大家所支持的。', null, '0', '2002', '1');
INSERT INTO `singer` VALUES ('92ddb9c6c0134a0dab6ca130b76a3fda', '马頔', 'image/singer/2922501907254769.jpg', null, '独立民谣音乐人，诗人，专栏作家，2010年开始民谣音乐演出，2011年组建麻油叶民间组织厂牌。2011年至今，在全国范围内组织厂牌进行了数十场民谣相关演出。', null, '0', '1001', '1');
INSERT INTO `singer` VALUES ('9d63ea982ebe422c9660080379c6959e', 'Carly Rae Jepsen', 'image/singer/179220395344787.jpg', null, 'Carly Rae Jepsen是一位来自加拿大的女歌手。2007年参加“Canadian Idol”选秀节目。表现突出的她获得了季军，虽说最后没有如愿夺下后冠，不过声音甜美，却不失个人风格的Carly Rae Jepsen也很快的发行的个人的首张专辑《Tug Of War》，曲风大多活泼律动或是温润清新，而且几乎首首动听。', null, '0', '2002', '1');
INSERT INTO `singer` VALUES ('a899a4687b984a66874646751f3b1010', '李志', 'image/singer/6049512976723323.jpg', null, '李志，南京知名音乐人。1978年生于江苏金坛。1997年进入东南大学自动控制系，之后一直在南京工作生活至今。以独立音乐人的身份出版发行了六张录音室专辑《被禁忌的游戏》（2004）、《梵高先生》（2005）、《这个世界会好吗》（2006）、《我爱南京》（2009）、《你好郑州》（2010）、《F》（2011），和三张演唱会录音专辑《工体东路没有人》（2009）、《勾三搭四》（2014）、《i/O》（2015）。', null, '0', '1001', '1');
INSERT INTO `singer` VALUES ('a98ceefbe51b43e588e2deac7377c524', '久石譲', 'image/singer/3306231466946799.jpg', '久石让;Joe Hisaishi', '久石让，日本著名作曲家、歌手、钢琴家，以担任电影配乐为主，是宫崎骏作品中不可欠缺的配乐大师，从宫崎骏的《风之谷》至《悬崖上的金鱼公主》的二十多年间所有长篇动画电影的音乐制作，更与北野武是黄金组合，创作了《坏孩子的天空》、《花火》、《菊次郎的夏天》、《大佬》等佳作的配乐。1981年久石让推出第一张专辑《Information》，确定了自己的音乐风格。1983年，经人介绍，久石让结识了宫崎骏，并为《风之谷》配乐，让当时不太有名的久石让受到了公众的注目，1985年宫崎骏创建“吉卜力”工作室后，久石让便一直担当宫崎骏动画的音乐监督至今。主要配乐作品有《天空之城》《龙猫》《菊次郎的夏天》等。', null, '0', '3001', '1');
INSERT INTO `singer` VALUES ('b88aa86cb91141ff86a77f0e2757d667', '羽·泉', 'image/singer/241892558127623.jpg', '野孩子', '中国大陆最受欢迎及知名度最高的男声音乐组合之一，由陈羽凡和胡海泉于1998年6月组成，组合名称各取两人名字中的一个字。他们创作演唱的歌曲很快便以清新灵动的曲风，激情感性的演绎在北京的音乐圈内引起广泛的瞩目，其中也吸引了始终关注内地原创音乐滚石（中国）唱片，通过接触迅速与之签约，并将在年底推出他们的首张专辑《最美》，依靠其出色的唱功与和声以及原创作品的流行潜质迅速走红大中华地区。曾先后发行12张音乐专辑，唱片累计销量逾1000万张。', null, '0', '1003', '1');
INSERT INTO `singer` VALUES ('c0f54aa5c6eb4c85a8e62b9eadb4df98', 'Bruno Mars', 'image/singer/3312828536306856.jpg', '布鲁诺·马尔斯;布鲁诺·马尔斯', '彼得·基恩·埃尔南德斯（Peter Gene Hernandez，1985年10月8日－），以其艺名布鲁诺·马尔斯（Bruno Mars）出名，是美国的创作型歌手、音乐制作人。成长于夏威夷火奴鲁鲁的音乐人世家中，马尔斯自幼就开始创作音乐。其童年时期在当地的多家音乐场所表演后，他立志以音乐为业，并在高中毕业后搬到洛杉矶。 在摩城唱片公司度过一段并不成功的职业生涯后，马尔斯在2009年同大西洋唱片签约。在为B.o.B的“Nothin\' on you”以及崔维·麦考伊的“Billionaire”创作并献声后，他作为独唱艺人的才华得到重视。', null, '0', '2001', '1');
INSERT INTO `singer` VALUES ('e6cb866b51394a5d82a96c86a58e0527', '莫文蔚', 'image/singer/3252355405741914.jpg', 'Karen Mok', '香港著名影、视、歌多栖女星。在多部电影、电视剧中担纲主演，并且出有多张专辑并获得重量级音乐奖项。是华人娱乐圈中非常成功的女星之一。英国伦敦大学毕业后，莫文蔚本打算在英国筹划舞台剧。那时她认识了雷颂德，并在偶然下被唱片公司发掘。2011年6月18日，第22届台湾流行音乐金曲奖颁奖典礼在台北小巨蛋落下帷幕。莫文蔚凭借《宝贝》获封歌后，获得最佳国语女歌手奖。影视代表作品：《喜剧之王》，《食神》；音乐代表作品：《如果没有你》，《广岛之恋》，《盛夏的果实》，《忽然之间》，《电台情歌》，《阴天》。', null, '0', '1002', '1');

-- ----------------------------
-- Table structure for smi_gl_rsms
-- ----------------------------
DROP TABLE IF EXISTS `smi_gl_rsms`;
CREATE TABLE `smi_gl_rsms` (
  `songMenuId` varchar(255) NOT NULL,
  `tag_songMenu_secondId` varchar(255) NOT NULL,
  PRIMARY KEY (`songMenuId`,`tag_songMenu_secondId`),
  KEY `secondTagId` (`tag_songMenu_secondId`),
  CONSTRAINT `secondTagId` FOREIGN KEY (`tag_songMenu_secondId`) REFERENCES `tag_songmenu_second` (`id`),
  CONSTRAINT `songMenuId` FOREIGN KEY (`songMenuId`) REFERENCES `songmenu_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of smi_gl_rsms
-- ----------------------------
INSERT INTO `smi_gl_rsms` VALUES ('1', '1');
INSERT INTO `smi_gl_rsms` VALUES ('10', '1');
INSERT INTO `smi_gl_rsms` VALUES ('11', '1');
INSERT INTO `smi_gl_rsms` VALUES ('2', '1');
INSERT INTO `smi_gl_rsms` VALUES ('3', '1');
INSERT INTO `smi_gl_rsms` VALUES ('4', '1');
INSERT INTO `smi_gl_rsms` VALUES ('5', '1');
INSERT INTO `smi_gl_rsms` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', '1');
INSERT INTO `smi_gl_rsms` VALUES ('6', '1');
INSERT INTO `smi_gl_rsms` VALUES ('7', '1');
INSERT INTO `smi_gl_rsms` VALUES ('73b42d99ea5644ce840ef61d5a560fd6', '1');
INSERT INTO `smi_gl_rsms` VALUES ('8', '1');
INSERT INTO `smi_gl_rsms` VALUES ('9', '1');
INSERT INTO `smi_gl_rsms` VALUES ('acdf9e54b4414e259f83baefae5fbe63', '1');
INSERT INTO `smi_gl_rsms` VALUES ('1', '2');
INSERT INTO `smi_gl_rsms` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', '2');
INSERT INTO `smi_gl_rsms` VALUES ('73b42d99ea5644ce840ef61d5a560fd6', '2');
INSERT INTO `smi_gl_rsms` VALUES ('acdf9e54b4414e259f83baefae5fbe63', '2');
INSERT INTO `smi_gl_rsms` VALUES ('1', '3');
INSERT INTO `smi_gl_rsms` VALUES ('3', '3');
INSERT INTO `smi_gl_rsms` VALUES ('73b42d99ea5644ce840ef61d5a560fd6', '3');
INSERT INTO `smi_gl_rsms` VALUES ('acdf9e54b4414e259f83baefae5fbe63', '3');
INSERT INTO `smi_gl_rsms` VALUES ('2', '4');
INSERT INTO `smi_gl_rsms` VALUES ('1', '5');
INSERT INTO `smi_gl_rsms` VALUES ('3', '5');
INSERT INTO `smi_gl_rsms` VALUES ('5', '7');
INSERT INTO `smi_gl_rsms` VALUES ('3', '8');

-- ----------------------------
-- Table structure for songlist_base
-- ----------------------------
DROP TABLE IF EXISTS `songlist_base`;
CREATE TABLE `songlist_base` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `refreshRate` varchar(255) DEFAULT NULL,
  `songNum` int(11) DEFAULT '0',
  `refreshDate` datetime DEFAULT NULL,
  `globe` tinyint(1) DEFAULT '0',
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of songlist_base
-- ----------------------------
INSERT INTO `songlist_base` VALUES ('1', '云音乐飙升榜', 'image/songList/7744959906898786.jpg', '每天更新', '8', '2016-10-15 15:51:03', '0', '1');
INSERT INTO `songlist_base` VALUES ('2', '云音乐新歌榜', 'image/songList/7808731581312441.jpg', '每天更新', '8', '2016-08-05 16:32:04', '0', '1');
INSERT INTO `songlist_base` VALUES ('3', '原创歌曲榜', 'image/songList/2902710698975677.jpg', '每天更新', '3', '2016-08-05 16:32:07', '0', '1');
INSERT INTO `songlist_base` VALUES ('4', '法国 NRJ Vos Hits 周榜', 'image/songList/6623458046388376.jpg', '每周五更新', '0', '2016-07-29 16:32:10', '1', '1');
INSERT INTO `songlist_base` VALUES ('af30853648c6427e9053a98a6f5cf2a8', '韩国Melon排行榜周榜', 'image/songList/1c5dd2a68b89422e9db8b4710164cea1.jpeg', '每周一更新', '0', '2016-10-18 11:23:07', '1', '1');

-- ----------------------------
-- Table structure for songlist_record
-- ----------------------------
DROP TABLE IF EXISTS `songlist_record`;
CREATE TABLE `songlist_record` (
  `id` varchar(255) NOT NULL,
  `collectionNum` int(11) DEFAULT '0',
  `shareNum` int(11) DEFAULT '0',
  `commentNum` int(11) DEFAULT '0',
  `playNum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of songlist_record
-- ----------------------------
INSERT INTO `songlist_record` VALUES ('1', '1000', '231', '12', '1244573');
INSERT INTO `songlist_record` VALUES ('2', '0', '0', '0', '6');
INSERT INTO `songlist_record` VALUES ('3', '0', '0', '0', '4');
INSERT INTO `songlist_record` VALUES ('4', '0', '0', '0', '0');
INSERT INTO `songlist_record` VALUES ('af30853648c6427e9053a98a6f5cf2a8', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for songmenu_base
-- ----------------------------
DROP TABLE IF EXISTS `songmenu_base`;
CREATE TABLE `songmenu_base` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT 'image/default_songMenu.jpg',
  `creatorName` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `songNum` int(11) DEFAULT '0',
  `introduction` text,
  `creatorId` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `songMenu_user` (`creatorId`),
  KEY `userId` (`userId`),
  CONSTRAINT `songMenu_user` FOREIGN KEY (`creatorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `songmenu_base_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of songmenu_base
-- ----------------------------
INSERT INTO `songmenu_base` VALUES ('0af0e562342b4c358c09ec5443606443', 'aaaa', 'image/default_songMenu.jpg', 'xiedacon', '2016-08-26 16:35:43', '0', null, '1527188383414715966829442506b666', null, '0');
INSERT INTO `songmenu_base` VALUES ('0f9b22b059e248fc878834e3760ac43b', '喜欢的音乐', 'image/default_songMenu_love.jpg', 'xiedacon', '2016-08-19 16:51:22', '2', null, '1527188383414715966829442506b666', '1527188383414715966829442506b666', '1');
INSERT INTO `songmenu_base` VALUES ('1', '我许你一世倾城', 'image/songMenu/1412872454251398.jpg', '哈哈哈', '2016-07-12 21:27:23', '6', '哈萨克多哈开设卡刷点卡卡上的空间和', '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('10', 'hkjsahdk', 'image/default_songMenu.jpg', 'al', '2016-07-31 13:43:44', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('11', 'sadas', 'image/default_songMenu.jpg', '123', '2016-07-05 13:43:48', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('2', '似的发射点', 'image/songMenu/3393092897157678.jpg', '哈哈哈', '2016-07-07 21:30:42', '5', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('2477641d70a04e25a80d3fb5687950cf', 'asdaaa', 'image/default_songMenu.jpg', 'xiedacon', '2016-11-04 19:20:17', '0', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('3', '阿三大萨达', 'image/songMenu/3443670421354064.jpg', '哈哈哈', '2016-07-09 21:30:45', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('4', '户籍科，名', 'image/songMenu/3443670420516182.jpg', '哈哈哈', '2016-07-29 21:30:48', '0', null, '3', null, '1');
INSERT INTO `songmenu_base` VALUES ('5', '回家看回家看美国海回家看美国海回家看美国海回家看美国海军军军军美国海军', 'image/songMenu/3420580675599415.jpg', 'dasda', '2016-07-16 21:30:54', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', 'iiiiiiiii', 'image/songMenu/147825817593627544f6d67b89a448c8.jpeg', 'xiedacon', '2016-08-26 18:51:43', '3', 'asczxcas', '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('5f46d80f7e134ada964f84559749a32e', 'ggg', 'image/songMenu/147950642191017efae7672526e47bf8.jpeg', 'xiedacon', '2016-08-26 18:48:17', '2', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('6', '阿三大ioiuo', 'image/songMenu/3394192399875578.jpg', 'github', '2016-07-26 21:30:58', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('68671d58cd414703b4daf2615ad1bed9', 'ccc', 'image/songMenu/147950644335227d4304c87e4dc4b7a8.jpeg', 'xiedacon', '2016-08-26 18:17:25', '0', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('7', '破iytutr', 'image/songMenu/3394192399875578.jpg', 'asd', '2016-07-28 21:31:02', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('73b42d99ea5644ce840ef61d5a560fd6', 'ddd', 'image/songMenu/147950654134227b644becadd6044b38.jpeg', 'xiedacon', '2016-08-26 18:19:31', '1', '', '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('8', '拉萨空间大', 'image/songMenu/3265549557483643.jpg', 'da', '2016-07-20 21:31:05', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('892314fb751a4359afb701d108405cc4', 'jjj', 'image/default_songMenu.jpg', 'xiedacon', '2016-08-26 18:52:44', '0', null, '1527188383414715966829442506b666', null, '0');
INSERT INTO `songmenu_base` VALUES ('9', '啊可视电话', 'image/default_songMenu.jpg', '亲吻', '2016-07-03 13:43:09', '0', null, '1', null, '1');
INSERT INTO `songmenu_base` VALUES ('9a04c5ede9864826b07b576e93e6a040', 'fff', 'image/songMenu/147950646322322f9a54b04d796413bb.jpeg', 'xiedacon', '2016-08-26 18:47:53', '0', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('ab576d89c48c4d6cb79c6c5bac06becc', 'aaaa', 'image/songMenu/1479506521380251c7288da655944959.jpeg', 'xiedacon', '2016-08-26 16:32:25', '0', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('acdf9e54b4414e259f83baefae5fbe63', 'hhh', 'image/songMenu/1390882218174956.jpg', 'xiedacon', '2016-08-26 18:50:09', '0', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('c6a188911cff4c85871b4ed7803e406b', 'bbb', 'image/songMenu/1479506510422187ecb5d4e26b543c98.jpeg', 'xiedacon', '2016-08-26 16:35:21', '0', null, '1527188383414715966829442506b666', null, '1');
INSERT INTO `songmenu_base` VALUES ('db3daffee2e74231999999497089d38c', '喜欢的音乐', 'image/default_songMenu_love.jpg', 'github', '2016-08-22 17:00:58', '0', null, '14571125147185645883325e30497db4', '14571125147185645883325e30497db4', '1');
INSERT INTO `songmenu_base` VALUES ('ffa6d5ed98464ed1aba175c637ec5bcc', 'eee', 'image/songMenu/147950649737128ab981075c0024b93b.jpeg', 'xiedacon', '2016-08-26 18:25:40', '0', null, '1527188383414715966829442506b666', null, '1');

-- ----------------------------
-- Table structure for songmenu_record
-- ----------------------------
DROP TABLE IF EXISTS `songmenu_record`;
CREATE TABLE `songmenu_record` (
  `id` varchar(255) NOT NULL,
  `collectionNum` int(11) DEFAULT '0',
  `shareNum` int(11) DEFAULT '0',
  `commentNum` int(11) DEFAULT '0',
  `playNum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of songmenu_record
-- ----------------------------
INSERT INTO `songmenu_record` VALUES ('0af0e562342b4c358c09ec5443606443', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('0f9b22b059e248fc878834e3760ac43b', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('1', '11', '0', '35', '7');
INSERT INTO `songmenu_record` VALUES ('10', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('11', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('2', '12', '0', '1', '12');
INSERT INTO `songmenu_record` VALUES ('2477641d70a04e25a80d3fb5687950cf', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('3', '1', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('4', '3', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('5', '302', '0', '0', '1');
INSERT INTO `songmenu_record` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('5f46d80f7e134ada964f84559749a32e', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('6', '13', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('68671d58cd414703b4daf2615ad1bed9', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('7', '12', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('73b42d99ea5644ce840ef61d5a560fd6', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('8', '87', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('892314fb751a4359afb701d108405cc4', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('9', '12', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('9a04c5ede9864826b07b576e93e6a040', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('ab576d89c48c4d6cb79c6c5bac06becc', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('acdf9e54b4414e259f83baefae5fbe63', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('c6a188911cff4c85871b4ed7803e406b', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('db3daffee2e74231999999497089d38c', '0', '0', '0', '0');
INSERT INTO `songmenu_record` VALUES ('ffa6d5ed98464ed1aba175c637ec5bcc', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for song_base
-- ----------------------------
DROP TABLE IF EXISTS `song_base`;
CREATE TABLE `song_base` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `lyricUri` varchar(255) DEFAULT NULL,
  `fileUri` varchar(255) DEFAULT NULL,
  `singerName` varchar(255) DEFAULT NULL,
  `albumName` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `singerId` varchar(255) DEFAULT NULL,
  `albumId` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `song_singer` (`singerId`),
  KEY `song_ablum` (`albumId`),
  CONSTRAINT `song_ablum` FOREIGN KEY (`albumId`) REFERENCES `album_base` (`id`),
  CONSTRAINT `song_singer` FOREIGN KEY (`singerId`) REFERENCES `singer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_base
-- ----------------------------
INSERT INTO `song_base` VALUES ('025bf67e1b0c461e8b03ae36d64eb664', '下完这场雨', 'image/song/18791753230762101.jpg', '04:31', 'lyrics/2016-10-13-17.lrc', 'music/2016-10-13-17.mp3', '后弦', '下完这场雨', null, '4', '2f46fc1a958741759391a950b431f267', '1');
INSERT INTO `song_base` VALUES ('02629abd902449b69ba26c2d486d318a', 'Uptown Funk', 'image/song/2528876745541310.jpg', '04:50', 'lyrics/2016-10-13-6.lrc', 'music/2016-10-13-6.mp3', 'Bruno Mars', null, null, 'c0f54aa5c6eb4c85a8e62b9eadb4df98', null, '1');
INSERT INTO `song_base` VALUES ('0f93ed56c7894edc977cceb232b8d2df', 'I Really Like You', 'image/song/7878000813226177.jpg', '03:26', 'lyrics/2016-10-13-7.lrc', 'music/2016-10-13-7.mp3', 'Carly Rae Jepsen', null, null, '9d63ea982ebe422c9660080379c6959e', null, '1');
INSERT INTO `song_base` VALUES ('0fcb450e630542e894efa48d585149ce', 'Love Yourself', 'image/song/3285340746015046.jpg', '03:53', 'lyrics/2016-10-13-5.lrc', 'music/2016-10-13-5.mp3', 'Justin Bieber', null, null, '0422a500fb46411c8f58d295f6c14064', null, '1');
INSERT INTO `song_base` VALUES ('1', '湫兮如风', 'image/song/1419469524541522.jpg', '04:50', 'lyrics/徐佳莹/徐佳莹-湫兮如风-(电影《大鱼海棠》片尾曲).lrc', 'music/徐佳莹/徐佳莹-湫兮如风-(电影《大鱼海棠》片尾曲).mp3', '徐佳莹', '湫兮如风', '电影《大鱼海棠》片尾曲', '6', '1', '1');
INSERT INTO `song_base` VALUES ('10', '单车恋人', 'image/song/666304046444415.jpg', '04:07', 'lyrics/后弦/后弦-单车恋人.lrc', 'music/后弦/后弦-单车恋人.mp3', '后弦', '9公主', null, '4', '8', '1');
INSERT INTO `song_base` VALUES ('11', '骄傲的少年', 'image/song/1391981724588577.jpg', '02:00', 'lyrics/南征北战/南征北战-骄傲少年.lrc', 'music/南征北战/南征北战-骄傲少年.mp3', '南征北战', '骄傲的少年', '动画《那年那兔那些事儿》第二季片尾曲', '5', '9', '1');
INSERT INTO `song_base` VALUES ('13', '逆战', 'image/song/101155069761055.jpg', '03:49', 'lyrics/张杰/张杰-逆战.lrc', 'music/张杰/张杰-逆战.mp3', '张杰', '逆战', '枪战网游《逆战》主题曲', '7', '10', '1');
INSERT INTO `song_base` VALUES ('14', '夜空中最亮的星', 'image/song/5920870115754269.jpg', '06:12', 'lyrics/张杰/张杰-夜空中最亮的星.lrc', 'music/张杰/张杰-夜空中最亮的星.mp3', '张杰', null, null, '7', null, '1');
INSERT INTO `song_base` VALUES ('15', '着魔', 'image/song/849922488315442.jpg', '03:35', 'lyrics/张杰/张杰-着魔.lrc', 'music/张杰/张杰-着魔.mp3', '张杰', '着魔', '网游《恶魔法则》主题曲', '7', '11', '1');
INSERT INTO `song_base` VALUES ('19f8f84bed4744a3a5f91a185808e5d4', '画风', 'image/song/1417270500276190.jpg', '04:07', 'lyric/2016-10-13-18.lrc', 'music/2016-10-13-18.mp3', '后弦', null, null, '4', null, '1');
INSERT INTO `song_base` VALUES ('1e9a7fb3339247b2bc3ed48a4dcac1af', '南山南', 'image/song/6648746813825382.jpg', '05:25', null, 'music/2016-10-13-4.mp3', '马頔', null, null, '92ddb9c6c0134a0dab6ca130b76a3fda', null, '1');
INSERT INTO `song_base` VALUES ('2', '追梦赤子心(CCTV音乐频道)', 'image/song/27487790708865 (1).jpg', '05:11', 'lyrics/GALA/GALA-追梦赤子心(CCTV音乐频道).lrc', 'music/GALA/GALA-追梦赤子心(CCTV音乐频道).mp3', 'GALA', '追梦痴子心', null, '1', '2', '1');
INSERT INTO `song_base` VALUES ('3', 'Goodnight Goodnight', 'image/song/2544269906854087.jpg', '04:03', 'lyrics/Maroon 5/Maroon 5-Goodnight Goodnight.lrc', 'music/Maroon 5/Maroon 5-Goodnight Goodnight.mp3', 'Maroon 5', 'It Won\'t Be Soon Before Long', null, '2', '3', '1');
INSERT INTO `song_base` VALUES ('4', 'Harder To Breathe', 'image/song/6647647302165577.jpg', '02:53', 'lyrics/Maroon 5/Maroon 5-Harder To Breathe.lrc', 'music/Maroon 5/Maroon 5-Harder To Breathe.mp3', 'Maroon 5', 'Songs About Jane', null, '2', '4', '1');
INSERT INTO `song_base` VALUES ('5', 'Won\'t Go Home Without You', 'image/song/2544269906854087 (1).jpg', '03:51', 'lyrics/Maroon 5/Maroon 5-Won\'t Go Home Without You.lrc', 'music/Maroon 5/Maroon 5-Won\'t Go Home Without You.mp3', 'Maroon 5', 'It Won\'t Be Soon Before Long', null, '2', '3', '1');
INSERT INTO `song_base` VALUES ('58ea5ea0036d492c9168cb2138d694dd', 'Mark My Words', 'image/song/3285340746015046 (1).jpg', '02:14', 'lyrics/2016-10-13-16.lrc', 'music/2016-10-13-16.mp3', 'Justin Bieber', null, null, '0422a500fb46411c8f58d295f6c14064', null, '1');
INSERT INTO `song_base` VALUES ('6', 'Air Traffic', 'image/song/6667438511838692.jpg', '03:01', 'lyrics/Owl City/Owl City-Air Traffic.lrc', 'music/Owl City/Owl City-Air Traffic.mp3', 'Owl City', 'Maybe I\'m Dreaming', null, '3', '5', '1');
INSERT INTO `song_base` VALUES ('658613c081934d5ba542bacdee1d9203', 'You Belong With Me', 'image/song/7945071024087485.jpg', '03:51', 'lyrics/2016-10-13-8.lrc', 'music/2016-10-13-8.mp3', 'Taylor Swift', null, null, '0986ba72bc3a40618678c8f61a21d361', null, '1');
INSERT INTO `song_base` VALUES ('6f2702eea29349a1981cc9a38010c9e8', '千本桜', 'image/song/888405395266415.jpg', '03:38', null, 'music/2016-10-13-15.mp3', '初音ミク', null, null, '354169e500304711a138f308c1a22897', null, '1');
INSERT INTO `song_base` VALUES ('7', 'The Christmas Song', 'image/song/2543170396784645.jpg', '03:53', 'lyrics/Owl City/Owl City-The Christmas Song.lrc', 'music/Owl City/Owl City-The Christmas Song.mp3', 'Owl City', 'The Christmas Song', null, '3', '6', '1');
INSERT INTO `song_base` VALUES ('71b0192fd59c4fb885948b2c2820bae4', 'a horse\'s dream 梦马', 'image/song/18521273370353427.jpg', '04:47', 'lyrics/2016-10-13-1.lrc', 'music/2016-10-13-1.mp3', '尚雯婕', '湫兮如风', '电影《宾虚》中国推广曲', '24b8d4d63ed943ef995c2183ba025c49', '1', '1');
INSERT INTO `song_base` VALUES ('8', '把它甩掉', 'image/song/59373627905249.jpg', '03:35', 'lyrics/后弦/后弦-把它甩掉.lrc', 'music/后弦/后弦-把它甩掉.mp3', '后弦', '很有爱', null, '4', '7', '1');
INSERT INTO `song_base` VALUES ('845772efd1e7412fb24e5aa009737921', '梵高先生', 'image/song/2323268069553116.jpg', '04:09', 'lyrics/2016-10-13-3.lrc', 'music/2016-10-13-3.mp3', '李志', null, null, 'a899a4687b984a66874646751f3b1010', null, '1');
INSERT INTO `song_base` VALUES ('9', '笔墨侍候', 'image/song/3382097768429231.jpg', '04:42', 'lyrics/后弦/后弦-笔墨侍候.lrc', 'music/后弦/后弦-笔墨侍候.mp3', '后弦', null, null, '4', null, '1');
INSERT INTO `song_base` VALUES ('ae4b5e734418433d8e221faf77c6f525', 'βίος <MK+nZk Version>', 'image/song/6657542906651932.jpg', '04:33', 'lyrics/2016-10-13-11.lrc', 'music/2016-10-13-11.mp3', '澤野弘之', null, null, '7288ccbbd92246d3a28db7b67cc1b2df', null, '1');
INSERT INTO `song_base` VALUES ('b29ea673aa174de385d1cf37c06e87e6', '奔跑', 'image/song/4457420139017765.jpg', '03:17', 'lyrics/2016-10-13-2.lrc', 'music/2016-10-13-2.mp3', '羽·泉', null, null, 'b88aa86cb91141ff86a77f0e2757d667', null, '1');
INSERT INTO `song_base` VALUES ('b73db985d70648e9b58e4af5896304c7', 'Innocence', 'image/song/693791837133670.jpg', '02:29', 'lyrics/2016-10-13-9.lrc', 'music/2016-10-13-9.mp3', 'Avril Lavigne', null, null, '7ca6a02f51324867b8d4bd6db72620a5', null, '1');
INSERT INTO `song_base` VALUES ('ba55f5d0ef404199b882bd7a202d3135', 'Summer', 'image/song/556352883666539.jpg', '02:34', null, 'music/2016-10-13-10.mp3', '久石譲', null, null, 'a98ceefbe51b43e588e2deac7377c524', null, '1');
INSERT INTO `song_base` VALUES ('d2c48ff114c6499f8465f47bc2d2578f', '恋爱サーキュレーション', 'image/song/642114790633458.jpg', '04:13', 'lyrics/2016-10-13-14.lrc', 'music/2016-10-13-14.mp3', '花澤香菜', null, '恋爱循环', '5dcd4d10774e443589916de44b956c47', null, '1');
INSERT INTO `song_base` VALUES ('d866076b15e04f4b84769a45ebe41e02', '单车恋人', 'image/song/666304046444415.jpg', '04:07', 'lyrics/2016-10-13-20.lrc', 'music/2016-10-13-20.mp3', '后弦', null, null, '4', null, '1');
INSERT INTO `song_base` VALUES ('eb226caff960444eb54841f52bffb7f4', '外面的世界', 'image/song/85761906972218.jpg', '04:31', 'lyrics/2016-10-13-12.lrc', 'music/2016-10-13-12.mp3', '莫文蔚', null, null, 'e6cb866b51394a5d82a96c86a58e0527', null, '1');
INSERT INTO `song_base` VALUES ('f426924b3d4042e88e31806d9703fb16', 'Departures ~あなたにおくるアイの歌~', 'image/song/7823025232375393.jpg', '04:15', null, 'music/2016-10-13-13.mp3', 'EGOIST', null, null, '10300f1269e9438099154dcc05a53823', null, '1');
INSERT INTO `song_base` VALUES ('fd0c0b75130e45dca74976f5e33a4413', '娃娃脸', 'image/song/36283883728991.jpg', '03:32', 'lyrics/2016-10-13-19.lrc', 'music/2016-10-13-19.mp3', '后弦', null, null, '4', null, '1');

-- ----------------------------
-- Table structure for song_gl_album
-- ----------------------------
DROP TABLE IF EXISTS `song_gl_album`;
CREATE TABLE `song_gl_album` (
  `songId` varchar(255) NOT NULL,
  `rank` int(11) DEFAULT NULL,
  `albumId` varchar(255) NOT NULL,
  PRIMARY KEY (`songId`,`albumId`),
  KEY `SGSL_SI_song` (`songId`),
  KEY `song_gl_album_ibfk_2` (`albumId`),
  CONSTRAINT `song_gl_album_ibfk_1` FOREIGN KEY (`songId`) REFERENCES `song_base` (`id`),
  CONSTRAINT `song_gl_album_ibfk_2` FOREIGN KEY (`albumId`) REFERENCES `album_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_gl_album
-- ----------------------------
INSERT INTO `song_gl_album` VALUES ('1', '1', '1');
INSERT INTO `song_gl_album` VALUES ('10', '1', '8');
INSERT INTO `song_gl_album` VALUES ('11', '1', '9');
INSERT INTO `song_gl_album` VALUES ('13', '1', '10');
INSERT INTO `song_gl_album` VALUES ('15', '1', '11');
INSERT INTO `song_gl_album` VALUES ('2', '1', '2');
INSERT INTO `song_gl_album` VALUES ('3', '1', '3');
INSERT INTO `song_gl_album` VALUES ('4', '1', '4');
INSERT INTO `song_gl_album` VALUES ('5', '2', '3');
INSERT INTO `song_gl_album` VALUES ('6', '1', '5');
INSERT INTO `song_gl_album` VALUES ('7', '1', '6');
INSERT INTO `song_gl_album` VALUES ('71b0192fd59c4fb885948b2c2820bae4', '1', '1');
INSERT INTO `song_gl_album` VALUES ('8', '1', '7');

-- ----------------------------
-- Table structure for song_gl_songlist
-- ----------------------------
DROP TABLE IF EXISTS `song_gl_songlist`;
CREATE TABLE `song_gl_songlist` (
  `songId` varchar(255) NOT NULL,
  `rank` int(11) NOT NULL,
  `songListId` varchar(255) NOT NULL,
  `rankChange` int(11) DEFAULT NULL,
  PRIMARY KEY (`songId`,`songListId`),
  KEY `SGSL_SLI_songList` (`songListId`),
  CONSTRAINT `SGSL_SI_song` FOREIGN KEY (`songId`) REFERENCES `song_base` (`id`),
  CONSTRAINT `SGSL_SLI_songList` FOREIGN KEY (`songListId`) REFERENCES `songlist_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_gl_songlist
-- ----------------------------
INSERT INTO `song_gl_songlist` VALUES ('025bf67e1b0c461e8b03ae36d64eb664', '1', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('02629abd902449b69ba26c2d486d318a', '2', '1', '-1');
INSERT INTO `song_gl_songlist` VALUES ('0f93ed56c7894edc977cceb232b8d2df', '3', '1', '3');
INSERT INTO `song_gl_songlist` VALUES ('0f93ed56c7894edc977cceb232b8d2df', '4', '2', '-1');
INSERT INTO `song_gl_songlist` VALUES ('0fcb450e630542e894efa48d585149ce', '4', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('1', '5', '1', '1');
INSERT INTO `song_gl_songlist` VALUES ('1', '2', '2', '0');
INSERT INTO `song_gl_songlist` VALUES ('1', '3', '3', '0');
INSERT INTO `song_gl_songlist` VALUES ('11', '7', '1', '-3');
INSERT INTO `song_gl_songlist` VALUES ('11', '6', '3', '3');
INSERT INTO `song_gl_songlist` VALUES ('13', '8', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('13', '5', '3', '13');
INSERT INTO `song_gl_songlist` VALUES ('14', '9', '1', '1');
INSERT INTO `song_gl_songlist` VALUES ('14', '4', '3', '-1');
INSERT INTO `song_gl_songlist` VALUES ('15', '10', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('19f8f84bed4744a3a5f91a185808e5d4', '11', '1', '5');
INSERT INTO `song_gl_songlist` VALUES ('19f8f84bed4744a3a5f91a185808e5d4', '7', '3', '1');
INSERT INTO `song_gl_songlist` VALUES ('1e9a7fb3339247b2bc3ed48a4dcac1af', '12', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('1e9a7fb3339247b2bc3ed48a4dcac1af', '6', '2', '0');
INSERT INTO `song_gl_songlist` VALUES ('2', '13', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('3', '14', '1', '1');
INSERT INTO `song_gl_songlist` VALUES ('4', '15', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('4', '3', '2', '1');
INSERT INTO `song_gl_songlist` VALUES ('4', '8', '3', '2');
INSERT INTO `song_gl_songlist` VALUES ('5', '1', '2', '0');
INSERT INTO `song_gl_songlist` VALUES ('58ea5ea0036d492c9168cb2138d694dd', '9', '3', '3');
INSERT INTO `song_gl_songlist` VALUES ('6', '1', '3', '0');
INSERT INTO `song_gl_songlist` VALUES ('7', '7', '2', '-1');
INSERT INTO `song_gl_songlist` VALUES ('71b0192fd59c4fb885948b2c2820bae4', '8', '2', '1');
INSERT INTO `song_gl_songlist` VALUES ('8', '2', '3', '0');
INSERT INTO `song_gl_songlist` VALUES ('845772efd1e7412fb24e5aa009737921', '10', '3', '1');
INSERT INTO `song_gl_songlist` VALUES ('9', '10', '2', '-1');
INSERT INTO `song_gl_songlist` VALUES ('b73db985d70648e9b58e4af5896304c7', '9', '2', '1');
INSERT INTO `song_gl_songlist` VALUES ('d866076b15e04f4b84769a45ebe41e02', '6', '1', '0');
INSERT INTO `song_gl_songlist` VALUES ('d866076b15e04f4b84769a45ebe41e02', '5', '2', '3');

-- ----------------------------
-- Table structure for song_gl_songmenu
-- ----------------------------
DROP TABLE IF EXISTS `song_gl_songmenu`;
CREATE TABLE `song_gl_songmenu` (
  `songMenuId` varchar(255) NOT NULL,
  `songId` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`songId`,`songMenuId`),
  KEY `SGSM_SMI_songMenu` (`songMenuId`),
  CONSTRAINT `SGSM_SI_song` FOREIGN KEY (`songId`) REFERENCES `song_base` (`id`),
  CONSTRAINT `SGSM_SMI_songMenu` FOREIGN KEY (`songMenuId`) REFERENCES `songmenu_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_gl_songmenu
-- ----------------------------
INSERT INTO `song_gl_songmenu` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', '025bf67e1b0c461e8b03ae36d64eb664', '2016-11-04 19:27:25');
INSERT INTO `song_gl_songmenu` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', '02629abd902449b69ba26c2d486d318a', '2016-11-04 19:27:28');
INSERT INTO `song_gl_songmenu` VALUES ('0f9b22b059e248fc878834e3760ac43b', '1', '2016-09-06 15:49:46');
INSERT INTO `song_gl_songmenu` VALUES ('1', '1', '2016-08-29 14:42:38');
INSERT INTO `song_gl_songmenu` VALUES ('2', '1', '2016-09-08 14:42:52');
INSERT INTO `song_gl_songmenu` VALUES ('5e9e6fafbbd7431d86688ed2250abaf3', '1', '2016-09-06 15:44:44');
INSERT INTO `song_gl_songmenu` VALUES ('5f46d80f7e134ada964f84559749a32e', '1', '2016-09-06 15:44:49');
INSERT INTO `song_gl_songmenu` VALUES ('73b42d99ea5644ce840ef61d5a560fd6', '1', '2016-09-06 15:51:14');
INSERT INTO `song_gl_songmenu` VALUES ('1', '14', '2016-09-12 14:42:23');
INSERT INTO `song_gl_songmenu` VALUES ('0f9b22b059e248fc878834e3760ac43b', '2', '2016-09-06 15:52:12');
INSERT INTO `song_gl_songmenu` VALUES ('1', '2', '2016-09-08 14:42:27');
INSERT INTO `song_gl_songmenu` VALUES ('2', '3', '2016-09-09 14:42:47');
INSERT INTO `song_gl_songmenu` VALUES ('1', '4', '2016-09-05 14:42:31');
INSERT INTO `song_gl_songmenu` VALUES ('5f46d80f7e134ada964f84559749a32e', '4', '2016-09-06 15:50:58');
INSERT INTO `song_gl_songmenu` VALUES ('1', '6', '2016-09-23 14:42:35');
INSERT INTO `song_gl_songmenu` VALUES ('2', '6', '2016-09-01 14:42:19');
INSERT INTO `song_gl_songmenu` VALUES ('1', '8', '2016-09-06 14:42:14');
INSERT INTO `song_gl_songmenu` VALUES ('2', '8', '2016-09-27 14:42:55');
INSERT INTO `song_gl_songmenu` VALUES ('2', '9', '2016-09-07 14:42:43');

-- ----------------------------
-- Table structure for song_record
-- ----------------------------
DROP TABLE IF EXISTS `song_record`;
CREATE TABLE `song_record` (
  `id` varchar(255) NOT NULL,
  `commentNum` int(11) DEFAULT '0',
  `playNum` int(11) DEFAULT '0',
  `collectionNum` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of song_record
-- ----------------------------
INSERT INTO `song_record` VALUES ('025bf67e1b0c461e8b03ae36d64eb664', '0', '0', '0');
INSERT INTO `song_record` VALUES ('02629abd902449b69ba26c2d486d318a', '0', '0', '0');
INSERT INTO `song_record` VALUES ('0f93ed56c7894edc977cceb232b8d2df', '0', '0', '0');
INSERT INTO `song_record` VALUES ('0fcb450e630542e894efa48d585149ce', '0', '0', '0');
INSERT INTO `song_record` VALUES ('1', '124', '32', '1000');
INSERT INTO `song_record` VALUES ('10', '31', '0', '300');
INSERT INTO `song_record` VALUES ('11', '3', '21', '540');
INSERT INTO `song_record` VALUES ('12', '1', '0', '123');
INSERT INTO `song_record` VALUES ('13', '0', '0', '300');
INSERT INTO `song_record` VALUES ('14', '0', '123', '200');
INSERT INTO `song_record` VALUES ('15', '4', '0', '4000');
INSERT INTO `song_record` VALUES ('19f8f84bed4744a3a5f91a185808e5d4', '0', '0', '0');
INSERT INTO `song_record` VALUES ('1e9a7fb3339247b2bc3ed48a4dcac1af', '0', '0', '0');
INSERT INTO `song_record` VALUES ('2', '3', '0', '450');
INSERT INTO `song_record` VALUES ('3', '0', '0', '400');
INSERT INTO `song_record` VALUES ('4', '3', '0', '700');
INSERT INTO `song_record` VALUES ('5', '0', '0', '870');
INSERT INTO `song_record` VALUES ('58ea5ea0036d492c9168cb2138d694dd', '0', '0', '0');
INSERT INTO `song_record` VALUES ('6', '1', '0', '231');
INSERT INTO `song_record` VALUES ('658613c081934d5ba542bacdee1d9203', '0', '0', '0');
INSERT INTO `song_record` VALUES ('6f2702eea29349a1981cc9a38010c9e8', '0', '0', '0');
INSERT INTO `song_record` VALUES ('7', '0', '0', '300');
INSERT INTO `song_record` VALUES ('71b0192fd59c4fb885948b2c2820bae4', '0', '0', '0');
INSERT INTO `song_record` VALUES ('8', '0', '0', '3000');
INSERT INTO `song_record` VALUES ('845772efd1e7412fb24e5aa009737921', '0', '0', '0');
INSERT INTO `song_record` VALUES ('9', '0', '0', '1231');
INSERT INTO `song_record` VALUES ('ae4b5e734418433d8e221faf77c6f525', '0', '0', '0');
INSERT INTO `song_record` VALUES ('b29ea673aa174de385d1cf37c06e87e6', '0', '0', '0');
INSERT INTO `song_record` VALUES ('b73db985d70648e9b58e4af5896304c7', '0', '0', '0');
INSERT INTO `song_record` VALUES ('ba55f5d0ef404199b882bd7a202d3135', '0', '0', '0');
INSERT INTO `song_record` VALUES ('d2c48ff114c6499f8465f47bc2d2578f', '0', '0', '0');
INSERT INTO `song_record` VALUES ('d866076b15e04f4b84769a45ebe41e02', '0', '0', '0');
INSERT INTO `song_record` VALUES ('eb226caff960444eb54841f52bffb7f4', '0', '0', '0');
INSERT INTO `song_record` VALUES ('f426924b3d4042e88e31806d9703fb16', '0', '0', '0');
INSERT INTO `song_record` VALUES ('fd0c0b75130e45dca74976f5e33a4413', '0', '0', '0');

-- ----------------------------
-- Table structure for tag_album
-- ----------------------------
DROP TABLE IF EXISTS `tag_album`;
CREATE TABLE `tag_album` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_album
-- ----------------------------
INSERT INTO `tag_album` VALUES ('1', '华语', '1');
INSERT INTO `tag_album` VALUES ('2', '欧美', '1');
INSERT INTO `tag_album` VALUES ('3', '韩国', '1');
INSERT INTO `tag_album` VALUES ('4', '日本', '1');

-- ----------------------------
-- Table structure for tag_songmenu_first
-- ----------------------------
DROP TABLE IF EXISTS `tag_songmenu_first`;
CREATE TABLE `tag_songmenu_first` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_songmenu_first
-- ----------------------------
INSERT INTO `tag_songmenu_first` VALUES ('1', '语种', '1');
INSERT INTO `tag_songmenu_first` VALUES ('2', '风格', '1');
INSERT INTO `tag_songmenu_first` VALUES ('3', '场景', '1');
INSERT INTO `tag_songmenu_first` VALUES ('4', '情感', '1');
INSERT INTO `tag_songmenu_first` VALUES ('5', '主题', '1');

-- ----------------------------
-- Table structure for tag_songmenu_second
-- ----------------------------
DROP TABLE IF EXISTS `tag_songmenu_second`;
CREATE TABLE `tag_songmenu_second` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fid` varchar(255) DEFAULT NULL,
  `visible` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `TSMS_TSMF` (`fid`),
  CONSTRAINT `TSMS_TSMF` FOREIGN KEY (`fid`) REFERENCES `tag_songmenu_first` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tag_songmenu_second
-- ----------------------------
INSERT INTO `tag_songmenu_second` VALUES ('1', '华语', '1', '1');
INSERT INTO `tag_songmenu_second` VALUES ('10', '电影原声', '5', '1');
INSERT INTO `tag_songmenu_second` VALUES ('2', '欧美', '1', '1');
INSERT INTO `tag_songmenu_second` VALUES ('3', '流行', '2', '1');
INSERT INTO `tag_songmenu_second` VALUES ('4', '摇滚', '2', '1');
INSERT INTO `tag_songmenu_second` VALUES ('5', '电子', '2', '1');
INSERT INTO `tag_songmenu_second` VALUES ('6', '清晨', '3', '1');
INSERT INTO `tag_songmenu_second` VALUES ('7', '夜晚', '3', '1');
INSERT INTO `tag_songmenu_second` VALUES ('8', '怀旧', '4', '1');
INSERT INTO `tag_songmenu_second` VALUES ('9', '浪漫', '4', '1');

-- ----------------------------
-- Table structure for user_base
-- ----------------------------
DROP TABLE IF EXISTS `user_base`;
CREATE TABLE `user_base` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT 'image/default.jpg',
  `sex` varchar(255) DEFAULT NULL,
  `dynamicNum` int(11) DEFAULT '0',
  `attentionNum` int(11) DEFAULT '0',
  `fansNum` int(11) DEFAULT '0',
  `introduction` text,
  `area` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `createSongMenuNum` int(11) DEFAULT '0',
  `collectSongMenuNum` int(11) DEFAULT '0',
  `birthday` datetime DEFAULT NULL,
  `level` int(11) DEFAULT '0',
  `experience` int(11) DEFAULT '0',
  `singerId` varchar(255) DEFAULT NULL,
  `lastSignDate` datetime DEFAULT NULL,
  `visible` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `singerId` (`singerId`),
  CONSTRAINT `user_base_ibfk_1` FOREIGN KEY (`singerId`) REFERENCES `singer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_base
-- ----------------------------
INSERT INTO `user_base` VALUES ('1', '张杰', 'image/user/1316115418501274.jpg', 'male', '0', '0', '0', '张杰最新动态发布、与歌迷朋友互动平台。', '北京市 - 东城区', null, '7', '6', null, '0', '0', '7', null, '1');
INSERT INTO `user_base` VALUES ('14571125147185645883325e30497db4', 'github', 'image/default.jpg', null, '0', '0', '0', null, null, null, '1', '0', null, '0', '0', null, null, '1');
INSERT INTO `user_base` VALUES ('1527188383414715966829442506b666', 'test', 'image/default.jpg', null, '0', '0', '0', null, null, null, '1', '3', null, '0', '0', null, null, '1');
INSERT INTO `user_base` VALUES ('2', '徐佳瑩', 'image/user/3188583720623545.jpg', 'female', '0', '4', '0', '台湾歌手', '台湾省 - 台北市', null, '0', '0', null, '0', '0', '6', null, '1');
INSERT INTO `user_base` VALUES ('3', '南征北战', 'image/user/1385384656394567.jpg', 'male', '0', '0', '0', '演艺经纪：18910381015-小魏；执行经纪：13520059905-奈熙Nicy。知名组合。代表作《我的天空》《骄傲的少年》《萨瓦迪卡》《回忆》《站起来》《飞跃吧》等。', '北京市 - 东城区', '10后', '0', '0', null, '0', '0', '5', null, '1');
INSERT INTO `user_base` VALUES ('4', '后弦', 'image/user/2017603836986087.jpg', 'male', '0', '0', '0', '后弦工作室正式成立！演出通告、词曲邀歌请洽小雪13051644165 工作邮箱:hohowork@163.com', '北京市 - 朝阳区', null, '0', '0', null, '0', '0', '4', null, '1');
INSERT INTO `user_base` VALUES ('5', 'GALA', 'image/user/2529976257077603.jpg', 'male', '0', '0', '0', 'GALA是个好月丢', '北京市 - 东城区 ', '00后', '0', '0', null, '2', '0', '1', null, '1');

-- ----------------------------
-- Table structure for user_gl_songmenu
-- ----------------------------
DROP TABLE IF EXISTS `user_gl_songmenu`;
CREATE TABLE `user_gl_songmenu` (
  `id` varchar(255) NOT NULL,
  `collectorId` varchar(255) DEFAULT NULL,
  `songMenuId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `songMenuId` (`songMenuId`),
  KEY `user_gl_songmenu_ibfk_1` (`collectorId`),
  CONSTRAINT `user_gl_songmenu_ibfk_1` FOREIGN KEY (`collectorId`) REFERENCES `user_base` (`id`),
  CONSTRAINT `user_gl_songmenu_ibfk_2` FOREIGN KEY (`songMenuId`) REFERENCES `songmenu_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_gl_songmenu
-- ----------------------------
INSERT INTO `user_gl_songmenu` VALUES ('1', '1', '1');
INSERT INTO `user_gl_songmenu` VALUES ('2', '1', '3');
INSERT INTO `user_gl_songmenu` VALUES ('2d017eb739634572ae0d87553860f4b7', '1527188383414715966829442506b666', '3');
INSERT INTO `user_gl_songmenu` VALUES ('3', '1', '2');
INSERT INTO `user_gl_songmenu` VALUES ('4', '1', '4');
INSERT INTO `user_gl_songmenu` VALUES ('46cd722c848445309c395e2018393748', '1527188383414715966829442506b666', '2');
INSERT INTO `user_gl_songmenu` VALUES ('5', '1', '5');
INSERT INTO `user_gl_songmenu` VALUES ('6', '1', '7');
INSERT INTO `user_gl_songmenu` VALUES ('9', '2', '3');
INSERT INTO `user_gl_songmenu` VALUES ('bfa54cabe24047cbb02aaa30fb57eb85', '1527188383414715966829442506b666', '1');

-- ----------------------------
-- Table structure for user_listenrecord_part
-- ----------------------------
DROP TABLE IF EXISTS `user_listenrecord_part`;
CREATE TABLE `user_listenrecord_part` (
  `id` varchar(255) NOT NULL,
  `songId_num` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_listenrecord_part
-- ----------------------------

-- ----------------------------
-- Table structure for user_listenrecord_total
-- ----------------------------
DROP TABLE IF EXISTS `user_listenrecord_total`;
CREATE TABLE `user_listenrecord_total` (
  `id` varchar(255) NOT NULL,
  `songId_num` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_listenrecord_total
-- ----------------------------

-- ----------------------------
-- Table structure for user_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` varchar(255) NOT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `weixin` varchar(255) DEFAULT NULL,
  `xinlangweibo` varchar(255) DEFAULT NULL,
  `renren` varchar(255) DEFAULT NULL,
  `douban` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `githubId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_relation
-- ----------------------------
INSERT INTO `user_relation` VALUES ('1', null, null, null, null, null, null, null, null);
INSERT INTO `user_relation` VALUES ('1527188383414715966829442506b666', null, '15271883833', null, null, null, null, 'y3OkiYUeuzoCgJ4wH3LNOw', null);
INSERT INTO `user_relation` VALUES ('2', null, null, null, null, null, null, null, null);
INSERT INTO `user_relation` VALUES ('3', null, null, null, null, null, null, null, null);
INSERT INTO `user_relation` VALUES ('4', null, null, null, null, null, null, null, null);
INSERT INTO `user_relation` VALUES ('5', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_setting
-- ----------------------------
DROP TABLE IF EXISTS `user_setting`;
CREATE TABLE `user_setting` (
  `id` varchar(255) NOT NULL,
  `privateMessageSetting` varchar(255) DEFAULT NULL,
  `informationSetting` varchar(255) DEFAULT NULL,
  `listenSongSetting` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_setting
-- ----------------------------
INSERT INTO `user_setting` VALUES ('1', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('10', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('11', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('12', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('13', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('14', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('15', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('2', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('3', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('4', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('5', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('6', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('7', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('8', 'aaaa', 'aaaa', 'aaaa');
INSERT INTO `user_setting` VALUES ('9', 'aaaa', 'aaaa', 'aaaa');
SET FOREIGN_KEY_CHECKS=1;
