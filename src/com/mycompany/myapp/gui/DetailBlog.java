/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycompany.myapp.entities.Blog;
import com.mycompany.myapp.entities.BlogReview;
import com.mycompany.myapp.entities.Reclamation;
import com.mycompany.myapp.services.ServiceBlog;
import com.mycompany.myapp.utils.Statics;
import java.io.FileOutputStream;

/**
 *
 * @author med
 */
public class DetailBlog extends Form {
     Form current;
     
     private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
Slider starRank = new Slider();
private Slider createStarRankSlider() {
    
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    System.out.println(starRank);
    return starRank;
}


    
    public DetailBlog(Blog p, Form previous) {
        setTitle("Detail Blog");
        setLayout(BoxLayout.yCenter());
        Label label_title = new Label("Comment");
        TextField tftitle = new TextField("","Comment");
        Button btnValider = new Button("Add Review");
        //ajout image
        
        //ajout informations
        Label title=new Label("Title : "+p.getTitle());
        Label description=new Label("Description : "+p.getDescription());
        Label date=new Label("Date : "+p.getCreatedAt());
        EncodedImage enc = EncodedImage.createFromImage(Image.createImage(200, 150, 0xffff0000), true);
                Image image = URLImage.createToStorage(enc, Statics.BOOK_IMG_URL +"/"+p.getImage(), Statics.BOOK_IMG_URL +"/"+p.getImage());
                System.out.println(Statics.BOOK_IMG_URL +p.getImage());
                ImageViewer img1 = new ImageViewer(image);
                
  

      
                 btnValider.addActionListener(new ActionListener() {
                      BlogReview r = new BlogReview();
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                try {
                   
                      starRank.addDataChangedListener((type, index) -> {
           System.out.println(index/2);
            r = new BlogReview(p.getId(), index/2,tftitle.getText() );
           
       });
                      System.out.println(r);
                      r = new BlogReview(p.getId(), 2,tftitle.getText() );
                      if(ServiceBlog.getInstance().addBlogReview(r)){
           Dialog.show("Success","Connection accepted",new Command("OK"));
           } else {
           Dialog.show("ERROR", "Server error", new Command("OK"));
           };
                } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
              
            }
        });
                 
             Button pdf=new Button("télécharger");
             pdf.addActionListener(l->{
             Dialog.show("Succés", " Félicitation téléchargement éffectué", "OK", null);
         // System.out.println("good");
            createPDF();
             });
             
           

        addAll(img1,title,description,date, pdf);
        this.add(FlowLayout.encloseCenter(createStarRankSlider()));
        addAll(label_title, tftitle,btnValider);
       
       
       

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListBlog(previous).showBack());

    }
    
     public void createPDF() {
        try {
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("D:\\movies/listE.pdf"));
            document.open();
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addContent(Document document) throws DocumentException {
        Blog f=new Blog();
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        preface.add(new Paragraph("19-05-2021" + "                                          " + "PDF blog"));
        //addEmptyLine(preface, 1);
        preface.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        //preface.add(new Paragraph(
        //        "Report generated by: " + System.getProperty("user.name") + ", " + new Date(), smallBold));
        preface.add(new Paragraph("Blog DETAILS"));
        preface.add(new Paragraph("Blog title : Onyx \n"+f.getTitle()));
        preface.add(new Paragraph("Description : java \n"+f.getDescription()));
        preface.add(new Paragraph("created at  :  appilcation mobile\n"+f.getCreatedAt()));
        preface.add(new Paragraph("views  : infoematique \n"+f.getView()));
        

        addEmptyLine(preface, 2);
        addEmptyLine(preface, 8);

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
