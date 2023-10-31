package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.models.request.AddActionTypeDto;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.actionTypeService.ActionTypeService;
import com.grapplesoft.meil_backend.services.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actiontype")
public class ActionTypeController {

    private final ActionTypeService actionTypeService;

//    @Autowired
//    public ActionTypeController(Qualifier("actionTypeServiceImpl") ActionTypeService actionTypeService) {
//        this.actionTypeService = actionTypeService;
//    }

    @Autowired
    public ActionTypeController(ActionTypeService actionTypeService) {
        this.actionTypeService = actionTypeService;

    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> addActionType(
            @RequestBody AddActionTypeDto request) {
        this.actionTypeService.addActionType(request);

        return null;
    }

}
