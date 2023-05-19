package com.example.jspboard2.service;

import org.springframework.mail.*;

public interface MailSender {
    /**
     * Send the given simple mail message.
     * @param simpleMessage the message to send
     * @throws MailParseException in case of failure when parsing the message
     * @throws MailAuthenticationException in case of authentication failure
     * @throws MailSendException in case of failure when sending the message
     */
    void send(SimpleMailMessage simpleMessage) throws MailException;

    /**
     * Send the given array of simple mail messages in batch.
     * @param simpleMessages the messages to send
     * @throws MailParseException in case of failure when parsing a message
     * @throws MailAuthenticationException in case of authentication failure
     * @throws MailSendException in case of failure when sending a message
     */
    void send(SimpleMailMessage... simpleMessages) throws MailException;
}