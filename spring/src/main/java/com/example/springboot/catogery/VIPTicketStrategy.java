package com.example.springboot.catogery;

public class VIPTicketStrategy implements TicketStrategy{
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.5; // VIP票半价
    }
}
