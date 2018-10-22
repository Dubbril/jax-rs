package org.koushik.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget baseTarget = client.target("http://localhost:8080/messenger/webapi/");
		WebTarget messageTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messageTarget.path("{messageId}");

		Message message1 = singleMessageTarget.resolveTemplate("messageId", "1").request(MediaType.APPLICATION_JSON)
				.get(Message.class);
		Message message2 = singleMessageTarget.resolveTemplate("messageId", "2").request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		Message newMessage = new Message(4, "My new message from JAX-RS client", "koushik");
		Response postRequest = messageTarget.request().post(Entity.json(newMessage));
		if(postRequest.getStatus() != 201) {
			System.out.println("Error");
		}
		Message createdMessage = postRequest.readEntity(Message.class);
		System.out.println(createdMessage.getMessage());
	}

}
