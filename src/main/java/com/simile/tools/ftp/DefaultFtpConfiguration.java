package com.simile.tools.ftp;

/**
 * 默认配置实现
 * Created by yitao on 2019/1/2.
 */
public class DefaultFtpConfiguration implements FtpConfiguration {
    private String ftpServer;
    private int ftpPort;
    private String ftpUsername;
    private String ftpPassword;
    private String ftpMode;
    private long connectTimeout;
    private long dataTimeout;

    private static DefaultFtpConfiguration configuration;

    private DefaultFtpConfiguration() {
    }

    public static DefaultFtpConfiguration getInstance() {
        if (configuration == null) {
            configuration = new DefaultFtpConfiguration();
        }
        return configuration;
    }

    public static DefaultFtpConfiguration getInstance(String ftpServer, int ftpPort, String ftpUsername, String ftpPassword, String ftpMode) {
        DefaultFtpConfiguration configuration = getInstance();
        configuration.ftpServer = ftpServer;
        configuration.ftpPort = ftpPort;
        configuration.ftpUsername = ftpUsername;
        configuration.ftpPassword = ftpPassword;
        configuration.ftpMode = ftpMode;
        return configuration;
    }

    @Override
    public String getFtpServer() {
        return ftpServer;
    }

    public void setFtpServer(String ftpServer) {
        this.ftpServer = ftpServer;
    }

    @Override
    public int getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(int ftpPort) {
        this.ftpPort = ftpPort;
    }

    @Override
    public String getFtpUsername() {
        return ftpUsername;
    }

    public void setFtpUsername(String ftpUsername) {
        this.ftpUsername = ftpUsername;
    }

    @Override
    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    @Override
    public String getFtpMode() {
        return ftpMode;
    }

    public void setFtpMode(String ftpMode) {
        this.ftpMode = ftpMode;
    }

    @Override
    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    @Override
    public long getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(long dataTimeout) {
        this.dataTimeout = dataTimeout;
    }
}
