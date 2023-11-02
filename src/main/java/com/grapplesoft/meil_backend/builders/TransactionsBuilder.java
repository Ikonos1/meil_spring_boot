package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.enums.ActionTypeEnum;
import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.TransactionMapped;
import com.grapplesoft.meil_backend.models.entities.*;
import com.grapplesoft.meil_backend.models.request.transactions.ChangeDepartment;

import java.time.LocalDate;

public class TransactionsBuilder {
    public static TransactionMapped buildMapped(Transaction transaction) {
        EmployeeWithoutPassword hseCoordPatch = null;
        if (transaction.getHsecoordid() != null) {
            hseCoordPatch = EmployeeBuilder.buildEmployeeWithoutPassword(transaction.getHsecoordid());
        }
        return TransactionMapped.builder()
                .id(transaction.getId())
                .actiontypeid(transaction.getActiontypeid())
                .hsecoordid(hseCoordPatch)
                .fromprojectid(null)
                .actiondate(null)
                .remarks(transaction.getRemarks())
                .build();
    }

    public static Transaction forT103(Actiontype actionType, Projectsite projectsite, Employee emp, Department dept1, Department dept2) {
        if (actionType != null && actionType.getAction().equals(ActionTypeEnum.T103.getValue())) {
            return Transaction.builder()
                    .actiondate(LocalDate.now())
                    .dept1(dept1)
                    .dept2(dept2)
                    .fromprojectid(projectsite.getProjid())
                    .actiontypeid(actionType)
                    .employeeid(emp)
                    .createdate(LocalDate.now())
                    .build();
        } else {
            return null;
        }
    }
}
