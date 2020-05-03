package com.andlvovsky.wvs.entity;

import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;

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
@Table(name = "site")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SiteEntity {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_seq")
  @SequenceGenerator(name = "site_seq", sequenceName = "site_seq", allocationSize = 1)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  private UserEntity user;

  @Column(name = "name", nullable = false)
  private String name;

  @CreationTimestamp
  @Column(name = "created_on", nullable = false)
  private ZonedDateTime createdOn;

  @Column(name = "link", nullable = false)
  private String link;
}
