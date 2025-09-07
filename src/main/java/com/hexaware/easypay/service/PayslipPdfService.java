package com.hexaware.easypay.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.hexaware.easypay.entity.PayoutCalc;
import com.hexaware.easypay.entity.Payslip;
import com.hexaware.easypay.exception.DefaultException;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PayslipPdfService {

    public byte[] generatePayslip(PayoutCalc payout, Payslip payslip) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             Document document = new Document()) {

            PdfWriter.getInstance(document, out);
            document.open();

            BaseFont font;
            try {
                font = BaseFont.createFont(
                    "src/main/resources/fonts/Outfit-Medium.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
                );
            } catch (IOException e) {
                throw new DefaultException("Font loading failed.");
            }

            Font titleFont = new Font(font, 16, Font.BOLD);
            Font paraFont = new Font(font, 12, Font.NORMAL);

            // Title
            Paragraph title = new Paragraph("Employee Payslip", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Employee Info
            document.add(new Paragraph("Employee ID: " + payout.getEmpId(), paraFont));
            if (payout.getEmployee() != null) {
                document.add(new Paragraph("Name: " + payout.getEmployee().getName(), paraFont));
                document.add(new Paragraph("Department: " + payout.getEmployee().getDept(), paraFont));
                document.add(new Paragraph("Email: " + payout.getEmployee().getEmail(), paraFont));
            }
            document.add(new Paragraph("\n"));


            document.add(new Paragraph("Base Salary: ₹" + payout.getBase(), paraFont));
            document.add(new Paragraph("HRA: ₹" + payout.getHra(), paraFont));
            document.add(new Paragraph("TA: ₹" + payout.getTa(), paraFont));
            document.add(new Paragraph("Other Allowances: ₹" + payout.getOa(), paraFont));
            document.add(new Paragraph("Gross Salary: ₹" + payout.getGross(), paraFont));
            document.add(new Paragraph("Provident Fund: ₹" + payout.getPf(), paraFont));
            document.add(new Paragraph("Income Tax: ₹" + payout.getIncomeTax(), paraFont));
            document.add(new Paragraph("Professional Tax: ₹" + payout.getProfTax(), paraFont));
            document.add(new Paragraph("Monthly Payout: ₹" + payout.getMonthly(), paraFont));
            document.add(new Paragraph("Daily Payout: ₹" + payout.getDaily(), paraFont));
            document.add(new Paragraph("\n"));


            document.add(new Paragraph("Benefits Included: ₹" + payslip.getBenefits(), paraFont));
            document.add(new Paragraph("Actual Monthly Payout: ₹" + payslip.getActualMonthlyPayout(), paraFont));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("This document is system-generated and does not require a signature.", paraFont));

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new DefaultException("Payslip PDF generation failed.");
        }
    }
}