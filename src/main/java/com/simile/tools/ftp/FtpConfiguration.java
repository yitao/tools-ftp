package com.simile.tools.ftp;

/**
 * FTP配置
 * Created by yitao on 2019/1/2.
 */
public interface FtpConfiguration {

    int BUF_SIZE = 1 << 20;

    String getFtpServer();

    int getFtpPort();

    String getFtpUsername();

    String getFtpPassword();

    String getFtpMode();

    long getConnectTimeout();

    long getDataTimeout();
}
