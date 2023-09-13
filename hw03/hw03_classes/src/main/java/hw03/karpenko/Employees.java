package hw03.karpenko;

import java.util.ArrayList;
import java.util.Iterator;

public class Employees implements Iterable<Employee>{
    ArrayList<Employee> employees;
    

    public Employees() {
        employees = new ArrayList<>();
    }

    public void Add(Employee e){
        employees.add(e);
    }
    public Iterator<Employee> iterator(){
        return employees.listIterator();            
    }
    
}
