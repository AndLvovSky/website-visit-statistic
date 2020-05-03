package com.andlvovsky.wvs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "api_key")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApiKeyEntity {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "api_key_seq")
  @SequenceGenerator(name = "api_key_seq", sequenceName = "api_key_seq", allocationSize = 1)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  private SiteEntity site;

  @Column(name = "key", nullable = false, unique = true)
  private String key;
}
