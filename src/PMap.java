import java.awt.*;  //for Graphics

public class PMap
{
  public static void createMap(Display d)
  {
    Color c = new Color(0, 115, 230);
    double h = 2.5;
    
    //top left box
    d.shapes.add(new Wall(new Point(50,50), new Point(110,50), h, 1, c));
    d.shapes.add(new Wall(new Point(110,50), new Point(110,90), h, 1, c));
    d.shapes.add(new Wall(new Point(110,90), new Point(50,90), h, 1, c));
    d.shapes.add(new Wall(new Point(50,90), new Point(50,50), h, 1, c));
    
    //top middle left box
    d.shapes.add(new Wall(new Point(150,50), new Point(230,50), h, 1, c));
    d.shapes.add(new Wall(new Point(230,50), new Point(230,90), h, 1, c));
    d.shapes.add(new Wall(new Point(230,90), new Point(150,90), h, 1, c));
    d.shapes.add(new Wall(new Point(150,90), new Point(150,50), h, 1, c));
    
    
    //below top left box
    d.shapes.add(new Wall(new Point(50,130), new Point(110,130), h, 1, c));
    d.shapes.add(new Wall(new Point(110,130), new Point(110,150), h, 1, c));
    d.shapes.add(new Wall(new Point(110,150), new Point(50,150), h, 1, c));
    d.shapes.add(new Wall(new Point(50,150), new Point(50,130), h, 1, c));
    
    //top left T
    d.shapes.add(new Wall(new Point(150,130), new Point(170,130), h, 1, c));
    d.shapes.add(new Wall(new Point(170,130), new Point(170,190), h, 1, c));
    d.shapes.add(new Wall(new Point(170,190), new Point(230,190), h, 1, c));
    d.shapes.add(new Wall(new Point(230,190), new Point(230,210), h, 1, c));
    d.shapes.add(new Wall(new Point(230,210), new Point(170,210), h, 1, c));
    
    d.shapes.add(new Wall(new Point(170,210), new Point(170,215), h, 1, c));
    d.shapes.add(new Wall(new Point(170,215), new Point(170,220), h, 1, c));
    d.shapes.add(new Wall(new Point(170,220), new Point(170,270), h, 1, c));
    
    d.shapes.add(new Wall(new Point(170,270), new Point(150,270), h, 1, c));
    d.shapes.add(new Wall(new Point(150,270), new Point(150,130), h, 1, c));
    
    //top middle T
    d.shapes.add(new Wall(new Point(210,130), new Point(350,130), h, 1, c));
    d.shapes.add(new Wall(new Point(210,130), new Point(210,150), h, 1, c));
    d.shapes.add(new Wall(new Point(350,130), new Point(350,150), h, 1, c));
    d.shapes.add(new Wall(new Point(210,150), new Point(270,150), h, 1, c));
    d.shapes.add(new Wall(new Point(270,150), new Point(270,210), h, 1, c));
    d.shapes.add(new Wall(new Point(270,210), new Point(290,210), h, 1, c));
    d.shapes.add(new Wall(new Point(350,150), new Point(290,150), h, 1, c));
    d.shapes.add(new Wall(new Point(290,150), new Point(290,210), h, 1, c));
    
    //top right T
    d.shapes.add(new Wall(new Point(410,130), new Point(390,130), h, 1, c));
    d.shapes.add(new Wall(new Point(390,130), new Point(390,190), h, 1, c));
    d.shapes.add(new Wall(new Point(390,190), new Point(330,190), h, 1, c));
    d.shapes.add(new Wall(new Point(330,190), new Point(330,210), h, 1, c));
    d.shapes.add(new Wall(new Point(330,210), new Point(390,210), h, 1, c));
    
    
    d.shapes.add(new Wall(new Point(390,210), new Point(390,215), h, 1, c));
    d.shapes.add(new Wall(new Point(390,215), new Point(390,220), h, 1, c));
    d.shapes.add(new Wall(new Point(390,220), new Point(390,270), h, 1, c));
    
    d.shapes.add(new Wall(new Point(390,270), new Point(410,270), h, 1, c));
    d.shapes.add(new Wall(new Point(410,270), new Point(410,130), h, 1, c));
    
    //top middle right box
    d.shapes.add(new Wall(new Point(410,50), new Point(330,50), h, 1, c));
    d.shapes.add(new Wall(new Point(410,50), new Point(410,90), h, 1, c));
    d.shapes.add(new Wall(new Point(330,90), new Point(330,50), h, 1, c));
    d.shapes.add(new Wall(new Point(330,90), new Point(410,90), h, 1, c));
    
    //top right box
    d.shapes.add(new Wall(new Point(510,50), new Point(450,50), h, 1, c));
    d.shapes.add(new Wall(new Point(450,50), new Point(450,90), h, 1, c));
    d.shapes.add(new Wall(new Point(450,90), new Point(510,90), h, 1, c));
    d.shapes.add(new Wall(new Point(510,90), new Point(510,50), h, 1, c));
    
    //below top right box
    d.shapes.add(new Wall(new Point(510,130), new Point(450,130), h, 1, c));
    d.shapes.add(new Wall(new Point(450,130), new Point(450,150), h, 1, c));
    d.shapes.add(new Wall(new Point(450,150), new Point(510,150), h, 1, c));
    d.shapes.add(new Wall(new Point(510,150), new Point(510,130), h, 1, c));
    
    //left vertical box
    d.shapes.add(new Wall(new Point(150,390), new Point(170,390), h, 1, c));
    d.shapes.add(new Wall(new Point(170,390), new Point(170,310), h, 1, c));
    d.shapes.add(new Wall(new Point(170,310), new Point(150,310), h, 1, c));
    d.shapes.add(new Wall(new Point(150,310), new Point(150,390), h, 1, c));
    
    //right vertical box
    d.shapes.add(new Wall(new Point(410,390), new Point(390,390), h, 1, c));
    d.shapes.add(new Wall(new Point(390,390), new Point(390,310), h, 1, c));
    d.shapes.add(new Wall(new Point(390,310), new Point(410,310), h, 1, c));
    d.shapes.add(new Wall(new Point(410,310), new Point(410,390), h, 1, c));
    
    //middle T
    d.shapes.add(new Wall(new Point(210,370), new Point(350,370), h, 1, c));
    d.shapes.add(new Wall(new Point(210,370), new Point(210,390), h, 1, c));
    d.shapes.add(new Wall(new Point(350,370), new Point(350,390), h, 1, c));
    d.shapes.add(new Wall(new Point(210,390), new Point(270,390), h, 1, c));
    d.shapes.add(new Wall(new Point(270,390), new Point(270,450), h, 1, c));
    d.shapes.add(new Wall(new Point(270,450), new Point(290,450), h, 1, c));
    d.shapes.add(new Wall(new Point(350,390), new Point(290,390), h, 1, c));
    d.shapes.add(new Wall(new Point(290,390), new Point(290,450), h, 1, c));
    
    //left horizontal box
    d.shapes.add(new Wall(new Point(150,450), new Point(230,450), h, 1, c));
    d.shapes.add(new Wall(new Point(230,450), new Point(230,430), h, 1, c));
    d.shapes.add(new Wall(new Point(230,430), new Point(150,430), h, 1, c));
    d.shapes.add(new Wall(new Point(150,430), new Point(150,450), h, 1, c));
    
    //right horizontal box
    d.shapes.add(new Wall(new Point(330,450), new Point(410,450), h, 1, c));
    d.shapes.add(new Wall(new Point(410,450), new Point(410,430), h, 1, c));
    d.shapes.add(new Wall(new Point(410,430), new Point(330,430), h, 1, c));
    d.shapes.add(new Wall(new Point(330,430), new Point(330,450), h, 1, c));
    
    //bottom middle T
    d.shapes.add(new Wall(new Point(210,490), new Point(350,490), h, 1, c));
    d.shapes.add(new Wall(new Point(210,490), new Point(210,510), h, 1, c));
    d.shapes.add(new Wall(new Point(350,490), new Point(350,510), h, 1, c));
    d.shapes.add(new Wall(new Point(210,510), new Point(270,510), h, 1, c));
    d.shapes.add(new Wall(new Point(270,510), new Point(270,570), h, 1, c));
    d.shapes.add(new Wall(new Point(270,570), new Point(290,570), h, 1, c));
    d.shapes.add(new Wall(new Point(350,510), new Point(290,510), h, 1, c));
    d.shapes.add(new Wall(new Point(290,510), new Point(290,570), h, 1, c));
    
    //bottom left T
    d.shapes.add(new Wall(new Point(150,490), new Point(170,490), h, 1, c));
    d.shapes.add(new Wall(new Point(170,490), new Point(170,550), h, 1, c));
    d.shapes.add(new Wall(new Point(170,550), new Point(230,550), h, 1, c));
    d.shapes.add(new Wall(new Point(230,550), new Point(230,570), h, 1, c));
    d.shapes.add(new Wall(new Point(150,490), new Point(150,550), h, 1, c));
    d.shapes.add(new Wall(new Point(150,550), new Point(50,550), h, 1, c));
    d.shapes.add(new Wall(new Point(50,550), new Point(50,570), h, 1, c));
    
    d.shapes.add(new Wall(new Point(50,570), new Point(70,570), h, 1, c));
    d.shapes.add(new Wall(new Point(70,570), new Point(100,570), h, 1, c));
    d.shapes.add(new Wall(new Point(100,570), new Point(210,570), h, 1, c));
    d.shapes.add(new Wall(new Point(210,570), new Point(230,570), h, 1, c));
    
    //bottom right T
    d.shapes.add(new Wall(new Point(410,490), new Point(390,490), h, 1, c));
    d.shapes.add(new Wall(new Point(390,490), new Point(390,550), h, 1, c));
    d.shapes.add(new Wall(new Point(390,550), new Point(330,550), h, 1, c));
    d.shapes.add(new Wall(new Point(330,550), new Point(330,570), h, 1, c));
    d.shapes.add(new Wall(new Point(410,490), new Point(410,550), h, 1, c));
    d.shapes.add(new Wall(new Point(410,550), new Point(510,550), h, 1, c));
    d.shapes.add(new Wall(new Point(510,550), new Point(510,570), h, 1, c));
    
    d.shapes.add(new Wall(new Point(510,570), new Point(490,570), h, 1, c));
    d.shapes.add(new Wall(new Point(490,570), new Point(460,570), h, 1, c));
    d.shapes.add(new Wall(new Point(460,570), new Point(350,570), h, 1, c));
    d.shapes.add(new Wall(new Point(350,570), new Point(330,570), h, 1, c));
    
    //left L
    d.shapes.add(new Wall(new Point(50,430), new Point(110,430), h, 1, c));
    d.shapes.add(new Wall(new Point(110,430), new Point(110,510), h, 1, c));
    d.shapes.add(new Wall(new Point(110,510), new Point(90,510), h, 1, c));
    d.shapes.add(new Wall(new Point(90,510), new Point(90,450), h, 1, c));
    d.shapes.add(new Wall(new Point(90,450), new Point(50,450), h, 1, c));
    d.shapes.add(new Wall(new Point(50,450), new Point(50,430), h, 1, c));
    
    //right L
    d.shapes.add(new Wall(new Point(510,430), new Point(450,430), h, 1, c));
    d.shapes.add(new Wall(new Point(450,430), new Point(450,510), h, 1, c));
    d.shapes.add(new Wall(new Point(450,510), new Point(470,510), h, 1, c));
    d.shapes.add(new Wall(new Point(470,510), new Point(470,450), h, 1, c));
    d.shapes.add(new Wall(new Point(470,450), new Point(510,450), h, 1, c));
    d.shapes.add(new Wall(new Point(510,450), new Point(510,430), h, 1, c));
    
    //middle box
    d.shapes.add(new Wall(new Point(210,250), new Point(260,250), h, 1, c));
    d.shapes.add(new Wall(new Point(300,250), new Point(350,250), h, 1, c));
    d.shapes.add(new Wall(new Point(350,250), new Point(350,330), h, 1, c));
    d.shapes.add(new Wall(new Point(350,330), new Point(210,330), h, 1, c));
    d.shapes.add(new Wall(new Point(210,330), new Point(210,250), h, 1, c));
    //inner
    d.shapes.add(new Wall(new Point(225,265), new Point(260,265), h, 1, c));
    d.shapes.add(new Wall(new Point(300,265), new Point(335,265), h, 1, c));
    d.shapes.add(new Wall(new Point(335,265), new Point(335,315), h, 1, c));
    d.shapes.add(new Wall(new Point(335,315), new Point(225,315), h, 1, c));
    d.shapes.add(new Wall(new Point(225,315), new Point(225,265), h, 1, c));    
    
    
    d.shapes.add(new Wall(new Point(260,265), new Point(260,250), h, 1, c));
    d.shapes.add(new Wall(new Point(300,265), new Point(300,250), h, 1, c));
    
    //borders
    d.shapes.add(new Wall(new Point(10,10), new Point(270,10), h, 1, c));
    d.shapes.add(new Wall(new Point(290,10), new Point(550,10), h, 1, c));
    
    d.shapes.add(new Wall(new Point(290,10), new Point(290,90), h, 1, c));
    d.shapes.add(new Wall(new Point(270,10), new Point(270,90), h, 1, c));
    d.shapes.add(new Wall(new Point(290,90), new Point(270,90), h, 1, c));
    
    d.shapes.add(new Wall(new Point(10,10), new Point(10,190), h, 1, c));
    d.shapes.add(new Wall(new Point(10,190), new Point(50,190), h, 1, c));
    d.shapes.add(new Wall(new Point(10,190), new Point(110,190), h, 1, c));
    d.shapes.add(new Wall(new Point(110,190), new Point(110,390), h, 1, c));
    d.shapes.add(new Wall(new Point(110,390), new Point(10,390), h, 1, c));
    d.shapes.add(new Wall(new Point(10,390), new Point(10,490), h, 1, c));
    d.shapes.add(new Wall(new Point(10,510), new Point(10,610), h, 1, c));
    
    d.shapes.add(new Wall(new Point(10,510), new Point(50,510), h, 1, c));
    d.shapes.add(new Wall(new Point(10,490), new Point(50,490), h, 1, c));
    d.shapes.add(new Wall(new Point(50,510), new Point(50,490), h, 1, c));
    
    d.shapes.add(new Wall(new Point(550,510), new Point(510,510), h, 1, c));
    d.shapes.add(new Wall(new Point(550,490), new Point(510,490), h, 1, c));
    d.shapes.add(new Wall(new Point(510,510), new Point(510,490), h, 1, c));
    
    d.shapes.add(new Wall(new Point(550,10), new Point(550,190), h, 1, c));
    d.shapes.add(new Wall(new Point(550,50), new Point(550,190), h, 1, c));
    d.shapes.add(new Wall(new Point(550,190), new Point(450,190), h, 1, c));
    d.shapes.add(new Wall(new Point(450,190), new Point(450,390), h, 1, c));
    d.shapes.add(new Wall(new Point(450,390), new Point(550,390), h, 1, c));
    d.shapes.add(new Wall(new Point(550,390), new Point(550,490), h, 1, c));
    d.shapes.add(new Wall(new Point(550,510), new Point(550,610), h, 1, c));
    
    d.shapes.add(new Wall(new Point(10,610), new Point(550,610), h, 1, c));
    d.shapes.add(new Wall(new Point(10,610), new Point(50,610), h, 1, c));
    d.shapes.add(new Wall(new Point(500,610), new Point(550,610), h, 1, c));
    
    //pellets
    for(int i=0; i<26;i++)
      d.shapes.add(new Pellet(130,30+20*i,false));
    for(int i=0; i<26;i++)
      d.shapes.add(new Pellet(430,30+20*i,false));
    
    for(int i=0; i<5; i++)
      d.add(new Pellet(30+20*i,410,false));
    for(int i=0; i<6; i++)
      d.add(new Pellet(150+20*i,410,false));
    for(int i=0; i<6; i++)
      d.add(new Pellet(310+20*i,410,false));
    for(int i=0; i<5; i++)
      d.add(new Pellet(450+20*i,410,false));
    for(int i=0; i<6; i++)
      d.add(new Pellet(150+20*i,470,false));
    for(int i=0; i<6; i++)
      d.add(new Pellet(310+20*i,470,false));
    for(int i=0; i<26; i++)
      d.add(new Pellet(30+20*i,590,false));
    
    
    for(int i=0; i<5; i++)
      d.add(new Pellet(30+20*i,530,false));
    for(int i=0; i<5; i++)
      d.add(new Pellet(450+20*i,530,false));
    d.add(new Pellet(530,550,true));
    d.add(new Pellet(530,570,false));
    d.add(new Pellet(30,550,true));
    d.add(new Pellet(30,570,false));
    
    for(int i=0; i<4; i++)
      d.add(new Pellet(190+20*i,530,false));
    d.add(new Pellet(190,490,false));
    d.add(new Pellet(190,510,false));
    d.add(new Pellet(370,490,false));
    d.add(new Pellet(370,510,false));
    d.add(new Pellet(250,550,false));
    d.add(new Pellet(250,570,false));
    d.add(new Pellet(310,550,false));
    d.add(new Pellet(310,570,false));
    for(int i=0; i<4; i++)
      d.add(new Pellet(310+20*i,530,false));
    
    d.shapes.add(new Pellet(250,450,false));
    d.shapes.add(new Pellet(250,430,false));
    
    d.shapes.add(new Pellet(310,450,false));
    d.shapes.add(new Pellet(310,430,false));
    
    d.add(new Pellet(30,430,false));
    d.add(new Pellet(30,450,false));
    d.add(new Pellet(50,470,false));
    d.add(new Pellet(70,470,false));
    d.add(new Pellet(70,490,false));
    d.add(new Pellet(70,510,false));
    d.add(new Pellet(30,470,true));
    
    d.add(new Pellet(530,430,false));
    d.add(new Pellet(530,450,false));
    d.add(new Pellet(510,470,false));
    d.add(new Pellet(490,470,false));
    d.add(new Pellet(490,490,false));
    d.add(new Pellet(490,510,false));
    d.add(new Pellet(530,470,true));
    
    for(int i=0; i<5; i++)
      d.add(new Pellet(30+20*i,110,false));
    for(int i=0; i<14; i++)
      d.add(new Pellet(150+20*i,110,false));
    for(int i=0; i<5; i++)
      d.add(new Pellet(530-20*i,110,false));
    
    for(int i=0; i<5; i++)
      d.add(new Pellet(30+20*i,170,false));
    for(int i=0; i<4; i++)
      d.add(new Pellet(190+20*i,170,false));
    for(int i=0; i<5; i++)
      d.add(new Pellet(530-20*i,170,false));
    for(int i=0; i<4; i++)
      d.add(new Pellet(370-20*i,170,false));
    
    d.add(new Pellet(30,130,false));
    d.add(new Pellet(30,150,false));
    d.add(new Pellet(530,130,false));
    d.add(new Pellet(530,150,false));
    
    d.add(new Pellet(190,130,false));
    d.add(new Pellet(190,150,false));
    d.add(new Pellet(370,130,false));
    d.add(new Pellet(370,150,false));
    
    for(int i=0; i<5; i++)
      d.add(new Pellet(30+20*i,30, false));
    
    for(int i=0; i<6; i++)
      d.add(new Pellet(150+20*i,30, false));
    
    for(int i=0; i<6; i++)
      d.add(new Pellet(310+20*i,30, false));
    
    for(int i=0; i<5; i++)
      d.add(new Pellet(530-20*i,30, false));
    
    for(int i=0; i<3; i++)
    {
      boolean b = false;
      if(i==1)
        b=true;
      d.add(new Pellet(30,50+20*i, b));
    }
    
    for(int i=0; i<3; i++)
    {
      boolean b = false;
      if(i==1)
        b=true; 
      d.add(new Pellet(250,50+20*i, b));
    }
    
    for(int i=0; i<3; i++)
    {
      boolean b = false;
      if(i==1)
        b=true;
      d.add(new Pellet(310,50+20*i, b));
    }
    
    for(int i=0; i<3; i++)
    {
      boolean b = false;
      if(i==1)
        b=true;
      d.add(new Pellet(530,50+20*i, b));
    }
  }
}