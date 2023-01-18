package com.ftd.seckill.ftdservice.tools;

import com.ftd.seckill.generator.MySqlTemplateGenerator;

public class MyBatisPlusGeneratorRunner {
    public static void main(String[] args){
        MySqlTemplateGenerator.generator("role,user,permission");
    }
}
