package com.intuit.craft.demo.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends PagingAndSortingRepository<Enduser, Integer> {

	@Query("SELECT u FROM Enduser u WHERE u.name = :name")
    Enduser findUserByNameNamedParams(@Param("name") String name);

	//@Query(value = "SELECT * FROM Enduser ORDER BY id", countQuery = "SELECT count(*) FROM Enduser", nativeQuery = true)
	@Query(value = "SELECT u FROM Enduser u")
	Page<Enduser> findAllUsersWithPaginationNative(Pageable pageable);
}
