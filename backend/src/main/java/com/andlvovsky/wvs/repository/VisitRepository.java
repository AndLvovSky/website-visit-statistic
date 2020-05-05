package com.andlvovsky.wvs.repository;

import com.andlvovsky.wvs.entity.VisitEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitRepository extends JpaRepository<VisitEntity, Long> {
  List<VisitEntity> findBySiteIdAndTimeBetween(Long siteId, LocalDateTime startTime, LocalDateTime endTime);
}
