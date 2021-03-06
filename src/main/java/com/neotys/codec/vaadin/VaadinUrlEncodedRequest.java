package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import static com.neotys.codec.vaadin.VaadinConstants.VAADIN_CHARSET;

public class VaadinUrlEncodedRequest {
	private final JSONObject content;

	public VaadinUrlEncodedRequest(final byte[] input) {
		this.content = new JSONObject();
		String toParse = new String(input, VAADIN_CHARSET);
		final String[] strPipes = toParse.split("\\&");
		for (String strPipe : strPipes) {
			final String[] params = strPipe.split("\\=");
			try {
				if (params.length > 1) {
					this.content.put(params[0], params[1]);
				} else {
					this.content.put(params[0], "");
				}
			} catch (JSONException e) {
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
		return sb.toString().getBytes(VAADIN_CHARSET);
	}
}
