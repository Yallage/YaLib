package com.rabbitown.yalib.util;
import com.rabbitown.yalib.YaLib;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * 将jar内的文件复制到jar包外的指定目录下
 * @author Hanbings
 */
public class FileUtil {
    // private static final LogUtil log = new LogUtil();

    /**
     * 释放文件到Jar外目录
     * @param path 需要释放的文件
     * @param outPath 释放的目标目录
     * @author Copy form baidu.com
     */
    public void copyFileOutOfJar(String path,String outPath){
        InputStream is = YaLib.class.getResourceAsStream(path);
        // 流式读取jar包内文件，使用classpath
        File f = new File(outPath);
        // 指定输出文件
        File fp = new File(f.getParent());
        // 输出文件的父目录
        if (!fp.exists()) {
            // 父目录不存在时先创建
            boolean mkdir = fp.mkdirs();
            System.out.println("[Loader][Lib] Create config path status: " + mkdir);
        }
        if (!f.exists()) {
            // 文件不存在时先创建
            boolean makeFile = false;
            try {
                makeFile = f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("[Loader][Lib] Create config file " + outPath + " Source file: " + path + " Status: " + makeFile);
        }
        OutputStream os = null;
        // 创建输出流
        try {
            os = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int index;
        // 当前读取的位数
        byte[] bytes = new byte[1024];
        // 指定每次读取的位数，这里以1024为例
        // 开始读取文件，一遍读取一边输出文件。每次读取1024的长度并储存于bytes这个数组中，然后写入至目标文件
        while (true) {
            try {
                if ((index = is.read(bytes)) == -1) {
                    break;
                }
                Objects.requireNonNull(os).write(bytes, 0, index);
                // 输出文件，write()方法参数分别为：写入的数据、开始写入的位置（0为从开头写入），写入长度
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 关闭流并保存文件
        try {
            Objects.requireNonNull(os).flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取文件对象
     * @param path 文件目录
     * @author Hanbings
     */
    public File getFile(String path){
        return new File(path);
    }

    /**
     * 判断一个运行目录下的文件是否完整
     * 缺失文件将直接从jar释放
     * @param fileList 被扫描目录的ArrayList
     * @author Hanbings
     */
    public void existsFileComparedWithJar(ArrayList<String> fileList){
        for (String s : fileList) {
            File file = new File(s);
            if (!file.exists()) {
                this.copyFileOutOfJar(s.substring(1),s);
            }
        }
    }

    /**
     * 判断单个文件是否存在
     * @param path 文件目录
     * @author Hanbings
     */
    public Boolean existsFile(String path){
        File file = this.getFile(path);
        return file.exists();
    }

    /**
     * 获取目录中文件列表
     *
     * @param path 被扫描的目录
     * @return 文件列表Array
     * @author Hanbings
     */
    public String[] getFileList(String path) {
        return new File(path).list();
    }
}
