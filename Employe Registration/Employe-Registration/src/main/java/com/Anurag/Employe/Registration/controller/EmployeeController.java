package com.Anurag.Employe.Registration.controller;

import com.Anurag.Employe.Registration.entity.Employee;
import com.Anurag.Employe.Registration.service.EmployeeService;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("/list")
    public String getAll(Model themodel){
        List<Employee> employees =  employeeService.showAll();
        themodel.addAttribute("employees",employees);
        return "directory";
    }

    @GetMapping("/showFormForAdd")
    public String showForm(Model theModel){
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);
        return "registration";
    }
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        employeeService.save(theEmployee);
        return "redirect:/list";
    }

    @GetMapping("delete")
    public String deleteById(@RequestParam("employeeId") int id, RedirectAttributes redirectAttributes){
        Employee employee = employeeService.findById(id);
        if(employee!=null){
            employeeService.deleteById(id);
            redirectAttributes.addFlashAttribute("message","Employee Deleted Successfully!!");
        }
        else redirectAttributes.addFlashAttribute("error","Employee with "+id+" not found");
        return "redirect:/list";
    }

    @PostMapping("/update")
    public String update(@RequestParam("employeeId") int id, Employee theEmployee,Model model){
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);
        return "registration";
    }


}
