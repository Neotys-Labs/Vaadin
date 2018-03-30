package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.contextual.AbstractEncoder;
import com.neotys.extensions.codec.functions.contextual.Context;

import java.util.concurrent.atomic.AtomicInteger;

public class VaadinWsEncoder extends AbstractEncoder {

	public VaadinWsEncoder(Context context) {
		super(context);
	}

	public byte[] apply(Object input) {
		final VaadinWsRequest request = (VaadinWsRequest) input;

		final AtomicInteger syncId = getSyncId();
		return request.format(syncId.get());
	}

	private AtomicInteger getSyncId() {
		return (AtomicInteger) getContext().getMap().computeIfAbsent("syncId", t -> new AtomicInteger());
	}
}
