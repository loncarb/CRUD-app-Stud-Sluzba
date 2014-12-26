/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menagedBeans;

import domen.Mesto;
import domen.Student;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class StudentMB {

    Student noviStudent;

    List<Mesto> listaMesta;

    Mesto trenutnoMesto;

    public Mesto getTrenutnoMesto() {
        return trenutnoMesto;
    }

    public void setTrenutnoMesto(Mesto trenutnoMesto) {
        this.trenutnoMesto = trenutnoMesto;
    }
    
    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }
    
    public Student getNoviStudent() {
        return noviStudent;
    }

    public SesijaMB getSesija() {
        return sesija;
    }

    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }

    @ManagedProperty(value = "#{sesijaMB}")
    SesijaMB sesija;
    
    @ManagedProperty(value = "#{vratiSveStudenteMB}")
    vratiSveStudenteMB vratiSt;

    public vratiSveStudenteMB getVratiSt() {
        return vratiSt;
    }

    public void setVratiSt(vratiSveStudenteMB vratiSt) {
        this.vratiSt = vratiSt;
    }
    
    public void setNoviStudent(Student noviStudent) {
        this.noviStudent = noviStudent;
    }
    
    /**
     * Creates a new instance of StudentMB
     */
    public StudentMB() {
        noviStudent = new Student();
        trenutnoMesto = new Mesto();
        setListaMesta(Kontroler.getInstance().vratiSvaMesta());
    }
    
    @PostConstruct
    public void postaviStudenta(){
        if(sesija.getStudentZaIzmenu()==null){
             
        }else{
            noviStudent = sesija.getStudentZaIzmenu();
            trenutnoMesto = sesija.studentZaIzmenu.getMesto();
            sesija.setStudentZaIzmenu(new Student());
        }
        
    }
    
    public void sacuvajStudenta (){
        if(Kontroler.getInstance().sacuvajStudenta(noviStudent)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student je sacuvan", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doslo je do greske i student nije sacuvan", ""));
        }
    }
}
