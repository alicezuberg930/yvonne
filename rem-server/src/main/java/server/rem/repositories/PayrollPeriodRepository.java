package server.rem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import server.rem.entities.PayrollPeriod;

public interface PayrollPeriodRepository extends JpaRepository<PayrollPeriod, String>, JpaSpecificationExecutor<PayrollPeriod> {
    
}
