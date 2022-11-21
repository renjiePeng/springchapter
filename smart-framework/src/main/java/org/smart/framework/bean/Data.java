package org.smart.framework.bean;

/**
 * @PackageName: org.smart.framework.bean
 * @Author 彭仁杰
 * @Date 2022/11/21 14:32
 * @Description 返回数据对象
 **/
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
