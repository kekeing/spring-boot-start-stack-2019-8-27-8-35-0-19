package com.tw.apistackbase.controller;


import com.tw.apistackbase.entity.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    List<Employee> employees = new ArrayList<Employee>();


    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<Employee>> getEmployeeList() {

//        employees.add(new Employee("1","like",22,"male"));
//        employees.add(new Employee("2","xiexinfang",22,"female"));
        return ResponseEntity.ok(employees);
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") String id){
        for (Employee employee : employees){
            if (employee.getId().equals(id)){
                return ResponseEntity.ok(employee);
            }
        }
       // return ResponseEntity.notFound().build();
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addEmployee(@RequestBody Employee employee) {
        employees.add(employee);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") String id) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()){
            if (iterator.next().getId().equals(id))
                iterator.remove();

        }
//        for (Employee employee : employees) {
//            if (employee.getId().equals(id)) {
//                findEmployee = employee;
//            }
//        }
//        employees.remove(findEmployee);


    }

    @PutMapping(value = "/")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void updateEmployee(@RequestBody Employee givenEmployee) {
        for (Employee employee : employees) {
            if (employee.getId().equals(givenEmployee.getId())) {
                employee.setName(givenEmployee.getName());
                employee.setAge(givenEmployee.getAge());
                employee.setGender(givenEmployee.getGender());
            }
        }

    }
}
