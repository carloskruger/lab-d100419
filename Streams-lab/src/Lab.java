package com.ga.streams;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Lab {

    private List<Employee> employees = Arrays.asList(
            new Employee("Bezos, Jeff", LocalDate.of(2004, 4, 2), 68_109.00, "Male"),
            new Employee("Sheryl Sandberg", LocalDate.of(2014, 7, 1), 87_846.00,"Female"),
            new Employee("Buffet, Warren", LocalDate.of(2011, 7, 23), 95_035.00, "Male"),
            new Employee("Susan Wojcick", LocalDate.of(2015, 6, 1), 37_210.00, "Female"),
            new Employee("Zuckerberg, Mark", LocalDate.of(2016, 5, 12), 48_450.00, "Male"),
            new Employee("Brin, Sergey", LocalDate.of(2016, 8, 5), 74_416.00, "Male")
    );


    private <T> void printList(List<T> list) {

        list.stream().forEach(employee -> {
            System.out.println(employee);
        });

    }

    @Test
    public void getEmployeesOver50k() {
        List<Employee>  employeesOver50k = employees.stream()
                .filter(employee -> employee.getSalary() > 50000)
        .collect(Collectors.toList());
        printList(employeesOver50k);

    }

    @Test
    public void getEmployeeNamesHiredAfter2012() {
        List<String> employeesHiredAfter2012 = employees.stream()
        .filter(employee -> employee.getHireDate().compareTo(LocalDate.of(2012, 1, 1)) > 0)
                .map(employee -> employee.getName())
                    .collect(Collectors.toList());

        printList(employeesHiredAfter2012);

    }

    @Test
    public void getMaxSalary() {
        Employee personWithMaxSalary = employees.stream()
                .max(Comparator.comparing(person->person.getSalary()))
                .orElse(null);

        double max = personWithMaxSalary.getSalary();
        System.out.println("Max:" + max);

    }

    @Test
    public void getMinSalary() {
        Employee personWithMinSalary = employees.stream()
                .min(Comparator.comparing(person->person.getSalary()))
                .orElse(null);
        double min = personWithMinSalary.getSalary();
        System.out.println("Min:" + min);
    }

    @Test
    public void getAverageSalaries() {
        List<Employee> maleEmployees = employees.stream()
                .filter(employee -> employee.getGender()=="Male")
                .collect(Collectors.toList());

        List<Employee> femaleEmployees = employees.stream()
                .filter(employee -> employee.getGender()=="Female")
                .collect(Collectors.toList());

        double sumMale = maleEmployees.stream().map(Employee::getSalary).collect(Collectors.summingDouble(f->f));
        double sumFemale = (Double) femaleEmployees.stream().map(Employee::getSalary).mapToDouble(f -> f).sum();

        double averageMale = sumMale / maleEmployees.size();
        double averageFemale = sumFemale / femaleEmployees.size();



        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
        System.out.println("Averages: Male:" + averageMale + " Female:" + averageFemale);
    }

    @Test
    public void getMaximumPaidEmployee() {
        Employee highest = employees.stream()
                .max(Comparator.comparing(person->person.getSalary()))
                .orElse(null);
      //  Employee highest = null;
        System.out.println(highest);
    }
}
