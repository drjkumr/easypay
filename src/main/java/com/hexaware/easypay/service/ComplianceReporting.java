package com.hexaware.easypay.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hexaware.easypay.entity.SalaryPolicy;
import com.hexaware.easypay.exception.DefaultException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

@Service
public class ComplianceReporting {

    public byte[] generateComplianceReport(Long policyId, SalaryPolicy salaryPolicy) {
       
            
       try (ByteArrayOutputStream out = new ByteArrayOutputStream();
     Document document = new Document()){

        PdfWriter.getInstance(document, out);

        document.open();

        BaseFont font;
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineWidth(0.5f);

        try {
            font = BaseFont.createFont(
            "src/main/resources/fonts/Outfit-Medium.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            }
             catch (IOException e) {
                throw new DefaultException("Internal font fetch error!");
            }

            Font titleFont = new Font(font,  16, Font.NORMAL);
            Font paraFont = new Font(font,  12, Font.NORMAL);

            //Title
             Paragraph title = new Paragraph("Complaince Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Chunk(lineSeparator));

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("The lastest compliance report for XYZ organization is: ", paraFont));

            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Policy ID:" + policyId, paraFont));

            document.add(new Paragraph("Base Percentage: " +  salaryPolicy.getBasePercent().multiply(BigDecimal.valueOf(100)) + "%", paraFont));

            document.add(new Paragraph("House Rent Allowance Percentage: " + salaryPolicy.getHraPercent().multiply(BigDecimal.valueOf(100)) + "%", paraFont));

            document.add(new Paragraph("Travel Allowance Percentage: " + salaryPolicy.getTaPercent().multiply(BigDecimal.valueOf(100)) + "%", paraFont));

            document.add(new Paragraph("Other Allowances Percentage: " + salaryPolicy.getOaPercent().multiply(BigDecimal.valueOf(100)) + "%", paraFont));

            document.add(new Paragraph("Providence Fund Percentage: " + salaryPolicy.getOaPercent().multiply(BigDecimal.valueOf(100)) + "%", paraFont));

            document.add(new Paragraph("Income Tax Percentage: " + salaryPolicy.getOaPercent().multiply(BigDecimal.valueOf(100)) + "%", paraFont));

            document.add(new Paragraph("Professional Tax (fixed): " + salaryPolicy.getProfTaxFixed(), paraFont));

            document.add(new Paragraph("Policy description: ", paraFont));

            document.add(new Paragraph("" + salaryPolicy.getPolicyDesc(), paraFont));

            document.add(new Chunk(lineSeparator));

            document.add(new Paragraph("Report generated on: " + LocalDateTime.now()));

            document.close();

            return out.toByteArray();

       }
    catch (Exception e) {
        throw new DefaultException("PDF could not be generated due to an internal error.");
    }



 




        

    }
    
}
