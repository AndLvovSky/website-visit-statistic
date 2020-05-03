package com.andlvovsky.wvs.repository;

import com.andlvovsky.wvs.entity.VisitEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
}
