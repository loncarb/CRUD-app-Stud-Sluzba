/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menagedBeans;

import domen.Proizvod;
import domen.Racun;
import domen.Stavkaracun;
import domen.StavkaracunId;
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
public class RacunMB {

    @ManagedProperty(value = "#{sesijaMB}")
    SesijaMB sesija;
    
    double ukupnoNaRacunu;

    List<Stavkaracun> listaStavki;

    public List<Stavkaracun> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<Stavkaracun> listaStavki) {
        this.listaStavki = listaStavki;
    }
    
    public double getUkupnoNaRacunu() {
        return ukupnoNaRacunu;
    }

    public void setUkupnoNaRacunu(double ukupnoNaRacunu) {
        this.ukupnoNaRacunu = ukupnoNaRacunu;
    }
    
    Racun tekuciRacun;
    
    Student tekuciStudent;

    public Student getTekuciStudent() {
        return tekuciStudent;
    }

    public void setTekuciStudent(Student tekuciStudent) {
        this.tekuciStudent = tekuciStudent;
    }
    
    Stavkaracun stavkaRacuna;
    Stavkaracun novaStavkaracun;

    List<Proizvod> listaProizvoda;

    Proizvod izabraniProizvod;

    public SesijaMB getSesija() {
        return sesija;
    }

    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }

    public Proizvod getIzabraniProizvod() {
        return izabraniProizvod;
    }

    public void setIzabraniProizvod(Proizvod izabraniProizvod) {
        this.izabraniProizvod = izabraniProizvod;
    }
    
    public List<Proizvod> getListaProizvoda() {
        return listaProizvoda;
    }

    public void setListaProizvoda(List<Proizvod> listaProizvoda) {
        this.listaProizvoda = listaProizvoda;
    }
    
    public Stavkaracun getStavkaRacuna() {
        return stavkaRacuna;
    }

    public void setStavkaRacuna(Stavkaracun stavkaRacuna) {
        this.stavkaRacuna = stavkaRacuna;
    }
    

    public Racun getTekuciRacun() {
        return tekuciRacun;
    }

    public void setTekuciRacun(Racun tekuciRacun) {
        this.tekuciRacun = tekuciRacun;
    }
    
    /**
     * Creates a new instance of RacunMB
     */
    public RacunMB() {
    }
    
    @PostConstruct
    public void inicijalizuj () {
        tekuciRacun = Kontroler.getInstance().kreirajRacun(sesija.getUlogovaniSluzbenik());
        tekuciRacun.setStavkaracuns(new ArrayList<Stavkaracun>());
        listaProizvoda = Kontroler.getInstance().vratiSveProizvode();
        tekuciStudent = new Student();
        stavkaRacuna = new Stavkaracun();
        novaStavkaracun = new Stavkaracun();
        novaStavkaracun.setProizvod(new Proizvod());
        ukupnoNaRacunu = 0;
        izabraniProizvod = new Proizvod();
        listaStavki = new ArrayList<>();
        System.out.println("post construct");
    }
    
    public void sacuvajRacun (){
        tekuciStudent = Kontroler.getInstance().nadjiStudenta(tekuciStudent);
        tekuciRacun.setStudent(tekuciStudent);
        if (Kontroler.getInstance().sacuvajRacun(tekuciRacun)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Racun je sacuvan", ""));
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Racun nije sacuvan", ""));
            
        }
    }

    public Stavkaracun getNovaStavkaracun() {
        return novaStavkaracun;
    }

    public void setNovaStavkaracun(Stavkaracun novaStavkaracun) {
        this.novaStavkaracun = novaStavkaracun;
    }
    
    
    public void ubaciStavku () {
        System.out.println("pre try catch");
        try{
       // novaStavkaracun.setProizvod(izabraniProizvod);
        System.out.println("proizvod je" +novaStavkaracun.getProizvod().getNaziv());
        novaStavkaracun.setCena(novaStavkaracun.getProizvod().getCena());
        novaStavkaracun.setRacun(tekuciRacun);
        System.out.println("kraj try catch");
        }catch (Exception ex){
            System.out.println("greska ubaciStavku()");
            ex.printStackTrace();
            
        }
        
        
        
        ukupnoNaRacunu = ukupnoNaRacunu + novaStavkaracun.getCena();
        tekuciRacun.setUkupanIznos(ukupnoNaRacunu);
        
        StavkaracunId stavkaID = new StavkaracunId();
        stavkaID.setRacun(tekuciRacun.getRacunId());
        stavkaID.setStavkaRacunaId(tekuciRacun.getStavkaracuns().size()+1);
        novaStavkaracun.setId(stavkaID);
        
        Stavkaracun stavkaZaListu = new Stavkaracun();
        stavkaZaListu.setCena(novaStavkaracun.getCena());
        stavkaZaListu.setId(novaStavkaracun.getId());
        stavkaZaListu.setIznosNaStavci(novaStavkaracun.getIznosNaStavci());
        stavkaZaListu.setKolicina(novaStavkaracun.getKolicina());
        stavkaZaListu.setProizvod(novaStavkaracun.getProizvod());
        stavkaZaListu.setRacun(novaStavkaracun.getRacun());
        
        listaStavki.add(stavkaZaListu);
        tekuciRacun.setStavkaracuns(listaStavki);

        
//        tekuciRacun.getStavkaracuns().add(stavkaZaListu);
//        for (Stavkaracun st: tekuciRacun.getStavkaracuns()){
//            System.out.println("stavka je "+st.getProizvod().getNaziv());
//        }
        System.out.println("sve uradjeno u ubaciStvarku()");
        
    }
    
    public void obrisiStavku () {
        if (tekuciRacun.getStavkaracuns().remove(stavkaRacuna)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stavka je obrisana", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Stavka nije obrisana", ""));
        }
    }
}
