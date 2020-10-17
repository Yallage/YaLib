package com.rabbitown.yalib.common;

import com.rabbitown.yalib.util.NetFileDownloadUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DependFile {
    /**
     * Setter And Getter
     */
    public void setList(){
        // 依赖
        Resources.DependFile.put("sqlite-jdbc-3.32.3.2.jar","https://github.com/xerial/sqlite-jdbc/releases/download/3.32.3.2/sqlite-jdbc-3.32.3.2.jar");
    }
    public HashMap<String,String> getList(){
        return Resources.DependFile;
    }
    public void download(){
        for (Map.Entry<String, String> entry : Resources.DependFile.entrySet()) {
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
