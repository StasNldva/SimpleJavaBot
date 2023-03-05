package com.example.javabot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "recipe_by_neledva_bot";
    }

    @Override
    public String getBotToken() {
        return "Not for public";
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.println("Message received -> " + message.getText());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Hello user! I received your message -> " + message.getText());
        sendMessage.setChatId(String.valueOf(message.getChatId()));

        if (message.getText().equals("/start")) {
            String text = "Welcome to Recipe bot! Please pass the meal of the day!\n";
            sendMessage.enableMarkdown(true);
            ReplyKeyboardMarkup replyKeyboardMarkup = getMenuKeyboard();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            sendMessage.setText(text);
        }

        if (message.getText().equalsIgnoreCase("breakfast")) {
            String menu = "Breakfast Menu:\n";
            menu += "1. BlueBerry-Banana-Nut Smoothie\n";
            menu += "2. Classic Omelet and Greens\n";
            menu += "3. Curry-Avacado Crispy Egg Toast";
            sendMessage.setText(menu);
        }
        if (message.getText().equalsIgnoreCase("lunch")) {
            String menu = "Lunch Menu:\n";
            menu += "1. Ukrainian Borsh\n";
            menu += "2. Wellington Beef\n";
            menu += "3. French Onion Soup";
            sendMessage.setText(menu);
        }
        if (message.getText().equalsIgnoreCase("dinner")) {
            String menu = "Lunch Menu:\n";
            menu += "1. Creamy-Lemom-Chicken Pasta\n";
            menu += "2. Turkey Takos\n";
            menu += "3. Vegeterian Lasagna";
            sendMessage.setText(menu);
        }

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private ReplyKeyboardMarkup getMenuKeyboard(){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("breakfast");
        keyboardRow.add("lunch");
        keyboardRow.add("dinner");
        keyboardRows.add(keyboardRow);
        //KeyboardRow keyboardSecondRow = new KeyboardRow();

        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }
}
