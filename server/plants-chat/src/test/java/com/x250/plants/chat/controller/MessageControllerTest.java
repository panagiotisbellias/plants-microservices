package com.x250.plants.chat.controller;

import com.x250.plants.chat.service.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

    @InjectMocks
    MessageController messageController;

    @Mock
    MessageService messageService;

    @Test
    void testConstructor() {
        MessageController messageController = new MessageController(messageService);
        Assertions.assertInstanceOf(MessageController.class, messageController);
    }

    @Test
    void testGetMessages() {
        Assertions.assertTrue(Objects.requireNonNull(messageController.getMessages("room").getBody()).isEmpty());
    }

}
