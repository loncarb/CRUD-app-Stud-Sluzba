/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menagedBeans;

import domen.Polozenispit;
import domen.Student;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class polozeniIspitiMB {

    List<Polozenispit> lista;
    
    @ManagedProperty(value = "#{sesijaMB}")
    SesijaMB sesija;

    public List<Polozenispit> getLista() {
        return lista;
    }

    Polozenispit ispit;

    public SesijaMB getSesija() {
        return sesija;
    }

    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }

    public Polozenispit getIspit() {
        return ispit;
    }

    public void setIspit(Polozenispit ispit) {
        this.ispit = ispit;
    }
    
    public void setLista(List<Polozenispit> lista) {
        this.lista = lista;
    }
    
    /**
     * Creates a new instance of polozeniIspitiMB
     */
    public polozeniIspitiMB() {
        lista = new ArrayList<>();
    }
    
    @PostConstruct
    public void inicijalizuj () {
        Student s = sesija.getStudentZaIzmenu();
        lista = Kontroler.getInstance().vratiPolozeneIspite(s);
        for(Polozenispit p:lista){
                System.out.println(p.getIspit().getNaziv());
            }
        sesija.setStudentZaIzmenu(new Student());
        if(lista.isEmpty()){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student nema položenih ispita.", ""));
        }
    }
    
    public List<Polozenispit> vratiPolozene () {
        Student s = sesija.getStudentZaIzmenu();
        lista = Kontroler.getInstance().vratiPolozeneIspite(s);
        sesija.setStudentZaIzmenu(new Student());
        return lista;
    }
}
