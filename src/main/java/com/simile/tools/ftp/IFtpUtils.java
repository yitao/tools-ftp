package com.simile.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by yitao on 2019/1/2.
 */
public interface IFtpUtils {
    //获取连接客户端
    FTPClient getFTPClient() throws IOException;

    //上传文件（夹）
    boolean uploadFile(InputStream is, String targetPath);

    //下载文件（夹）
    boolean downloadFile(OutputStream os, String targetPath) throws IOException;

    //删除远程文件
    boolean deleteFile(String targetPath);

    //删除远程文件夹
    boolean deleteDir(String targetPath, boolean unProtected);

    //远程文件夹文件列表
    Map<String,List<FTPFile>> listFile(String targetPath) throws IOException;

    //远程文件拷贝
    boolean copyFile(String targetPath);

    //远程文件夹拷贝
    boolean copyDir(String targetPath);

    //远程文件重命名
    boolean renameFile(String targetPath, String newName);

    //远程文件夹重命名
    boolean renameDir(String targetPath, String newName);

    boolean downloadDir(File localWk, String targetWk,String dirName) throws IOException;
}
