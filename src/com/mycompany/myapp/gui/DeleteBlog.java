/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceBlog;
import com.mycompany.myapp.services.ServiceReclamation;

/**
 *
 * @author med
 */
public class DeleteBlog extends Form {
    public DeleteBlog(Blog p, Form previous) {
        setTitle("delete Blog");
        setLayout(BoxLayout.yCenter());
        
        Label title=new Label("Title : "+p.getTitle());
        Label description=new Label("Description : "+p.getDescription());
        Label date=new Label("Date : "+p.getCreatedAt());
        Button btnSubmit = new Button("Delete");
        Button btnret = new Button("return");
        
            btnret.addActionListener(e -> new ListBlog(previous).show());
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
             
                    System.out.println(p.getId());
                    System.out.println("deleted Product");
                    
                    if (ServiceBlog.getInstance().Delete(p)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
      
        });
        

        addAll(title,description,date,btnSubmit,btnret);
      //  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
