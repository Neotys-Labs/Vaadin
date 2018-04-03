package com.neotys.codec.vaadin;

import java.nio.charset.Charset;

class VaadinConstants {
	static final String SYNC_ID = "syncId";
	static final String UIDL = "uidl";
	static final String TO_REMOVE = "for(;;);";
	static final String REGEX_TO_REMOVE = "for\\(;;\\);";
	static final Charset VAADIN_CHARSET = Charset.forName("UTF-8");
}
