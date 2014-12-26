/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import db.DatabaseBroker;
import domen.Ispit;
import domen.Mesto;
import domen.Polozenispit;
import domen.Proizvod;
import domen.Racun;
import domen.Sluzbenik;
import domen.Student;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Kontroler {

    private static Kontroler instance;
    private DatabaseBroker db;

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public Kontroler() {
        System.out.println("konstruktor kontroler");
        db = new DatabaseBroker();
    }

    public Sluzbenik prijaviSluzbenika(Sluzbenik tekuciSluzbenik) {
        Sluzbenik s = db.nadjiSluzbenika(tekuciSluzbenik);
        return s;
    }

    public boolean sacuvajStudenta(Student noviStudent) {
        return db.sacuvajStudenta(noviStudent);
    }

    public List<Student> vratiSveStudente() {
        List<Student> lista = db.vratiStudente(new Student());
        return lista;
    }

    public boolean obrisiStudenta(Student s) {
        try {
            if (db.obirsiStudenta(s)) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return false;
        }
    }

    public List<Polozenispit> vratiPolozeneIspite(Student s) {
        List<Polozenispit> lista = null;
        try {
            lista = db.vratiPolozeneIspite(s);
            for (Polozenispit p : lista) {
                System.out.println(p.getIspit().getNaziv());
            }
            return lista;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return null;
        }
    }

    public List<Ispit> vratiSveIspite() {
        List<Ispit> lista = db.vratiSveIspite();
        return lista;
    }

    public boolean prijaviIspit(Ispit i, Student s, Sluzbenik sl) {
        try {
            if (db.prijaviIspit(i, s, sl)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public List<Mesto> vratiSvaMesta() {
        try {
            List<Mesto> lista = db.vratiSvaMesta();
            return lista;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return null;
        }
    }

    public List<Proizvod> vratiSveProizvode() {
        try {
            List<Proizvod> lista = db.vratiSveProizvode();
            return lista;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return null;
        }
    }

    public Racun kreirajRacun(Sluzbenik sluzbenik) {
        try {
            int brojRacuna = db.vratiBrojRacuna();

            Racun r = db.kreirajRacun(brojRacuna, sluzbenik);
            System.out.println("ovde");
            return r;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return null;
        }
    }

    public boolean sacuvajRacun(Racun r) {
        try {
            if (db.sacuvajRacun(r)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return false;
        }
    }

    public List<Student> vratiStudente(Student s) {
        try {
            List<Student> lista = db.vratiStudente(s);
            return lista;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return null;
        }
    }
    
    public Student nadjiStudenta (Student s) {
        try {
            System.out.println("JMBG kontroler:" +s.getJmbg());
            Student lista = db.nadjiStudenta(s.getJmbg());
            return lista;
        } catch (Exception ex) {
            System.out.println("greska kontroler");
            return null;
        }
    }
    
    public List<Student> nadjiStudente (Student s){
        List<Student> lista = new ArrayList<>();
        try{
            lista = db.nadjiStudente(s);
            if(lista.isEmpty()){
                System.out.println("lista je prazna");
            }
            return lista;
        }catch(Exception ex){
            System.out.println("greska kontroler nadjiStudente()");
            ex.printStackTrace();
            return null;
        }
    }
    
}
