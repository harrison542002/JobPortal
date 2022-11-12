package com.jobs.repository;

import com.jobs.model.Education;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EducationRepository extends CrudRepository<Education, Integer> {

    @Query("SELECT edu FROM Education edu WHERE user_profile_id=:id")
    public List<Education> getEducation(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Education SET school=:school, qualification=:qualification WHERE edu_id =:id")
    Integer updateEdu(@Param("school") String school,
                           @Param("qualification") String qualification,
                           @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Education WHERE user_profile_id=:id")
    public void deleteEdu(@Param("id") Integer user_profile_id);
}
