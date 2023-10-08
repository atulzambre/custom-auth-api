package com.custom.auth.service.impl;

import com.custom.auth.entity.User;
import com.custom.auth.repository.UserRepository;
import com.custom.auth.service.ExtractService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtractServiceImpl implements ExtractService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public XSSFWorkbook extractAllUserDetails() {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = xssfWorkbook.createSheet("user details");
        XSSFRow row = xssfSheet.createRow(0);

        //create header
        row.createCell(0).setCellValue("User Id");
        row.createCell(1).setCellValue("UserName");
        row.createCell(2).setCellValue("Password");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("IsActive");
        row.createCell(5).setCellValue("Created DateTime");
        row.createCell(6).setCellValue("Updated DateTime");
        row.createCell(7).setCellValue("User Roles");

        //add actual details
        List<User> userList = userRepository.findAll();

        for (int i = 0; i < userList.size(); i++) {
            row = xssfSheet.createRow(i + 1);
            User user = userList.get(i);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getUsername());
            row.createCell(2).setCellValue(user.getPassword());
            row.createCell(3).setCellValue(user.getEmail());
            row.createCell(4).setCellValue(user.getIsActive());
            row.createCell(5).setCellValue(user.getCreatedDateTime().toString());
            row.createCell(6).setCellValue(user.getUpdatedDateTime().toString());
            row.createCell(7).setCellValue(user.getRoles().toString());
        }
        return xssfWorkbook;
    }
}
