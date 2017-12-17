# kachi
### Description
卡池后台系统
### Author
TEAM
### 开发时间
20171211--20170120
### spring boot 使用说明：
1. 修改配置文件
- src/main/resources/application.properties可修改端口号、数据库连接等。
2. 在myeclipse运行
- 打开src/main/java/Application.java，main方法右键运行run或者debug。
- 不需要tomcat。
- 启动完成myeclipse控制台有success字样，注意控制台有没有报错。
3. 主要资源位置目录
- mybatis表映射：src/main/resources/mybatis/
- css文件：          src/main/resources/static/resources/css/
- 图片文件：         src/main/resources/static/resources/images/
- js文件：           src/main/resources/static/resources/js/
- html页面：         src/main/resources/static/resources/site/
4.应用打包：
-部署之前先打包，运行项目根目录的 01.打包.bat。
-结果输出到build_log.txt，如果里面有BUILD SUCCESS字样说明打包成功。
-打包成功的kachi-0.0.1-SNAPSHOT.jar文件放在target目录，整个jar包是一个独立的程序。
5.部署到服务器：
-把kachi-0.0.1-SNAPSHOT.jar拷过去服务器，使用bat运行，不需要tomcat。
-bat脚本的写法参照 02.运行.bat。要注意路径是否正确。
6. 附录
- spring boot 学习资料： http://www.ityouknow.com/spring-boot
