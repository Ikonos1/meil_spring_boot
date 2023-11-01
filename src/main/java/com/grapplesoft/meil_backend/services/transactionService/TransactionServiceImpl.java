package com.grapplesoft.meil_backend.services.transactionService;

import com.grapplesoft.meil_backend.enums.ActionTypeEnum;
import com.grapplesoft.meil_backend.models.entities.*;
import com.grapplesoft.meil_backend.models.request.transactions.AllotProjectSiteRequestDto;
import com.grapplesoft.meil_backend.models.request.transactions.DeallotProjectSiteRequest;
import com.grapplesoft.meil_backend.repositories.*;
import com.grapplesoft.meil_backend.services.employeeService.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    public TransactionServiceImpl(@Qualifier("transactionRepository") TransactionRepository transactionRepository,
                                  @Qualifier("projectSiteRepository") ProjectSiteRepository projectSiteRepository,
                                  @Qualifier("projectRepository") ProjectRepository projectRepository,
                                  @Qualifier("employeeServiceImpl") EmployeeService employeeService,
                                  @Qualifier("addressRepository") AddressRepository addressRepository,
                                  @Qualifier("actionTypeRepository") ActionTypeRepository actionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.projectSiteRepository = projectSiteRepository;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.addressRepository = addressRepository;

        actionTypes.addAll(actionTypeRepository.findAll());
    }


    /**
     * Allot Project Site - creates a new transaction for a project site with Actiontype T101
     *
     * @param request {@link AllotProjectSiteRequestDto} - request body
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see AllotProjectSiteRequestDto
     */
    @Override
    public Transaction allotProjectsite(AllotProjectSiteRequestDto request) {
        /*
              - here the allocated project is to be linked to a projectsite fetched from the database
              - this is not implemented yet as project sites are to be seeded via Master Management functions
              - the master managment is not implemented yet
         */

        // if the transactions list is not empty for search by project ID and Employee ID, return null
        if (!transactionRepository.findByProjectIdAndActionId(request.projectId(), 1).isEmpty()) {
            return null;
        }

        Project project = this.projectRepository.findById(request.projectId()).orElse(null);
        Employee siteManager = this.employeeService.getEmployeeById(request.siteManagerId());
        Employee projCoordinator = this.employeeService.getEmployeeById(request.projectCoordinatorId());
        Employee courierP = this.employeeService.getEmployeeById(request.courierPcode());
        Address address = this.addressRepository.findById(request.addressId()).orElse(null);

        // field validations
        if (projCoordinator != null) {

            // get action type for current transaction
            Actiontype action = this.getActionType(ActionTypeEnum.T101);

            // add transaction
            return this.addTransaction(Transaction.builder()
                    .actiontypeid(action)
                    .hsecoordid(projCoordinator)
                    .fromprojectid(project)
                    .actiondate(LocalDate.now())
                    .createdate(LocalDate.now())
                    .remarks(request.remarks())
                    .build());
        } else {
            return null;
        }
    }

    /**
     * Deallot a project site - creates a new transaction for existing project site with Actiontype T102.
     * If transactions exist for same project site, project and employee, and its action type is T102 then return null.
     *
     * @param request {@link DeallotProjectSiteRequest}
     * @return {@link Transaction}
     * @Author Vishwesh Shukla
     * @see DeallotProjectSiteRequest
     */
    @Override
    public Transaction deallotProjectSite(DeallotProjectSiteRequest request) {
        // fetch all existing transactions by project id and action Type 2 to check if the project has already been dealloted
        var existingTransactionsT101 = this.transactionRepository.findByProjectIdAndActionId(request.projectId(), 1);
        var existingTransactionsT102 = this.transactionRepository.findByProjectIdAndActionId(request.projectId(), 2);

        // if the latest transaction is of type T102, return null
        if (existingTransactionsT101.isEmpty() || !existingTransactionsT102.isEmpty()) {
            return null;
        } else {
            // if the project site exists, create a new transaction with action type t102. ELSE return null.
            Project project = this.projectRepository.findById(request.projectId()).orElse(null);
            Employee projCoordinator = this.employeeService.getEmployeeById(request.projectCoordinatorId());

            // build transaction and return
            Actiontype action = this.getActionType(ActionTypeEnum.T102);
            return this.addTransaction(Transaction.builder()
                    .actiontypeid(action)
                    .hsecoordid(projCoordinator)
                    .fromprojectid(project)
                    .actiondate(LocalDate.now())
                    .createdate(LocalDate.now())
                    .remarks(request.remarks())
                    .build());

        }

//        var latestTransaction = existingTransactions.stream().max(Comparator.comparing(Transaction::getCreatedate));
//        && Objects.equals(latestTransaction.get().getActiontypeid().getAction(), ActionTypeEnum.T102.getValue())
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }

    @Override
    public void deleteTransaction(Long id) {

    }

    private Transaction addTransaction(Transaction transaction) {
        if (transaction != null) {
            return this.transactionRepository.save(transaction);
        } else return null;
    }

    private Actiontype getActionType(ActionTypeEnum type) {
        return actionTypes.stream().filter(it -> it.getAction().equals(type.getValue())).findAny().orElse(null);
//        return actionTypes.stream().takeWhile(it -> it.getAction().equals(type.getValue())).findFirst().orElse(null);
    }

    private final TransactionRepository transactionRepository;
    private final ProjectSiteRepository projectSiteRepository;
    private final ProjectRepository projectRepository;
    private final AddressRepository addressRepository;
    private final EmployeeService employeeService;

    private final List<Actiontype> actionTypes = new ArrayList<>();
}
