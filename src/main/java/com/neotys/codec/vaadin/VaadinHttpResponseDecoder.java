package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.contextual.AbstractDecoder;
import com.neotys.extensions.codec.functions.contextual.Context;

import java.util.concurrent.atomic.AtomicInteger;

public class VaadinHttpResponseDecoder extends AbstractDecoder {
	public VaadinHttpResponseDecoder(Context context) {
		super(context);
	}

	private AtomicInteger getSyncId() {
		return (AtomicInteger) getContext().getMap().computeIfAbsent("syncId", t -> new AtomicInteger());
	}

	public Object apply(final byte[] input) {
		getSyncId().incrementAndGet();
		return new VaadinHttpResponse(input);
	}
}
