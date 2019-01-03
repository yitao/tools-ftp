package com.simile.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 可回收的FTP工具类
 * 利用java弱对象进行回收
 * Created by yitao on 2019/1/2.
 */
public class SoftRecyclableFtpUtils extends AbstractFtpUtils {

    SoftReference<FTPClient> softReference = null;

    public SoftRecyclableFtpUtils(FtpConfiguration configuration) throws IOException{
        super(configuration);
    }

    @Override
    public FTPClient getFTPClient() throws IOException {
        FTPClient ftpClient = null;
        if (softReference == null) {
            ftpClient = super.getFTPClient();
            softReference = new SoftReference(ftpClient);
        } else {
            ftpClient = softReference.get();
        }
        return ftpClient;
    }


}
