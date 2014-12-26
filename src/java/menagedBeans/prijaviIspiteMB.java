/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menagedBeans;

import domen.Ispit;
import domen.Student;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class prijaviIspiteMB {

    Student tekuciStudent;
    
    List<Ispit> lista;
    
    Ispit izabraniIspit;

    public Ispit getIzabraniIspit() {
        return izabraniIspit;
    }

    public void setIzabraniIspit(Ispit izabraniIspit) {
        this.izabraniIspit = izabraniIspit;
    }

    public List<Ispit> getLista() {
        return lista;
    }

    public void setLista(List<Ispit> lista) {
        this.lista = lista;
    }
    
    @ManagedProperty(value = "#{sesijaMB}")
    SesijaMB sesija;

    public Student getTekuciStudent() {
        return tekuciStudent;
    }

    public void setTekuciStudent(Student tekuciStudent) {
        this.tekuciStudent = tekuciStudent;
    }

    public SesijaMB getSesija() {
        return sesija;
    }

    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }
    
    /**
     * Creates a new instance of prijaviIspite
     */
    public prijaviIspiteMB() {
        
    }
    
    @PostConstruct
    public void inicijalizuj() {
        Student s = sesija.getStudentZaIzmenu();
        tekuciStudent = s;
        System.out.println(tekuciStudent.getIme()+" je i dalje u sesiji");
        sesija.setStudentZaIzmenu(new Student());
        System.out.println(tekuciStudent.getIme()+" vise ne bi trebalo da je u sesiji");
        lista = Kontroler.getInstance().vratiSveIspite();
    }
    
    public void prijaviIspit() {
        System.out.println(tekuciStudent.getIme()+"ime studenta koji je u sesiji");
        if (Kontroler.getInstance().prijaviIspit(izabraniIspit, tekuciStudent, sesija.ulogovaniSluzbenik)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ispit je prijavljen", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Doslo je do greske, ispit nije prijavljen", ""));
            
        }
    }
}
