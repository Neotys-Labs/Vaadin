package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import static com.neotys.codec.vaadin.VaadinConstants.SYNC_ID;
import static com.neotys.codec.vaadin.VaadinConstants.VAADIN_CHARSET;


public final class VaadinWsRequest {
	// Do not remove, inform users that size will be automatically computed by Neoload before sending the request
	private String size = "Size will be automatically computed by Neoload before sending the request.";
	private JSONObject content;


	public VaadinWsRequest(final byte[] input) {
		super();
		final String toParse = new String(input, VAADIN_CHARSET);
		int index = toParse.indexOf("|");
		setContent(toParse.substring(index + 1));
	}

	public JSONObject getContent() {
		return content;
	}

	private void setContent(final String cont) {
		try {
			this.content = new JSONObject(cont);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public byte[] format(final int syncId) {
		if (this.content == null) {
			return " ".getBytes();
		}
		content.put(SYNC_ID, syncId);
		final String contentAsString = this.content.toString();
		final String output = contentAsString.length() + "|" + contentAsString;
		return output.getBytes(VAADIN_CHARSET);
	}


}
