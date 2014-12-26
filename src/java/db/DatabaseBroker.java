/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.HibernateUtil;
import domen.Ispit;
import domen.Mesto;
import domen.Polozenispit;
import domen.PolozenispitId;
import domen.Proizvod;
import domen.Racun;
import domen.Sluzbenik;
import domen.Stavkaracun;
import domen.StavkaracunId;
import domen.Student;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author user
 */
public class DatabaseBroker {

    public Sluzbenik nadjiSluzbenika(Sluzbenik s) {
//        try {
//            Session sesija = HibernateUtil.getSessionFactory().openSession();
//            sesija.beginTransaction();
//            Sluzbenik sluzbenikIzBaze = (Sluzbenik) sesija.get(Sluzbenik.class, s.getKorisnickoIme());
//            sesija.getTransaction().commit();
//            if(sluzbenikIzBaze!=null){
//                if (sluzbenikIzBaze.getKorisnickaSifra()==s.getKorisnickaSifra()){
//                    s=sluzbenikIzBaze;
//                }else{
//                    s=null;
//                }
//            }
//            if(sluzbenikIzBaze==null){
//                s=null;
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return s;
        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();
        Criteria crit = sesija.createCriteria(Sluzbenik.class);
        crit.add(Example.create(s));
        s = (Sluzbenik) crit.uniqueResult();
        sesija.getTransaction().commit();
        return s;
    }

    public List<Student> vratiStudente(Student s) {
        List<Student> lista = null;
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Criteria crit = sesija.createCriteria(Student.class);
            crit.add(Example.create(s));
            lista = crit.list();
            sesija.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public List<Student> nadjiStudente(Student s) {
        List<Student> lista = new ArrayList<>();
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Criteria crit = sesija.createCriteria(Student.class);
            crit.add(Example.create(s)).add( Restrictions.like("mesto", s.getMesto()) );
//            System.out.println("ime:" +s.getIme());
//            System.out.println("prezime"+ s.getPrezime());
//            System.out.println("mesto" +s.getMesto().getNaziv());
            lista = crit.list();
            sesija.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            System.out.println("greska nadjiStudente() DB");
            ex.printStackTrace();
            return null;
        }

    }

    public Student nadjiStudenta(String jmbg) {
        System.out.println("ovde1");
        Student trazeni = null;
        try {
            System.out.println(jmbg);
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
//            Criteria crit = sesija.createCriteria(Student.class);
//            crit.add(Example.create(s));
//            trazeni = (Student) crit.uniqueResult();
//            Student s = new Student();
//            s.setJmbg(jmbg);
//            
////            
//            String sql = "SELECT * FROM `studentskasluzba`.`student` WHERE CONCAT(`student`.`JMBG`) = " + jmbg;
//            Query query = sesija.createSQLQuery(sql);
//            trazeni = (Student) query.uniqueResult();
            trazeni = (Student) sesija.get(Student.class, jmbg);
            sesija.getTransaction().commit();
            return trazeni;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public boolean sacuvajStudenta(Student s) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.saveOrUpdate(s);
            sesija.getTransaction().commit();
            System.out.println("db obavio svoje");
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean izmeniStudenta(Student s) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            sesija.saveOrUpdate(s);
            sesija.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<Student> vratiSveStudente() {
        List<Student> lista = new ArrayList<>();
        try {

            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();

            Query query = sesija.createQuery("from Student");
            lista = query.list();
            sesija.getTransaction().commit();

            return lista;
        } catch (Exception ex) {
            System.out.println("greska DB vratiSveStudente()");
            ex.printStackTrace();
            return lista;
        }

    }

    public boolean obirsiStudenta(Student s) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();

            Criteria crit = sesija.createCriteria(Student.class);
            crit.add(Example.create(s));
            s = (Student) crit.uniqueResult();
            System.out.println(s.getJmbg() + " ovo je jmbg");
//            Criteria crit = sesija.createCriteria(Student.class).add( Restrictions.like("jmbg", s.getJmbg()) );
//            s = (Student) crit.uniqueResult();
//            System.out.println("student jmbg:"+ s.getJmbg());  sa ovim kodom iybacuje gresku koju sam opisao u mailu
            //            sesija.delete(s);

            String sql = "DELETE FROM `studentskasluzba`.`student` WHERE CONCAT(`student`.`JMBG`) = " + s.getJmbg();
            Query query = sesija.createSQLQuery(sql);
            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);
//            sa ovim kodom je problem jer nece da mi obrise prave maticne brojeve, on ih valjda drugacije vodi jer su float, npr studente kao sto su '1' hoce da obrise ali nece da obrise prave studente kao sto su Bogdan Lonca ili Jovan Jovanovic
            sesija.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return false;
        }
    }

    public List<Polozenispit> vratiPolozeneIspite(Student s) {
        List<Polozenispit> lista = null;
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            lista = sesija.createQuery("from Polozenispit as p WHERE p.ocena>5 AND p.student.jmbg=" + s.getJmbg()).list();
            sesija.getTransaction().commit();
            System.out.println("DB posle ispisanih ispita");
            return lista;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public List<Ispit> vratiSveIspite() {
        Session sesija = HibernateUtil.getSessionFactory().openSession();
        sesija.beginTransaction();
        List<Ispit> lista = null;
        Query query = sesija.createQuery("from Ispit");
        lista = query.list();
        sesija.getTransaction().commit();
        return lista;
    }

    public boolean prijaviIspit(Ispit i, Student s, Sluzbenik sl) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();

            Criteria crite = sesija.createCriteria(Sluzbenik.class);
            crite.add(Example.create(sl));
            sl = (Sluzbenik) crite.uniqueResult();

            Criteria crit = sesija.createCriteria(Student.class);
            crit.add(Example.create(s));
            s = (Student) crit.uniqueResult();
            System.out.println(s.getJmbg() + " ovo je jmbg");

            Polozenispit p = new Polozenispit();
            PolozenispitId pID = new PolozenispitId(i.getIspitId(), s.getJmbg(), sl.getKorisnickoIme());
            p.setId(pID);
            p.setIspit(i);
            p.setStudent(s);
            p.setSluzbenik(sl);
            p.setOcena(0);
            p.setPrijavljen(true);
            sesija.save(p);
            sesija.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println("greska DB");
            System.out.println(sl.getKorisnickoIme());
            ex.printStackTrace();
            return false;
        }
    }

    public Mesto vratiMesto(int postanskiBroj) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();

            Criteria crite;
            Mesto m = new Mesto();
            System.out.println("Postanski broj " + postanskiBroj);
            m.setPostanskiBroj(postanskiBroj);
            crite = sesija.createCriteria(Mesto.class).add(Restrictions.like("postanskiBroj", postanskiBroj));

            m = (Mesto) crite.uniqueResult();
