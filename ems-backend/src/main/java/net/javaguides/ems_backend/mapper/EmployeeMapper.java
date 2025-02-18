package net.javaguides.ems_backend.mapper;

import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;

public class EmployeeMapper {

    //It converts an entity (Employee) into a DTO (EmployeeDto) so that the entity is not directly exposed in APIs.
    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return new EmployeeDto(
                employee.getEmail(),
                employee.getLastName(),
                employee.getFirstName(),
                employee.getId()
        );
    }

    //mapping employee dto to employee entity
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getEmail(),
                employeeDto.getLastName(),
                employeeDto.getFirstName(),
                employeeDto.getId()
        );
    }
}
