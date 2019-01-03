package com.simile.tools.ftp;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.*;

/**
 * Created by yitao on 2019/1/2.
 */
public abstract class AbstractFtpUtils implements IFtpUtils {
    protected FTPClient ftpClient;
    protected FtpConfiguration configuration;

    public AbstractFtpUtils(FtpConfiguration configuration) throws IOException {
        this.configuration = configuration;
        ftpClient = new FTPClient();
        ftpClient.connect(configuration.getFtpServer(), configuration.getFtpPort());
        ftpClient.login(configuration.getFtpUsername(), configuration.getFtpPassword());
        ftpClient.setBufferSize(FtpConfiguration.BUF_SIZE);
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        ftpClient.enterLocalPassiveMode();
    }

    public FTPClient getFTPClient() throws IOException {

        return ftpClient;
    }

    //检查连接
    public boolean checkConnection() {
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            return true;
        }
        return false;
    }

    //切换工作目录
    public boolean changeWoringPath(String pathname) throws IOException {
        return ftpClient.changeWorkingDirectory(pathname);
    }

    public File getLocalFile(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    @Override
    public boolean uploadFile(InputStream is, String targetPath) {
        return false;
    }

    @Override
    public boolean downloadFile(OutputStream os, String targetPath) throws IOException {
        if (StringUtils.endsWith(targetPath, "/")) {
        } else {
            return downloadSingleFile(os, targetPath);
        }
        return false;
    }

    public boolean downloadSingleFile(OutputStream os, String targetPath) throws IOException {
        int subIndex = StringUtils.lastIndexOf(targetPath, "/");
        String fileName = null;
        if (subIndex != -1) {
            String be = StringUtils.substring(targetPath, 0, subIndex);
            fileName = StringUtils.substring(targetPath, subIndex);
            changeWoringPath(be);
        } else {
            fileName = targetPath;
        }
        ftpClient.retrieveFile(fileName, os);
        return true;
    }

    @Override
    public boolean deleteFile(String targetPath) {
        return false;
    }

    @Override
    public boolean deleteDir(String targetPath, boolean unProtected) {
        return false;
    }

    @Override
    public Map<String, List<FTPFile>> listFile(String targetPath) throws IOException {
        Map<String, List<FTPFile>> result = new LinkedHashMap<>();
        if (ftpClient.changeWorkingDirectory(targetPath)) {
            listFile(ftpClient, targetPath, result);
        }
        return result;
    }

    @Override
    public boolean copyFile(String targetPath) {
        return false;
    }

    @Override
    public boolean copyDir(String targetPath) {
        return false;
    }

    @Override
    public boolean renameFile(String targetPath, String newName) {
        return false;
    }

    @Override
    public boolean renameDir(String targetPath, String newName) {
        return false;
    }

    public static void listFile(FTPClient ftpClient, String sourceDir, final Map<String, List<FTPFile>> result) throws IOException {
        FTPFile[] ftpFiles = ftpClient.listFiles(sourceDir);
        if (!result.containsKey(sourceDir)) {
            result.put(sourceDir, new ArrayList<>());
        }
        for (FTPFile ftpFile : ftpFiles) {
            if (ftpFile.isFile()) {
                result.get(sourceDir).add(ftpFile);
            } else if (ftpFile.isDirectory()) {
                listFile(ftpClient, sourceDir + "/" + ftpFile.getName(), result);
            }
        }
    }

    @Override
    public boolean downloadDir(File localWk, String targetWk, String dirName) throws IOException {
        String path = targetWk + dirName;
        Map<String, List<FTPFile>> ftpFilesMap = listFile(path);
        for (Map.Entry<String, List<FTPFile>> entry : ftpFilesMap.entrySet()) {
            String name = entry.getKey();
            List<FTPFile> ftpFiles = entry.getValue();
            changeWoringPath(name);
            File parent = new File(localWk, name.replace(path, dirName));
            if (!parent.exists()) {
                parent.mkdirs();
            }
            for (FTPFile ftpFile : ftpFiles) {
                File file = new File(parent, ftpFile.getName());
                OutputStream os = new FileOutputStream(file);
                downloadSingleFile(os, ftpFile.getName());
                IOUtils.closeQuietly(os);
            }
        }
        return false;
    }
}
