package com.xishui.union.captain.watcher.bytebuddy.loader;

import com.xishui.union.captain.watcher.WatcherAgent;
import com.xishui.union.captain.watcher.holder.ClassLoaderHolder;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WatcherClassLoader extends ClassLoader {

    private static WatcherClassLoader watcherClassLoader;

    public WatcherClassLoader(ClassLoader parent) {
        super(parent);
    }

    public static WatcherClassLoader getClassLoader() {
        if (WatcherClassLoader.watcherClassLoader == null) {
            synchronized (WatcherClassLoader.class) {
                if (null == WatcherClassLoader.watcherClassLoader) {
                    WatcherClassLoader.watcherClassLoader = new WatcherClassLoader(WatcherAgent.class.getClassLoader());
                }
            }
        }
        return WatcherClassLoader.watcherClassLoader;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.indexOf(".class") < 0) {
            name.concat(".class");
        }
        String clazzName = ClassLoaderHolder.get(name);
        if(clazzName.indexOf(".class") >= 0){
            clazzName = clazzName.substring(0,clazzName.indexOf(".class")).replace('/','.');
        }
        if(clazzName.startsWith(".")){
            clazzName = clazzName.substring(1);
        }
        try {
            URL classFileUrl = new URL("file:" + name);
            byte[] data = null;
            BufferedInputStream is = null;
            ByteArrayOutputStream baos = null;
            try {
                is = new BufferedInputStream(classFileUrl.openStream());
                baos = new ByteArrayOutputStream();
                int ch = 0;
                while ((ch = is.read()) != -1) {
                    baos.write(ch);
                }
                data = baos.toByteArray();
            } finally {
                if (is != null)
                    try {
                        is.close();
                    } catch (IOException ignored) {
                    }
                if (baos != null)
                    try {
                        baos.close();
                    } catch (IOException ignored) {
                    }
            }
            return defineClass(clazzName, data, 0, data.length);
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        throw new ClassNotFoundException("Can't Find class:" + name);
    }
}
