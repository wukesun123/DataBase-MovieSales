package com.example.springboot.catogery;

public class Client {
    public static void main(String[] args) {
        double originalPrice = 100; // 原始票价

        // 创建不同的售票策略
        TicketStrategy studentStrategy = new StudentTicketStrategy();
        TicketStrategy vipStrategy = new VIPTicketStrategy();
        TicketStrategy regularStrategy = new RegularTicketStrategy();

        // 使用策略
        TicketContext context = new TicketContext(studentStrategy);
        double studentPrice = context.getTicketPrice(originalPrice);

        context.setStrategy(vipStrategy);
        double vipPrice = context.getTicketPrice(originalPrice);

        context.setStrategy(regularStrategy);
        double regularPrice = context.getTicketPrice(originalPrice);

        System.out.println("学生票价：" + studentPrice + " 元");
        System.out.println("VIP票价：" + vipPrice + " 元");
        System.out.println("普通票价：" + regularPrice + " 元");
    }
}
