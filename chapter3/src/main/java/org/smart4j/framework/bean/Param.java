package org.smart4j.framework.bean;

import java.util.Map;

import org.smart4j.chapter2.util.CastUtil;  
  
/** 
 * 请求参数对象 
 */  
public class Param {  
    private Map<String, Object> paramMap;  
  
    /** 
     * 构造函数 
     * 
     * @param paramMap 
     */  
    public Param(Map<String, Object> paramMap) {  
        this.paramMap = paramMap;  
    }  
  
    /** 
     * 根据参数名获取long型参数值 
     * 
     * @param name 
     * @return 
     */  
    public long getLong(String name) {  
        return CastUtil.castLong(paramMap.get(name));  
    }  
  
    /** 
     * 获取所有字段信息 
     * 
     * @return 
     */  
    public Map<String, Object> getParamMap() {  
        return paramMap;  
    }  
}  


/*在Param类中，会有一系列的get方法，可通过参数名获取指定类型的参数值，也可以获取所有参数的Map结构。
还可以从Handler对象中获取Action的方法返回值，该返回值可能有两种情况：
1）若返回值时一个View类型的视图对象，则返回一个JSP页面。
2）若返回值时一个Data类型的数据对象，则返回一个JSON数据。
我们需要根据上面的两种情况来判断Action的返回值，并做不同的处理。*/
