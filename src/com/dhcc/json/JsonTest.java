package com.dhcc.json;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.junit.Test;

// ~ File Information
// ====================================================================================================================

public class JsonTest {

	// setCycleDetectionStrategy,防止自包含,主要是因为在 Web项目中数据库表很多时
	// ，往往会出现表Set集合与表Set集合的互相嵌套，
	// 较为常见的比如部门和与员工对象中，部门里有员工对象的集合，
	// 员工对象中有部门，这样在转化为json时会报如下的错误。
	// 在将这些数据进行JSON转换时很可能会报一个错误
	// “There is a cycle in the hierarchy!”。
	@Test
	public void testCycleObject() {

		CycleObject object = new CycleObject();

		object.setMemberId("test");
		object.setSex("male");

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

		JSONObject json = JSONObject.fromObject(object, jsonConfig);
		// JSONObject json = JSONObject.fromObject(object);
		System.out.println(json);
	}

	/*
	 * setExcludes：排除需要序列化成json的属性
	 */
	@Test
	public void testExcludeProperites() {

		String str = "{'string':'JSON', 'integer': 1, 'double': 2.0, 'boolean': true}";
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] {
			"double", "boolean"
		});
		// 利用
		JSONObject json = (JSONObject) JSONSerializer.toJSON(str, config);

		System.out.println(json.getString("string"));
		System.out.println(json.getInt("integer"));
		System.out.println(json.has("double"));
		System.out.println(json.has("boolean"));
	}

	/*
	 * setIgnoreDefaultExcludes方法,如下方法中， 如果设置为false则打印出的内容中只有name，没有class 这是源码中默认的会过滤的关键字。 private static final String[]
	 * DEFAULT_EXCLUDES = { "class", "declaringClass", "metaClass" };
	 */
	@Test
	public void testMap() {

		Map map = new HashMap();

		map.put("name", "json");
		map.put("class", "2");
		map.put("test", "test");
		JsonConfig config = new JsonConfig();

		config.setIgnoreDefaultExcludes(true);// 默认为false，即过滤默认的key
		// JSONObject jsonObject = JSONObject.fromObject(map,config);
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);
	}

	/*
	 * registerJsonBeanProcessor 当value类型是从java的一个bean转化过来的时候， 可以提供自定义处理器
	 */
	@Test
	public void testRegisterJsonBeanProcessor() {

		Map map = new HashMap();

		map.put("name", "json");
		map.put("class", "ddd");
		map.put("date", new Date());

		JsonConfig config = new JsonConfig();
		config.setIgnoreDefaultExcludes(false);
		/*
		 * 当输出时间格式时，采用和js兼容的格式输出。
		 * 
		 * JsDateJsonBeanProcessor 是json-lib已经提供的类，我们也可以实现自己的JsonBeanProcessor。 厚盾的预算系统中的processor就是自己实现的输出指定的日期格式。
		 * 如果没有config设置时间格式，那么json中date类型的数据格式如下： "date":{"date":8,"day":4,"hours":15,"minutes":42,"month":4,
		 * "seconds":3,"time":1399534923897,"timezoneOffset":-480,"year":114} 而设置了如下的处理器之后的格式则如下
		 * "date":{"year":2014,"month":4,"day":8,"hours":15,"minutes":44,"seconds":20,"milliseconds":827}
		 * 如果自己实现个处理器，则效果如下，处理器是参考厚盾公司的处理器来的，只是些处理日期的类。
		 * 不过厚盾系统中的DateJsonValueProcessor是config.registerJsonValueProcessor()的实现处理器，如果要是先bena 的话，需要自己手动的重新实现。
		 * "date":"2014-05-08"
		 */
		config.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
		// config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONObject jsonObject = JSONObject.fromObject(map, config);
		System.out.println(jsonObject);

	}

	/*
	 * registerDefaultValueProcessor,为了演示，首先实现了两个processor，一个是针对Integer， 一个针对PlainObject(自定义的类)
	 * 以上两个类用于处理当value为null的时候该如何输出。 还准备了两个普通的自定义bean：PlainObjectHolder和PlainObject
	 * 
	 * A，如果JSONObject.fromObject(null) 这个参数直接传null进去,json-lib会怎么处理： public static JSONObject fromObject( Object object,
	 * JsonConfig jsonConfig ) { if( object == null || JSONUtils.isNull( object ) ){ return new JSONObject( true );
	 * 看代码是直接返回了一个空的JSONObject，没有用到任何默认值输出。 B，其次，我们看如果java对象直接是一个JDK中已经有的类 （什么指
	 * Enum，Annotation，JSONObject，DynaBean，JSONTokener，JSONString，Map，String，Number，Array）， 但是值为null ，json-lib如何处理 }else
	 * if( object instanceof Enum ){ throw new JSONException( "'object' is an Enum. Use JSONArray instead" ); // 不支持枚举
	 * }else if( object instanceof Annotation || (object != null && object.getClass() .isAnnotation()) ){ throw new
	 * JSONException( "'object' is an Annotation." ); // 不支持 注解 }else if( object instanceof JSONObject ){ return
	 * _fromJSONObject( (JSONObject) object, jsonConfig ); }else if( object instanceof DynaBean ){ return _fromDynaBean(
	 * (DynaBean) object, jsonConfig ); }else if( object instanceof JSONTokener ){ return _fromJSONTokener(
	 * (JSONTokener) object, jsonConfig ); }else if( object instanceof JSONString ){ return _fromJSONString(
	 * (JSONString) object, jsonConfig ); }else if( object instanceof Map ){ return _fromMap( (Map) object, jsonConfig
	 * ); }else if( object instanceof String ){ return _fromString( (String) object, jsonConfig ); }else if(
	 * JSONUtils.isNumber( object ) || JSONUtils.isBoolean( object ) || JSONUtils.isString( object ) ){ return new
	 * JSONObject(); // 不支持纯数字 }else if( JSONUtils.isArray( object ) ){ throw new JSONException(
	 * "'object' is an array. Use JSONArray instead" ); //不支持数组，需要用JSONArray替代 }else{
	 * 
	 * 我们看如果 java 对象是自定义类型的， 并且里面的属性包含空值（没赋值，默认是null） 也就是上面B还没贴出来的最后一个else else {return _fromBean( object, jsonConfig
	 * );}
	 */

	@Test
	public void testDefaultValueProcessor() {

		PlainObjectHolder holder = new PlainObjectHolder();
		JsonConfig config = new JsonConfig();
		config.registerDefaultValueProcessor(PlainObject.class, new MyPlainObjectProcessor());
		config.registerDefaultValueProcessor(Integer.class, new MyDefaultIntegerValueProcessor());
		JSONObject json = JSONObject.fromObject(holder, config);
		System.out.println(json);

	}
	/*
	 * java序列化为json的话，主要的方法为，JSONSerializer.toJson()方法。
	 */
	@Test
	public void testJava2json(){
		String jsonString = "{'name':'hello','class':'ddd'}";  
        JsonConfig config = new JsonConfig();  
        config.setIgnoreDefaultExcludes(true); // 与JAVA To Json的时候一样，不设置class属性无法输出  
        JSONObject json = (JSONObject) JSONSerializer.toJSON(jsonString,config);  
        System.out.println(json);  
	}
	
	/*做程序的时候主要是使用 Java To Json的方式，下面描述的是 安全性问题
	 * 输出的内容：

  {"\"}<IMG src='x.jpg' onerror=javascript:alert('说了你不要进来') border=0> {":"" }
 

 如果把这段内容直接贴到记事本里面，命名为 testSecu.html ，然后用浏览器打开发现执行了其中的 js脚本。这样就容易产生XSS安全问题。
	 */
	@Test
	public void testSecurity(){
		
		Map map = new HashMap();  
	       map.put("\"}<IMG src='x.jpg' onerror=javascript:alert('说了你不要进来') border=0> {", "");  
	       
	       JSONObject jsonObject = JSONObject.fromObject(map);  
	       System.out.println(jsonObject);  
		
	}

}
