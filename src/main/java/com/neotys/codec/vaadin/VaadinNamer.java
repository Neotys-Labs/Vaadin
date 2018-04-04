package com.neotys.codec.vaadin;

import com.google.common.collect.ImmutableList;
import com.neotys.extensions.codec.functions.Namer;

import java.util.List;

public class VaadinNamer implements Namer {

	private static final List<String> NAMES = ImmutableList.of("setText", "requestRows",
			"select", "click", "actiontarget", "popstate", "requestChildTree", "call");

	@Override
	public String apply(final Object input) {
		final VaadinWsRequest request = (VaadinWsRequest) input;
		String jsonAsString = request.getContent().toString();
		return NAMES.stream()
				.filter(jsonAsString::contains)
				.findFirst()
				.orElse("defaultName");
	}

}
