package com.Anurag.Employe.Registration.service;

import com.Anurag.Employe.Registration.entity.Employee;
import com.Anurag.Employe.Registration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //show all employee -- Directory
    public List<Employee> showAll(){
        List<Employee> employeeList =  employeeRepository.findAll();
        return employeeList;
    }


    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee searchById(int id){
        Employee employee = employeeRepository.getById(id);
        if(employee!=null){
            return employee;
        }
        else throw  new RuntimeException("Id Not Found");
    }
    public void deleteById(int id){

        employeeRepository.deleteById(id);
    }

    public Employee findById(int id){
        Optional<Employee> employee =  employeeRepository.findById(id);
        Employee employee1 = null;
        if(employee.isPresent()){
            employee1 = employee.get();
        }
        else throw new RuntimeException("Employee Not found");
        return employee1;
    }


}
