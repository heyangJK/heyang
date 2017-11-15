package org.smart4j.framework.bean;  
  
/** 
 * 返回数据对象 
 */  
public class Data {  
    /** 
     * 模型数据 
     */  
    private Object model;  
  
    public Data(Object model) {  
        this.model = model;  
    }  
  
    /** 
     * 获取数据 
     * 
     * @return 
     */  
    public Object getModel() {  
        return model;  
    }  
}  

/*返回的Data类型的数据封装了一个Object类型的模型数据，
框架会将该对象写入HttpServletResponse对象中，从而自己输出至浏览器。*/