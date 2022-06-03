package com.wjh.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author wenjianhai
 * @date 2022/5/29
 * @since JDK 1.8
 */
public final class JsonUtils {

	private JsonUtils() {
	}

	/**
	 * 根据JSON字符串解析出java对象
	 * <p>
	 * 需要提供java对象类型
	 *
	 * @param text  JSON字符串，不能为<code>NULL</code>
	 * @param clazz java对象类型
	 * @return java对象
	 */
	public static final <T> T parse(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}

	/**
	 * 根据字节数组解析出java对象
	 * <p>
	 * 需要提供java对象类型
	 *
	 * @param input 字节数组
	 * @param clazz java对象类型
	 * @return java对象
	 */
	public static final <T> T parse(byte[] input, Class<T> clazz) {
		return JSON.parseObject(input, clazz, new Feature[0]);
	}

	/**
	 * 根据java对象转换成字节数组
	 * <p>
	 * 按照默认方式转换，只输出属性和属性值信息，不加入任何其他类型信息
	 *
	 * @param object java对象
	 * @return 字节数组
	 */
	public static final byte[] toJSONBytes(Object object) {
		return JSON.toJSONBytes(object, new SerializerFeature[0]);
	}

	/**
	 * 获取JSON串中指定的key对应的值
	 *
	 * @param json
	 * @param key
	 * @return String
	 * @author wenjianhai
	 * @date 2018年11月12日
	 * @since JDK 1.7
	 */
	public static String getJsonValue(String json, String key) {
		if (StringUtils.isBlank(json) || StringUtils.isBlank(key)) {
			return null;
		}
		JSONObject jsonObj = JSONObject.parseObject(json);
		return jsonObj.getString(key);
	}

	/**
	 * 获取JSON串中指定的key对应的值
	 *
	 * @param json 数组
	 * @param key
	 * @return String
	 * @author wenjianhai
	 * @date 2018年12月12日
	 * @since JDK 1.7
	 */
	public static List<String> getJsonArrayValue(String json, String key) {

		if (StringUtils.isBlank(json) || StringUtils.isBlank(key)) {
			return null;
		}

		List<String> list = new ArrayList<>();

		JSONArray jsonArray = JSON.parseArray(json);

		if (jsonArray != null) {

			for (int i = 0; i < jsonArray.size(); i++) {

				JSONObject jsonObj = jsonArray.getJSONObject(i);

				list.add(jsonObj.getString(key));
			}
		}
		return list;
	}

	public static String toJsonStringFormat(Object obj) {
		// 自定义时间格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

		return JSON.toJSONString(obj, //
				// SerializerFeature.PrettyFormat, // 格式化结果
				SerializerFeature.WriteMapNullValue, // 输出值为null的字段
				SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null,输出为”“,而非null
				// SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null,输出为0,而非null
				// SerializerFeature.DisableCircularReferenceDetect, // 消除对同一对象循环引用的问题
				SerializerFeature.WriteNullListAsEmpty, // List字段如果为null,输出为[],而非null
				SerializerFeature.WriteDateUseDateFormat // 自定义时间格式
		);
	}
}
