package com.andlvovsky.wvs.repository;

import com.andlvovsky.wvs.entity.SiteEntity;
import com.andlvovsky.wvs.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {
  @Query("select s from SiteEntity s inner join ApiKeyEntity k on s.id = k.site.id where k.key = :apiKey")
  Optional<SiteEntity> findByApiKey(@Param("apiKey") String apiKey);

  List<SiteEntity> findByUser(UserEntity user);
}
