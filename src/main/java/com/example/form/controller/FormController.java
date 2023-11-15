package com.example.form.controller;

import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.form.entity.Details;
import com.example.form.service.FormService;

@RestController
@RequestMapping("api/form")
public class FormController {

	@Autowired
	private FormService formService;

	/**
	 * The below method is used to save the MultiPart file and it's details.
	 * 
	 * @param values to get the details entity
	 * @param files  to get the MultiPart file
	 * @return saved MultiPart file details
	 * @throws Exception if error occurs
	 */
	@RequestMapping(value = "create/1", method = RequestMethod.POST)
	public Details create(@ModelAttribute Details values, @RequestParam MultipartFile[] files) throws Exception {
		return formService.create(values, files);
	}
	
	/**
	 * The below method is used to read the MultiPart file and it's details of unique user.
	 * 
	 * @param value of unique user identification
	 * @return MultiPart file details of unique user
	 */
	@RequestMapping("read/{email}")
	public List<Details> read(@PathVariable String email) {
		return formService.read(email);
	}
    
	/**
	 * The below method is used to read the MultiPart file and it's details of all user.
	 * 
	 * @return MultiPart file details of all user
	 */
	@RequestMapping("read")
	public List<Details> readAll() {
		return formService.readAll();
	}
	
	

}
