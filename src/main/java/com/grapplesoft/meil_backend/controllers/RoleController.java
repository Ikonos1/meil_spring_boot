package com.grapplesoft.meil_backend.controllers;


import com.grapplesoft.meil_backend.builders.RoleBuilder;
import com.grapplesoft.meil_backend.models.RoleDto;
import com.grapplesoft.meil_backend.models.RolePlain;
import com.grapplesoft.meil_backend.models.entities.Role;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.roleService.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    private final RoleService roleService;

    @Autowired
    public RoleController(@Qualifier("roleServiceImpl") RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/insert")
    ResponseEntity<ApiResponse<RolePlain>> insertRole(
            @Valid @RequestBody RoleDto roleDto) {
        Role insertedRole = this.roleService.insertRole(roleDto);
        return ResponseEntity.ok(new ApiResponse<RolePlain>(
                HttpStatus.CREATED.value(),
                RoleBuilder.rolePlain(insertedRole),
                "Role inserted successfully"
        ));
    }

}
