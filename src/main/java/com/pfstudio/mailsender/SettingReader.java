package com.pfstudio.mailsender;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * @author 杨丰畅
 * @description TODO
 * @date 2020/4/22 16:24
 **/
public class SettingReader {

    public static Properties properties = new Properties();

    static {
        InputStream in =
                Thread.currentThread().getContextClassLoader().getResourceAsStream("application" +
                        ".properties");
        try {
            if (in != null) {
                properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getExcelPath() {
        return (String) properties.get("EXCEL_PATH");
    }

    public static String getEmailSender() {
        return (String) properties.get("EMAIL_SENDER");
    }

    public static String getGrantCode() {
        return (String) properties.get("GRANT_CODE");
    }

    public static String getMailTitle() {
        return new String(((String) properties.get("MAIL_TITLE")).getBytes(), StandardCharsets.UTF_8);
    }

    public static String getMailContent() {
        return new String(((String) properties.get("MAIL_CONTENT")).getBytes(), StandardCharsets.UTF_8);
    }


}
