package com.dhcc.json;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

/**
 * 
 * json-lib是一个java类库，提供将Java对象，
 * 包括beans, maps, collections, java arrays and XML等
 * 转换成JSON，或者反向转换的功能。
 * @author zx
 * @createDate 2014-5-8
 * @since TODO: 来源版本
 *
 */
public class JsonLibTest {

	/*
	 * 普通类型，List，Collection等都是用JSONArray解析， Map，自定义类型使用jsonobject解析， 可以将map理解为一个对象，里面的key/value对可以理解为对象的属性/属性值
	 * 即{key1:value1,key2:value2......}
	 * 
	 * JSONObject是一个name：values集合，通过它的get(key)方法取得到的是key对应的values部分（字符串）
	 * 
	 * 通过它的getJSONObject(key)可以取到一个JSONObject，--> 转换成map, 通过它的getJSONArray(key) 可以取到一个JSONArray ，
	 */
	// 一般数组转换为json
	@Test
	public void testArrayToJSON() {

		boolean[] boolArray = new boolean[] {
			true, false
		};
		JSONArray jsonArray = JSONArray.fromObject(boolArray);

		System.out.println(jsonArray);

	}

	// collections转换为json对象
	@Test
	public void testListToJSON() {

		List<String> list = new ArrayList<String>();

		list.add("first");
		list.add("second");
		list.add("third");

		JSONArray listArray = JSONArray.fromObject(list);
		System.out.println(listArray);

	}

	// 字符串json转换成json， 根据情况是用JSONArray或JSONObject
	@Test
	public void testStringToJSON() {

		JSONArray jsonArray = JSONArray.fromObject("['json','is','easy']");

		System.out.println(jsonArray);

	}

	// map转换为json，是使用jsonobject
	@Test
	public void testMapToJSON() {

		Map map = new HashMap();

		map.put("name", "json");
		map.put("bool", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("arra", new String[] {
			"a", "b"
		});
		map.put("func", "function(i){return this.arra[i];}");

		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);

	}

	// 复合型bean转化为json
	@Test
	public void testBeanToJSON() {

		MyBean myBean = new MyBean();

		myBean.setId("001");
		myBean.setName("银行卡");
		myBean.setDate(new Date());

		List cardNum = new ArrayList();
		cardNum.add("农行");
		cardNum.add("工行");
		cardNum.add("建行");
		cardNum.add(new Person("test"));

		myBean.setCardNum(cardNum);

		JSONObject jsonObject = JSONObject.fromObject(myBean);
		System.out.println(jsonObject);

	}

	// 普通类型的json转化成对象
	@Test
	public void testJSONToObject() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		String json = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";

		JSONObject jsonObject = JSONObject.fromObject(json);

		System.out.println(jsonObject);

		Object bean = JSONObject.toBean(jsonObject);

		assertEquals(jsonObject.get("name"), PropertyUtils.getProperty(bean, "name"));
		assertEquals(jsonObject.get("bool"), PropertyUtils.getProperty(bean, "bool"));
		assertEquals(jsonObject.get("int"), PropertyUtils.getProperty(bean, "int"));
		assertEquals(jsonObject.get("double"), PropertyUtils.getProperty(bean, "double"));
		assertEquals(jsonObject.get("func"), PropertyUtils.getProperty(bean, "func"));
		// System.out.println(PropertyUtils.getProperty(bean, "name"));
		// System.out.println(PropertyUtils.getProperty(bean, "bool"));
		// /System.out.println(PropertyUtils.getProperty(bean, "int"));
		// System.out.println(PropertyUtils.getProperty(bean, "double"));
		// System.out.println(PropertyUtils.getProperty(bean, "func"));
		// System.out.println(PropertyUtils.getProperty(bean, "array"));

		List arrayList = (List) JSONArray.toCollection(jsonObject.getJSONArray("array"));
		for (Object object : arrayList) {
			System.out.println(object);
		}

	}

	// 将json解析成复合类型对象, 包含List
	@Test
	public void testJSONToBeanHavaList() {
		String json = "{list:[{name:'test1'},{name:'test2'}],map:[{test1:{name:'test1'}},{test2:{name:'test2'}}]}";
		// String json = "{list:[{name:'test1'},{name:'test2'}]}";
		Map classMap = new HashMap();
		classMap.put("list", Person.class);
		MyBeanWithPerson diyBean = (MyBeanWithPerson) JSONObject.toBean(JSONObject.fromObject(json),
				MyBeanWithPerson.class, classMap);
		System.out.println(diyBean);

		List list = diyBean.getList();
		for (Object o : list) {
			if (o instanceof Person) {
				Person p = (Person) o;
				System.out.println(p.getName());
			}
		}
	}

	// 将json解析成复合类型对象, 包含Map
	@Test
	public void testJSONToBeanHavaMap() {
		// 把Map看成一个对象
		String json = "{list:[{name:'test1'},{name:'test2'}],map:{test1:{name:'test1'},test2:{name:'test2'}}}";
		Map classMap = new HashMap();
		classMap.put("list", Person.class);
		classMap.put("map", Map.class);
		// 使用暗示，直接将json解析为指定自定义对象，其中List完全解析,Map没有完全解析
		MyBeanWithPerson diyBean = (MyBeanWithPerson) JSONObject.toBean(JSONObject.fromObject(json),
				MyBeanWithPerson.class, classMap);
		System.out.println(diyBean);

		System.out.println("do the list release");
		List<Person> list = diyBean.getList();
		for (Person o : list) {
			Person p = (Person) o;
			System.out.println(p.getName());
		}

		System.out.println("do the map release");

		// 先往注册器中注册变换器，需要用到ezmorph包中的类
		MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
		Morpher dynaMorpher = new BeanMorpher(Person.class, morpherRegistry);
		morpherRegistry.registerMorpher(dynaMorpher);

		Map map = diyBean.getMap();
		/* 这里的map没进行类型暗示，故按默认的，里面存的为net.sf.ezmorph.bean.MorphDynaBean类型的对象 */
		System.out.println(map);
		List<Person> output = new ArrayList();
		for (Iterator i = map.values().iterator(); i.hasNext();) {
			// 使用注册器对指定DynaBean进行对象变换
			output.add((Person) morpherRegistry.morph(Person.class, i.next()));
		}

		for (Person p : output) {
			System.out.println(p.getName());
		}

	}

}
