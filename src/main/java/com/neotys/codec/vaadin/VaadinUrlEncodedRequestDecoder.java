package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.Decoder;

public class VaadinUrlEncodedRequestDecoder implements Decoder {
	public Object apply(final byte[] input) {
		return new VaadinUrlEncodedRequest(input);
	}
}
