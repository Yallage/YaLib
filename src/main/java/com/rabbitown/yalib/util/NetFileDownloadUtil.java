package com.rabbitown.yalib.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetFileDownloadUtil {
    /**
     * 从网络Url中下载文件
     * @param urlStr URL
     * @param saveName 保存下来的名字
     * @param savePath 保存目录
     * @throws IOException 抛出IO错误 懂的都懂
     */
    public void download(String urlStr,String saveName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator + saveName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        fos.close();
        inputStream.close();
        System.out.println("DownLoad" + url + "Done.");
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream 输入流
     * @return 返回流
     * @throws IOException 抛出IO错误 懂的都懂
     */
    public byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}

