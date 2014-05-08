package com.dhcc.json;

/**
自定义的类，主要用于测试使用。
 * 
 * @author zx
 * @createDate 2014-5-8
 * @since TODO: 来源版本
 *
 */
public class PlainObjectHolder {  
	  
    private PlainObject object; // 自定义类型  
    private Integer a; // JDK自带的类型  
  
    public PlainObject getObject() {  
        return object;  
    }  
  
    public void setObject(PlainObject object) {  
        this.object = object;  
    }  
  
    public Integer getA() {  
        return a;  
    }  
  
    public void setA(Integer a) {  
        this.a = a;  
    }  
  
}  
