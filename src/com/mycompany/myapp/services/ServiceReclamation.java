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
import com.mycompany.myapp.entities.Reclamation;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author med
 */
public class ServiceReclamation {
     public ArrayList<Reclamation> reclamations;
     
     public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
    
    
    public boolean addReclamation(Reclamation t) {
       
        System.out.println("********");
       //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url = Statics.BASE_URL +"new?object=" + t.getObject()+"&description=" +t.getDescription()+"&typeReclamation=" + t.getTypeReclamation();
    
       req.setUrl(url);
        System.out.println(t);
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

     
     
     public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            reclamations=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 String object = obj.get("object").toString();
                t.setObject(object);
                String desc = obj.get("description").toString();
                t.setDescription(desc);
                String typeRec = obj.get("typeReclamation").toString();
                t.setTypeReclamation(typeRec);
                String status = obj.get("status").toString();
                t.setStatus(status);
                String recDate = (String) (obj.get("reclamationDate"));
                t.setDateReclamation(recDate);
                
                reclamations.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return reclamations;
    }
     
     public ArrayList<Reclamation> getAllReclamations() {
        req = new ConnectionRequest();
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL + "rec/api/liste";
        System.out.println("===>" + url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
     
      public boolean  Delete(Reclamation p){
       String url = Statics.BASE_URL + "deleterecApi/" +p.getId();
  
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
      
      public boolean modifierRec(Reclamation t) {
        String url = Statics.BASE_URL +"editr/"+t.getId()+"object=" + t.getObject()+"&description=" +t.getDescription()+"&typeReclamation=" + t.getTypeReclamation();
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
      
      public Reclamation Detailrec( int idProduit , Reclamation t) {
        
        String url = Statics.BASE_URL+"/rec/api/det=?"+idProduit;
        req.setUrl(url);
        
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                 String object = obj.get("object").toString();
                t.setObject(object);
                String desc = obj.get("description").toString();
                t.setDescription(desc);
                String typeRec = obj.get("typeReclamation").toString();
                t.setTypeReclamation(typeRec);
                String status = obj.get("status").toString();
                t.setStatus(status);
                String recDate = (String) (obj.get("reclamationDate"));
                t.setDateReclamation(recDate);
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

              return t;
        
        
    }
      
      public boolean  Response(Reclamation p){
       String url = Statics.BASE_URL + "reponse/treat/" +p.getId();
  
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
    
    
}
