package hw03_classes.src.main.java.hw03.karpenko;

// import java.util.Random;

public abstract class Employee implements Comparable<Employee> {

    //region Public Methods

    /**
     * Расчет среднемесячной заработной платы
     * @return
     */
    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        int res = this.getSurName().compareTo(o.getSurName());
        if (res == 0){
            return this.getName().compareTo(o.getName());
        }
        return res;
    }


    //endregion

    //region Constructors And Initializers

    {
        //System.out.println("Initialize - Employee");
        id = ++counter;
    }

    // private Employee(){
    //     this("#Surnane#", "#Name#");
    // }

    // private Employee(String surName, String name){
    //     this(surName, name, 500);
    // }

    protected Employee(String surName, String name, double salary){
        //System.out.println("Constructor - Employee");
        if (salary < 0){
            throw new RuntimeException("Ставка заработной платы должна быть положительной");
        }
        this.surName = surName;
        this.name = name;
        this.salary = salary;
    }

    //endregion

    //region Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public double getSalary() {
        return salary;
    }

    // public void setSalary(double salary) {
    //     if (salary < 30000){
    //         throw new RuntimeException("Уровень заработной платы должен быть не менее 30000");
    //     }
    //     this.salary = salary;
    // }

    //endregion

    //region Fields

    private int id;

    /**
     * Имя
     */
    protected String name;

    /**
     * Фамилия
     */
    protected String surName;

    /**
     * Ставка заработной платы
     */
    protected double salary;

    //endregion

    //region Static Fields

    // protected static String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман" };
    // protected static String[] surNames = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов" };
    // protected static Random random = new Random();
    private static int counter = 1;

    //endregion

}
