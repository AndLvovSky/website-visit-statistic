package com.andlvovsky.wvs.entity;

import com.andlvovsky.wvs.meta.Device;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name = "visit")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class VisitEntity {
  @Id
  @ToString.Include
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_seq")
  @SequenceGenerator(name = "visit_seq", sequenceName = "visit_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  private SiteEntity site;

  @Column(name = "time", nullable = false)
  private LocalDateTime time;

  @Column(name = "device", nullable = false)
  @Enumerated(EnumType.STRING)
  private Device device;

  @Column(name = "country", nullable = false)
  private String country = "Ukraine";
}
