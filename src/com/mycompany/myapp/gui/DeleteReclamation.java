/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.mycompany.myapp.entities.Reclamation;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceReclamation;
/**
 *
 * @author med
 */
public class DeleteReclamation extends Form {
    public DeleteReclamation(Reclamation p, Form previous) {
        setTitle("delete Reclamation");
        setLayout(BoxLayout.yCenter());

        Label object=new Label("Libelle : "+p.getObject());
        Label desc=new Label("Categorie : "+p.getDescription());
        Label type=new Label("Description : "+p.getTypeReclamation());
        Label status=new Label("Prix : "+p.getStatus());
        Label date=new Label("Note : "+p.getDateReclamation());
        Button btnSubmit = new Button("Delete");
        Button btnret = new Button("return");
        
            btnret.addActionListener(e -> new HomeForm(p).show());
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
             
                    System.out.println(p.getId());
                    System.out.println("deleted Product");
                    
                    if (ServiceReclamation.getInstance().Delete(p)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
      
        });
        

        addAll(object,desc,type,status,date,btnSubmit,btnret);
      //  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
