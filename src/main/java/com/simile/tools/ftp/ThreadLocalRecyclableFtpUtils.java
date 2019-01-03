package com.simile.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * 可回收的FTP工具类
 * 利用java本地线程进行回收
 * Created by yitao on 2019/1/2.
 */
public class ThreadLocalRecyclableFtpUtils extends AbstractFtpUtils {
    ThreadLocal<FTPClient> threadLocal = null;

    public ThreadLocalRecyclableFtpUtils(FtpConfiguration configuration) throws IOException{
        super(configuration);
    }

    @Override
    public FTPClient getFTPClient() throws IOException {
        FTPClient ftpClient = null;
        if (threadLocal == null) {
            threadLocal = new ThreadLocal<>();
            ftpClient = super.getFTPClient();
            threadLocal.set(ftpClient);
        } else {
            ftpClient = threadLocal.get();
        }
        return ftpClient;
    }


}
