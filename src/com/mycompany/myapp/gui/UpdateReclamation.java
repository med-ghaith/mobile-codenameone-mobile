/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
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

/**
 *
 * @author med
 */
public class UpdateReclamation extends Form {
    Form current;

   public UpdateReclamation(Reclamation p , Form previous) {
        setTitle("edit reclamation");
        setLayout(BoxLayout.y());
        TextField id = new TextField(String.valueOf(p.getId()), "reclamation id");
        Label label_object = new Label("Object");
        TextField tfObject = new TextField(p.getObject(),"reclamation object");
        Label label_desc = new Label("Description");
        TextField tfDesc = new TextField(p.getDescription(),"reclamation description");
        Label label_type = new Label("Type");
        TextField tfType = new TextField(p.getTypeReclamation(),"reclamation type");
        

        Button btnValider = new Button("edit reclamation");
        Button btnRet = new Button("Return");
        btnRet.addActionListener(e-> new ListReclamation(previous).show() );

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfObject.getText().length()==0) && (tfDesc.getText().length()==0)&& (tfType.getText().length()==0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Reclamation p = new Reclamation(Integer.parseInt(id.getText()), tfType.getText().toString(), tfObject.getText().toString(),tfDesc.getText().toString());
                        System.out.println(p.getId());
                        System.out.println("---------");
                        System.out.println(id.getText());
                        if (ServiceReclamation.getInstance().modifierRec(p)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
            
        });
        

        addAll(label_object, tfObject,label_desc,tfDesc,label_type,tfType, btnValider,btnRet);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListReclamation(previous).showBack());
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.previous.showBack());

    }
}
