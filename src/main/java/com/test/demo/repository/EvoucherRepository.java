package com.test.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import com.test.demo.models.Evoucher;

public interface EvoucherRepository extends JpaRepository<Evoucher, Long>{

}
