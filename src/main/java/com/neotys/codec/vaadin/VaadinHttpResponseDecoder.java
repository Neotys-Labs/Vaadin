package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.contextual.AbstractDecoder;
import com.neotys.extensions.codec.functions.contextual.Context;

import java.util.concurrent.atomic.AtomicInteger;

import static com.neotys.codec.vaadin.VaadinConstants.SYNC_ID;

public class VaadinHttpResponseDecoder extends AbstractDecoder {
	public VaadinHttpResponseDecoder(Context context) {
		super(context);
	}

	private AtomicInteger getSyncId() {
		return (AtomicInteger) getContext().getMap().computeIfAbsent(SYNC_ID, t -> new AtomicInteger());
	}

	public Object apply(final byte[] input) {
		// syncId is automatically incremented when receiving a response
		getSyncId().incrementAndGet();
		return new VaadinHttpResponse(input);
	}
}
