package de.renefranke.rng.server;

import de.renefranke.rng.client.GeneratorService;
import de.renefranke.rng.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GeneratorServiceImpl extends RemoteServiceServlet implements
		GeneratorService {

	private int UseCount = 0;
	
	public int getRandomNumber() {
		UseCount++;
		return 42;
	}

	public int getUseCount()
	{
		return UseCount;
	}
	
	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
