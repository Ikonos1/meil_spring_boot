package com.grapplesoft.meil_backend.repositories;

import com.grapplesoft.meil_backend.models.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "SELECT * FROM Transaction t WHERE t.fromprojectid = :projectId", nativeQuery = true)
    List<Transaction> findByProjectId(@Param("projectId") Long projectId);

    @Query(value = "SELECT * FROM Transaction t WHERE t.fromprojectid = :projectId AND t.actiontypeid = :actionTypeId", nativeQuery = true)
    List<Transaction> findByProjectIdAndActionId(@Param("projectId") Long projectId, @Param("actionTypeId") Integer actionTypeId);

    @Query(value = "SELECT * FROM Transaction t WHERE t.fromprojectid = :projectId AND t.hsecoordid = :employeeId", nativeQuery = true)
    List<Transaction> findByProjectIdAndEmployeeId(@Param("projectId") Long projectId, @Param("employeeId") Long employeeId);

    @Query(value = "SELECT * FROM Transaction t WHERE t.fromprojectid = :projectId AND t.hsecoordid = :employeeId AND t.projectsite = :projectSiteId", nativeQuery = true)
    List<Transaction> findByProjectIdAndEmployeeIdAndProjectSiteId(@Param("projectId") Long projectId, @Param("employeeId") Long employeeId, @Param("projectSiteId") Long projectSiteId);
}
