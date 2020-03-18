package com.ccsu.zy.util;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class JSONUtil {

	public static class MapDeserializer implements JsonDeserializer<Map<String, Object>> {

	    protected Type getPrimitiveType(JsonPrimitive primitive) {
	        if(primitive.isBoolean()) {
	            return Boolean.class;
	        } 
	        else if(primitive.isNumber()) {
	        	String str = primitive.getAsString();

				if (str.indexOf('.') > 0)
					return Double.class;
				
				return Integer.class;
	        }
	        return String.class;
	    }
	    
	    protected Object getValue(JsonElement element, JsonDeserializationContext context) {
	        Object value = null;
	        if(element.isJsonObject()) {
	            value = context.deserialize(element, Map.class);
	        }
	        else if (element.isJsonArray()) {
	            value = context.deserialize(element, Collection.class);
	        }
	        else {
	            value = context.deserialize(element, getPrimitiveType(element.getAsJsonPrimitive()));
	        }
	        return value;
	    }
	    
		public Map<String, Object> deserialize(JsonElement element,
				Type typeOfT, JsonDeserializationContext context)
				throws JsonParseException {
			if (element.isJsonNull()) {
				return null;
			}
			JsonObject json = element.getAsJsonObject();
			Map<String, Object> result = new HashMap<String, Object>();
			for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
				JsonElement jsonValue = entry.getValue();
				result.put(entry.getKey(), getValue(jsonValue, context));
			}
			return result;
		}
	}
	
	/**
	 * object to json string
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		return toJSONString(obj, false);
	}
	
	/**
	 * object to json string
	 * @param obj
	 * @param inject_class 涓簍rue鐨勬椂鍊欎細鍦╦son涓蹭腑娉ㄥ叆绫讳俊鎭敤浜庡弽灏?
	 * @return
	 */
	public static String toJSONString(Object obj, boolean inject_class) {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
				.create();
		
		if (inject_class) {
			JsonObject jo = null;
			jo = (JsonObject)gson.toJsonTree(obj);
			jo.addProperty("__class", obj.getClass().getName().toString());
			return gson.toJson(jo);
		} else {
			return gson.toJson(obj);
		}
	}
	
	public static String toJSONString(Object obj, boolean inject_class, String ext_key, Object ext_value) {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		
		if (inject_class) {
			JsonObject jo = null;
			jo = (JsonObject)gson.toJsonTree(obj);
			jo.addProperty("__class", obj.getClass().getName().toString());
			jo.add(ext_key, (JsonObject)gson.toJsonTree(ext_value));
			return gson.toJson(jo);
		} else {
			return gson.toJson(obj);
		}
	}

	public static JsonObject toJSONObject(Object obj) {
		return toJSONObject(obj, false);
	}
	
	public static JsonObject toJSONObject(Object obj, boolean inject_class) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		
		if (inject_class) {
			JsonObject jo = null;
			jo = (JsonObject)gson.toJsonTree(obj);
			jo.addProperty("__class", obj.getClass().getName().toString());
			return jo;
		} else {
			return (JsonObject)gson.toJsonTree(obj);
		}
	}

	/**
	 * string to json
	 * @param str �??瑕佽浆鎹㈡垚鐗瑰畾�?�硅薄鐨刯son瀛楃涓?
	 * @param clazz �??瑕佽浆鎹㈢殑绫诲�?
	 * @return 杞崲鍚庣殑瀵硅�?
	 */
	public static <T> T toObject(String str, Class<T> clazz) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Map.class, new MapDeserializer()).create();

		return gson.fromJson(str, clazz);
	}

	public static <T> T toObject(String str, Type type) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").registerTypeAdapter(Map.class, new MapDeserializer()).create();

		return gson.fromJson(str, type);
	}
}
