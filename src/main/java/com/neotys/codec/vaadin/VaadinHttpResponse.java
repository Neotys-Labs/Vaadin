package com.neotys.codec.vaadin;

import com.google.common.annotations.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VaadinHttpResponse {

	private Map<String, String> mapping = new HashMap<String, String>();
	private JSONObject content;

	public VaadinHttpResponse(final byte[] input) {
		super();
		String toParse = null;
		String StrToremove = "for(;;);";

		try {
			toParse = new String(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// no op
		}
		if (toParse.length() > 0) {
			int index = toParse.indexOf(StrToremove);
			if (index == -1) {
				settContent(toParse);
			} else {

				toParse = toParse.replaceAll("for\\(;;\\);", "");
				settContent(toParse);
				// settContent(toParse.substring(index+ StrToremove.length()));
			}
			computeMapping();
		}

	}

	private JSONObject getStateJsonObject() {
		try {

			JSONObject tmp = content;
			try {
				tmp = (JSONObject) content.get("uidl");
			} catch (JSONException e) {

			}
			return (JSONObject) tmp.get("state");
		} catch (JSONException e) {
			return null;
		}
	}

	private void computeMapping() {
		if (content == null) {
			return;
		}
		MappingUtils.findIds(mapping, content);
	}

	public void settContent(String cont) {
		try {
			this.content = new JSONObject();
			String StrPrefix = "{";
			int in_index = cont.indexOf(StrPrefix);
			if (in_index > 0) {
				cont = "{ \"VaadinMessage\" :" + cont;
				cont = cont + "}";
			} else {
				if (in_index == -1) {
					cont = "{ \"VaadinMessage\" :" + cont;
					cont = cont + "}";
				}
			}

			this.content = new JSONObject(cont);
			// need to decode recursiv json content in the key uidl
			if (this.content.has("uidl")) {
				String uidl = (String) this.content.get("uidl");
				if (uidl != null) {
					this.content.put("uidl", new JSONObject(uidl));
				}
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JSONObject getContent() {
		return content;
	}

	@VisibleForTesting
	Map<String, String> getMapping() {
		return mapping;
	}
}
