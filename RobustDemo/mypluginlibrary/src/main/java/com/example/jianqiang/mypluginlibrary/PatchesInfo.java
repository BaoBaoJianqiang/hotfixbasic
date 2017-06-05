package com.example.jianqiang.mypluginlibrary;

import java.util.List;

public interface PatchesInfo {
    //存放哪些类需要热修复
    public List<PatchedClassInfo> getPatchedClassesInfo();
}