package hw03_classes.src.main.java.hw03.karpenko;

import java.util.Comparator;

public class EmployeeSurnameNameComparator implements Comparator<Employee> {
    @Override
  
    public int compare(Employee o1, Employee o2) {
        int res = o1.getSurName().compareTo(o2.getSurName());
        if (res == 0){
            return o1.getName().compareTo(o2.getName());
        }
        return res;
    }
}
