package com.jobs.repository;

import com.jobs.model.Experience;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExperienceRepository extends CrudRepository<Experience, Integer> {

    @Query("SELECT exp FROM Experience exp WHERE user_profile_id =:id")
    public List<Experience> getExp(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query("UPDATE Experience SET company=:company, position=:position, country=:country, " +
            "start_data=:start_data, end_date=:end_date WHERE user_profile_id=:user_profile_id")
    public Integer updateExp(@Param("company") String company,
                      @Param("position") String position,
                      @Param("country") String country,
                      @Param("start_data") String start_data,
                      @Param("end_date") String end_date,
                      @Param("user_profile_id") Integer user_profile_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Experience WHERE user_profile_id=:id")
    public void deleteExp(@Param("id") Integer user_profile_id);
}
