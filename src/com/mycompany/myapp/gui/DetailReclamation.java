/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;

/**
 *
 * @author med
 */
public class DetailReclamation extends Form {
    Form current;
    
    public DetailReclamation(Reclamation p, Form previous) {
        setTitle("Detail reclamation");
        setLayout(BoxLayout.yCenter());
        
        //ajout image
        
        //ajout informations
        Label object=new Label("Object : "+p.getObject());
        Label description=new Label("Description : "+p.getDescription());
        Label type=new Label("Type : "+p.getTypeReclamation());
        Label Status=new Label("Status : "+p.getStatus());
        Label date=new Label("Date : "+p.getDateReclamation());

        addAll(object,description,type,Status,date);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListReclamation(previous).showBack());

    }
}
