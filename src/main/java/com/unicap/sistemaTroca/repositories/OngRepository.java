package com.unicap.sistemaTroca.repositories;

import com.unicap.sistemaTroca.models.Ong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OngRepository extends JpaRepository<Ong, Long> {



}
