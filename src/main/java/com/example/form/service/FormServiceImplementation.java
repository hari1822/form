package com.example.form.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.mime.MimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import com.example.form.entity.Details;
import com.example.form.entity.FileE;

import com.example.form.exception.CustomException;
import com.example.form.logs.CustomLogs;
import com.example.form.repository.FileRepository;
import com.example.form.repository.FormRepository;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class FormServiceImplementation implements FormService {

    @Autowired
    FormRepository formRepository;
    
    @Autowired
    FileRepository fileRepository;

    @Value("${spring.files.location}")
    String location;

    @Value("${spring.files.allowed-types}")
    String[] extensions;
    
    @Value("${spring.servlet.multipart.max-file-size}")
    String size;

    CustomLogs logg = new CustomLogs();
    public static final Logger logging = Logger.getLogger("com.example.form.service.FormServiceImplementation");
    
    @Override
    public Details create(Details values, MultipartFile[] files) throws Exception {

        List<FileE> savedDetails = new ArrayList<FileE>();
        
        List<String> fileDetails = new ArrayList<String>();
        
       
      
        formRepository.save(values);
        
        for (MultipartFile file : files) {
        	int min =0;
            String filename = file.getOriginalFilename();
            Tika tika = new Tika();
            TikaConfig config = new TikaConfig();
            MimeType original = config.getMimeRepository().forName(tika.detect(file.getInputStream()));
            	
            	if (file.getSize()==min){
                logg.demoLog(logging,Level.SEVERE,"Field is empty");			
				throw new CustomException("Field is empty ");
			}

            if (!(FilenameUtils.isExtension(original.getExtension(), extensions))) {
            	fileDetails.add("Upload valid file type for: " + filename);
            	logg.demoLog(logging,Level.WARNING,"Extension mismatch for: " + filename);
            	continue;
            }

            FileE exisitingDetails = new FileE();

            exisitingDetails.setFilename(filename);
            exisitingDetails.setSize(file.getSize());
            exisitingDetails.setType(file.getContentType());
            exisitingDetails.setUuid(UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            exisitingDetails.setLink(location + filename);
            exisitingDetails.setUserid(values.getId());
            file.transferTo(new File(location + filename));
            savedDetails.add(exisitingDetails);
            logg.demoLog(logging, Level.INFO, filename+ " saved successfully");
            fileDetails.add( filename + " saved successfully");
        }      
        values.setFile(savedDetails);
        System.out.println(fileDetails);
        fileRepository.saveAll(values.getFile());
        logg.demoLog(logging, Level.INFO,"form saved successfully");
        return null;
    }

    @Override
    public List<Details> read(String email) {
    	logg.demoLog(logging, Level.INFO, "Method started to fetch");
        return formRepository.findByFileId(email);
    }

    @Override
    public List<Details> readAll() {
        return formRepository.findAll();
    }

	
}
