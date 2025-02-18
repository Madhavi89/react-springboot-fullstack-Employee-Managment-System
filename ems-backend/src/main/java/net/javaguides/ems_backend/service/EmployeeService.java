package net.javaguides.ems_backend.service;

import net.javaguides.ems_backend.dto.EmployeeDto;
import java.util.List;

//This defines service layer which handles employee related business logic
public interface EmployeeService {
    //Return Type, Method Name and Parameters ()
    //Create Method
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    //Get Id Method
    EmployeeDto getEmployeeById(Long empid);

    //Get All Employess
    List<EmployeeDto> getAllEmployees();

    //Put Update Method
    EmployeeDto updateEmployee(Long empid, EmployeeDto employeeDto);

    //Delete Method
    void deleteEmployee(Long empid);
}
