package com.example.form.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.form.entity.Details;

@Repository
public interface FormRepository extends JpaRepository<Details, Integer> {

	
	@Query("SELECT details FROM Details details WHERE details.email = :email")
	List<Details>  findByFileId(String email);
	

}
