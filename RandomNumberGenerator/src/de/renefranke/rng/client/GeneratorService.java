package de.renefranke.rng.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("generate")
public interface GeneratorService extends RemoteService {
	int getRandomNumber();
	int getUseCount();
}
