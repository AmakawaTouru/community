## 安洁社区

### 技术栈
Spring Boot + MyBatis + MySQL + BootStrap + Ajax + Thymeleaf

### 项目预览
[img](http://m.qpic.cn/psc?/V12qOu4F0ZpoXd/TmEUgtj9EK6.7V8ajmQrEEpp0fAgxAGeIg2UptXQ8Ctv3N5Ki58h1NyoJYVf96rrp5O2QkmfGrLX2iVJl2IelmV*yeXifIx8dIGWNlhjeWg!/b&bo=WwdhAwAAAAADJzw!&rf=viewer_4)

### 项目地址
www.amakawa.top

### 项目简介
该社区是一个互动交流平台，实现了注册登录，第三方登录，发帖评论，回复点赞，消息提醒，内容搜索等功能。

图片通过阿里云OSS方式进行存储。通过引入开源项目Editor.md嵌入了富文本编辑器。

### 实现的功能和技术

- 基于Mybatis的数据库增删改查
- 基于SpringMVC处理请求，业务处理,以及Thymeleaf模板引擎实现页面展示
- 基于BootStrap和CSS优化页面展示效果
- 采用Cookie方式存储用户的登录状态，存储验证码
- 采用拦截器调用Session信息读取用户信息以及更新消息未读数
- 基于Ajax实现和服务端异步传输数据来实现评论列表的展示以及点赞数的增加
- 调用GitHub提供的接口实现第三方登录，引入开源项目Editor.md编写MarkDown文本和上传图片
- 采用Git将项目上传到GitHub

### 资料
[码问SpringBoot项目学习](https://www.bilibili.com/video/BV1r4411r7au?p=1)

[Spring 文档](https://spring.io/guides)

[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)

[开源项目Editor.md](https://github.com/pandao/editor.md)

### 工具
[Lombok](https://www.projectlombok.org)

[Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)

[Git](https://git-scm.com/download)

### 数据库脚本
于本项目的community.sql文件中保存。
