package com.example.springboot.catogery;

public class TicketContext {
    private TicketStrategy strategy;

    public TicketContext(TicketStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TicketStrategy strategy) {
        this.strategy = strategy;
    }

    public double getTicketPrice(double originalPrice) {
        return strategy.calculatePrice(originalPrice);
    }
}
