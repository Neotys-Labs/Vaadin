package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.Encoder;

public class VaadinUrlEncoder implements Encoder {
	public byte[] apply(final Object input) {
		final VaadinUrlEncodedRequest request = (VaadinUrlEncodedRequest) input;
		return request.format();
	}
}
