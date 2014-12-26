/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menagedBeans;

import domen.HibernateUtil;
import domen.Sluzbenik;
import domen.Student;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import kontroler.Kontroler;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

/**
 *
 * @author user
 */
@ManagedBean
@RequestScoped
public class SluzbenikMB {

    Sluzbenik tekuciSluzbenik;

    @ManagedProperty(value = "#{sesijaMB}")
    SesijaMB sesija;
    
    public Sluzbenik getTekuciSluzbenik() {
        return tekuciSluzbenik;
    }

    public void setTekuciSluzbenik(Sluzbenik tekuciSluzbenik) {
        this.tekuciSluzbenik = tekuciSluzbenik;
    }
    
    /**
     * Creates a new instance of SluzbenikMB
     */
    public SluzbenikMB() {
        System.out.println("kostruktor");
        tekuciSluzbenik = new Sluzbenik();
    }
    
    public String login (){
        Sluzbenik s = Kontroler.getInstance().prijaviSluzbenika(tekuciSluzbenik);
       if (s==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Korisnik sa tim podacima ne postoji u sistemu!!!!", ""));
            return null;
        }
        
        
        tekuciSluzbenik = s;
        sesija.setUlogovaniSluzbenik(tekuciSluzbenik);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Korisnik je uspesno prijavljen!!!!", ""));
        return "pocetna";
    }

    public SesijaMB getSesija() {
        return sesija;
    }

    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }
}
