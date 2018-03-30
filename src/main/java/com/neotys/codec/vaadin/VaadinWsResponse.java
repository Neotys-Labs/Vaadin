package com.neotys.codec.vaadin;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class VaadinWsResponse {
	private int size;
	private int NLSyncId;
	private JSONObject content;
	private Map<String, String> mapping = new HashMap<String, String>();

	/**
	 * WS Vaadim WS Request based on Atmosphere.
	 *
	 * @author bduval
	 */
	public VaadinWsResponse(final byte[] input) {
		super();
		String toParse = null;
		String StrToremove = "for(;;);";

		try {
			toParse = new String(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// no op
		}
		String[] StrPipes = toParse.split("\\|");
		String StrResult;
		int index_remove;

		//---debug to see why we don't get the size of the response in the replay
		size = Integer.valueOf(StrPipes[0]);

		if (StrPipes.length < 3) {
			int jsonstring = StrPipes[1].indexOf(StrToremove);
			if (jsonstring == -1) {
				settContent(StrPipes[1]);
			} else {

				index_remove = StrPipes[1].indexOf(StrToremove);
				StrResult = StrPipes[1].substring(index_remove + StrToremove.length());
				settContent(StrResult);
				//settContent(StrPipes[1].substring(jsonstring+StrToremove.length()));
			}
		} else {
			this.content = new JSONObject();

			String pattern = "(\\d+)";
			Pattern reg = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher testreg = reg.matcher(StrPipes[2]);
			if (testreg.find()) {
				try {
					this.content.put("X-Atmosphere-tracking-id", StrPipes[1]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					this.content.put("TimeStamp", StrPipes[2]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				index_remove = StrPipes[1].indexOf(StrToremove);

				StrResult = StrPipes[1].substring(index_remove + StrToremove.length());
				for (int i = 2; i < StrPipes.length; i++) {
					StrResult = StrResult + StrPipes[i];
				}

				settContent(StrResult);


			}
		}


	}

	private static String removeBraces(final String jsonContentAsString) {
		return jsonContentAsString.substring(1, jsonContentAsString.length() - 1);
	}

	// jsonContentAsString = [{...}]
	public void settContent(String jsonContentAsString) {

		// get sync id from cont
		final String unbracedJsonAstring = removeBraces(jsonContentAsString);


		try {
			String StrPrefix = "{";
			int in_index = jsonContentAsString.indexOf(StrPrefix);

			JSONObject tempobj = new JSONObject(unbracedJsonAstring);
			if (in_index > 0) {
				jsonContentAsString = "{ \"VaadinMessage\" :" + jsonContentAsString;
				jsonContentAsString = jsonContentAsString + "}";
			}

			this.content = new JSONObject(jsonContentAsString);
			MappingUtils.findIds(mapping, content);


			if (tempobj.has("syncId")) {
				NLSyncId = (Integer) tempobj.get("syncId");
				if (NLSyncId > 0) {
					NLSyncId--;
				}
			} else
				NLSyncId = -1;


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int getSize() {
		return size;
	}

	public JSONObject getContent() {
		return content;
	}


}
