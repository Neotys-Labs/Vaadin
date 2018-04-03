package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import static com.neotys.codec.vaadin.VaadinConstants.VAADIN_CHARSET;

public class VaadinHttpRequest {
	private String VaadingSecurityToken;
	private JSONObject content;


	public VaadinHttpRequest(final byte[] input) {
		super();
		final String toParse = new String(input, VAADIN_CHARSET);
		if (toParse.contains("\"rpc\":[[")) {
			setContent(toParse);
		} else {
			if (toParse.contains("&")) {
				this.content = new JSONObject();
				String[] strPipes = toParse.split("\\&");
				for (String strPipe : strPipes) {
					final String[] params = strPipe.split("\\=");
					putToJsonObject(params[0], params[1]);
				}

			} else {
				if (toParse.contains("undefined")) {
					this.content = new JSONObject();
					putToJsonObject("HeartBeat", toParse);
				}
			}
		}


	}

	private void putToJsonObject(final String key, final String value) {
		try {
			this.content.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public JSONObject getContent() {
		return content;
	}

	public void setContent(final String cont) {
		try {
			this.content = new JSONObject(cont);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public byte[] format() {
		if (this.content == null) {
			return " ".getBytes();
		}

		String jsonPrettyPrintString = "";
		if (content.has("HeartBeat")) {
			jsonPrettyPrintString = "undefined";
		} else {
			try {
				jsonPrettyPrintString = content.toString(4);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonPrettyPrintString.getBytes(VAADIN_CHARSET);
	}
}
