package com.ftd.seckill.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;

public class MySqlTemplateGenerator {

    // database url
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ftd_schema?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8";
    // username
    private static final String DB_USERNAME = "root";
    // password
    private static final String DB_PASSWDRD = "123456";
    // schema
    private static final String DB_SCHEMA = "ftd_schema";
    // auther
    private static final String DB_AUTHOR = "ftd";
    // table list
    private static final String DB_TABLE_LIST = "";

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder(DB_URL, DB_USERNAME, DB_PASSWDRD)
            .schema(DB_SCHEMA)
            ;

    public static void generator (String tableNames){
        FastAutoGenerator
                // 配置数据源
                .create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(DB_AUTHOR)    //作者
                            .commentDate("yyyy-MM-dd hh:mm:ss") //注释日期
                            .outputDir(System.getProperty("user.dir") + "/src/main/java") // 输出目录
                            .disableOpenDir() //禁止打开输出目录
                            ;
                })
                // 包配置
                .packageConfig(config -> {
                    config.parent("com.ftd.seckill.ftdservice")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mybatis-plus/mappers")); //设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tableNames) // 设置需要生成的表名
                            // Entity 策略配置
                            .entityBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .enableLombok() //开启 Lombok
                            .naming(NamingStrategy.underline_to_camel)  // 数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)  // 数据库表字段映射到实体的命名策略：下划线转驼峰命
                            // Mapper 策略配置
                            .mapperBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            // Service 策略配置
                            .serviceBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                            // Controller 策略配置
                            .controllerBuilder()
                            .enableFileOverride() // 覆盖已生成文件
                            ;
                })
                // 模板配置
                .templateEngine(new VelocityTemplateEngine())
                .templateConfig(builder ->{
                    builder.entity("templates/entity.java")
                            .mapper("templates/mapper.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .controller("templates/controller.java")
                            ;
                })
                // 执行
                .execute();
    }
}
