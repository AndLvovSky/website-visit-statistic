package com.andlvovsky.wvs.entity;

import com.andlvovsky.wvs.meta.Device;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

  @Column(name = "device")
  @Enumerated(EnumType.STRING)
  private Device device;

  @Column(name = "country", nullable = false)
  private String country;

  @Column(name = "ip", nullable = false)
  private String ip;

  @CreationTimestamp
  @Column(name = "actual_time", nullable = false)
  private ZonedDateTime actualTime;

  @Column(name = "referral_website")
  private String referralWebsite;

  @Column(name = "website_version")
  private String websiteVersion;
}
