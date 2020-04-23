package com.pfstudio.mailsender.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pfstudio.mailsender.MailSender;
import com.pfstudio.mailsender.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 杨丰畅
 * @description TODO
 * @date 2020/4/22 16:17
 **/
public class UserDataListener extends AnalysisEventListener<UserInfo> {

    private static final Logger logger = LoggerFactory.getLogger(UserDataListener.class);

    // 有个很重要的点 UserDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去

    /**
     * 每隔 1000 条存储数据库，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;

    public UserDataListener() {

    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(UserInfo data, AnalysisContext context) {
        // logger.info("读取到数据 {}",data.toString());
        MailSender.send(data);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        logger.info("解析完成");
    }
}
