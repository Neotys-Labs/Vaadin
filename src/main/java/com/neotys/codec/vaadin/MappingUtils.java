package com.neotys.codec.vaadin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

class MappingUtils {

	static void findIds(Map<String, String> mapping, Object object) {
		if (object instanceof JSONObject) {
			JSONObject jsonObject = (JSONObject) object;
			final Iterator it = jsonObject.keys();
			while (it.hasNext()) {
				Object key = it.next();
				final Object value = jsonObject.get(key.toString());
				if (value instanceof JSONObject) {
					final String id = getId((JSONObject) value);
					if (id != null) {
						mapping.put(id, key.toString());
					} else {
						findIds(mapping, value);
					}
				} else if (value instanceof JSONArray) {
					findIds(mapping, value);
				}
			}
		} else if (object instanceof JSONArray) {
			JSONArray array = (JSONArray) object;
			for (int i = 0; i < array.length(); i++) {
				findIds(mapping, array.get(i));
			}
		}
	}

	private static String getId(JSONObject value) {
		try {
			return value.getString("id");
		} catch (JSONException e) {
			return null;
		}
	}

}
