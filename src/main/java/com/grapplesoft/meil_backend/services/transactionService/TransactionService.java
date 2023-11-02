package com.grapplesoft.meil_backend.services.transactionService;

import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.Transaction;
import com.grapplesoft.meil_backend.models.request.transactions.AllotProjectSiteRequestDto;
import com.grapplesoft.meil_backend.models.request.transactions.ChangeDepartment;
import com.grapplesoft.meil_backend.models.request.transactions.DeallotProjectSiteRequest;
import com.grapplesoft.meil_backend.models.request.transactions.EmployeeTransfer;

import java.util.List;

public interface TransactionService {
    /**
     * Allot Project Site - creates a new transaction for a project site with Actiontype T101
     *
     * @param request {@link AllotProjectSiteRequestDto} - request body
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see AllotProjectSiteRequestDto
     */
    Result<Transaction> allotProjectsite(AllotProjectSiteRequestDto request);

    /**
     * Deallot a project site - creates a new transaction for existing project site with Actiontype T102.
     * If transactions exist for same project site, project and employee, and its action type is T102 then return null.
     *
     * @param request {@link DeallotProjectSiteRequest}
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see DeallotProjectSiteRequest
     */
    Result<Transaction> deallotProjectSite(DeallotProjectSiteRequest request);

    List<Transaction> getAllTransactions();

    void deleteTransaction(Long id);

    Result<Transaction> changeDepartment(ChangeDepartment cdept);
    Result<Transaction> employeeTransfer(EmployeeTransfer empt);
    Result<Transaction> employeetempdepu(EmployeeTransfer empt);
    Result<Transaction> rejointempdepu(EmployeeTransfer empt);
}
