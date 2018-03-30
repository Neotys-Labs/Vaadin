package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.Encoder;

public class VaadinUrlEncoder implements Encoder {
	public byte[] apply(Object input) {
		final VaadinUrlEncodedRequest request = (VaadinUrlEncodedRequest) input;
		return request.format();
	}
}
