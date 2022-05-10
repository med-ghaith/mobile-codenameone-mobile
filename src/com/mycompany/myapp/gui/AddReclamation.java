/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import java.util.Date;

/**
 *
 * @author med
 */
public class AddReclamation extends Form {
    public AddReclamation(Form previous) {
        setTitle("Add a new reclamation");
        setLayout(BoxLayout.y());
        Label label_object = new Label("Object");
        TextField tfObject = new TextField("","reclamation object");
        Label label_type = new Label("Type");
        TextField tfType = new TextField("","reclamation type");
        Label label_desc = new Label("Description");
        TextField tfDesc = new TextField("","reclamation description");
        
        
        Button btnValider = new Button("Add reclamation");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfObject.getText().length()==0) && (tfDesc.getText().length()==0)&& (tfType.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Reclamation  p = new Reclamation (tfType.getText().toString(), tfObject.getText().toString(),tfDesc.getText().toString() );
                       
                        if( ServiceReclamation.getInstance().addReclamation(p))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(label_object,tfObject,label_desc,tfDesc,label_type,tfType,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
