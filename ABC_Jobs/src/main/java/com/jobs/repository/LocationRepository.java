package com.jobs.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jobs.model.Location;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    @Query("SELECT local FROM Location local WHERE l_id = :id")
    public Location getLocation(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Location WHERE l_id =:l_id")
    public void deleteById(@Param("l_id") Integer l_id);

    @Modifying
    @Transactional
    @Query("UPDATE Location SET country =:country, city =:city WHERE l_id =:l_id")
    public Integer editProfile(@Param("country") String country,
                               @Param("city") String city,
                               @Param("l_id") Integer l_id);

}
