package net.javaguides.ems_backend.service.impl;

import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.entity.Employee;
import net.javaguides.ems_backend.exception.ResourceNotFound;
import net.javaguides.ems_backend.mapper.EmployeeMapper;
import net.javaguides.ems_backend.repository.EmployeeRepository;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static net.javaguides.ems_backend.mapper.EmployeeMapper.mapToEmployee;
import static net.javaguides.ems_backend.mapper.EmployeeMapper.mapToEmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    //Dependency Injection
    /*Your constructor is correctly using constructor injection to inject EmployeeRepository
    into EmployeeServiceImpl. This is a best practice in Spring Boot as it ensures dependency
    inversion and allows for easier unit testing.*/

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Convert DTO to Entity
        Employee employee = mapToEmployee(employeeDto);
        // Save to Database
        Employee savedEmployee = employeeRepository.save(employee);
        // Convert Entity back to DTO and return
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long empid) {
        Employee employee = employeeRepository.findById(empid)
                .orElseThrow(() -> new ResourceNotFound("Employee not found with id: " + empid)); //Retrieves the employee by id. If not found, throws an exception.
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        //Java 8-15 → Use Collectors.toList()
       /* List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());*/
        // Java 16+ we can use → .toList()
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long empid, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(empid).orElseThrow(
                () -> new ResourceNotFound("Employee not found with id: " + empid));

        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long empid) {
        Employee existingEmployee = employeeRepository.findById(empid)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employeeRepository.delete(existingEmployee);
    }


}
