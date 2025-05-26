package com.project.repository;

import com.project.model.PlayerStats;
import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import static com.project.config.SQLQuery.GET_BY_USER_ID;

@Repository
public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {
    @Query(GET_BY_USER_ID)
    Optional<PlayerStats> findByUserId(@Param("userId") Long userId);

}
