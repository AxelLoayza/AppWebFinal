package pe.edu.tecsup.prj_crud_spring_boot.views;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import pe.edu.tecsup.prj_crud_spring_boot.domain.entitties.Alumno;

import java.awt.Color;
import java.util.List;
import java.util.Map;

@Component("alumno/ver")
public class AlumnoPdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Alumno> alumnos = (List<Alumno>) model.get("alumnos");

        PdfPTable tabla = new PdfPTable(1); // Una columna
        tabla.setSpacingAfter(20);

        PdfPCell cell = new PdfPCell(new Phrase("Lista de Alumnos"));
        cell.setBackgroundColor(new Color(184, 218, 255));
        cell.setPadding(8f);
        tabla.addCell(cell);

        PdfPTable tabla2 = new PdfPTable(5); // Cinco columnas: ID, Código, Nombre, Apellido, Carrera, Ciclo
        tabla2.addCell("ID");
        tabla2.addCell("Código");
        tabla2.addCell("Nombre");
        tabla2.addCell("Apellido");
        tabla2.addCell("Carrera");
        tabla2.addCell("Ciclo");

        for (Alumno alumno : alumnos) {
            tabla2.addCell("" + alumno.getId());
            tabla2.addCell(alumno.getCodigo());
            tabla2.addCell(alumno.getNombre());
            tabla2.addCell(alumno.getApellido());
            tabla2.addCell(alumno.getCarrera());
            tabla2.addCell("" + alumno.getCiclo());
        }

        document.add(tabla);
        document.add(tabla2);
    }
}