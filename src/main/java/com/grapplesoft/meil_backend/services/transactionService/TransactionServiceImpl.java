package com.grapplesoft.meil_backend.services.transactionService;

import com.grapplesoft.meil_backend.builders.TransactionsBuilder;
import com.grapplesoft.meil_backend.enums.ActionTypeEnum;
import com.grapplesoft.meil_backend.models.Result;
import com.grapplesoft.meil_backend.models.entities.*;
import com.grapplesoft.meil_backend.models.request.transactions.AllotProjectSiteRequestDto;
import com.grapplesoft.meil_backend.models.request.transactions.ChangeDepartment;
import com.grapplesoft.meil_backend.models.request.transactions.DeallotProjectSiteRequest;
import com.grapplesoft.meil_backend.models.request.transactions.EmployeeTransfer;
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
                                  @Qualifier("actionTypeRepository") ActionTypeRepository actionTypeRepository,
                                  @Qualifier("departmentRepository") DepartmentRepository departmentRepository,
                                  @Qualifier("employeeRepository") EmployeeRepository employeeRepository,
                                  @Qualifier("hseFunctionRepository") HseFunctionRepository hseFunctionRepository) {
        this.transactionRepository = transactionRepository;
        this.projectSiteRepository = projectSiteRepository;
        this.projectRepository = projectRepository;
        this.employeeService = employeeService;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
        this.hseFunctionRepository = hseFunctionRepository;
        actionTypes.addAll(actionTypeRepository.findAll());
        this.employeeRepository = employeeRepository;
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
    public Result<Transaction> allotProjectsite(AllotProjectSiteRequestDto request) {
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
            return Result.success(this.addTransaction(Transaction.builder()
                    .actiontypeid(action)
                    .hsecoordid(projCoordinator)
                    .fromprojectid(project)
                    .actiondate(LocalDate.now())
                    .createdate(LocalDate.now())
                    .remarks(request.remarks())
                    .build()));
        } else {
            return Result.failure(new Exception("Project Coordinator not found"));
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
    public Result<Transaction> deallotProjectSite(DeallotProjectSiteRequest request) {
        // fetch all existing transactions by project id and action Type 2 to check if the project has already been dealloted
        var existingTransactionsT101 = this.transactionRepository.findByProjectIdAndActionId(request.projectId(), 1);
        var existingTransactionsT102 = this.transactionRepository.findByProjectIdAndActionId(request.projectId(), 2);

        // if the latest transaction is of type T102, return null
        if (existingTransactionsT101.isEmpty()) {
            return Result.failure(new Exception("No matching project found to deallot."));
        } else if (!existingTransactionsT102.isEmpty()) {
            return Result.failure(new Exception("Project has already been dealloted."));
        } else {
            // if the project site exists, create a new transaction with action type t102. ELSE return null.
            Project project = this.projectRepository.findById(request.projectId()).orElse(null);
            Employee projCoordinator = this.employeeService.getEmployeeById(request.projectCoordinatorId());

            // build transaction and return
            Actiontype action = this.getActionType(ActionTypeEnum.T102);
            return Result.success(
                    this.addTransaction(Transaction.builder()
                            .actiontypeid(action)
                            .hsecoordid(projCoordinator)
                            .fromprojectid(project)
                            .actiondate(LocalDate.now())
                            .createdate(LocalDate.now())
                            .remarks(request.remarks())
                            .build())
            );

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

    @Override
    public Result<Transaction> changeDepartment(ChangeDepartment request) {
        // Find transactions related to the employee and project
        List<Transaction> transactions = transactionRepository.findByProjectIdAndEmployeeId(request.fromProjectId(), request.employeeId());

        if (transactions.stream().anyMatch(it -> it.getActiontypeid().getAction().equals(ActionTypeEnum.T102.getValue()))) {
            return Result.failure(new Exception("The project linked with provided employee is already dealloted."));
        } else {
            Employee employee = employeeService.getEmployeeById(request.employeeId());


            if (employee != null) {
                var department = departmentRepository.findById(request.deptIdTo()).orElse(null);

                employee.setDeptCode(department);
                employee.setEditDate(LocalDate.now());
                employeeRepository.save(employee);

                // Find the project site by its ID
                Projectsite projectSite = this.projectSiteRepository.findById(request.fromProjectId()).orElse(null);

                if (projectSite != null) {
                    Actiontype actiontype = this.getActionType(ActionTypeEnum.T103);
                    Transaction transaction = this.addTransaction(TransactionsBuilder.forT103(actiontype, projectSite, employee,
                            departmentRepository.findById(request.deptIdFrom()).orElse(null),
                            departmentRepository.findById(request.deptIdFrom()).orElse(null)));

                    if (actiontype != null) {
                        return Result.success(transaction);
                    } else {
                        return Result.failure(new Exception("Action type not found"));
                    }
                } else {
                    return Result.failure(new Exception("Project-site not found"));
                }
            } else {
                // Set an error response if the employee is not found
                return Result.failure(new Exception("Employee not found"));
            }
        }
    }

    private boolean isProjectDealloted(Long projectId, Long employeeId) {
        return !transactionRepository.findByProjectIdAndEmployeeIdAndActionId(projectId, employeeId, 2).isEmpty();
    }

    @Override
    public Result<Transaction> employeeTransfer(EmployeeTransfer empt) {

        if (!isProjectDealloted(empt.fromprojectid(), empt.employeeid())) {
            // If the action type is not found, proceed with the employee transfer
            Employee emp = employeeService.getEmployeeById(empt.employeeid());

            if (emp != null) {

                // Find the source and target project sites by their IDs
                Project fromproject = this.projectRepository.findById(empt.fromprojectid()).orElse(null);
                Project toproject = this.projectRepository.findById(empt.toprojectid()).orElse(null);

                // Find the action type (T104)
                Actiontype action = this.getActionType(ActionTypeEnum.T115);

                // Find Hsefunctions for the source and target functions
                Hsefunction fun1 = hseFunctionRepository.findById(empt.function1()).orElse(null);
                Hsefunction fun2 = hseFunctionRepository.findById(empt.function2()).orElse(null);

                if (fromproject == null || toproject == null) {
                    return Result.failure(new Exception(String.format("No record found for project => %s", fromproject == null ? empt.fromprojectid() : empt.toprojectid())));
                }

//                if (fromproject == null) {
//                    return Result.failure(new Exception("No record found in fromproject."));
//                }
//
//                if (toproject == null) {
//
//                    return Result.failure(new Exception("No record found in  toproject."));
//                }

                if (action == null) {
                    return Result.failure(new Exception("No record found in  action"));
                }

                if (fun1 == null || fun2 == null) {
                    return Result.failure(new Exception(String.format("No record found for hsefunction => %s", fun1 == null ? empt.function1() : empt.function2())));
                }

//                if (fun1 == null) {
//
//                    return Result.failure(new Exception("No record found in  hsefunction1"));
//                }
//
//                if (fun2 == null) {
//
//                    return Result.failure(new Exception("No record found in  hsefunction1"));
//                }

                emp.setHseFunctionId(fun2);
                emp.setProjCode(toproject);
                emp.setEditDate(LocalDate.now());
                employeeRepository.save(emp);
                // Create a new transaction for the employee transfer
                Transaction trsanc = this.addTransaction(Transaction.builder()
                        .actiontypeid(action)
                        .function1(fun1)
                        .function2(fun2)
                        .fromprojectid(fromproject)
                        .toprojectid(toproject)
                        .createuserid(emp)
                        .date1(empt.date1())
                        .date2(empt.date2())
                        .actiondate(LocalDate.now())
                        .createdate(LocalDate.now())
                        .build());

                // Set a success response with the transaction data
                return Result.success(trsanc);
            } else {
                return Result.failure(new Exception("No Employee found"));
            }
        } else {
            return Result.failure(new Exception("This Employee's project is already de-allotted"));
        }
    }

    @Override
    public Result<Transaction> employeetempdepu(EmployeeTransfer empt) {
        return null;
    }

    @Override
    public Result<Transaction> rejointempdepu(EmployeeTransfer empt) {
        return null;
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
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final HseFunctionRepository hseFunctionRepository;
    private final EmployeeService employeeService;

    private final List<Actiontype> actionTypes = new ArrayList<>();
}
