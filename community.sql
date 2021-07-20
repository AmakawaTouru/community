/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.29 : Database - my_community
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_community` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my_community`;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `commentator` int(11) NOT NULL,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `like_count` int(11) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`parent_id`,`type`,`commentator`,`gmt_create`,`gmt_modified`,`like_count`,`content`,`comment_count`) values (17,40,1,6,1626698838608,1626698838608,2,'测试',0),(18,41,1,6,1626703543657,1626703543657,1,'好',0);

/*Table structure for table `conn_question_tagv` */

DROP TABLE IF EXISTS `conn_question_tagv`;

CREATE TABLE `conn_question_tagv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qid` int(11) NOT NULL,
  `tvid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Data for the table `conn_question_tagv` */

insert  into `conn_question_tagv`(`id`,`qid`,`tvid`) values (4,25,4),(5,25,5),(6,26,1),(7,26,2),(8,26,3),(9,26,4),(10,26,5),(11,26,7),(12,26,15),(13,27,30),(14,27,31),(15,27,32),(16,27,29),(17,27,1),(18,28,1),(19,28,2),(20,28,3),(21,28,4),(22,28,7),(23,28,8),(24,29,1),(25,29,11),(26,29,20),(27,29,28),(28,29,27),(29,29,5),(30,29,3),(35,30,1),(36,30,11),(37,30,12),(38,30,23),(40,39,63),(42,37,1),(43,40,1),(44,41,6);

/*Table structure for table `conn_tagp_tagv` */

DROP TABLE IF EXISTS `conn_tagp_tagv`;

CREATE TABLE `conn_tagp_tagv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tpid` int(11) NOT NULL,
  `tvid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `conn_tagp_tagv` */

insert  into `conn_tagp_tagv`(`id`,`tpid`,`tvid`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,1,21),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(26,1,26),(27,1,27),(28,1,28),(29,2,29),(30,2,30),(31,2,31),(32,2,32),(33,2,33),(34,2,34),(35,2,35),(36,2,36),(37,2,37),(38,2,38),(39,2,39),(40,3,40),(41,3,41),(42,3,42),(43,3,43),(44,3,44),(45,3,45),(46,3,46),(47,3,47),(48,3,48),(49,3,49),(50,3,50),(51,4,51),(52,4,52),(53,4,53),(54,4,54),(55,4,55),(56,4,56),(57,4,57),(58,4,58),(59,4,59),(60,5,60),(61,5,61),(62,5,62),(63,5,63),(64,5,64),(65,5,65),(66,5,66),(67,5,67),(68,5,68),(69,5,69),(70,5,70),(71,5,71);

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `outer_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `gmt_create` bigint(20) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `notifier_name` varchar(50) NOT NULL,
  `outer_title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `notification` */

