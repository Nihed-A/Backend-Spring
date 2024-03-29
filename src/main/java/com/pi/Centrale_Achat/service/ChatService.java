package com.pi.Centrale_Achat.service;



import com.pi.Centrale_Achat.entities.Chat;
import com.pi.Centrale_Achat.entities.Message;
import com.pi.Centrale_Achat.exceptions.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.List;

public interface ChatService {

    public Chat addChat(UserDetails userDetails, Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(int id)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatBySecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameOrSecondUserName(String username)  throws ChatNotFoundException;

    HashSet<Chat> getChatByFirstUserNameAndSecondUserName(String firstUserName, String secondUserName)  throws ChatNotFoundException;

    Chat addMessage(Message add, int chatId)  throws ChatNotFoundException;

    Message addMessage2(Message message, int chatId);

    List<Message> getAllMessagesInChat(int chatId) throws NoChatExistsInTheRepository;
}
