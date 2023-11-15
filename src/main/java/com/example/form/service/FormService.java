package com.example.form.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.form.entity.Details;


public interface FormService {

	Details create(Details values, MultipartFile[] files) throws Exception;	
	List<Details> read(String email);	
	List<Details> readAll();

	
}
