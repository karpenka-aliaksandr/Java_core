package hw03_classes.src.main.java.hw03.karpenko;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Freelancer extends Employee{

    private Freelancer(String surName, String name, String freelanceExchange, double salary){
        super(surName, name, salary);
        this.freelanceExchange = freelanceExchange;
    }

    public static Employee getInstance(){
        return new Freelancer(
                surNames[random.nextInt(surNames.length)],
                names[random.nextInt(surNames.length)],
                freelanceExchanges[random.nextInt(freelanceExchanges.length)],
                random.nextInt(1000-100)+100);
    }

    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    @Override
    public double calculateSalary() {
        return 20.8 * 8 * salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер на бирже %s; Среднемесячная заработная плата: %.2f (руб.)",
                surName, name, freelanceExchange, calculateSalary());
    }
    /**
     * Биржа фриланса;
     */
    protected String freelanceExchange;


    public String getFreelanceExchange() {
        return freelanceExchange;
    }

    public void setFreelanceExchange(String freelanceExchange) {
        this.freelanceExchange = freelanceExchange;
    }
    protected static String[] names = new String[] { "Богдан", "Тимофей", "Артём", "Дмитрий", "Арсений", "Кирилл", "Даниил", "Алексей", "Максим", "Елисей" };
    protected static String[] surNames = new String[] { "Устинов", "Кузнецов", "Сидоров", "Львов", "Лапшин", "Бирюков", "Копылов", "Федоров", "Лыткин", "Соколов" };
    protected static String[] freelanceExchanges = new String[] { "Fl.ru", "Kwork", "MailWeblancer.net", "UpWork", "Fiverr", "Weblancer"};
    protected static Random random = new Random();

    

}
