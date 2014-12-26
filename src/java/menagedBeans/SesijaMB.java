/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package menagedBeans;

import domen.Sluzbenik;
import domen.Student;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author user
 */
@ManagedBean
@SessionScoped
public class SesijaMB {

    Sluzbenik ulogovaniSluzbenik;

    Student studentZaIzmenu;

    public Student getStudentZaIzmenu() {
        return studentZaIzmenu;
    }

    public void setStudentZaIzmenu(Student studentZaIzmenu) {
        this.studentZaIzmenu = studentZaIzmenu;
    }
    
    /**
     * Creates a new instance of SesijaMB
     */
    public SesijaMB() {
        studentZaIzmenu = new Student();
    }
    
    public Sluzbenik getUlogovaniSluzbenik() {
        return ulogovaniSluzbenik;
    }

    public void setUlogovaniSluzbenik(Sluzbenik ulogovaniSluzbenik) {
        this.ulogovaniSluzbenik = ulogovaniSluzbenik;
    }
}
