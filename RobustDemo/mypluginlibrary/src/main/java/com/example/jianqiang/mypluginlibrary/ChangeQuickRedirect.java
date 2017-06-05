package com.example.jianqiang.mypluginlibrary;

public interface ChangeQuickRedirect {
    //判断当前方法是否要热修复
    public boolean isSupport(String methodSignature, Object[] paramArrayOfObject);

    //具体修复逻辑
    public Object accessDispatch(String methodSignature, Object[] paramArrayOfObject);
}