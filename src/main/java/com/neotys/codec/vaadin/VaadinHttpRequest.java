package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class VaadinHttpRequest {
	private String VaadingSecurityToken;
	private JSONObject content;


	public VaadinHttpRequest(final byte[] input) {
		super();
		String toParse = null;
		/* to be fixed
		 * String csrfToken;
		 * String rpc;
		 * String syncId;
		 */
		try {
			toParse = new String(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// no op
		}
		if (toParse.contains("\"rpc\":[[")) {
			setContent(toParse);
		} else {
			if (toParse.contains("&")) {
				this.content = new JSONObject();
				String[] strPipes = toParse.split("\\&");
				for (String strPipe : strPipes) {
					final String[] params = strPipe.split("\\=");
					try {
						this.content.put(params[0], params[1]);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			} else {
				if (toParse.contains("undefined")) {
					this.content = new JSONObject();
					try {
						this.content.put("HeartBeat", toParse);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}


	}

	public JSONObject getContent() {
		return content;
	}

	public void setContent(final String cont) {
		try {
			this.content = new JSONObject(cont);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return jsonPrettyPrintString.getBytes(Charset.forName("UTF-8"));
	}
}
