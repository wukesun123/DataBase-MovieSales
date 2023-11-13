package com.example.springboot.catogery;

public class StudentTicketStrategy implements TicketStrategy{
    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.8; // 学生票打八折
    }
}
