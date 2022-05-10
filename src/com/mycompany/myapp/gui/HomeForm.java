/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Sheet;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.Reclamation;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
       
        
    }
    public HomeForm(Reclamation p) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        //product
        Button btnAddProd = new Button("Add Reclamation");
        Button btnListProd = new Button("List Reclamation");
        Button btnAddBlog = new Button("Add Blog");
       Button btnListblog = new Button("List Blogs");
       Button stat = new Button("stats");
       //category
       
        

        
        
        
        //product
        btnAddProd.addActionListener(e-> new AddReclamation(current).show());
        btnAddBlog.addActionListener(e-> new AddBlog(current).show());
        btnListProd.addActionListener(e-> new ListReclamation(current).show());
  btnListblog.addActionListener(e-> new ListBlog(current).show());
     
        
        //category
        
        addAll(btnAddProd,btnListProd,btnAddBlog,btnListblog);
        
        
    }
     public HomeForm(Blog p) {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        //product
        Button btnAddProd = new Button("Add Blog");
        Button btnListProd = new Button("List Blogs");
       
       //category
       
        

        
        
        
        //product
       // btnAddProd.addActionListener(e-> new AddReclamation(current).show());
        btnListProd.addActionListener(e-> new ListBlog(current).show());
  
     
        
        //category
        
        addAll(btnAddProd,btnListProd);
        
        
    }
     
    
}
