package com.simile.tools.ftp.test;

import com.simile.tools.ftp.DefaultFtpConfiguration;
import com.simile.tools.ftp.FtpUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by yitao on 2019/1/2.
 */
public class FtpTest {

    @Test
    public void testDownloadDir() throws IOException {
        String templateId = "59535819500266645772b2ec";
        String targetDir = "/bizcard/card/template/";
        String localPath = "D:\\data\\template\\";
        long t1 = System.currentTimeMillis();
        String server = "";
        String username = "";
        String password = "";
        DefaultFtpConfiguration configuration = DefaultFtpConfiguration.getInstance(server, 21, username, password, "0");
        FtpUtils.loadConfiguration(FtpUtils.MODE_COMMON, configuration);
        File file = new File(localPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        FtpUtils.downloadDir(file, targetDir, templateId);
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

    }
}
