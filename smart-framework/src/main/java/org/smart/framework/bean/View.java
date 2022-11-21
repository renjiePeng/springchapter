package org.smart.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @PackageName: org.smart.framework.bean
 * @Author 彭仁杰
 * @Date 2022/11/21 14:28
 * @Description 返回视图对象
 **/
public class View {

    /**
     * 视图路径
     */
    private String path;

    private Map<String,Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
