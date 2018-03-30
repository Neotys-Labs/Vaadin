package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.functions.contextual.AbstractEncoder;
import com.neotys.extensions.codec.functions.contextual.Context;

import java.util.concurrent.atomic.AtomicInteger;

import static com.neotys.codec.vaadin.VaadinConstants.SYNC_ID;

public class VaadinWsEncoder extends AbstractEncoder {

	public VaadinWsEncoder(final Context context) {
		super(context);
	}

	public byte[] apply(final Object input) {
		final VaadinWsRequest request = (VaadinWsRequest) input;

		final AtomicInteger syncId = getSyncId();
		return request.format(syncId.get());
	}

	private AtomicInteger getSyncId() {
		return (AtomicInteger) getContext().getMap().computeIfAbsent(SYNC_ID, t -> new AtomicInteger());
	}
}
