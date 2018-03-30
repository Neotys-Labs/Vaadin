package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class VaadinUrlEncodedRequest {
	private final JSONObject content;

	public VaadinUrlEncodedRequest(final byte[] input) {
		String toParse = null;

		this.content = new JSONObject();
		try {
			toParse = new String(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// no op
		}
		String[] strPipes = toParse.split("\\&");
		for (int i = 0; i < strPipes.length; i++) {
			String[] params = strPipes[i].split("\\=");
			try {
				if (params.length > 1) {
					this.content.put(params[0], params[1]);
				} else {
					this.content.put(params[0], "");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public JSONObject getContent() {
		return content;
	}

	public byte[] format() {

//		String jsonPrettyPrintString = "";
//		if (content.has("HeartBeat"))
//		{
//			jsonPrettyPrintString = "undefined";
//		}
//		else
//		{
//			try {
//				jsonPrettyPrintString = content.toString(4);
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		String output = jsonPrettyPrintString;

		final StringBuilder sb = new StringBuilder();

		final String[] names = JSONObject.getNames(content);
		for (int index = 0; index < names.length; index++) {
			sb.append(names[index]).append("=");
			try {
				sb.append(content.get(names[index]));
			} catch (JSONException e) {
				// no op
			}
			if (index + 1 < names.length) {
				sb.append("&");
			}
		}
		return sb.toString().getBytes(Charset.forName("UTF-8"));
	}
}
