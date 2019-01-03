package com.simile.tools.ftp;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

/**
 * 可回收的FTP工具类
 * Created by yitao on 2019/1/2.
 */
public class CommonFtpUtils extends AbstractFtpUtils {
    private FTPClient ftpClient;

    public CommonFtpUtils(FtpConfiguration configuration) throws IOException {
        super(configuration);
    }

    @Override
    public FTPClient getFTPClient() throws IOException {
        this.ftpClient = super.getFTPClient();
        return this.ftpClient;
    }
}
