package com.hexaware.easypay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.easypay.dto.EmployeeDTO;
import com.hexaware.easypay.dto.PayrollDTO;
import com.hexaware.easypay.dto.SalaryPolicyDTO;
import com.hexaware.easypay.entity.Employee;
import com.hexaware.easypay.entity.SalaryPolicy;
import com.hexaware.easypay.service.IAdminService;

@RestController
@RequestMapping("/api/admin")
 @CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @PostMapping("/create-emp") // ✅
    public String createEmployee(@RequestBody EmployeeDTO dto) {
        return adminService.createEmployee(dto);
    }

    @PutMapping("/update-emp/{id}") // ✅
    public String updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO dto) {
        return adminService.updateEmployee(id, dto);
    }

    @GetMapping("/getall") // ✅
    public List<Employee> getAllEmployees() {
        return adminService.getAllEmployees();
    }

    @GetMapping("/get-id/{id}") // ✅
    public Employee getEmployeeById(@PathVariable Long id) {
        return adminService.getEmployeeById(id);
    }

    @GetMapping("/get-name/{name}") // ✅
    public List<Employee> getEmployeeByName(@PathVariable String name) {
        return adminService.getEmployeeByName(name);
    }

    @GetMapping("/get-dept/{dept}")// ✅
    public List<Employee> getEmployeeByDept(@PathVariable String dept) {
        return adminService.getEmployeeByDept(dept);
    }

    @GetMapping("/get-email/{email}")// ✅
    public Employee getEmployeeByEmail(@PathVariable String email) {
        return adminService.getEmployeeByEmail(email);
    }

    @DeleteMapping("/delete-emp/{id}")// ✅
    public String deleteEmployeeById(@PathVariable Long id) {
        return adminService.deleteEmployeeById(id);
    }

    @DeleteMapping("/purge") // ✅
    public String purgeAllEmployees() {
        return adminService.purgeAllEmployees();
    }

    @PostMapping("/create-policy")// ✅
    public String defineSalaryPolicy(@RequestBody SalaryPolicyDTO dto) {
        return adminService.defineSalaryPolicy(dto);
    }

    @PutMapping("/update-policy/{id}")// ✅
    public String modifySalaryPolicy(@PathVariable Long id, @RequestBody SalaryPolicyDTO dto) {
        return adminService.modifySalaryPolicy(id, dto);
    }

    @GetMapping("/compliance-report/{policyId}")// ✅
    public ResponseEntity<byte[]> downloadCompliancePdf(@PathVariable Long policyId) {
        byte[] pdfBytes = adminService.generateCompliancePdf(policyId);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=compliance-report-" + policyId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/get-policies")// ✅
    public List<SalaryPolicy> getAllPolicies() {
        return adminService.getAllPolicies();
    }

    @GetMapping("/get-policy/{id}") // ✅
    public SalaryPolicy getSalaryPolicyById(@PathVariable Long id) {
        return adminService.getPolicyById(id);
    }

    @DeleteMapping("/delete-policy/{id}")// ✅
    public String deletePolicy(@PathVariable Long id) {
        return adminService.deletePolicy(id);
    }

    @PutMapping("/activate-deactivate-payroll/{id}") // ✅
    public String activateDeactivatePayroll(@PathVariable Long id, @RequestBody PayrollDTO dto) {
        return adminService.activateDeactivatePayroll(id, dto);
    }


}
