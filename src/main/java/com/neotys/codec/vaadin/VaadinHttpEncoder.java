package com.neotys.codec.vaadin;


import com.neotys.extensions.codec.functions.contextual.AbstractEncoder;
import com.neotys.extensions.codec.functions.contextual.Context;

public class VaadinHttpEncoder extends AbstractEncoder {
	public VaadinHttpEncoder(Context context) {
		super(context);
	}

	public byte[] apply(Object input) {
		final VaadinHttpRequest request = (VaadinHttpRequest) input;
		return request.format();

	}
}
