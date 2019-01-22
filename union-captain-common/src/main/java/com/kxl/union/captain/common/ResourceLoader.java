package com.kxl.union.captain.common;

import com.kxl.union.captain.common.logger.CaptainLogger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public enum ResourceLoader {
    LOADER;

    public synchronized void loader(final Set<URL> urls, String... files) {
        if (null == files || files.length < 0) {
            return;
        }
        String classpath = System.getProperty("java.class.path");
        final List<String> pathLists = Arrays.asList(classpath.split(System.getProperty("path.separator")));
        final List<String> dependencyPaths = new ArrayList<>();
        pathLists.forEach(path -> {
            if (path.indexOf("jdk") >= 0 || path.indexOf("/jre") >= 0) {
            } else {
                dependencyPaths.add(path);
            }
        });

        final List<JarFile> jarFiles = new ArrayList<>();
        final List<String> sourceFiles = new ArrayList<>();
        dependencyPaths.forEach(dependency -> {
            //jar文件分离
            if (dependency.endsWith(".jar")) {
                try {
                    jarFiles.add(new JarFile(new File(dependency)));
                } catch (IOException e) {
                    CaptainLogger.error(this.getClass(), dependency + " new JarFile Err:" + e.getMessage());
                }
            } else {
                sourceFiles.add(dependency);
            }
        });
        for (final String name : files) {
            for (JarFile jar : jarFiles) {
                try {
                    for (Enumeration<JarEntry> enumeration = jar.entries(); enumeration.hasMoreElements(); ) {
                        JarEntry jarEntry = enumeration.nextElement();
                        //满足当前jar的配置 META-INF
                        if (name.substring(1).equals(jarEntry.getName())) {
                            urls.add(new URL("jar:file:" + jar.getName() + "!" + name));
                        }
                        //针对spring boot 打包后
                        if (jarEntry.getName().startsWith("BOOT-INF/lib/")) {
                            urls.add(new URL("jar:file:" + jar.getName() + "!/" + jarEntry.getName() + "!" + name));
                        }
                    }
                } catch (Exception e) {
                    CaptainLogger.error(this.getClass(), jar.getName() + " new Jar URL Err:" + e.getMessage());
                }
            }
            for (String source : sourceFiles) {
                try {
                    File file = new File(source + name);
                    if (file.exists()) {
                        if (file.isDirectory()) {
                            lookupURLs(file, urls);
                        } else {
                            urls.add(new URL("file:" + file.getAbsolutePath()));
                        }
                    }
                } catch (Exception e) {
                    CaptainLogger.error(this.getClass(), source + " new File URL Err:" + e.getMessage());
                }
            }
            if (null == urls || urls.size() <= 0) {
                CaptainLogger.info(this.getClass(), "Resource Resolver,Get URLs is null.");
            } else {
                urls.forEach(u -> {
                    System.out.println("path" + u.getPath());
                });
            }

        }
    }

    private void lookupURLs(File directoryPath, final Set<URL> urls) throws Exception {
        for (File f : directoryPath.listFiles()) {
            if (!f.isDirectory()) {
                urls.add(new URL("file:" + f.getAbsolutePath()));
            } else {
                lookupURLs(f, urls);
            }
        }

    }
}
