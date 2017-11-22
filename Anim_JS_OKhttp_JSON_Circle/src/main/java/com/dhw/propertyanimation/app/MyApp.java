package com.dhw.propertyanimation.app;

import android.app.Application;
import android.support.v4.BuildConfig;

import com.dhw.propertyanimation.util.CrashConfig;
import com.dhw.propertyanimation.util.CrashHandler;
import com.dhw.propertyanimation.util.FileUtils;

public class MyApp extends Application {


    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FileUtils.CreateDir();//创建错误日志文件夹
        if (CrashConfig.HAVE_LOG) {
            CrashHandler crashHandler = CrashHandler.getInstance();
            crashHandler.init(this.getApplicationContext());
        }
        boolean b = FileUtils.checkFilePathExists(FileUtils.SDPATH);
        StringBuffer buffer = new StringBuffer();
        buffer.append("是否会生成错误日志："+(CrashConfig.HAVE_LOG+""))
                .append("\n\n")
                .append("当前编译模式：")
                .append(BuildConfig.DEBUG ? "debug模式" : "release模式")
                .append("\n\n")
                .append("存放错误日志文件夹是否存在：" + b)
                .append("\n\n")
                .append("存放错误日志文件夹物理路径：")
                .append("\n\n")
                .append(FileUtils.file.getAbsolutePath());

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}