package com.grapplesoft.meil_backend.services.employeeService;

import com.grapplesoft.meil_backend.builders.EmployeeBuilder;
import com.grapplesoft.meil_backend.models.EmployeeDto;
import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.entities.Employee;
import com.grapplesoft.meil_backend.models.entities.Role;
import com.grapplesoft.meil_backend.models.entities.Token;
import com.grapplesoft.meil_backend.models.pagination.PagedListData;
import com.grapplesoft.meil_backend.models.request.AddEmployeeRequest;
import com.grapplesoft.meil_backend.models.request.UpdateEmployeeRequest;
import com.grapplesoft.meil_backend.repositories.EmployeeRepository;
import com.grapplesoft.meil_backend.repositories.TokenRepository;
import com.grapplesoft.meil_backend.services.mailService.MailService;
import com.grapplesoft.meil_backend.services.roleService.RoleService;
import com.grapplesoft.meil_backend.services.tokenService.TokenService;
import com.grapplesoft.meil_backend.utils.PaginationUtility;
import com.grapplesoft.meil_backend.utils.passwordUtility.PasswordUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final MailService mailService;
    private static final BCryptPasswordEncoder passwordEncoder = PasswordUtility.getPasswordEncoder();
    private final TokenRepository tokenRepository;

    @Autowired
    public EmployeeServiceImpl(@Qualifier("employeeRepository") EmployeeRepository employeeRepository,
                               RoleService roleService,
                               TokenService tokenService,
                               TokenRepository tokenRepository,
                               MailService mailService) {
        this.employeeRepository = employeeRepository;
        this.roleService = roleService;
        this.tokenService = tokenService;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    /**
     * @param addEmployeeRequest - employee Dto object to be added to the database {@link EmployeeDto}
     * @return created Employee object - {@link Employee}
     */
    @Override
    public Employee addEmployee(AddEmployeeRequest addEmployeeRequest) {
        // TODO: add department {@link Department}
        // TODO: add project @link Project
        // TODO: add leadership code (@link HseLeaderShip)
        // TODO: add HSE level (int 1 to 5)
        // TODO: add HSEcomppoints (int)


        if (this.employeeRepository.findByEmailOffice(addEmployeeRequest.emailOffice()).isPresent()) {
            return null;
        }

        // fetch the role object from the database
        Role role = this.roleService.getRoleByRoleId(addEmployeeRequest.role());

        // encode the password
        String generatedPassword = PasswordUtility.generateRandomPassword();
        String passwordEncoded = passwordEncoder.encode(generatedPassword);

        // create the employee object
        Employee employee = EmployeeBuilder.fromAddEmployeeRequest(addEmployeeRequest, role, passwordEncoded, false);

        // save this employee without token, so an employeeid is auto-generated
        employee = this.employeeRepository.save(employee);
        // save the employee object to the database and return the saved object

        // send email to the employee with the password
        this.mailService.sendMail("Account successfully created!", employee.getEmailOffice(),
                "You account has been successfully created. Your auto generated password is: " + generatedPassword + ". Please change your password after logging in.");

        // create token for the employee
        Token token = this.tokenService.createTokenForEmployee(employee);
        // set newly generated token for employee
        employee.setToken(token);

        // update the created employee with the token
        return this.employeeRepository.save(employee);
    }


    /**
     * @return
     */
    @Override
    public PagedListData<Employee> gellAllEmployees(Long page, Long size) {
        if (page == null || size == null) {
            Page<Employee> employees = this.employeeRepository.findAll(PaginationUtility.buildPageableUnpaged());
            return PaginationUtility.buildPagedListData(employees);
        }
        Pageable pageable = PaginationUtility.buildPageable(page.intValue(), size.intValue());
        return PaginationUtility.buildPagedListData(this.employeeRepository.findAll(pageable));
    }

    /**
     * @param request
     * @return
     */
    @Override
    public EmployeeWithoutPassword updateEmployee(UpdateEmployeeRequest request) {
        // TODO: add department {@link Department}
        // TODO: add project @link Project
        // TODO: add leadership code (@link HseLeaderShip)
        // TODO: add HSE level (int 1 to 5)
        // TODO: add HSEcomppoints (int)

        Employee employee = this.employeeRepository.findById(request.id()).orElse(null);
        if (employee != null) {
            employee.setEmployeeFirstName(request.firstName() != null ? request.firstName() : employee.getEmployeeFirstName());
            employee.setEmployeeLastName(request.lastName() != null ? request.lastName() : employee.getEmployeeLastName());
            employee.setDesignation(request.designation() != null ? request.designation() : employee.getDesignation());
            if (request.role() != null && !request.role().equals(employee.getRole().getId())) {
                employee.setRole(this.roleService.getRoleByRoleId(request.role()));
            }
            employee.setDateOfBirth(request.dateOfBirth() != null ? request.dateOfBirth() : employee.getDateOfBirth());
            employee.setDateOfJoining(request.dateOfJoining() != null ? request.dateOfJoining() : employee.getDateOfJoining());
            employee.setDrivingLicense(request.drivingLicense() != null ? request.drivingLicense() : employee.getDrivingLicense());
            employee.setEmailOffice(request.emailOffice() != null ? request.emailOffice() : employee.getEmailOffice());
            employee.setEmail2(request.email2() != null ? request.email2() : employee.getEmail2());
            employee.setMobileOffice(request.mobileOffice() != null ? request.mobileOffice() : employee.getMobileOffice());
            employee.setMobile2(request.mobile2() != null ? request.mobile2() : employee.getMobile2());
            employee.setMobile3(request.mobile3() != null ? request.mobile3() : employee.getMobile3());
            employee.setWhatsAppNum(request.whatsAppNum() != null ? request.whatsAppNum() : employee.getWhatsAppNum());
            employee.setRemarks(request.remarks() != null ? request.remarks() : employee.getRemarks());

            if (request.password() != null) {
                // encode the password
                String passwordEncoded = passwordEncoder.encode(request.password());
                employee.setPassword(passwordEncoded);
            }

            // create the employee object
            Employee updatedEmployee = this.employeeRepository.save(employee);

            return EmployeeBuilder.buildEmployeeWithoutPassword(updatedEmployee);
        } else {
            return null;
        }
    }

    /**
     * @param deleteEmployeeInput
     */
    @Override
    public void deleteEmployee(EmployeeDto deleteEmployeeInput) {

    }

    /**
     * @param emailOffice
     */
    @Override
    public void deleteEmployee(String emailOffice) {
        Employee emp = this.employeeRepository.findByEmailOffice(emailOffice).orElse(null);
        if (emp != null) {
            // delete token for employee first
            this.tokenRepository.delete(emp.getToken());
            // delete employee
            this.employeeRepository.deleteByEmailOffice(emailOffice);
        }
    }

    /**
     * @param emailOffice
     * @return
     */
    @Override
    @Transactional
    public Employee getEmployeeByEmailOffice(String emailOffice) {
        return this.employeeRepository.findByEmailOffice(emailOffice).orElse(null);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    /**
     * @param emailOffice
     * @param password
     * @return
     */
    @Override
    public Employee authenticateEmployee(String emailOffice, String password) {
        Employee fetchedEmployee = this.employeeRepository.findByEmailOffice(emailOffice).orElse(null);

        // if email exists and password matches, return the employee object
        if (fetchedEmployee != null && passwordEncoder.matches(password, fetchedEmployee.getPassword())) {
            return fetchedEmployee;
        } else {
            return null;
        }
    }
}
