package pro.sky.skyprospringdemo8;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.skyprospringdemo8.entity.Employee;
import pro.sky.skyprospringdemo8.exception.EmployeeNotFoundException;
import pro.sky.skyprospringdemo8.service.DepartmentService;
import pro.sky.skyprospringdemo8.service.EmployeeService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pro.sky.skyprospringdemo8.generator.EmployeeGenerator.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {


    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getEmployeeWithMaxSalary_success() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee employeeWithMaxSalary = getPetrFirstDep();
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Employee actualEmployeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(depId);
        assertEquals(employeeWithMaxSalary, actualEmployeeWithMaxSalary);
        assertEquals(depId, actualEmployeeWithMaxSalary.getDepartmentId());
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMaxSalary_withEmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с максимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMaxSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_success() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee employeeWithMinSalary = getIvanFirstDep();

        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );

        //Начало теста
        Employee actualEmployeeWithMinSalary = departmentService.getEmployeeWithMinSalary(depId);
        assertEquals(employeeWithMinSalary, actualEmployeeWithMinSalary);
        assertEquals(depId, actualEmployeeWithMinSalary.getDepartmentId());
        assertTrue(petrFirstDep.getSalary() > ivanFirstDep.getSalary());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeeWithMinSalary_EmployeeNotFoundException() {
        //Подготовка входных данных
        int depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        when(employeeService.getAll()).thenReturn(Collections.singletonList(
                getIlyaSecondDep()
        ));
        String expectedMessage = "404 Сотрудник с минимальной зарплатой не найден";

        //Начало теста
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.getEmployeeWithMinSalary(depId));
        assertEquals(expectedMessage, exception.getMessage());
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_success() {
        //Подготовка входных данных
        Integer depId = null;

        //Подготовка ожидаемого результата
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep));
        expectedEmployeeMap.put(SECOND_DEPARTMENT_ID, Collections.singletonList(ilyaSecondDep));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }

    @Test
    void getEmployeesByDepartment_whenDepIdIsNull() {
        //Подготовка входных данных
        Integer depId = FIRST_DEPARTMENT_ID;

        //Подготовка ожидаемого результата
        Employee petrFirstDep = getPetrFirstDep();
        Employee ivanFirstDep = getIvanFirstDep();
        Employee ilyaSecondDep = getIlyaSecondDep();

        when(employeeService.getAll()).thenReturn(
                List.of(petrFirstDep, ivanFirstDep, ilyaSecondDep)
        );
        Map<Integer, List<Employee>> expectedEmployeeMap = new HashMap<>();
        expectedEmployeeMap.put(FIRST_DEPARTMENT_ID, List.of(petrFirstDep, ivanFirstDep));

        //Начало теста
        Map<Integer, List<Employee>> actualEmployeeMap = departmentService.getEmployeesByDepartment(depId);
        assertEquals(expectedEmployeeMap, actualEmployeeMap);
        verify(employeeService).getAll();
    }
}
