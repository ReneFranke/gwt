package de.renefranke.rng.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RandomNumberGenerator implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side generator service.
	 */
	private final GeneratorServiceAsync generatorService = GWT
			.create(GeneratorService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final Button useButton = new Button("Usage Count");
		final TextBox nameField = new TextBox();
		nameField.setText("Hit Send for a new number");

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		useButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("useButtonContainer").add(useButton);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create a handler for the sendButton and nameField
		class NumberHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				requestNewNumber();
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void requestNewNumber() {
				// First, we validate the input.

				// Then, we send the input to the server.
				sendButton.setEnabled(true);
				useButton.setEnabled(true);
				generatorService.getRandomNumber(
						new AsyncCallback<Integer>() {
							public void onFailure(Throwable caught) {
								
							}

							public void onSuccess(Integer result) {
								nameField.setText(result.toString());
							}
						});
			}
		}
		
		class UsageHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				requestUseCount();
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void requestUseCount() {
				// First, we validate the input.

				// Then, we send the input to the server.
				useButton.setEnabled(false);
				generatorService.getUseCount(
						new AsyncCallback<Integer>() {
							public void onFailure(Throwable caught) {
								
							}

							public void onSuccess(Integer result) {
								nameField.setText(result.toString());
							}
						});
			}
		}

		// Add a handler to send the name to the server
		NumberHandler nHandler = new NumberHandler();
		UsageHandler uHandler = new UsageHandler();
		sendButton.addClickHandler(nHandler);
		useButton.addClickHandler(uHandler);
	}
}
