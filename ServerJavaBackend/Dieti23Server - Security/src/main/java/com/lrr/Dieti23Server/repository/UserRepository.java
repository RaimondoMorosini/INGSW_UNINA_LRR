package com.lrr.Dieti23Server.repository;

import com.lrr.Dieti23Server.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utente,String> {

    @Query("SELECT u.password FROM Utente u WHERE u.email = ?1")
    String getPasswordByEmail(String email);


}
