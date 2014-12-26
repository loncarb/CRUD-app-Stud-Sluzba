package domen;
// Generated Sep 16, 2014 3:44:36 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Proizvod generated by hbm2java
 */
public class Proizvod implements java.io.Serializable {

    private Integer proizvodId;
    private Double cena;
    private String naziv;
    private Set<Stavkaracun> stavkaracuns = new HashSet<Stavkaracun>(0);

    public Proizvod() {
    }

    public Proizvod(int proizvodId, double cena, String naziv) {
        this.proizvodId = proizvodId;
        this.cena = cena;
        this.naziv = naziv;
    }

    public Proizvod(int proizvodId, double cena, String naziv, Set<Stavkaracun> stavkaracuns) {
        this.proizvodId = proizvodId;
        this.cena = cena;
        this.naziv = naziv;
        this.stavkaracuns = stavkaracuns;
    }

    public Integer getProizvodId() {
        return this.proizvodId;
    }

    public void setProizvodId(Integer proizvodId) {
        this.proizvodId = proizvodId;
    }

    public Double getCena() {
        return this.cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getNaziv() {
        return this.naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Set<Stavkaracun> getStavkaracuns() {
        return this.stavkaracuns;
    }

    public void setStavkaracuns(Set<Stavkaracun> stavkaracuns) {
        this.stavkaracuns = stavkaracuns;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.proizvodId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proizvod other = (Proizvod) obj;
        if (this.proizvodId != other.proizvodId) {
            return false;
        }
        return true;
    }

}