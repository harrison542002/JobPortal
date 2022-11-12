package com.jobs.repository;

import com.jobs.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("FROM Admin WHERE username=:username AND password=:password")
    public List<Admin> checkAdmin(@Param("username") String username,
                                  @Param("password") String password);

}
