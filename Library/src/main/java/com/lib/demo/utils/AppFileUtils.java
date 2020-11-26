package com.lib.demo.utils;

import org.aspectj.util.FileUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

public class AppFileUtils {
    /**
     * 得到文件上传的路径
     */
    public static String PATH = "C:/upload/";

    static {


        InputStream inputStream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");

        Properties properties = new Properties();

        try {
            properties.load(inputStream);

            PATH = properties.getProperty("path");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static ResponseEntity<Object> downloadFile(HttpServletResponse response, String path, String oldName) {

        //4,使用绝对路径+相对路径去找到文件对象

        File file = new File(AppFileUtils.PATH, path);
        if (file.exists()) {
            try {
                oldName = URLEncoder.encode(oldName, "UTF-8");
                byte[] bytes = FileUtil.readAsByteArray(file);
                HttpHeaders header = new HttpHeaders();
                //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
                header.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                //设置下载的文件的名称
                header.setContentDispositionFormData("attachment", oldName);

                ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);

                return entity;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            PrintWriter out;
            try {
                out = response.getWriter();
                out.write("文件不存在");
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    /**
     * 根据相对路径删除硬盘上文件
     *
     * @param path
     */
    public static void deleteFileUsePath(String path) {
        String realPath = PATH + path;
        //根据文件
        File file = new File(realPath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 更改文件名
     *
     * @param carimg
     */
    public static String updateFileName(String carimg, String suffix) {
        try {
            File file = new File(PATH, carimg);
            if (file.exists()) {
                file.renameTo(new File(PATH, carimg.replace(suffix, "")));
                return carimg.replace(suffix, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据路径 删除图片
     * @param carimg
     */
    public static void removeFileByPath(String carimg) {
        try {
            File  file = new File(PATH,carimg);
            if(file.exists()){
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
