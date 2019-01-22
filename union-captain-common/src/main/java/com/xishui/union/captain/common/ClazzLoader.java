package com.xishui.union.captain.common;

public enum ClazzLoader {
    LOADER;

    public Class<?> load(String clazz,ClassLoader classLoader) throws Exception{
        if(null == classLoader){
            classLoader = ClazzLoader.class.getClassLoader();
        }
        return Class.forName(clazz,true,classLoader);
    }

}
