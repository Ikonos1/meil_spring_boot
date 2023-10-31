package com.grapplesoft.meil_backend.builders;

import com.grapplesoft.meil_backend.models.EmployeeWithoutPassword;
import com.grapplesoft.meil_backend.models.TransactionMapped;
import com.grapplesoft.meil_backend.models.entities.Transaction;

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
}
