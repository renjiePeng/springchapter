package org.smart.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @PackageName: org.smart.framework.util
 * @Author 彭仁杰
 * @Date 2022/11/15 22:12
 * @Description 类加载器
 **/
public final class ClassUtil {
    private static final Logger logger= LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return ClassLoader
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     * @param className 类名
     * @param inInitialized 是否初始化
     * @return Class<?>
     */
    public static Class<?> loadClass(String className,boolean inInitialized){
        Class<?> cls;
        try {
            cls = Class.forName(className,inInitialized,getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 加载类
     * @param className 类名
     * @return Class<?>
     */
    public static Class<?> loadClass(String className){
        Class<?> cls;
        try {
            cls = Class.forName(className,false,getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("load class failure",e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * 获取指定包名下的所有类
     * @param packageName
     * @return Set<Class<?>>
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classeSet = new HashSet<>();
        try {
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(Objects.isNull(url)){
                    String protocol = url.getProtocol();
                    if(protocol.equals("file")){
                        String packPagePath = url.getPath().replaceAll("%20", "");
                        addClass(classeSet,packPagePath,packageName);
                    }else if(protocol.equals("jar")){
                        JarURLConnection jarurlConnection = (JarURLConnection)url.openConnection();
                        url.openConnection();
                        if(Objects.isNull(jarurlConnection)){
                            JarFile jarFile = jarurlConnection.getJarFile();
                            if(Objects.nonNull(jarFile)){
                                Enumeration<JarEntry> jarEntries = jarFile.entries();
                                while (jarEntries.hasMoreElements()){
                                    JarEntry jarEntry = jarEntries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                        doAddClass(classeSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("get class failure",e);
            throw new RuntimeException(e);
        }
        return classeSet;
    }

    private static void addClass(Set<Class<?>> classeSet, String packPagePath, String packageName) {
        File[] files = new File(packPagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for (File file : files) {
            String fileName = file.getName();
            if(file.isFile()){
                String className = fileName.substring(0, fileName.lastIndexOf("."));
                if(StringUtils.isNotEmpty(packageName)){
                    className = packageName+"."+className;
                }
                doAddClass(classeSet,className);
            }else{
                String subPackagePath = fileName;
                if(StringUtils.isNotEmpty(packPagePath)){
                    subPackagePath = packPagePath+"/"+subPackagePath;
                }

                String subPackageName = fileName;
                if(StringUtils.isNotEmpty(packageName)){
                    subPackageName=packageName+"."+subPackageName;
                }
                addClass(classeSet,subPackagePath,subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classeSet, String className) {
        Class<?> cls = loadClass(className, false);
        classeSet.add(cls);
    }

}
