/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
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
public class UpdateBlog extends Form {
    Form current;

   public UpdateBlog(Blog p , Form previous) {
        setTitle("edit reclamation");
        setLayout(BoxLayout.y());
        TextField id = new TextField(String.valueOf(p.getId()), "blog id");
        Label label_title = new Label("Title");
        TextField tftitle = new TextField(p.getTitle()," title");
        Label label_desc = new Label("Description");
        TextField tfdesc = new TextField(p.getDescription()," description");
         Label photoLabel = new Label("Photo");
        Button selectPhoto = new Button("parcourir");
        TextField photoField = new TextField("", "Importer une photo", 10, TextArea.ANY);
        photoField.setEditable(false);
        selectPhoto.addActionListener((evt) -> {
            if (Dialog.show("Photo!", "une annonce avec des  photos est 10 fois plus visible", "app photo", "Gallerie") == false) {
                Display.getInstance().openGallery((e) -> {
                    if (e != null && e.getSource() != null) {
                        String file = (String) e.getSource();
                        System.out.println(file);
                        photoField.setText(file.substring(file.lastIndexOf('/') + 1));
                    }
                }, Display.GALLERY_IMAGE);
            } else {
                System.out.println("ici on va accerder à l'appareille photo");
            }
        });
        
        Container photoContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        photoContainer.add(photoLabel);
        photoContainer.add(photoField);
        photoContainer.add(selectPhoto);
        

        Button btnValider = new Button("edit blog");
        Button btnRet = new Button("Return");
        btnRet.addActionListener(e-> new ListBlog(previous).show() );

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 if ((tftitle.getText().length()==0) && (tfdesc.getText().length()==0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Blog p = new Blog(Integer.parseInt(id.getText()), tftitle.getText().toString(), tfdesc.getText().toString(),photoField.getText());
                        System.out.println(p.getId());
                        System.out.println("---------");
                        System.out.println(id.getText());
                        if (ServiceBlog.getInstance().modifierBlog(p)) {
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
        

        addAll(label_title, tftitle,label_desc,tfdesc,photoContainer, btnValider,btnRet);
       // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListReclamation(previous).showBack());
        // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> this.previous.showBack());

    }
}
