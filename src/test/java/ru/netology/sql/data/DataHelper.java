package ru.netology.sql.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static final Faker FAKER = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static CardInfo getApprovedCardInfo() {
        return new CardInfo("4444 4444 4444 4441", "02", "25", "Ivan Ivanov", "123");
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo("4444 4444 4444 4442", "02", "25", "Ivan Ivanov", "123");
    }

    public static CardInfo getInvalidCardInfo() {
        return new CardInfo("4444 4444 4444 4443", "02", "25", "Ivan Ivanov", "123");
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    @Value
    public static class CreditRequest {
        String id;
        String bankId;
        String status;
    }

    @Value
    public static class Order {
        String id;
        String creditId;
        String paymentId;
    }

    @Value
    public static class Payment {
        String id;
        int amount;
        String status;
        String transactionId;
    }
}
