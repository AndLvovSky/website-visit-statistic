package com.andlvovsky.wvs.statistics;

import com.andlvovsky.wvs.entity.VisitEntity;
import com.andlvovsky.wvs.meta.Device;
import com.andlvovsky.wvs.repository.VisitRepository;
import com.andlvovsky.wvs.utils.PostgresqlDbBaseTest;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.spring.api.DBRider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;

@DBRider
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VisitRepositoryIntegrationTest extends PostgresqlDbBaseTest {
  @Autowired
  protected DataSource dataSource;

  @SuppressWarnings("unused")
  public ConnectionHolder connectionHolder = () -> dataSource.getConnection();

  @Autowired
  private VisitRepository visitRepository;

  @BeforeEach
  public void beforeEach() {
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("john_doe", "password");
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

  @Test
  @DataSet(executeScriptsBefore = "db-cleanup.sql",
      strategy = SeedStrategy.INSERT,
      value = "datasets/visits.yml")
  @DisplayName("should return visits by time between")
  void shouldReturnVisitsByTimeBetween() {
    // given
    Long siteId = 1001L;
    LocalDateTime startTime = LocalDateTime.of(2021, 4, 10, 0, 0, 0);
    LocalDateTime endTime = LocalDateTime.of(2021, 4, 25, 0, 0, 0);

    // when
    List<VisitEntity> visits = visitRepository.findBySiteIdAndTimeBetween(siteId, startTime, endTime);

    // then
    assertThat(visits.size()).isEqualTo(2);

    assertThat(visits.get(0))
        .returns(1001L, from(VisitEntity::getId))
        .returns(1001L, from(visit -> visit.getSite().getId()))
        .returns("US", from(VisitEntity::getCountry))
        .returns(Device.PC, from(VisitEntity::getDevice))
        .returns("95.202.130.78", from(VisitEntity::getIp))
        .returns(ZonedDateTime.of(
            LocalDateTime.of(2021, 4, 22, 9, 0, 0),
            ZoneId.of("UTC")),
            from(VisitEntity::getActualTime)
        )
        .returns("twitter.com", from(VisitEntity::getReferralWebsite))
        .returns("v2", from(VisitEntity::getWebsiteVersion))
        .returns("/", from(VisitEntity::getPath));

    assertThat(visits.get(1))
        .returns(1003L, from(VisitEntity::getId))
        .returns(1001L, from(visit -> visit.getSite().getId()))
        .returns("US", from(VisitEntity::getCountry))
        .returns(Device.MOBILE, from(VisitEntity::getDevice))
        .returns("91.202.130.78", from(VisitEntity::getIp))
        .returns(ZonedDateTime.of(
            LocalDateTime.of(2021, 4, 15, 17, 0, 0),
            ZoneId.of("UTC")),
            from(VisitEntity::getActualTime)
        )
        .returns("twitter.com", from(VisitEntity::getReferralWebsite))
        .returns("v2", from(VisitEntity::getWebsiteVersion))
        .returns("/", from(VisitEntity::getPath));
  }
}
