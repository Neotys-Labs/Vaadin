package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.contextual.AbstractDecoder;
import com.neotys.extensions.codec.functions.contextual.Context;

public class VaadinHttpRequestDecoder extends AbstractDecoder {
	public VaadinHttpRequestDecoder(final Context context) {
		super(context);
	}

	public Object apply(final byte[] input) {
		return new VaadinHttpRequest(input);
	}
}
