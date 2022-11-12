package com.jobs.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.jobs.model.UserProfile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Integer> {

    @Query("SELECT profile FROM UserProfile profile WHERE user_id=:id")
    public List<UserProfile> getProfile(@Param("id") Integer id);

    @Query("SELECT DISTINCT profile FROM UserProfile profile JOIN profile.location location WHERE CONCAT(firstName, lastName, location.city, location.country) LIKE %:query%")
    public List<UserProfile> getFromSearch(@Param("query") String query, Pageable pageable);
    
    @Query("SELECT DISTINCT profile FROM UserProfile profile WHERE CONCAT(firstName, lastName) LIKE %:query%")
    public List<UserProfile> getFromSearchForMessaging(@Param("query") String query);

    @Query("SELECT DISTINCT COUNT(profile) FROM UserProfile profile JOIN profile.location location WHERE CONCAT(firstName, lastName, location.city, location.country) LIKE %:query%")
    public Integer getTotal(@Param("query") String query);


    @Modifying
    @Transactional
    @Query("UPDATE UserProfile SET firstName=:firstName, lastName=:lastName WHERE user_profile_id=:user_profile_id")
    public Integer editProfile(@Param("firstName") String firstName,
                               @Param("lastName") String lastName,
                               @Param("user_profile_id") Integer user_profile_id);

    @Modifying
    @Transactional
    @Query("UPDATE UserProfile SET self_description =:self_description WHERE user_profile_id =:user_profile_id")
    public Integer editSelf_description(@Param("self_description") String self_description,
                               @Param("user_profile_id") Integer user_profile_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserProfile WHERE user_profile_id =:p_id")
    public void deleteById(@Param("p_id") Integer p_id);


}
