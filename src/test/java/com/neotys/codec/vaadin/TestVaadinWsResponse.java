package com.neotys.codec.vaadin;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestVaadinWsResponse {
	@Test
	public void testResponse() throws IOException, URISyntaxException {
		final String testContent = new String(Files.readAllBytes(Paths.get(TestVaadinWsResponse.class.getResource("vaadinContent.txt").toURI())));
		final VaadinWsResponse resp = new VaadinWsResponse(testContent.getBytes());
		assertEquals("51", resp.getMapping().get("menu-item-users"));
		assertEquals("29", resp.getMapping().get("home"));
	}
}
