package com.example.springboot.catogery;

public class RegularTicketStrategy implements TicketStrategy{
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice; // 不打折
    }
}
