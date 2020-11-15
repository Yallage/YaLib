package com.rabbitown.yalib.util;

import com.rabbitown.yalib.util.NetFileDownloadUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class DependFile {
    // ################ Map ################ //
    HashMap<String, String> DependFile = new HashMap<>();
    /**
     * add Depend file
     */
    public void addDependFile(String keys,String value){
        DependFile.put(keys, value);
    }
    /**
     * Setter And Getter
     */
    public void setDependMap(){
        // 依赖
        DependFile.put("sqlite-jdbc-3.32.3.2.jar","https://github.com/xerial/sqlite-jdbc/releases/download/3.32.3.2/sqlite-jdbc-3.32.3.2.jar");
    }
    public HashMap<String,String> getDependMap(){
        return DependFile;
    }
    public void download(){
        for (Map.Entry<String, String> entry : DependFile.entrySet()) {
            File file = new File("./libs/" + entry.getKey());
            if(!file.exists()){
                NetFileDownloadUtil netFileDownloadUtil = new NetFileDownloadUtil();
                try {
                    netFileDownloadUtil.download(entry.getValue(),entry.getKey(),"./libs");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
