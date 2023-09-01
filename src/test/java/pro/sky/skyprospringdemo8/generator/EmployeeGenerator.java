package pro.sky.skyprospringdemo8.generator;

import pro.sky.skyprospringdemo8.entity.Employee;

public class EmployeeGenerator {


    public static final String IVAN_FIRST_NAME = "Ivan";
    public static final String IVAN_LAST_NAME = "Ivanov";
    public static final double IVAN_SALARY = 790;

    public static final String PETR_FIRST_NAME = "Petr";
    public static final String PETR_LAST_NAME = "Petrov";
    public static final double PETR_SALARY = 819;

    public static final String ILYA_FIRST_NAME = "Ilya";
    public static final String ILYA_LAST_NAME = "Ilyn";
    public static final double ILYA_SALARY = 777;


    public static final int FIRST_DEPARTMENT_ID = 1;
    public static final int SECOND_DEPARTMENT_ID = 2;

    public static Employee getIvanFirstDep() {
        return new Employee(IVAN_FIRST_NAME, IVAN_LAST_NAME, IVAN_SALARY, FIRST_DEPARTMENT_ID);
    }

    public static Employee getPetrFirstDep() {
        return new Employee(PETR_FIRST_NAME, PETR_LAST_NAME, PETR_SALARY, FIRST_DEPARTMENT_ID);
    }

    public static Employee getIlyaSecondDep() {
        return new Employee(ILYA_FIRST_NAME, ILYA_LAST_NAME, ILYA_SALARY, SECOND_DEPARTMENT_ID);
    }
}