insert  into `notification`(`id`,`notifier`,`receiver`,`outer_id`,`type`,`gmt_create`,`status`,`notifier_name`,`outer_title`) values (7,6,6,40,3,1626701456750,1,'anjieliyadian@gmail.com','浅析红黑树（RBTree）原理及实现'),(8,6,6,40,3,1626701554762,0,'anjieliyadian@gmail.com','浅析红黑树（RBTree）原理及实现'),(9,6,4,41,1,1626703543672,0,'anjieliyadian@gmail.com','JVM 垃圾回收机制及其实现原理'),(10,6,6,41,3,1626703545398,0,'anjieliyadian@gmail.com','JVM 垃圾回收机制及其实现原理');

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` text,
  `gmt_create` bigint(20) DEFAULT NULL,
  `gmt_modified` bigint(20) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  `priorities` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`id`,`title`,`description`,`gmt_create`,`gmt_modified`,`creator`,`comment_count`,`view_count`,`like_count`,`tag`,`priorities`) values (37,'这里是安洁社区，大家来畅所欲言吧！','### **安洁社区**\r\n\r\n社区规则：\r\n1、不得发表谩骂、包含人身攻击的文章。不得在回复中发表带有煽动、怂恿等语言的信息。\r\n2、不得发暴力色情的帖子；\r\n3、不得发造谣生事、恶意中伤他人的帖子；\r\n4、不得发布任何违反国家法律法规的言论，不得发表任何包含种族,性别，宗教的歧视性内容，不得发表猥亵性的文章，对于任何人都不能进行污辱、漫骂及人身攻击。\r\n\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/ffcda71f-eca4-4fb9-b62d-094f45510357.jpg?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&\r\nSignature=Yxbc2ME8Ots1nbTN73J8%2FdEBIHs%3D)\r\n\r\n\r\n\r\n',1626266217596,1626266217596,4,0,11,0,'javascript',1),(40,'浅析红黑树（RBTree）原理及实现','## 红黑树与AVL树的比较：\r\n\r\n1.AVL树的时间复杂度虽然优于红黑树，但是对于现在的计算机，cpu太快，可以忽略性能差异\r\n2.红黑树的插入删除比AVL树更便于控制操作\r\n3.红黑树整体性能略优于AVL树（红黑树旋转情况少于AVL树）\r\n\r\n红黑树的性质：\r\n\r\n红黑树是一棵二叉搜索树，它在每个节点增加了一个存储位记录节点的颜色，可以是RED,也可以是BLACK；通过任意一条从根到叶子简单路径上颜色的约束，红黑树保证最长路径不超过最短路径的二倍，因而近似平衡。\r\n\r\n具体性质如下：\r\n\r\n    每个节点颜色不是黑色，就是红色\r\n    根节点是黑色的\r\n    如果一个节点是红色，那么它的两个子节点就是黑色的（没有连续的红节点）\r\n    对于每个节点，从该节点到其后代叶节点的简单路径上，均包含相同数目的黑色节点\r\n\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/daff6b54-8b39-4bcc-bffc-1052db8b3d0a.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=LuEdO%2BX04rIYltAp3bpsl66ZA5E%3D)\r\n\r\n你的最短路径就是全黑节点，最长路径就是一个红节点一个黑节点，最后黑色节点相同时，最长路径刚好是最短路径的两倍。\r\n\r\n红黑树的插入：\r\n\r\n红黑树插入节点过程大致分析：\r\n\r\n    RBTree为二叉搜索树，我们按照二叉搜索树的方法对其进行节点插入\r\n    RBTree有颜色约束性质，因此我们在插入新节点之后要进行颜色调整\r\n\r\n具体步骤如下：\r\n\r\n    根节点为NULL，直接插入新节点并将其颜色置为黑色\r\n    根节点不为NULL，找到要插入新节点的位置\r\n    插入新节点\r\n    判断新插入节点对全树颜色的影响，更新调整颜色\r\n\r\n首先红黑树的插入其实不是那么容易实现的，以前搜索树的插入我们很容易理解现在我们首先思考一个问题，你插入节点的默认颜色是RED或BLACK？ \r\n\r\n这里我们需要根据性质来思考，首先如果插入黑节点，这个可以直接插入无论它的父亲是什么颜色，但是红黑树的性质是每条路径的黑色节点数目相同这个时候你再想想那其他路径的黑色节点数目一定比你现在少一个节点，所以调整起来是非常繁琐的. 插入红节点不需要调整其他路径，如果它的父亲为黑，那么直接插入，如果他的父亲为红那么在该路径上面开始分情况调整. 所以插入节点默认颜色一定要为红.如果为黑调节成本太大了.\r\n\r\n接下来开始插入节点如果插入节点的父亲为黑那么直接插入后返回不需要做任何调整. 但是如果插入节点的父亲为红，那么就需要调整了.具体的调整过程可以分为三个情况：\r\n\r\n\r\n\r\n第一种情况：\r\ncur为红，parent为红，pParent为黑，uncle存在且为红\r\n则将parent,uncle改为黑，pParent改为红，然后把pParent当成cur，继续向上调整。\r\n\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/c9db137e-d39a-4758-a788-2cea451a56aa.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=lpLDkhwaCW9mLnNijl4ypb1RMaA%3D)\r\n\r\n第二种情况\r\ncur为红，parent为红，pParent为黑，uncle不存在/u为黑，\r\nparent为pParent的左孩子，cur为parent的左孩子，则进行右单旋转；\r\n\r\nuncle不存在：\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/d339bf95-e204-40a1-9788-88114d5b16ac.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=oGhXzu9RXVybb78eBYYn547LLEM%3D)\r\n\r\nuncle存在且为黑： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/a6abc331-9bdf-40fe-88b7-520a3413399a.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=XghqykXnNHbsHyhwl6nwkO5al7I%3D)\r\n\r\n相反，parent为pParent的右孩子，cur为parent的右孩子，则进行左单旋转；\r\np、g变色–p变黑，g变红.\r\n\r\nuncle不存在： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/b7897094-b593-46b0-9fef-9f0213336b04.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=dhuR75laG800Me%2BxBFb4cfkkNKs%3D)\r\n\r\nuncle存在且为黑： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/d83cd6dc-b4d7-47ea-b086-2e35dd62b053.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=%2FpZSNSxrUDDb%2F0N5c8NgWMowKYQ%3D)\r\n\r\n3.第三种情况\r\ncur为红，p为红，g为黑，u不存在/u为黑\r\np为g的左孩子，cur为p的右孩子，则针对p做左单旋转\r\n则转换成了情况2\r\nuncle不存在： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/c0187b29-5653-4419-bb18-889f7b5be559.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=i9GxhPe4uxh2425ByEiQuMXu2G0%3D)\r\n\r\nuncle存在且为黑： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/f5d6bc57-4f1c-43f6-84a2-e8ef2ba02104.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=8J5WrxHt7ToH0ME41s%2BWS49I9WA%3D)\r\n\r\n\r\n相反，p为g的右孩子，cur为p的左孩子，则针对p做右单旋转\r\n则转换成了情况2\r\nuncle不存在： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/accaee1a-aab4-4e68-803b-33c8cf6a8d42.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=HRQdGCreOe4bMOdEdiDiJCfK9rU%3D)\r\n\r\n\r\nuncle存在且为黑： \r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/ea046a0a-a4e8-4916-a658-858b71564909.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=J548yPkVr30LOxFMjUzdPElDTwQ%3D)\r\n\r\n\r\n红黑树插入节点代码实现如下：\r\n```c\r\nbool Insert(const K& key, const V& value)\r\n    {\r\n        //根节点为空\r\n        if (_root == NULL)\r\n        {\r\n            _root = new Node(key,value);\r\n            _root->_color = BLACK;\r\n\r\n            return true;\r\n        }\r\n        //根节点不为空\r\n\r\n        //找到新节点插入位置\r\n        Node* parent = NULL;\r\n        Node* cur = _root;\r\n\r\n        while (cur)\r\n        {\r\n            if (cur->_key < key)\r\n            {\r\n                parent = cur;\r\n                cur = cur->_right;\r\n            }\r\n            else if (cur->_key >key)\r\n            {\r\n                parent = cur;\r\n                cur = cur->_left;\r\n            }\r\n            else\r\n                return false;\r\n        }\r\n        //插入新节点\r\n        cur = new Node(key, value);\r\n        cur->_color = RED;\r\n        if (parent->_key > key)\r\n        {\r\n            parent->_left = cur;\r\n            cur->_parent = parent;\r\n        }\r\n        else//parent->_key < key\r\n        {\r\n            parent->_right = cur;\r\n            cur->_parent = parent;\r\n        }\r\n\r\n        //插入节点后颜色的调整\r\n        while (parent && parent->_color == RED)\r\n        {\r\n            Node* grandfather = parent->_parent;//grandfather颜色一定为黑色\r\n            if (parent == grandfather->_left)\r\n            {\r\n                Node* uncle = grandfather->_right;\r\n\r\n                //uncle存在且为红\r\n                if (uncle && uncle->_color == RED)\r\n                {\r\n                    parent->_color = uncle->_color = BLACK;\r\n                    grandfather->_color = RED;\r\n\r\n                    cur = grandfather;\r\n                    parent = cur->_parent;\r\n                }\r\n                else//uncle不存在/uncle存在且为黑\r\n                {\r\n                    if (cur == parent->_right)\r\n                    {\r\n                        RotateL(parent);\r\n                        swap(parent, cur);\r\n                    }\r\n                    RotateR(grandfather);\r\n                    parent->_color = BLACK;\r\n                    grandfather->_color = RED;\r\n                }\r\n            }\r\n            else//grandfather->_right==parent\r\n            {\r\n                Node* uncle = grandfather->_left;\r\n\r\n                //uncle存在且为红\r\n                if (uncle && uncle->_color == RED)\r\n                {\r\n                    parent->_color = uncle->_color = BLACK;\r\n                    grandfather->_color = RED;\r\n\r\n                    cur = grandfather;\r\n                    parent = cur->_parent;\r\n                }\r\n                else//不存在/存在且为黑\r\n                {\r\n                    if (cur == parent->_left)\r\n                    {\r\n                        RotateR(parent);\r\n                        swap(cur, parent);\r\n                    }\r\n\r\n                    RotateL(grandfather);\r\n                    parent->_color = BLACK;\r\n                    grandfather->_color = RED;\r\n                }\r\n            }\r\n        }//end while (parent && parent->_color == RED)\r\n\r\n        _root->_color = BLACK;\r\n\r\n        return true;\r\n    }\r\n```\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n',1626696098759,1626696098759,6,1,16,0,'javascript',0),(41,'JVM 垃圾回收机制及其实现原理','# 前言\r\n对于 JVM 来说，我们都不陌生，其是 Java Virtual Machine（Java 虚拟机）的缩写，它也是一个虚构出来的计算机，是通过在实际的计算机上仿真模拟各种计算机功能来实现的。JVM 有自己完善的硬件架构，如处理器、堆栈等，还具有相应的指令系统，其本质上就是一个程序，当它在命令行上启动的时候，就开始执行保存在某字节码文件中的指令。\r\n\r\nJava 语言的可移植性就是建立在 JVM 的基础之上的，任何平台只要装有针对于该平台的 Java 虚拟机，字节码文件（.class）就可以在该平台上运行，这就是“一次编译，多次运行”。除此之外，作为 Java 语言最重要的特性之一的自动垃圾回收机制，也是基于 JVM 实现的。那么，自动垃圾回收机制到底是如何实现的呢？在本文中，就让我们一探究竟。\r\n\r\n\r\n# 垃圾\r\n\r\n## 什么是垃圾？\r\n在 JVM 进行垃圾回收之前，首先就是判断哪些对象是垃圾，也就是说，要判断哪些对象是可以被销毁的，其占有的空间是可以被回收的。根据 JVM 的架构划分，我们知道， 在 Java 世界中，几乎所有的对象实例都在堆中存放，所以垃圾回收也主要是针对堆来进行的。\r\n\r\n在 JVM 的眼中，垃圾就是指那些在堆中存在的，已经“死亡”的对象。而对于“死亡”的定义，我们可以简单的将其理解为“不可能再被任何途径使用的对象”。那怎样才能确定一个对象是存活还是死亡呢？这就涉及到了垃圾判断算法，其主要包括引用计数法和可达性分析法。\r\n\r\n## 垃圾判断算法\r\n\r\n### 引用计数法\r\n在这种算法中，假设堆中每个对象（不是引用）都有一个引用计数器。当一个对象被创建并且初始化赋值后，该对象的计数器的值就设置为 1，每当有一个地方引用它时，计数器的值就加 1，例如将对象 b 赋值给对象 a，那么 b 被引用，则将 b 引用对象的计数器累加 1。\r\n\r\n反之，当引用失效时，例如一个对象的某个引用超过了生命周期（出作用域后）或者被设置为一个新值时，则之前被引用的对象的计数器的值就减 1。而那些引用计数为 0 的对象，就可以称之为垃圾，可以被收集。\r\n\r\n特别地，当一个对象被当做垃圾收集时，它引用的任何对象的计数器的值都减 1。\r\n\r\n    优点：引用计数法实现起来比较简单，对程序不被长时间打断的实时环境比较有利。\r\n    缺点：需要额外的空间来存储计数器，难以检测出对象之间的循环引用。\r\n————————————————\r\n\r\n\r\n### 可达性分析法\r\n可达性分析法也被称之为根搜索法，可达性是指，如果一个对象会被至少一个在程序中的变量通过直接或间接的方式被其他可达的对象引用，则称该对象就是可达的。更准确的说，一个对象只有满足下述两个条件之一，就会被判断为可达的：\r\n\r\n    对象是属于根集中的对象\r\n    对象被一个可达的对象引用\r\n\r\n在这里，我们引出了一个专有名词，即根集，其是指正在执行的 Java 程序可以访问的引用变量（注意，不是对象）的集合，程序可以使用引用变量访问对象的属性和调用对象的方法。在 JVM 中，会将以下对象标记为根集中的对象，具体包括：\r\n\r\n    虚拟机栈（栈帧中的本地变量表）中引用的对象\r\n    方法区中的常量引用的对象\r\n    方法区中的类静态属性引用的对象\r\n    本地方法栈中 JNI（Native 方法）的引用对象\r\n    活跃线程（已启动且未停止的 Java 线程）\r\n\r\n根集中的对象称之为GC Roots，也就是根对象。可达性分析法的基本思路是：将一系列的根对象作为起始点，从这些节点开始向下搜索，搜索所走过的路径称为引用链，如果一个对象到根对象没有任何引用链相连，那么这个对象就不是可达的，也称之为不可达对象。\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/2d042808-b3b1-4a9d-8d8b-525a5c4cbe18.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=jndLAmKENncN8lGUNuGCOAUGWnA%3D)\r\n\r\n\r\n如上图所示，形象的展示了可达对象与不可达对象的示例，其中灰色的对象都是不可达对象，表示可以被垃圾收集的对象。在可达性分析法中，对象有两种状态，那么是可达的、要么是不可达的，在判断一个对象的可达性的时候，就需要对对象进行标记。关于标记阶段，有几个关键点是值得我们注意的，分别是：\r\n\r\n    开始进行标记前，需要先暂停应用线程，否则如果对象图一直在变化的话是无法真正去遍历它的。暂停应用线程以便 JVM 可以尽情地收拾家务的这种情况又被称之为安全点（Safe Point），这会触发一次 Stop The World（STW）暂停。触发安全点的原因有许多，但最常见的应该就是垃圾回收了。\r\n        安全点的选定基本上是以程序“是否具有让程序长时间执行的特征”为标准进行选定的。“长时间执行”的最明显特征就是指令序列复用，例如方法调用、循环跳转、异常跳转等，所以具有这些功能的指令才会产生安全点。对于安全点，另一个需要考虑的问题就是如何在 GC 发生时让所有线程（这里不包括执行 JNI 调用的线程）都“跑”到最近的安全点上再停顿下来。两种解决方案：\r\n            抢先式中断（Preemptive Suspension）：抢先式中断不需要线程的执行代码主动去配合，在 GC 发生时，首先把所有线程全部中断，如果发现有线程中断的地方不在安全点上，就恢复线程，让它“跑”到安全点上。现在几乎没有虚拟机采用这种方式来暂停线程从而响应 GC 事件。\r\n            主动式中断（Voluntary Suspension）：主动式中断的思想是当 GC 需要中断线程的时候，不直接对线程操作，仅仅简单地设置一个标志，各个线程执行时主动去轮询这个标志，发现中断标志为真时就自己中断挂起。轮询标志地地方和安全点是重合的，另外再加上创建对象需要分配内存的地方。\r\n    暂停时间的长短并不取决于堆内对象的多少也不是堆的大小，而是存活对象的多少。因此，调高堆的大小并不会影响到标记阶段的时间长短。\r\n    在根搜索算法中，要真正宣告一个对象死亡，至少要经历两次标记过程：\r\n        如果对象在进行根搜索后发现没有与根对象相连接的引用链，那它会被第一次标记并且进行一次筛选。筛选的条件是此对象是否有必要执行 finalize()方法（可看作析构函数，类似于 OC 中的dealloc，Swift 中的deinit）。当对象没有覆盖finalize()方法，或finalize()方法已经被虚拟机调用过，虚拟机将这两种情况都视为没有必要执行。\r\n        如果该对象被判定为有必要执行finalize()方法，那么这个对象将会被放置在一个名为F-Queue的队列中，并在稍后由一条由虚拟机自动建立的、低优先级的Finalizer线程去执行finalize()方法。finalize()方法是对象逃脱死亡命运的最后一次机会（因为一个对象的finalize()方法最多只会被系统自动调用一次），稍后 GC 将对F-Queue中的对象进行第二次小规模的标记，如果要在finalize()方法中成功拯救自己，只要在finalize()方法中让该对象重新引用链上的任何一个对象建立关联即可。而如果对象这时还没有关联到任何链上的引用，那它就会被回收掉。\r\n    GC 判断对象是否可达看的是强引用。\r\n\r\n当标记阶段完成后，GC 开始进入下一阶段，删除不可达对象。当然，可达性分析法有优点也有缺点，\r\n\r\n    优点：可以解决循环引用的问题，不需要占用额外的空间\r\n    缺点：多线程场景下，其他线程可能会更新已经访问过的对象的引用\r\n\r\n在上面的介绍中，我们多次提到了“引用”这个概念，在此我们不妨多了解一些引用的知识，在 Java 中有四种引用类型，分别为：\r\n\r\n    强引用（Strong Reference）：如Object obj = new Object()，这类引用是 Java 程序中最普遍的。只要强引用还存在，垃圾收集器就永远不会回收掉被引用的对象。\r\n    软引用（Soft Reference）：它用来描述一些可能还有用，但并非必须的对象。在系统内存不够用时，这类引用关联的对象将被垃圾收集器回收。JDK1.2 之后提供了SoftReference类来实现软引用。\r\n    弱引用（Weak Reference）：它也是用来描述非必须对象的，但它的强度比软引用更弱些，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。当垃圾收集器工作时，无论当前内存是否足够，都会回收掉只被弱引用关联的对象。在 JDK1.2 之后，提供了WeakReference类来实现弱引用。\r\n    虚引用（Phantom Reference）：也称为幻引用，最弱的一种引用关系，完全不会对其生存时间构成影响，也无法通过虚引用来取得一个对象实例。为一个对象设置虚引用关联的唯一目的是希望能在这个对象被收集器回收时收到一个系统通知。JDK1.2 之后提供了PhantomReference类来实现虚引用。\r\n\r\n\r\n### 垃圾回收\r\n\r\n通过上面的介绍，我们已经知道了什么是垃圾以及如何判断一个对象是否是垃圾。那么接下来，我们就来了解如何回收垃圾，这就是垃圾回收算法和垃圾回收器需要做的事情了。\r\n\r\n### 垃圾回收算法\r\n\r\n标记-清除算法\r\n\r\n标记-清除（Tracing Collector）算法是最基础的收集算法，为了解决引用计数法的问题而提出。它使用了根集的概念，它分为“标记”和“清除”两个阶段：首先标记出所需回收的对象，在标记完成后统一回收掉所有被标记的对象，它的标记过程其实就是前面的可达性分析法中判定垃圾对象的标记过程。\r\n\r\n    优点：不需要进行对象的移动，并且仅对不存活的对象进行处理，在存活对象比较多的情况下极为高效。\r\n    缺点：标记和清除过程的效率都不高，这种方法需要使用一个空闲列表来记录所有的空闲区域以及大小，对空闲列表的管理会增加分配对象时的工作量；标记清除后会产生大量不连续的内存碎片，虽然空闲区域的大小是足够的，但却可能没有一个单一区域能够满足这次分配所需的大小，因此本次分配还是会失败，不得不触发另一次垃圾收集动作。\r\n\r\n下图为“标记-清除”算法的示意图：\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/de4db10b-2edd-40f5-be73-af4a5502572a.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=nCMcfJggC6Bsv7ZnXcMcOeSCkLo%3D)\r\n\r\n\r\n下图为使用“标记-清除”算法回收前后的状态：\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/5e40d92b-e550-4ed9-a214-815a1d3e0d7e.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=DxG35IUEnzZhoqkfrMjUTQar3Ps%3D)\r\n\r\n### 标记-整理算法\r\n标记-整理（Compacting Collector）算法标记的过程与“标记-清除”算法中的标记过程一样，但对标记后出的垃圾对象的处理情况有所不同，它不是直接对可回收对象进行清理，而是让所有的对象都向一端移动，然后直接清理掉端边界以外的内存。在基于“标记-整理”算法的收集器的实现中，一般增加句柄和句柄表。\r\n\r\n    优点：经过整理之后，新对象的分配只需要通过指针碰撞便能完成，比较简单；使用这种方法，空闲区域的位置是始终可知的，也不会再有碎片的问题了。\r\n    缺点：GC 暂停的时间会增长，因为你需要将所有的对象都拷贝到一个新的地方，还得更新它们的引用地址。\r\n\r\n下图为“标记-整理”算法的示意图：\r\n\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/b4312b3b-3d2e-4e70-a412-e751166353a1.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=Ub4HM8W85iNEg%2F76jnGQFkKbX24%3D)\r\n\r\n下图为使用“标记-整理”算法回收前后的状态：\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/d2602782-eead-4088-9b54-6914f029f17f.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=VgX2vFB8akQLfF%2F%2BJTK%2FZQXqa2Y%3D)\r\n\r\n\r\n### 复制算法\r\n\r\n复制（Copying Collector）算法的提出是为了克服句柄的开销和解决堆碎片的垃圾回收。它将内存按容量分为大小相等的两块，每次只使用其中的一块（对象面），当这一块的内存用完了，就将还存活着的对象复制到另外一块内存上面（空闲面），然后再把已使用过的内存空间一次清理掉。\r\n\r\n复制算法比较适合于新生代（短生存期的对象），在老年代（长生存期的对象）中，对象存活率比较高，如果执行较多的复制操作，效率将会变低，所以老年代一般会选用其他算法，如“标记-整理”算法。一种典型的基于复制算法的垃圾回收是stop-and-copy算法，它将堆分成对象区和空闲区，在对象区与空闲区的切换过程中，程序暂停执行。\r\n\r\n    优点：标记阶段和复制阶段可以同时进行；每次只对一块内存进行回收，运行高效；只需移动栈顶指针，按顺序分配内存即可，实现简单；内存回收时不用考虑内存碎片的出现。\r\n    缺点：需要一块能容纳下所有存活对象的额外的内存空间。因此，可一次性分配的最大内存缩小了一半。\r\n\r\n下图为复制算法的示意图：\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/0b8d76fb-c139-4447-815a-7fb711193b42.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=y5zubiialsMQUhuy4fdILpb5Wsk%3D)\r\n\r\n下图为使用复制算法回收前后的状态：\r\n![](https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/0bc3463b-32ac-46f1-a7be-20accf5e743e.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=q5EUqjW09jKIS2HKtN2I08GEElU%3D)\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n',1626702384013,1626702384013,4,1,4,0,'java',0);

/*Table structure for table `tag_properties` */

DROP TABLE IF EXISTS `tag_properties`;

CREATE TABLE `tag_properties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `properties` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `tag_properties` */

insert  into `tag_properties`(`id`,`properties`) values (1,'编程语言'),(2,'框架'),(3,'服务器'),(4,'数据库'),(5,'开发工具');

/*Table structure for table `tag_values` */

DROP TABLE IF EXISTS `tag_values`;

CREATE TABLE `tag_values` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `tag_values` */

insert  into `tag_values`(`id`,`val`) values (1,'javascript'),(2,'php'),(3,'css'),(4,'html'),(5,'html5'),(6,'java'),(7,'node.js'),(8,'python'),(9,'c++'),(10,'c'),(11,'golang'),(12,'objective-c'),(13,'typescript'),(14,'shell'),(15,'swift'),(16,'c#'),(17,'sass'),(18,'ruby'),(19,'bash'),(20,'less'),(21,'asp.net'),(22,'lua'),(23,'scala'),(24,'coffeescript'),(25,'actionscript'),(26,'rust'),(27,'erlang'),(28,'perl'),(29,'laravel'),(30,'spring'),(31,'mybatis'),(32,'express'),(33,'django'),(34,'flask'),(35,'yii'),(36,'ruby-on-rails'),(37,'tornado'),(38,'koa'),(39,'struts'),(40,'linux'),(41,'nginx'),(42,'docker'),(43,'apache'),(44,'ubuntu'),(45,'centos'),(46,'缓存 tomcat'),(47,'负载均衡'),(48,'unix'),(49,'hadoop'),(50,'windows-server'),(51,'mysql'),(52,'redis'),(53,'mongodb'),(54,'sql'),(55,'oracle'),(56,'nosql memcached'),(57,'sqlserver'),(58,'postgresql'),(59,'sqlite'),(60,'git'),(61,'github'),(62,'visual-studio-code'),(63,'vim'),(64,'sublime-text'),(65,'eclipse'),(66,'ide'),(67,'svn'),(68,'visual-studio'),(69,'atom emacs'),(70,'textmate'),(71,'hg');

/*Table structure for table `thumb` */

DROP TABLE IF EXISTS `thumb`;

CREATE TABLE `thumb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thumb_id` int(11) NOT NULL,
  `thumb_id_parent` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `thumb` */

insert  into `thumb`(`id`,`thumb_id`,`thumb_id_parent`) values (1,6,17),(2,4,17),(3,6,18);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `token` varchar(36) DEFAULT NULL,
  `GMT_CREATE` bigint(20) DEFAULT NULL,
  `GMT_MODIFIED` bigint(20) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`account_id`,`name`,`token`,`GMT_CREATE`,`GMT_MODIFIED`,`avatar_url`,`email`,`password`) values (0,'1','1','1',1,1,NULL,NULL,NULL),(4,NULL,'AmakawaTouru','53b55188-85db-4917-942a-82e36b608d18',1626262214146,1626262214146,'https://avatars.githubusercontent.com/u/55305532?v=4','123@anjiemail.com','123456'),(6,NULL,'anjieliyadian@gmail.com','25c905a6-a285-4ee0-a3af-5453762d0d40',1626597009659,1626597009659,'https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/78697263-ba66-4120-aebc-0d511989b4e8.jpg?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=p5DG7ChEQWMVdeErVicGsAZ2jfY%3D','anjieliyadian@gmail.com','123456'),(7,NULL,'865215392@qq.com','3e31b694-e928-48f1-9c82-3fb51ff2c5a2',1626683727927,1626683727927,'https://oss-anjieliya-guangzhou.oss-cn-guangzhou.aliyuncs.com/5391084a-2e89-4614-a03b-0219a926e152.png?Expires=1962631209&OSSAccessKeyId=LTAI5t6LtFY5QprwdNW7rMUY&Signature=6fZwTmv7xV1KrTgnFBTQmh%2FWVno%3D','865215392@qq.com','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
