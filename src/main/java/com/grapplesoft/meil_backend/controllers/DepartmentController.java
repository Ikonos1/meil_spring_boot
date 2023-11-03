package com.grapplesoft.meil_backend.controllers;


import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.models.entities.Department;
import com.grapplesoft.meil_backend.models.request.DepartmentRequest;
import com.grapplesoft.meil_backend.services.departmentService.DepartmentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController extends BaseController {

    private final DepartmentService departmentService;

    DepartmentController(
            @Qualifier("departmentServiceImpl") DepartmentService departmentService
    ) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<?> add(@RequestBody DepartmentRequest departmentRequest) {

        if (departmentService.insert(departmentRequest) != null) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Department added successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("someting went wrong"));
        }
    }

    @PutMapping
    public ResponseEntity<?> edit(@RequestBody DepartmentRequest departmentRequest) {
        if (departmentService.edit(departmentRequest) != null) {

            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Department updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("someting went wrong"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getall() {

        return ResponseEntity.ok(departmentService.getall());
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody Department deldept) {
        if (departmentService.delete(deldept.getDeptcode())) {

            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Department deleted successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest("someting went wrong"));
        }
    }
}
