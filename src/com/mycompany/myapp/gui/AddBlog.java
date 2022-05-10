/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
public class AddBlog extends Form {
    String path;
String  filePath;
Container imgCtn;
ImageViewer l = new ImageViewer();
    public AddBlog(Form previous) {
        setTitle("Add a new blog");
        setLayout(BoxLayout.y());
        Label label_title = new Label("Title");
        TextField tftitle = new TextField("","Title");
        Label label_desc = new Label("Description");
        TextField tfdesc = new TextField("","Description");
        Label label_image = new Label("Image");
        TextField tfimage = new TextField("","Image");
        Button getimage = new Button("get image");
  getimage.addActionListener(e->{
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if( evt!=null && evt.getSource()!=null){
                        path = (String)evt.getSource();
                        Image image = null;
                        try {
                            image = Image.createImage(FileSystemStorage.getInstance()
                                    .openInputStream(path)).fill(125, 175);
                            System.out.println(path);
                        } catch (Exception ex) {
                            Dialog.show("Error", ex.getMessage(), "OK", null);
                        }
                        l = new ImageViewer(image);
                        l.getStyle().setMarginLeft(40);
                        imgCtn.add(l);
                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        
        imgCtn = new Container();
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
        Button btnValider = new Button("Add blog");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftitle.getText().length()==0) && (tfdesc.getText().length()==0)&& (tfimage.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Blog  p = new Blog (tftitle.getText().toString(), tfdesc.getText().toString(),photoField.getText() );
                        System.out.println(path);
                        if( ServiceBlog.getInstance().addBlog(p))
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
        
        addAll(label_title,tftitle,label_desc,tfdesc,label_image,photoContainer,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
     
}
