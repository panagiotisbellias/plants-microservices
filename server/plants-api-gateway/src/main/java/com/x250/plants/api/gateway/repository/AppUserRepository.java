package com.x250.plants.api.gateway.repository;


import com.x250.plants.api.gateway.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByEmail(String email);
}
