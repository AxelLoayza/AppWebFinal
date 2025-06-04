package pe.edu.tecsup.prj_crud_spring_boot.domain.entitties;


import jakarta.persistence.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name= "auditoria")
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Integer id;
    private String tabla;
    @Column(name = "id_registro")
    private Integer idRegistro;
    private Date fecha;
    private String usuario;
    private String tipo;

    public Auditoria(String tabla, Integer idRegistro, Date fecha, String usuario, String tipo) {
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

}
