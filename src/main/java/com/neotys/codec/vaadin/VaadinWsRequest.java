package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;


public final class VaadinWsRequest {
	/**
	 * WS Vaadim WS Request based on Atmosphere.
	 *
	 * @author bduval
	 */
	//private int size;
	private String size = "Size will be automatically computed by Neoload before sending the request.";
	private JSONObject content;


	public VaadinWsRequest(final byte[] input) {
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
		int index = toParse.indexOf("|");
		settContent(toParse.substring(index + 1));


	}

	public JSONObject getContent() {
		return content;
	}

	public void settContent(String cont) {
		try {
			JSONObject conte = new JSONObject(cont);
			this.content = conte;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public byte[] format(int syncId) {
		if (this.content == null) {
			return " ".getBytes();
		}
		content.put("syncId", syncId);
		String contentAsString = this.content.toString();
		String output = contentAsString.length() + "|" + contentAsString;
		return output.getBytes(Charset.forName("UTF-8"));
	}


}
