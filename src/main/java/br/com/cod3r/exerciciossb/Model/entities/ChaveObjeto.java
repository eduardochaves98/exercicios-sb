package br.com.cod3r.exerciciossb.Model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
public class ChaveObjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String URA;
    private String CLIENTES;
    private Date DATA_MODIFICACAO;
    private String NOME;
    @NotBlank
    private String STATUS;
    private String OBJETO;

    public ChaveObjeto(String URA, String CLIENTES, String NOME, String STATUS, String OBJETO) {
        this.URA = URA;
        this.CLIENTES = CLIENTES;
        this.NOME = NOME;
        this.STATUS = STATUS;
        this.OBJETO = OBJETO;
    }

    public ChaveObjeto() {

    }

    public ChaveObjeto(Object consultaChave) {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getURA() {
        return URA;
    }

    public void setURA(String URA) {
        this.URA = URA;
    }

    public String getCLIENTES() {
        return CLIENTES;
    }

    public void setCLIENTES(String CLIENTES) {
        this.CLIENTES = CLIENTES;
    }

    public Date getDATA_MODIFICACAO() {
        return DATA_MODIFICACAO;
    }

    public void setDATA_MODIFICACAO(Date DATA_MODIFICACAO) {
        this.DATA_MODIFICACAO = DATA_MODIFICACAO;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }



    public String getOBJETO() {
        return OBJETO;
    }

    public void setOBJETO(String OBJETO) {
        this.OBJETO = OBJETO;
    }
}
