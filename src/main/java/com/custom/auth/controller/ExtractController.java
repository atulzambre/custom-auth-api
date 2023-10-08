package com.custom.auth.controller;

import com.custom.auth.service.ExtractService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("admin/extract")
public class ExtractController {

    @Autowired
    private ExtractService extractService;

    @GetMapping("users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity extractAllUserDetails() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XSSFWorkbook xssfWorkbook = extractService.extractAllUserDetails();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "force-download"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=UserDetails.xlsx");
        xssfWorkbook.write(byteArrayOutputStream);
        xssfWorkbook.close();
        return new ResponseEntity(new ByteArrayResource(byteArrayOutputStream.toByteArray()),header,HttpStatus.OK);
    }

}
