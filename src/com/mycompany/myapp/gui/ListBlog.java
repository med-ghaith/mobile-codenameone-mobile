/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceBlog;
import com.mycompany.myapp.services.ServiceReclamation;
import com.mycompany.myapp.utils.Statics;

/**
 *
 * @author med
 */
public class ListBlog extends Form {
    
    Resources theme = UIManager.initFirstTheme("/theme");
    public ListBlog(Form previous) {
        setTitle("List Blog");
          
               Container List = new Container (BoxLayout.y());
    
       
        for (Blog p : ServiceBlog.getInstance().getAllBlogs()) {
            Container c = new Container(BoxLayout.yCenter());
            
            MultiButton mb = new MultiButton("Title : " + p.getTitle()+" Description : "+p.getDescription());
            EncodedImage enc = EncodedImage.createFromImage(Image.createImage(200, 150, 0xffff0000), true);
                Image image = URLImage.createToStorage(enc, Statics.BOOK_IMG_URL +"/"+p.getImage(), Statics.BOOK_IMG_URL +"/"+p.getImage());
                System.out.println(Statics.BOOK_IMG_URL +p.getImage());
                ImageViewer img1 = new ImageViewer(image);
            mb.addActionListener(a -> new DetailBlog(p, previous).show());
            //System.out.println(user.getId());
            Button update = new Button("Update");

            update.addActionListener(e -> new UpdateBlog(p, previous).show());
            Button delete = new Button("Delete");

            delete.addActionListener(e -> new DeleteBlog(p, previous).show());

            
            Container c2 = new Container(BoxLayout.xCenter());
            c2.add(delete);
            c2.add(update);
            c.addAll(img1,mb,c2);
            add(c);

        }
        /* *** *SEARCHBAR* *** */
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
