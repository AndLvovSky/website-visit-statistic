package com.andlvovsky.wvs.repository;

import com.andlvovsky.wvs.entity.ApiKeyEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKeyEntity, Long> {
}
