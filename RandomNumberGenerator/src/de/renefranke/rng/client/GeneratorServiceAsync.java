package de.renefranke.rng.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GeneratorService</code>.
 */
public interface GeneratorServiceAsync {
	void getRandomNumber(AsyncCallback<Integer> callback);
	void getUseCount(AsyncCallback<Integer> callback);
}
