package com.example.jianqiang.mypluginlibrary;

/**
 * Created by jianqiang on 17/1/18.
 */
public class PatchedClassInfo {
    public String fixClassName;     //哪个类需要热修复
    public String patchClassName;   //Patch类名

    public PatchedClassInfo(String fixClassName, String patchClassName) {
        this.fixClassName = fixClassName;
        this.patchClassName = patchClassName;
    }
}
