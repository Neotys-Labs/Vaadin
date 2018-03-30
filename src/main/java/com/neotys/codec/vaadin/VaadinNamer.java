package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.Namer;

public class VaadinNamer implements Namer {

	@Override
	public String apply(final Object input) {
		final VaadinWsRequest request = (VaadinWsRequest) input;
		String jsonAsString = request.getContent().toString();
		if (jsonAsString.contains("setText")) {
			return "setText";
		} else if (jsonAsString.contains("requestRows")) {
			return "requestRows";
		} else if (jsonAsString.contains("select")) {
			return "select";
		} else if (jsonAsString.contains("click")) {
			return "click";
		}
		return "defaultName";
	}

}
