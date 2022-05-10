/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author med
 */
public class ListReclamation extends Form {
    public ListReclamation(Form previous) {
        setTitle("List Reclamations");
          setLayout(BoxLayout.yCenter());
               
    
       
        for (Reclamation p : ServiceReclamation.getInstance().getAllReclamations()) {
            Container c = new Container(BoxLayout.yCenter());
            
            MultiButton mb = new MultiButton("Object : " + p.getObject()+" Description : "+p.getDescription()+ "Type Relamation"+p.getTypeReclamation()+ "Status" +p.getStatus());
            mb.addActionListener(a -> new DetailReclamation(p, previous).show());
            //System.out.println(user.getId());
            Button update = new Button("Edit");

            update.addActionListener(e -> new UpdateReclamation(p, previous).show());
            Button delete = new Button("Delete");

            delete.addActionListener(e -> new DeleteReclamation(p, previous).show());
            
            Button response = new Button("Response");

            response.addActionListener(e -> new ResponseReclamation(p, previous).show());

            
            Container c2 = new Container(BoxLayout.xCenter());
            
            c2.add(delete);
            c2.add(update);
            c2.add(response);
            c.addAll(mb,c2);
            add(c);

        }
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        
//        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceProduct.getInstance().getAllProduct().toString());
//        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
