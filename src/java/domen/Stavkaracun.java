package domen;
// Generated Sep 16, 2014 3:44:36 PM by Hibernate Tools 3.6.0



/**
 * Stavkaracun generated by hbm2java
 */
public class Stavkaracun  implements java.io.Serializable {


     private StavkaracunId id;
     private Racun racun;
     private Proizvod proizvod;
     private double cena;
     private int kolicina;
     private double iznosNaStavci;

    public Stavkaracun() {
    }

    public Stavkaracun(StavkaracunId id, Racun racun, Proizvod proizvod, double cena, int kolicina, double iznosNaStavci) {
       this.id = id;
       this.racun = racun;
       this.proizvod = proizvod;
       this.cena = cena;
       this.kolicina = kolicina;
       this.iznosNaStavci = iznosNaStavci;
    }
   
    public StavkaracunId getId() {
        return this.id;
    }
    
    public void setId(StavkaracunId id) {
        this.id = id;
    }
    public Racun getRacun() {
        return this.racun;
    }
    
    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    public Proizvod getProizvod() {
        return this.proizvod;
    }
    
    public void setProizvod(Proizvod proizvod) {
        this.proizvod = proizvod;
    }
    public double getCena() {
        return this.cena;
    }
    
    public void setCena(double cena) {
        this.cena = cena;
    }
    public int getKolicina() {
        return this.kolicina;
    }
    
    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }
    public double getIznosNaStavci() {
        return this.iznosNaStavci;
    }
    
    public void setIznosNaStavci(double iznosNaStavci) {
        this.iznosNaStavci = iznosNaStavci;
    }




}

