package hw03.karpenko;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker extends Employee{

    private Worker(String surName, String name, String organization, double salary){
        super(surName, name, salary);
        this.organization = organization;
    }

    public static Employee getInstance(){
        return new Worker(
                surNames[random.nextInt(surNames.length)],
                names[random.nextInt(surNames.length)],
                organizations[random.nextInt(organizations.length)],
                random.nextInt(250000-30000)+30000);
    }

    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий в %s; Среднемесячная заработная плата (фиксированная месячная оплата): %.2f (руб.)",
                surName, name, organization, calculateSalary());
    }
    /**
     * Организация в которой работает рабочий;
     */
    protected String organization;


    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    protected static String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман" };
    protected static String[] surNames = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов" };
    protected static String[] organizations = new String[] { "Google", "Yandex", "Mail", "Сбер", "GeekBrains" };
    protected static Random random = new Random();

    

}
