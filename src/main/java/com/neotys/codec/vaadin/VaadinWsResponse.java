package com.neotys.codec.vaadin;


import com.google.common.annotations.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.neotys.codec.vaadin.VaadinConstants.*;


public final class VaadinWsResponse {
	private int size;
	private int NLSyncId;
	private JSONObject content;
	private Map<String, String> mapping = new HashMap<>();

	/**
	 * WS Vaadim WS Request based on Atmosphere.
	 *
	 * @author bduval
	 */
	public VaadinWsResponse(final byte[] input) {
		super();
		final String toParse = new String(input, VAADIN_CHARSET);
		final String[] strPipes = toParse.split("\\|");

		int index_remove;

		//---debug to see why we don't get the size of the response in the replay
		size = Integer.valueOf(strPipes[0]);

		if (strPipes.length < 3) {
			int jsonstring = strPipes[1].indexOf(TO_REMOVE);
			if (jsonstring == -1) {
				setContent(strPipes[1]);
			} else {

				index_remove = strPipes[1].indexOf(TO_REMOVE);
				setContent(strPipes[1].substring(index_remove + TO_REMOVE.length()));
				//setContent(strPipes[1].substring(jsonstring+strToremove.length()));
			}
		} else {
			this.content = new JSONObject();

			String pattern = "(\\d+)";
			Pattern reg = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher testreg = reg.matcher(strPipes[2]);
			if (testreg.find()) {
				try {
					this.content.put("X-Atmosphere-tracking-id", strPipes[1]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				try {
					this.content.put("TimeStamp", strPipes[2]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {

				index_remove = strPipes[1].indexOf(TO_REMOVE);

				final StringBuilder strResult = new StringBuilder(strPipes[1].substring(index_remove + TO_REMOVE.length()));
				for (int i = 2; i < strPipes.length; i++) {
					strResult.append(strPipes[i]);
				}

				setContent(strResult.toString());

			}
		}


	}

	private static String removeBraces(final String jsonContentAsString) {
		return jsonContentAsString.substring(1, jsonContentAsString.length() - 1);
	}

	// jsonContentAsString = [{...}]
	public void setContent(String jsonContentAsString) {

		// get sync id from cont
		final String unbracedJsonAstring = removeBraces(jsonContentAsString);


		try {
			String strprefix = "{";
			int inIndex = jsonContentAsString.indexOf(strprefix);

			JSONObject tempobj = new JSONObject(unbracedJsonAstring);
			if (inIndex > 0) {
				jsonContentAsString = "{ \"VaadinMessage\" :" + jsonContentAsString;
				jsonContentAsString = jsonContentAsString + "}";
			}

			this.content = new JSONObject(jsonContentAsString);
			MappingUtils.findIds(mapping, content);


			if (tempobj.has(SYNC_ID)) {
				NLSyncId = (Integer) tempobj.get(SYNC_ID);
				if (NLSyncId > 0) {
					NLSyncId--;
				}
			} else
				NLSyncId = -1;


		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public int getSize() {
		return size;
	}

	public JSONObject getContent() {
		return content;
	}

	@VisibleForTesting
	Map<String, String> getMapping() {
		return mapping;
	}
}
