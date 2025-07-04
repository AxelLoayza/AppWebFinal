package pe.edu.tecsup.prj_crud_spring_boot.views;


import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Curso;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("curso/ver")
public class CursoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Curso> cursos = (List<Curso>) model.get("cursos");

        PdfPTable tabla = new PdfPTable(1); // Una columna
        tabla.setSpacingAfter(20);

        PdfPCell cell =null;

        cell = new PdfPCell(new Phrase("Lista de Cursos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(3);
        //tabla2.setWidths(new float[]{3.5f, 1, 1,1});
        tabla2.addCell("ID");
        tabla2.addCell("Nombre");
        tabla2.addCell("Créditos");

        for (Curso curso : cursos) {
            tabla2.addCell(""+curso.getId());
            tabla2.addCell(curso.getNombre());
            tabla2.addCell(""+curso.getCreditos());
        }

        document.add(tabla);
        document.add(tabla2);
    }
}