/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceReclamation;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;



/**
 *
 * @author med
 */
public class ResponseReclamation extends Form {
    Form current;

   public ResponseReclamation(Reclamation p , Form previous) {
        setTitle("add Response");
        setLayout(BoxLayout.y());
        TextField id = new TextField(String.valueOf(p.getId()), "reclamation id");
        Label label_object = new Label("Object");
        TextField tfObject = new TextField();
        Label label_desc = new Label("Description");
        TextField tfDesc = new TextField();
        
        

        Button btnValider = new Button("response");
        Button btnRet = new Button("Return");
        btnRet.addActionListener(e-> new ListReclamation(previous).show() );

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfObject.getText().length()==0) && (tfDesc.getText().length()==0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                       // Mailing.send("mohamedghaith.talbi@esprit.tn",tfObject.getText() ,tfDesc.getText(), "", "");
                       Message m = new Message(tfDesc.getText());
                   // m.getAttachments().put(textAttachmentUri, "text/plain");
                     //Display.getInstance().sendMessage(new String[] {"mohamedghaith.talbi@esprit.tn"}, tfDesc.getText(), m);
                        System.out.println(p.getId());
                        System.out.println("---------");
                        System.out.println(id.getText());
                        if (ServiceReclamation.getInstance().Response(p)) {
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
        

        addAll(label_object, tfObject,label_desc,tfDesc, btnValider,btnRet);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListReclamation(previous).showBack());
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.previous.showBack());
        

    }
   
    
}
