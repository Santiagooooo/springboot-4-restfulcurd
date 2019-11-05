package com.santiago.controller;

import com.santiago.dao.DepartmentDao;
import com.santiago.dao.EmployeeDao;
import com.santiago.entities.Department;
import com.santiago.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域中
        model.addAttribute("emps", employees);

        //thymeleaf默认会拼串，拼成classpath:/templates/xxx.html
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    //springmvc自动关机将请求参数与入参对象的属性进行一一绑定，
    //前提是请求参数的名字与javaBean的属性值要一致。
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息"+employee);
        employeeDao.save(employee);
        //如果返回员工列表用return "emps"，将自动拼接为classpath:/templates/emps.html
        return "redirect:/emps";
    }

    //来到修改页面，查出当前员工以及部门信息，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        //回到修改页面（使add.html同时完成添加和修改员工的功能）
        return "emp/add";
    }

    //修改数据
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("修改的员工的数据"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
}
