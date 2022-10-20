package com.ileuskov.employeeManager;

import com.ileuskov.employeeManager.model.Employee;
import com.ileuskov.employeeManager.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Controller class that will be receiving frontend api calls and
//redirecting them to the DB through our backend service layer (employeeService)
@RestController
@RequestMapping("/employee") // which means that every request will start with /employee and can be deepened by other HTTP calls
public class EmployeeResource {

    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees (){
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id){ // this way we get access to id provided in the url
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee (@RequestBody Employee employee){
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee (@RequestBody Employee employee){
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeById (@PathVariable("id") Long id){ // this way we get access to id provided in the url
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
