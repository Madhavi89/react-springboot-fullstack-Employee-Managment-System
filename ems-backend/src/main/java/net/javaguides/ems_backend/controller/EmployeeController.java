package net.javaguides.ems_backend.controller;

import net.javaguides.ems_backend.dto.EmployeeDto;
import net.javaguides.ems_backend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
//Marks this as a REST API controller.
@RestController
//Base URL for all employee-related endpoints.
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    /*@PostMapping → Defines an HTTP POST method (used to create a new resource).
    @RequestBody → Tells Spring Boot to convert JSON input into a EmployeeDto object.
    ResponseEntity<EmployeeDto> → Wraps the response with a proper HTTP status code.*/
    // Create Employee API
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build Get Employee REST API
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long empid) {
        EmployeeDto getEmployee = employeeService.getEmployeeById(empid);
        return ResponseEntity.ok(getEmployee);
    }

    //Build Get All Employee REST API
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //Build Get All Employee REST API
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long empid, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(empid, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }

    //Build Delete Method
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long empid){
        employeeService.deleteEmployee(empid);
        return ResponseEntity.ok("Employee deleted successfully!");
    }

}


