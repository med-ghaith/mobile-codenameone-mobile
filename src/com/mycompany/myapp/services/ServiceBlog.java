/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.BlogReview;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author med
 */
public class ServiceBlog {
    public ArrayList<Blog> blogs;

    public static ServiceBlog instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceBlog() {
        req = new ConnectionRequest();
    }

    public static ServiceBlog getInstance() {
        if (instance == null) {
            instance = new ServiceBlog();
        }
        return instance;
    }
    
    public boolean addBlog(Blog p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL +"blog/new/bl?title=" + p.getTitle()+"&description=" +p.getDescription()+"&image=" + p.getImage();
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public ArrayList<Blog> parseBlogs(String jsonText) {
        try {
            blogs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> productsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) productsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Blog p = new Blog();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                String title = obj.get("title").toString();
                p.setTitle(title);
                String desc = obj.get("description").toString();
                p.setDescription(desc);
                String image = obj.get("image").toString();
                p.setImage(image);
                int view = Integer.parseInt(obj.get("view").toString());
                p.setView((int) view);
                String createdAt = (String) (obj.get("createdAt"));
                p.setCreatedAt(createdAt);
                
                blogs.add(p);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return blogs;
    }
    
    public ArrayList<Blog> getAllBlogs() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "blog/api/liste";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                blogs = parseBlogs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return blogs;
    }
    
    public Blog Detailblog( int idProduit , Blog p) {
        
        String url = Statics.BASE_URL+"blog/api/det=?"+idProduit;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                String title = obj.get("title").toString();
                p.setTitle(title);
                String desc = obj.get("description").toString();
                p.setDescription(desc);
                String image = obj.get("image").toString();
                p.setImage(image);
                
            }catch(Exception ex) {
                System.out.println( ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return p;
        
        
    }
    
    public boolean  Delete(Blog p){
       String url = Statics.BASE_URL + "blog/deleteblApi/" +p.getId();
  
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

      
}
    
    public boolean modifierBlog(Blog t) {
        String url = Statics.BASE_URL +"blog/editbl/"+t.getId()+"?title=" + t.getTitle()+"&description=" +t.getDescription()+"&image=" + t.getImage();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
    
    public boolean addBlogReview(BlogReview p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL +"blog/addReview/"+p.getBlogId()+"?comment=" + p.getComment()+"&rate=" + p.getRate() ;
        System.out.println(url);
        //String url = Statics.BASE_URL + "create";

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
}
