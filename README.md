![travis-ci](https://travis-ci.org/xiedacon/music.svg?branch=master)

# music
一个简单的音乐网站项目，采用前后端分离架构，由js控制页面渲染

---

###所用技术

- jQuery：前端库
- bootstrap：UI框架（网站后台页面）
- springMVC：MVC框架
- spring framework：IOC容器
- mybatis：ORM框架
- POI：生成与解析excel表格
- tomcat：web容器
- mysql：数据库

###前端部分

- 前台为SPA应用，采用自己写的SPA框架（真不知天高地厚），页面纯手打未使用UI框架
- 后台为普通静态页面，使用bootstrap框架

###后台部分

- springMVC：restful风格
- spring：统一事务管理
- mybatis：接口-mapper开发模式

###功能介绍

- 任何人都可以浏览专辑、榜单、歌曲、歌手与其他用户的基本信息以及听音乐
- 用户登录有多种方式：手机号直接登录，github、qq、微信、微博的第三方登录（暂只实现github，国内网络不稳定）
- 用户登录后，可以收藏、创建、修改歌单
- 后台可对专辑、榜单、歌曲、歌手、专辑分类、歌手分类、歌单分类进行CRUD
- 支持excel批量上传歌曲、歌手、专辑，榜单歌曲excel下载与上传

###尚未完成

- redis：缓存
- spring security：权限管理
- solr：全文搜索
- 歌曲文件传输格式及方法修改
- js模块化
- 页面html、css重构（兼容性、命名规则、使用规范）
- 评论消息提醒（消息队列）
- 添加关注、粉丝、签到、经验、等级、个人设置等功能
