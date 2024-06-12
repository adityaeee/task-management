package com.aditya.management.repository;

import com.aditya.management.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String > {
}
