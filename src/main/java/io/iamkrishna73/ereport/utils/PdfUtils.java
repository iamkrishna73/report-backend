package io.iamkrishna73.ereport.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import io.iamkrishna73.ereport.entity.CitizenPlan;

import java.awt.Color;
import java.io.*;
import java.util.List;
import java.util.stream.Stream;

public class PdfUtils {
    public static ByteArrayInputStream downloadToPdf(List<CitizenPlan> plans) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Add Header
            Font fontHeader = FontFactory.getFont(FontFactory.TIMES_BOLD, 22);
            Paragraph para = new Paragraph("Citizen Plan Structure", fontHeader);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            // Create Table with 7 Columns
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Set Column Widths
            table.setWidths(new int[]{3, 4, 4, 4, 4, 4, 4});

            // Add Table Header
            Stream.of("ID", "Citizen Name", "Plan Name", "Plan Status", "Plan Start Date", "Plan End Date", "Benefits Amount")
                    .forEach(headerTitle -> {
                        PdfPCell header = new PdfPCell(new Phrase(headerTitle, FontFactory.getFont(FontFactory.TIMES_BOLD, 12)));
                        header.setBackgroundColor(Color.CYAN);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        header.setPadding(5);
                        table.addCell(header);
                    });

            // Add Table Rows
            for (CitizenPlan plan : plans) {
                table.addCell(createCell(String.valueOf(plan.getCitizenId()), Element.ALIGN_CENTER));
                table.addCell(createCell(plan.getCitizenName(), Element.ALIGN_CENTER));
                table.addCell(createCell(plan.getPlanName(), Element.ALIGN_CENTER));
                table.addCell(createCell(plan.getPlanStatus(), Element.ALIGN_CENTER));
                table.addCell(createCell(plan.getPlanStartDate() != null ? plan.getPlanStartDate() : "N/A", Element.ALIGN_CENTER));
                table.addCell(createCell(plan.getPlanEndDate() != null ? plan.getPlanEndDate() : "N/A", Element.ALIGN_CENTER));
                table.addCell(createCell(plan.getBenefitsAmount() != null ? String.valueOf(plan.getBenefitsAmount()) : "N/A", Element.ALIGN_CENTER));
            }
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static PdfPCell createCell(String content, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

}
