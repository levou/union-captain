package com.xishui.union.captain.factory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarTest {

    public static void main(String... args) {
//        System.out.println(Component.class.getProtectionDomain().getCodeSource().getLocation().getFile());
        String classpath = System.getProperty("java.class.path");
        String[] paths = classpath.split(System.getProperty("path.separator"));

        List<String> pathLists = Arrays.asList(paths);
        List<String> sysLib = new ArrayList<>();
        List<String> projectLib = new ArrayList<>();
        pathLists.forEach(path -> {
            if (path.indexOf("jdk") >= 0) {
                sysLib.add(path);
            } else {
                projectLib.add(path);
            }
        });

        List<JarFile> jarFiles = new ArrayList<>();
        List<String> sourceFiles = new ArrayList<>();
        projectLib.forEach(project -> {
            if (project.endsWith(".jar")) {
                try {
                    jarFiles.add(new JarFile(new File(project)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sourceFiles.add(project);
            }
        });
        List<URL> urls = new ArrayList<>();

        String name = "/META-INF/component-captain.def";

        for (JarFile jar : jarFiles) {
            try {
                JarEntry jarEntry = jar.getJarEntry(name);
//                System.out.println("jar:" + jar.getName());
                if (null != jarEntry) {
                    urls.add(new URL("jar:file:" + jar.getName() + "!" + name));
                } else {
                    System.out.println("jar:" + jar.getName() + " not found " + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(String source : sourceFiles){
            try {
                File file = new File(source + name);
                if (file.exists()) {
                    urls.add(new URL("file:"+file.getAbsolutePath()));
                }else{
                    System.out.println("source :" + file.getAbsolutePath()+" not found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("---------------ulrs------------------");
        urls.forEach(url -> System.out.println(url));
    }
}
