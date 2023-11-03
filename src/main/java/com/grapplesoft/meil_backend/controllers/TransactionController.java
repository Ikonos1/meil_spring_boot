package com.grapplesoft.meil_backend.controllers;

import com.grapplesoft.meil_backend.builders.ApiResponseBuilder;
import com.grapplesoft.meil_backend.builders.TransactionsBuilder;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.TransactionMapped;
import com.grapplesoft.meil_backend.models.entities.Transaction;
import com.grapplesoft.meil_backend.models.request.transactions.AllotProjectSiteRequestDto;
import com.grapplesoft.meil_backend.models.request.transactions.ChangeDepartment;
import com.grapplesoft.meil_backend.models.request.transactions.DeallotProjectSiteRequest;
import com.grapplesoft.meil_backend.models.request.transactions.EmployeeTransfer;
import com.grapplesoft.meil_backend.models.response.ApiResponse;
import com.grapplesoft.meil_backend.services.transactionService.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController extends BaseController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(@Qualifier("transactionServiceImpl") TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping(value = "/t101", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> allocateProject(
            @RequestBody AllotProjectSiteRequestDto request
    ) {
        Result<Transaction> result = this.transactionService.allotProjectsite(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "T101 successfully executed"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t102", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> deallotProject(
            @RequestBody DeallotProjectSiteRequest request
    ) {
        Result<Transaction> result = this.transactionService.deallotProjectSite(request);

        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "T102 successfully executed"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t103", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> changedepartment(@RequestBody ChangeDepartment cdept) {
        Result<Transaction> result = transactionService.changeDepartment(cdept);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee department changed. T103 executed successfully."));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }

    @PostMapping(value = "/t104", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApiResponse<String>> employeetransaction(@RequestBody EmployeeTransfer empt) {
        Result<Transaction> result = transactionService.employeeTransfer(empt);
        if (result.isSuccess()) {
            return ResponseEntity.ok(ApiResponseBuilder.success(null, "Employee Transfered sucessfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponseBuilder.badRequest(result.error().getMessage()));
        }
    }


    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<ApiResponse<List<TransactionMapped>>> getAllTransactions() {
        List<TransactionMapped> transactionsMapped = new ArrayList<>();
        List<Transaction> transactions = this.transactionService.getAllTransactions();

        for (Transaction transaction : transactions) {
            transactionsMapped.add(TransactionsBuilder.buildMapped(transaction));
        }

        return ResponseEntity.ok(
                ApiResponseBuilder.success(transactionsMapped, "Transactions fetched successfully")
        );
    }

}
