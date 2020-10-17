package com.rabbitown.yalib.common;

import java.util.ArrayList;

public class DependFile {
    /**
     * Setter And Getter
     */
    public void setList(){
        // 依赖
        Resources.DependFile.add("./libs/commons-lang-2.6.jar");
        Resources.DependFile.add("./libs/Java-WebSocket-1.5.1.jar");
        Resources.DependFile.add("./libs/log4j-api-2.13.3.jar");
        Resources.DependFile.add("./libs/log4j-core-2.13.3.jar");
        Resources.DependFile.add("./libs/log4j-slf4j-impl-2.13.3.jar");
        Resources.DependFile.add("./libs/log4j-web-2.13.3.jar");
        Resources.DependFile.add("./libs/slf4j-api-1.7.25.jar");
        Resources.DependFile.add("./libs/snakeyaml-1.27.jar");
        Resources.DependFile.add("./libs/sqlite-jdbc-3.32.3.2.jar");
        // 语言
        Resources.DependFile.add("./language/zh_CN.yml");
        Resources.DependFile.add("./language/en_US.yml");
        // 配置文件
        Resources.DependFile.add("./config.yml");

    }
    public ArrayList<String> getList(){
        return Resources.DependFile;
    }
}
