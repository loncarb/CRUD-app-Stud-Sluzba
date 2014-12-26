/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package konverteri;

import db.DatabaseBroker;
import domen.Mesto;
import domen.Proizvod;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author user
 */
@FacesConverter(value = "konvProizvod")
public class KonvProizvod implements Converter{
    
    DatabaseBroker db;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        db = new DatabaseBroker();
        Proizvod p = null;
        if (value!=null && !value.isEmpty()){
            
            int sifra = Integer.parseInt(value);
             p = db.vratiProizvod(sifra);
        }
        return p;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        db = new DatabaseBroker();
        if (value instanceof Proizvod){
            
            Proizvod p = (Proizvod) value;
            
            if (p.getProizvodId()==null){
                return "";
            }
            return String.valueOf(p.getProizvodId());
        }
        return "";
    }
}
