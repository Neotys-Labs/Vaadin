package com.neotys.codec.vaadin;

import com.neotys.extensions.codec.AbstractBinder;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.instanceOf;
import static com.neotys.extensions.codec.predicates.MorePredicates.*;

public class VaadinBinder extends AbstractBinder {
	private static final String CONTENT_TYPE_RESPONSE = "application/json;charset=UTF-8";
	private static final String CONTENT_TYPE_REQUEST = "text/plain;charset=utf-8";
	private static final String CONTENT_TYPE_REQUEST2 = "application/json; charset=UTF-8";
	private static final String CONTENT_TYPE_REQUEST3 = "application/x-www-form-urlencoded";

	@Override
	protected void configure() {
		whenEntity(and(isWebSocketEntity(), isResponseEntity())).decodeWith(VaadinWsResponseDecoder.class);
		whenEntity(and(isWebSocketEntity(), isRequestEntity())).decodeWith(VaadinWsRequestDecoder.class);
		whenObject(instanceOf(VaadinWsRequest.class)).encodeWith(VaadinWsEncoder.class).nameWith(VaadinNamer.class);

		//		whenEntity(and(and(hasHTTPContentType(CONTENT_TYPE_REQUEST3),isRequestEntity()),and(hasHTTPContentType(CONTENT_TYPE_RESPONSE),isResponseEntity()))).decodeWith(VaadinHttpRequestDecoder.class);
		whenEntity(and(hasHTTPContentType(CONTENT_TYPE_RESPONSE), isResponseEntity())).decodeWith(VaadinHttpResponseDecoder.class);
		whenEntity(and(hasHTTPContentType(CONTENT_TYPE_REQUEST), isRequestEntity())).decodeWith(VaadinHttpRequestDecoder.class);
		whenEntity(and(hasHTTPContentType(CONTENT_TYPE_REQUEST2), isRequestEntity())).decodeWith(VaadinHttpRequestDecoder.class);
		whenEntity(and(hasHTTPContentType(CONTENT_TYPE_REQUEST3), isRequestEntity())).decodeWith(VaadinUrlEncodedRequestDecoder.class);

		whenObject(instanceOf(VaadinHttpRequest.class)).encodeWith(VaadinHttpEncoder.class);
		whenObject(instanceOf(VaadinUrlEncodedRequest.class)).encodeWith(VaadinUrlEncoder.class);
	}
}
