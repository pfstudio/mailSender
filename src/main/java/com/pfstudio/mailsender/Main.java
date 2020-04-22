package com.pfstudio.mailsender;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pfstudio.mailsender.listener.UserDataListener;
import com.pfstudio.mailsender.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 杨丰畅
 * @description TODO
 * @date 2020/4/22 16:22
 **/
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Object excelPath = SettingReader.getProperties().get("EXCEL_PATH");
        if (excelPath != null) {
            String path = (String) excelPath;
            logger.info("excel path is {}", path);
            read(path, UserInfo.class, new UserDataListener());
        } else {
            logger.error("未成功读取配置文件！");
        }
    }

    public static <T> void read(String path, Class<T> klass,
                                AnalysisEventListener<T> eventListener) {
        EasyExcel.read(path, klass, eventListener).doReadAll();
    }
}
