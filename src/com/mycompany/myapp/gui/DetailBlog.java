/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author med
 */
public class DetailBlog extends Form {
     Form current;
    
    public DetailBlog(Blog p, Form previous) {
        setTitle("Detail Blog");
        setLayout(BoxLayout.yCenter());
        
        //ajout image
        
        //ajout informations
        Label title=new Label("Title : "+p.getTitle());
        Label description=new Label("Description : "+p.getDescription());
        Label date=new Label("Date : "+p.getCreatedAt());
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(200, 150, 0xffff0000), true);
                Image image = URLImage.createToStorage(enc, Statics.BOOK_IMG_URL +"/"+p.getImage(), Statics.BOOK_IMG_URL +"/"+p.getImage());
                System.out.println(Statics.BOOK_IMG_URL +p.getImage());
                ImageViewer img1 = new ImageViewer(image);

        addAll(img1,title,description,date);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListBlog(previous).showBack());

    }
}