//            m = (Mesto) crite.uniqueResult();
//            String sql = "FROM mesto WHERE postanskibroj = :PB";
//            Query quey = sesija.createQuery(sql);
//            quey.setInteger("PB", postanskiBroj);
//            Mesto m = (Mesto) quey.uniqueResult();
            sesija.getTransaction().commit();
            System.out.println("Mesto " + m.getNaziv());
            return m;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public List<Mesto> vratiSvaMesta() {
        List<Mesto> lista = null;
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("from Mesto");
            lista = query.list();
            sesija.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public List<Proizvod> vratiSveProizvode() {
        List<Proizvod> lista = null;
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("from Proizvod");
            lista = query.list();
            sesija.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public int vratiBrojRacuna() {
        List<Racun> lista = null;
        int broj = -1;
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Query query = sesija.createQuery("from Racun order by RacunID DESC");
            lista = query.list();
            if (lista.isEmpty()) {
                System.out.println("lista je prazna DB");
                return 1;
            }

            broj = lista.get(0).getRacunId();
            System.out.println("broj racuna je: " + broj);
            sesija.getTransaction().commit();
            return broj + 1;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return -1;
        }
    }

    public Racun kreirajRacun(int brojRacuna, Sluzbenik slubenik) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Racun r = new Racun();
            r.setRacunId(brojRacuna);
            r.setDatum(new Date());
            r.setSluzbenik(slubenik);
            sesija.saveOrUpdate(r);
            sesija.getTransaction().commit();
            return r;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }

    public boolean sacuvajRacun(Racun racun) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            for (Stavkaracun stavka : racun.getStavkaracuns()) {
////                sesija.save(stavka.getId());
//                Proizvod p = vratiProizvod(stavka.getProizvod().getProizvodId());
//                stavka.setProizvod(p);
//                sesija.save(stavka);
                sacuvajStavkuRacuna(stavka);
            }
            System.out.println("zavrsio sa stavkama racuna");
            racun.setStavkaracuns(null);
            sesija.saveOrUpdate(racun);
            sesija.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return false;
        }
    }

    public void sacuvajStavkuRacuna(Stavkaracun stavka) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();
            Proizvod p = vratiProizvod(stavka.getProizvod().getProizvodId());
            Stavkaracun stavkaZaCuvanje = new Stavkaracun();
            StavkaracunId stavkaID = new StavkaracunId();
            stavkaID.setRacun(stavka.getId().getRacun());
            System.out.println("id racuna je za stavku je " + stavka.getId().getRacun());
            stavkaID.setStavkaRacunaId(stavka.getId().getStavkaRacunaId());
            stavkaZaCuvanje.setId(stavkaID);
            stavkaZaCuvanje.setCena(stavka.getCena());
            stavkaZaCuvanje.setIznosNaStavci(stavka.getIznosNaStavci());
            stavkaZaCuvanje.setKolicina(stavka.getKolicina());
            stavkaZaCuvanje.setProizvod(p);
            stavkaZaCuvanje.setRacun(stavka.getRacun());

            sesija.saveOrUpdate(stavkaZaCuvanje);

            sesija.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println("greska pri cuvanju stavke");
            ex.printStackTrace();
        }

    }

    public Proizvod vratiProizvod(int sifra) {
        try {
            Session sesija = HibernateUtil.getSessionFactory().openSession();
            sesija.beginTransaction();

            Criteria crite = sesija.createCriteria(Proizvod.class);
//            Proizvod p = new Proizvod();
//            Proizvod rez;
//            p.setProizvodId(sifra);       
//            
//            crite.add(Example.create(p));
//            rez = (Proizvod) crite.uniqueResult();
            Proizvod rez = (Proizvod) sesija.get(Proizvod.class, sifra);
            sesija.getTransaction().commit();
            System.out.println("proizvod koji db vraca je " + rez.getNaziv());
            return rez;
        } catch (Exception ex) {
            System.out.println("greska DB");
            ex.printStackTrace();
            return null;
        }
    }
}
