package com.grapplesoft.meil_backend.services.transactionService;

import com.grapplesoft.meil_backend.models.entities.Transaction;
import com.grapplesoft.meil_backend.models.request.transactions.AllotProjectSiteRequestDto;
import com.grapplesoft.meil_backend.models.request.transactions.DeallotProjectSiteRequest;

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
    Transaction allotProjectsite(AllotProjectSiteRequestDto request);

    /**
     * Deallot a project site - creates a new transaction for existing project site with Actiontype T102.
     * If transactions exist for same project site, project and employee, and its action type is T102 then return null.
     *
     * @param request {@link DeallotProjectSiteRequest}
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see DeallotProjectSiteRequest
     */
    Transaction deallotProjectSite(DeallotProjectSiteRequest request);

    List<Transaction> getAllTransactions();

    void deleteTransaction(Long id);
}
