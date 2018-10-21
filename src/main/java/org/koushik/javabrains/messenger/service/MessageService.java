package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.exception.DataNotFoundException;
import org.koushik.javabrains.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Hello World", "koushik"));
		messages.put(2L, new Message(2, "Hello Jersey", "koushik"));
	};

	public List<Message> getAllMessages() {
		return new ArrayList<>(messages.values());
	}

	public List<Message> getAllMessageForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		for (Message message : messages.values()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagePaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<>(messages.values());
		if (start + size > list.size())
			return new ArrayList<>();
		return list.subList(start, start + size);
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with id "+id+" not found");
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1L);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}
}
