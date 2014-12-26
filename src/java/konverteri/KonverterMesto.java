/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konverteri;

import db.DatabaseBroker;
import domen.Mesto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author user
 */
@FacesConverter(value = "konvMesto")
public class KonverterMesto implements Converter{

    DatabaseBroker db;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        db = new DatabaseBroker();
        Mesto m = null;
        if (value!=null && !value.isEmpty()){
            
            int postanskiBroj = Integer.parseInt(value);
             m = db.vratiMesto(postanskiBroj);
        }
        System.out.println("Mesto "+m.getNaziv());
        return m;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        db = new DatabaseBroker();
        if (value instanceof Mesto){
            
            Mesto m = (Mesto) value;
            return String.valueOf(m.getPostanskiBroj());
        }
        return "";
    }
}
