package hw03_classes.src.main.java.hw03.karpenko;

import java.util.Comparator;

public class WorkerOrganizationComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return (((Worker)o1).getOrganization().compareTo(((Worker)o2).getOrganization()));
    }
}
