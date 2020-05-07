package com.andlvovsky.wvs.repository;

import com.andlvovsky.wvs.entity.ApiKeyEntity;
import com.andlvovsky.wvs.entity.SiteEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {
  Optional<ApiKeyEntity> findBySite(SiteEntity site);
}
