package com.dhcc.json;

import net.sf.json.JSONNull;
import net.sf.json.processors.DefaultValueProcessor;

/**
 * 
自定义的processor，主要是为了处理PlainObject为空的情况
 * 
 * @author zx
 * @createDate 2014-5-8
 * @since TODO: 来源版本
 *
 */

public class MyPlainObjectProcessor implements DefaultValueProcessor {  
	  
    public Object getDefaultValue(Class type) {  
        if (type != null && PlainObject.class.isAssignableFrom(type)) {  
            return "美女" + "瑶瑶";  
        }  
        return JSONNull.getInstance();  
    }  
}  
