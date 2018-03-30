package com.neotys.codec.vaadin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class VaadinHttpResponse {

	private static String testContent = "{\"v-uiId\":0,\"uidl\":\"{\\\"Vaadin-Security-Key\\\":\\\"d97edc65-dfbb-402f-bffc-d6e64d8d6479\\\",\\\"Vaadin-Push-ID\\\":\\\"0a5a1b9d-6511-4cca-abc2-55ab48bb7da5\\\",\\\"syncId\\\": 0, \\\"resynchronize\\\": true, \\\"clientId\\\": 0, \\\"changes\\\" : [[\\\"change\\\",{\\\"pid\\\":\\\"0\\\"},[\\\"0\\\",{\\\"id\\\":\\\"0\\\",\\\"focused\\\":\\\"8\\\",\\\"v\\\":{\\\"action\\\":\\\"\\\"}},[\\\"actions\\\",{},[\\\"action\\\",{\\\"key\\\":\\\"2349\\\",\\\"kc\\\":13,\\\"mk\\\":[]}]]]],[\\\"change\\\",{\\\"pid\\\":\\\"2\\\"},[\\\"1\\\",{\\\"id\\\":\\\"2\\\"}]]], \\\"state\\\":{\\\"0\\\":{\\\"localeServiceState\\\":{\\\"localeData\\\":[{\\\"name\\\":\\\"fr\\\",\\\"monthNames\\\":[\\\"janvier\\\",\\\"f�vrier\\\",\\\"mars\\\",\\\"avril\\\",\\\"mai\\\",\\\"juin\\\",\\\"juillet\\\",\\\"ao�t\\\",\\\"septembre\\\",\\\"octobre\\\",\\\"novembre\\\",\\\"d�cembre\\\"],\\\"shortMonthNames\\\":[\\\"janv.\\\",\\\"f�vr.\\\",\\\"mars\\\",\\\"avr.\\\",\\\"mai\\\",\\\"juin\\\",\\\"juil.\\\",\\\"ao�t\\\",\\\"sept.\\\",\\\"oct.\\\",\\\"nov.\\\",\\\"d�c.\\\"],\\\"shortDayNames\\\":[\\\"dim.\\\",\\\"lun.\\\",\\\"mar.\\\",\\\"mer.\\\",\\\"jeu.\\\",\\\"ven.\\\",\\\"sam.\\\"],\\\"dayNames\\\":[\\\"dimanche\\\",\\\"lundi\\\",\\\"mardi\\\",\\\"mercredi\\\",\\\"jeudi\\\",\\\"vendredi\\\",\\\"samedi\\\"],\\\"firstDayOfWeek\\\":1,\\\"dateFormat\\\":\\\"dd/MM/yy\\\",\\\"twelveHourClock\\\":false,\\\"hourMinuteDelimiter\\\":\\\":\\\",\\\"am\\\":null,\\\"pm\\\":null}]},\\\"pushConfiguration\\\":{\\\"mode\\\":\\\"AUTOMATIC\\\"},\\\"theme\\\":\\\"neoload\\\",\\\"height\\\":\\\"100.0%\\\",\\\"width\\\":\\\"100.0%\\\",\\\"registeredEventListeners\\\":[\\\"clientConnectorDetach\\\"]},\\\"10\\\":{\\\"target\\\":\\\"_blank\\\",\\\"caption\\\":\\\"R�initialiser le mot de passe\\\",\\\"id\\\":\\\"login-reset-password\\\",\\\"resources\\\":{\\\"href\\\":{\\\"uRL\\\":\\\"https://preprod-auth.saas.neotys.com:443/lostPassword/?lang=fr\\\"}}},\\\"11\\\":{\\\"clickShortcutKeyCode\\\":13,\\\"width\\\":\\\"100.0%\\\",\\\"caption\\\":\\\"Se connecter\\\",\\\"styles\\\":[\\\"classic-button\\\"],\\\"id\\\":\\\"login-login-button\\\"},\\\"12\\\":{\\\"spacing\\\":true,\\\"childData\\\":{},\\\"height\\\":\\\"30.0px\\\",\\\"id\\\":\\\"progress-layout-id\\\"},\\\"14\\\":{\\\"contentMode\\\":\\\"HTML\\\",\\\"text\\\":\\\"� 2017 <a href=\\\\\\\"http://www.neotys.fr\\\\\\\" target=\\\\\\\"_blank\\\\\\\" id=\\\\\\\"neotys-link-id\\\\\\\">Neotys</a>\\\"},\\\"2\\\":{\\\"tabIndex\\\":-1,\\\"height\\\":\\\"100.0%\\\",\\\"width\\\":\\\"100.0%\\\"},\\\"3\\\":{\\\"childData\\\":{\\\"4\\\":{\\\"alignmentBitmask\\\":48,\\\"expandRatio\\\":0}},\\\"height\\\":\\\"100.0%\\\",\\\"width\\\":\\\"100.0%\\\"},\\\"5\\\":{\\\"spacing\\\":true,\\\"childData\\\":{\\\"6\\\":{\\\"alignmentBitmask\\\":5,\\\"expandRatio\\\":0}},\\\"id\\\":\\\"login-view\\\"},\\\"6\\\":{\\\"childData\\\":{\\\"10\\\":{\\\"alignmentBitmask\\\":34,\\\"expandRatio\\\":0},\\\"11\\\":{\\\"alignmentBitmask\\\":48,\\\"expandRatio\\\":0},\\\"12\\\":{\\\"alignmentBitmask\\\":48,\\\"expandRatio\\\":0},\\\"14\\\":{\\\"alignmentBitmask\\\":9,\\\"expandRatio\\\":0},\\\"7\\\":{\\\"alignmentBitmask\\\":20,\\\"expandRatio\\\":0},\\\"8\\\":{\\\"alignmentBitmask\\\":48,\\\"expandRatio\\\":0},\\\"9\\\":{\\\"alignmentBitmask\\\":48,\\\"expandRatio\\\":0}},\\\"width\\\":\\\"300.0px\\\"},\\\"7\\\":{\\\"width\\\":\\\"100.0%\\\",\\\"id\\\":\\\"login-logo\\\",\\\"resources\\\":{\\\"source\\\":{\\\"uRL\\\":\\\"theme://../neoload/images/neoload-logo-with-text-color.png\\\"}}},\\\"8\\\":{\\\"placeholder\\\":\\\"E-mail\\\",\\\"width\\\":\\\"100.0%\\\",\\\"id\\\":\\\"login-username\\\"},\\\"9\\\":{\\\"width\\\":\\\"100.0%\\\",\\\"id\\\":\\\"login-password\\\"}}, \\\"types\\\":{\\\"0\\\":\\\"0\\\",\\\"1\\\":\\\"2\\\",\\\"10\\\":\\\"5\\\",\\\"11\\\":\\\"4\\\",\\\"12\\\":\\\"7\\\",\\\"14\\\":\\\"10\\\",\\\"15\\\":\\\"2\\\",\\\"2\\\":\\\"1\\\",\\\"3\\\":\\\"8\\\",\\\"4\\\":\\\"9\\\",\\\"5\\\":\\\"6\\\",\\\"6\\\":\\\"7\\\",\\\"7\\\":\\\"11\\\",\\\"8\\\":\\\"12\\\",\\\"9\\\":\\\"3\\\"}, \\\"hierarchy\\\":{\\\"0\\\":[\\\"2\\\",\\\"1\\\"],\\\"1\\\":[],\\\"15\\\":[],\\\"2\\\":[\\\"3\\\"],\\\"3\\\":[\\\"4\\\"],\\\"4\\\":[\\\"5\\\",\\\"15\\\"],\\\"5\\\":[\\\"6\\\"],\\\"6\\\":[\\\"7\\\",\\\"8\\\",\\\"9\\\",\\\"10\\\",\\\"11\\\",\\\"12\\\",\\\"14\\\"]}, \\\"rpc\\\" : [], \\\"meta\\\" : {\\\"repaintAll\\\":true}, \\\"resources\\\" : {}, \\\"typeMappings\\\" : { \\\"com.vaadin.server.Responsive\\\" : 2 , \\\"com.vaadin.ui.AbstractField\\\" : 13 , \\\"com.vaadin.ui.VerticalLayout\\\" : 7 , \\\"com.vaadin.ui.Image\\\" : 11 , \\\"com.vaadin.v7.ui.VerticalLayout\\\" : 8 , \\\"com.vaadin.ui.AbstractComponentContainer\\\" : 14 , \\\"com.vaadin.ui.AbstractLayout\\\" : 15 , \\\"com.vaadin.ui.AbstractOrderedLayout\\\" : 16 , \\\"com.vaadin.ui.AbstractFocusable\\\" : 17 , \\\"com.vaadin.ui.AbstractTextField\\\" : 18 , \\\"com.neotys.nlweb.frontend.saas.login.SAASLoginViewImpl\\\" : 9 , \\\"com.vaadin.ui.AbstractSingleComponentContainer\\\" : 19 , \\\"com.neotys.web.security.platform.ui.vaadin.DefaultViewDisplayPanel\\\" : 20 , \\\"com.vaadin.server.AbstractClientConnector\\\" : 21 , \\\"com.vaadin.ui.Button\\\" : 4 , \\\"com.vaadin.ui.Link\\\" : 5 , \\\"com.neotys.web.security.platform.ui.vaadin.component.login.AbstractSAASLoginViewImpl\\\" : 22 , \\\"com.vaadin.ui.AbstractComponent\\\" : 23 , \\\"com.vaadin.server.AbstractExtension\\\" : 24 , \\\"com.vaadin.ui.UI\\\" : 25 , \\\"com.vaadin.ui.CustomComponent\\\" : 26 , \\\"com.vaadin.ui.Label\\\" : 10 , \\\"com.neotys.nlweb.frontend.navigation.NeoLoadViewDisplayPanel\\\" : 1 , \\\"com.vaadin.ui.HorizontalLayout\\\" : 6 , \\\"com.neotys.web.security.platform.ui.vaadin.DefaultUI\\\" : 0 , \\\"com.neotys.web.security.platform.ui.vaadin.component.login.LoginViewImpl\\\" : 27 , \\\"com.vaadin.ui.PasswordField\\\" : 3 , \\\"com.vaadin.ui.TextField\\\" : 12 , \\\"com.vaadin.ui.AbstractEmbedded\\\" : 28 , \\\"com.vaadin.ui.Panel\\\" : 29 }, \\\"typeInheritanceMap\\\" : { \\\"2\\\" : 24 , \\\"13\\\" : 23 , \\\"7\\\" : 16 , \\\"11\\\" : 28 , \\\"8\\\" : 7 , \\\"14\\\" : 23 , \\\"15\\\" : 14 , \\\"16\\\" : 15 , \\\"17\\\" : 23 , \\\"18\\\" : 13 , \\\"9\\\" : 22 , \\\"19\\\" : 23 , \\\"20\\\" : 29 , \\\"4\\\" : 17 , \\\"5\\\" : 23 , \\\"22\\\" : 27 , \\\"23\\\" : 21 , \\\"24\\\" : 21 , \\\"25\\\" : 19 , \\\"26\\\" : 23 , \\\"10\\\" : 23 , \\\"1\\\" : 20 , \\\"6\\\" : 16 , \\\"0\\\" : 25 , \\\"27\\\" : 26 , \\\"3\\\" : 12 , \\\"12\\\" : 18 , \\\"28\\\" : 23 , \\\"29\\\" : 19 }, \\\"dependencies\\\": [{\\\"type\\\":\\\"JAVASCRIPT\\\",\\\"url\\\":\\\"published:///jquery-3.1.1.min.js\\\"}]}\"}";
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

	public static void main(String[] args) {
		final VaadinHttpResponse resp = new VaadinHttpResponse(testContent.getBytes());
		System.out.println(resp.mapping);
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
}
