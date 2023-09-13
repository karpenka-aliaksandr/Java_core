package hw03.karpenko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {

        Random rnd = new Random();
           
        //region проверка работы только с workers

        List<Employee> employees = Worker.getEmployees(10);
        System.out.println("Не отсортированные workers");
        for (Employee employee: employees) {
            System.out.println(employee);
        }

        Collections.sort(employees, new WorkerOrganizationComparator());
        System.out.println();
        System.out.println("Отсортированные по организации работы employees(workers)");
        for (Employee employee: employees) {
            System.out.println(employee);
        }

        Comparator<Employee> organizationThenSurnameNameComparator = new WorkerOrganizationComparator().
            thenComparing(new EmployeeSurnameNameComparator());
        
        Collections.sort(employees, organizationThenSurnameNameComparator);
        System.out.println();
        System.out.println("Отсортированные по организации работы, затем по фимилии и затем по имени employees(workers)");
        for (Employee employee: employees) {
            System.out.println(employee);
        }
        
        Collections.sort(employees);
        System.out.println();
        System.out.println("Отсортированные по умолчанию (фамилия, затем имя) employees");
        for (Employee employee: employees) {
            System.out.println(employee);
        }

        System.out.println();
        System.out.println();

        //endregion

        //region проверка работы workers+freelancers
        
        List<Employee> employees1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (rnd.nextInt(2) == 0){
                employees1.addAll(Worker.getEmployees(1)); 
            } else {
                employees1.addAll(Freelancer.getEmployees(1));
            }
        }
        
        System.out.println("Не отсортированные Employees");
        for (Employee employee: employees1) {
            System.out.println(employee);
        }
  
        Collections.sort(employees1);
        System.out.println();
        System.out.println("Отсортированные по умолчанию (фамилия, затем имя) employees");
        for (Employee employee: employees1) {
            System.out.println(employee);
        }
        System.out.println();
        System.out.println();

        //endregion

        //region проверка работы коллекции Employees ()

        Employees employees2 = new Employees();
        for (int i = 0; i < 10; i++) {
            if (rnd.nextInt(2) == 0){
                employees2.Add(Worker.getEmployees(1).get(0)); 
            } else {
                employees2.Add(Freelancer.getEmployees(1).get(0));
            }
        }
        System.out.println("Вывод коллекции через forEach");
        for (Employee e : employees2) {
            System.out.println(e);         
        }

        //endregion


    }
}
