package com.dhcc.json;

import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;
/**
 * 
自定义的processor，主要是为了处理Integer为空的情况
 * 
 * @author zx
 * @createDate 2014-5-8
 * @since TODO: 来源版本
 *
 */

public class MyDefaultIntegerValueProcessor implements DefaultValueProcessor {

	 public Object getDefaultValue(Class type) {  
	        if (type != null && Integer.class.isAssignableFrom(type)) {  
	            return Integer.valueOf(9999);  
	        }  
	        return JSONNull.getInstance();  
	    }  
   
  
}  
