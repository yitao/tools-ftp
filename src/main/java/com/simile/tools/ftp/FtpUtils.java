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
 * FTP工具类
 * Created by yitao on 2019/1/2.
 */
public class FtpUtils {
    private static int mode;
    public final static int MODE_COMMON = 0;  //普通
    public final static int MODE_RECYCLE = 1; //软对象回收
    public final static int MODE_THREAD_LOCAL = 2; //本地线程
    private static FtpConfiguration configuration;
    private static FTPClient ftpClient;
    private static IFtpUtils iFtpUtils;


    public static void loadConfiguration(int mode, FtpConfiguration configuration) throws IOException {
        FtpUtils.mode = mode;
        FtpUtils.configuration = configuration;
        switch (mode) {
            case 1:
                iFtpUtils = new SoftRecyclableFtpUtils(configuration);
                break;
            case 2:
                iFtpUtils = new ThreadLocalRecyclableFtpUtils(configuration);
                break;
            default:
                iFtpUtils = new CommonFtpUtils(configuration);
                break;
        }
    }

    public static FTPClient getFTPClient() throws IOException {
        return iFtpUtils.getFTPClient();
    }

    public static boolean renameDir(String targetPath, String newName) {
        return iFtpUtils.renameDir(targetPath, newName);
    }

    public static boolean downloadFile(OutputStream os, String targetPath) throws IOException {
        return iFtpUtils.downloadFile(os, targetPath);
    }

    public static boolean downloadDir(File localWk, String targetWk, String dirName) throws IOException {
        return iFtpUtils.downloadDir(localWk, targetWk, dirName);
    }

    public static boolean uploadFile(InputStream is, String targetPath) {
        return iFtpUtils.uploadFile(is, targetPath);
    }

    public static boolean deleteFile(String targetPath) {
        return iFtpUtils.deleteFile(targetPath);
    }

    public static boolean copyDir(String targetPath) {
        return iFtpUtils.copyDir(targetPath);
    }

    public static Map<String, List<FTPFile>> listFile(String targetPath) throws IOException {
        return iFtpUtils.listFile(targetPath);
    }

    public static boolean deleteDir(String targetPath, boolean unProtected) {
        return iFtpUtils.deleteDir(targetPath, unProtected);
    }

    public static boolean renameFile(String targetPath, String newName) {
        return iFtpUtils.renameFile(targetPath, newName);
    }

    public static boolean copyFile(String targetPath) {
        return iFtpUtils.copyFile(targetPath);
    }
}
