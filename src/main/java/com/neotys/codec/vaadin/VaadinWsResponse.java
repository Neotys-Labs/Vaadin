package com.neotys.codec.vaadin;


import com.google.common.annotations.VisibleForTesting;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.neotys.codec.vaadin.VaadinConstants.SYNC_ID;


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
		String strToremove = "for(;;);";

		try {
			toParse = new String(input, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// no op
		}
		String[] strPipes = toParse.split("\\|");
		String strResult;
		int index_remove;

		//---debug to see why we don't get the size of the response in the replay
		size = Integer.valueOf(strPipes[0]);

		if (strPipes.length < 3) {
			int jsonstring = strPipes[1].indexOf(strToremove);
			if (jsonstring == -1) {
				settContent(strPipes[1]);
			} else {

				index_remove = strPipes[1].indexOf(strToremove);
				strResult = strPipes[1].substring(index_remove + strToremove.length());
				settContent(strResult);
				//settContent(strPipes[1].substring(jsonstring+strToremove.length()));
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					this.content.put("TimeStamp", strPipes[2]);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				index_remove = strPipes[1].indexOf(strToremove);

				strResult = strPipes[1].substring(index_remove + strToremove.length());
				for (int i = 2; i < strPipes.length; i++) {
					strResult = strResult + strPipes[i];
				}

				settContent(strResult);


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

	@VisibleForTesting
	Map<String, String> getMapping() {
		return mapping;
	}
}
