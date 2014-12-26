/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menagedBeans;

import domen.Mesto;
import domen.Student;
import java.util.ArrayList;
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
public class vratiSveStudenteMB {

    List<Student> lista;

    @ManagedProperty(value = "#{sesijaMB}")
    SesijaMB sesija;

    Student studentZaizmenu;

    Student studentZaPretragu;

    List<Mesto> listaMesta;

    public Student getStudentZaPretragu() {
        return studentZaPretragu;
    }

    public void setStudentZaPretragu(Student studentZaPretragu) {
        this.studentZaPretragu = studentZaPretragu;
    }

    public Student getStudentZaizmenu() {
        return studentZaizmenu;
    }

    public void setStudentZaizmenu(Student studentZaizmenu) {
        this.studentZaizmenu = studentZaizmenu;
    }

    public SesijaMB getSesija() {
        return sesija;
    }

    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }

    public List<Student> getLista() {
        return lista;
    }

    public void setLista(List<Student> lista) {
        this.lista = lista;
    }

    /**
     * Creates a new instance of vratiSveStudenteMB
     */
    public vratiSveStudenteMB() {
        System.out.println("konstruktor");
        lista = new ArrayList<>();
        studentZaizmenu = new Student();
        studentZaPretragu = new Student();
    }

    public List<Mesto> getListaMesta() {
        return listaMesta;
    }

    public void setListaMesta(List<Mesto> listaMesta) {
        this.listaMesta = listaMesta;
    }

    @PostConstruct
    public void inicijalizuj() {

        lista = Kontroler.getInstance().vratiSveStudente();
        setListaMesta(Kontroler.getInstance().vratiSvaMesta());

        System.out.println("post konstruktor");
    }

    public String izmeniStudenta() {
        if (studentZaizmenu == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali studenta", ""));
            return null;
        }
        sesija.setStudentZaIzmenu(studentZaizmenu);
        return "/radSaStudentima/noviStudent";
    }

    public void obrisiStudenta() {
        if (studentZaizmenu == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali studenta", ""));
        }
        Student s = studentZaizmenu;
        studentZaizmenu = new Student();
        if (Kontroler.getInstance().obrisiStudenta(s)) {
            lista = Kontroler.getInstance().vratiSveStudente();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student je obrisan", ""));
            
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "doslo je do greske, student nije obrisan", ""));
            
        }
    }

    public String polozeniIspiti() {
        if (studentZaizmenu == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali studenta", ""));
            return null;
        }
        sesija.setStudentZaIzmenu(studentZaizmenu);
        return "/radSaIspitima/prikaziPolozeneIspite";
    }

    public String prijaviIspite() {
        if (studentZaizmenu == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Niste izabrali studenta", ""));
            return null;
        }
        sesija.setStudentZaIzmenu(studentZaizmenu);
        System.out.println(sesija.studentZaIzmenu.getIme() + " je ubacen u sesiju");
        return "/radSaIspitima/prijaviIspite";
    }

    public void nadjiStudenta() {
        try {
            List<Student> listaStud = new ArrayList<>();
            System.out.println("JMBG za pretragu je:" + studentZaPretragu.getJmbg());
            if (!studentZaPretragu.getJmbg().equals("")) {
                Student trazeni = Kontroler.getInstance().nadjiStudenta(studentZaPretragu);
                if(trazeni!=null){
                    listaStud.add(trazeni);
                }else{
                    listaStud=null;
                }
                
            } else {
                if (studentZaPretragu.getIme().equals("")) {
                    studentZaPretragu.setIme(null);
                }

                if (studentZaPretragu.getPrezime().equals("")) {
                    studentZaPretragu.setPrezime(null);
                }
                System.out.println("uradio je sta treba");
                listaStud = Kontroler.getInstance().nadjiStudente(studentZaPretragu);
            }
            
            if(listaStud==null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne moze da nadje studente po zadatim vrednostima", ""));
            }else{
                lista = listaStud;
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sistem ne moze da nadje studente po zadatim vrednostima", ""));
            
        }

    }
}
