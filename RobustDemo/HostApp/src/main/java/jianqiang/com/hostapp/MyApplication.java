package jianqiang.com.hostapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.jianqiang.mypluginlibrary.ChangeQuickRedirect;
import com.example.jianqiang.mypluginlibrary.PatchedClassInfo;
import com.example.jianqiang.mypluginlibrary.PatchesInfo;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import dalvik.system.DexClassLoader;

public class MyApplication extends Application {
    private DexClassLoader mLoader;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        Log.d("jw", "start load dex");
        boolean isSucc = loadDex(base);
        Log.d("jw", "load dex issucc:" + isSucc);
        if (isSucc) {
            Log.d("jw", "start patch");
            patch();
        }
        super.attachBaseContext(base);
    }

    @SuppressLint({"SdCardPath"})
    private boolean loadDex(Context ctx) {
        File dexFile = new File("/sdcard/patch.dex");
        if (!dexFile.exists()) {
            Log.d("jw", "patch.dex is not exist!");
            return false;
        }
        try {
            File odexDir = new File(ctx.getFilesDir() + File.separator + "odex" + File.separator);
            if (!odexDir.exists()) {
                odexDir.mkdirs();
            }
            mLoader = new DexClassLoader(dexFile.getAbsolutePath(), odexDir.getAbsolutePath(), null, ctx.getClassLoader());
            Log.i("jw", "mloader;" + mLoader);
            return true;
        } catch (Throwable e) {
            Log.d("jw", "load patch error:" + Log.getStackTraceString(e));
        }
        return false;
    }

    @SuppressLint("NewApi")
    private void patch() {
        try {
            //先得到修复包中的PatchesInfoImpl类
            Class<?> patchInfoClazz = mLoader.loadClass("jianqiang.com.plugin1.PatchesInfoImpl");
            PatchesInfo patchInfo = (PatchesInfo) patchInfoClazz.newInstance();
            //获取修复包中所有待修复类信息
            List<PatchedClassInfo> infoList = patchInfo.getPatchedClassesInfo();
            for (PatchedClassInfo info : infoList) {
                //加载所有修复类对象
                ChangeQuickRedirect redirectObj = (ChangeQuickRedirect) mLoader.loadClass(
                        info.patchClassName).newInstance();
                //获取待修复旧类类型
                Class<?> fixClass = mLoader.loadClass(info.fixClassName);
                //将修复类对象设置到待修复旧类的changeQuickRedirect变量中
                Field redirectF = fixClass.getField("changeQuickRedirect");
                redirectF.set(null, redirectObj);
            }
        } catch (Throwable e) {
            Log.d("jw", "patch error:" + Log.getStackTraceString(e));
        }
    }
}