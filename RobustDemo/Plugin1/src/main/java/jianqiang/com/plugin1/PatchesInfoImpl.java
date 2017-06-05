package jianqiang.com.plugin1;

import com.example.jianqiang.mypluginlibrary.PatchedClassInfo;
import com.example.jianqiang.mypluginlibrary.PatchesInfo;

import java.util.ArrayList;
import java.util.List;

public class PatchesInfoImpl implements PatchesInfo {
    public List<PatchedClassInfo> getPatchedClassesInfo() {
        List<PatchedClassInfo> patchedClassesInfos = new ArrayList<PatchedClassInfo>();
        PatchedClassInfo patchedClass = new PatchedClassInfo(
                "cn.wjdiankong.robust.MoneyBean",
                MoneyBeanStatePatch.class.getCanonicalName());
        patchedClassesInfos.add(patchedClass);
        return patchedClassesInfos;
    }
}