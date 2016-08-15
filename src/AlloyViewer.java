import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class AlloyViewer extends JFrame {
    private static final long serialVersionUID = 814805851353902879L;
    private TempUpdater tempUpdater = null;
    private Alloy alloy;
    public PixelCanvas canvas;
    static AlloyViewer alloyViewer;
    Color[] colorSet;

    public AlloyViewer(Alloy alloy) {
        alloyViewer = this;
        this.setTitle("Assignment 3");
        this.setSize(800, 400);
        //this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        colorSet = new Color[1275];
        int count = 0;
        for(int i = 0 ; i <255; i ++){
            colorSet[count++] = new Color(0,i,255);
        }
        for(int i = 0 ; i <255; i ++){
            colorSet[count++] = new Color(0,255,255-i);
        }
        for(int i = 0 ; i <255; i ++){
            colorSet[count++] = new Color(i,255,0);
        }
        for(int i = 0 ; i <255; i ++){
            colorSet[count++] = new Color(255,255-i,0);
        }
        for(int i = 0 ; i <255; i ++){
            colorSet[count++] = new Color(255,i,i);
        }
        this.alloy = new Alloy(alloy);
        tempUpdater = new TempUpdater(alloy);
        tempUpdater.setDaemon(true);
        tempUpdater.start();
        canvas = new PixelCanvas();
        this.add(canvas);
    }

    public class PixelCanvas extends Canvas {

        @Override
        public void paint(Graphics oldg) {
            //super.paint(oldg);
            int size =(int)Math.min(this.getWidth()/alloy.getWidth(), this.getHeight()/alloy.getHeight());
            Image img_buffer = createImage(size*alloy.getWidth(), size*alloy.getHeight());
            Graphics g= img_buffer.getGraphics();
            for(int i = 0; i < alloy.getHeight(); i++) {
                for(int j = 0; j < alloy.getWidth(); j++) {
                    g.setColor(colorSet[(int)(alloy.getIndex(i,j)*1274/Math.max(alloy.getS(), alloy.getT()))]);//relatively

                    g.fillRect(j*size, i*size, size, size);
                }
            }
            oldg.drawImage(img_buffer, (AlloyViewer.this.getWidth()-img_buffer.getWidth(this))/2, (AlloyViewer.this.getHeight()-img_buffer.getHeight(this))/2-20, this);
        }

        @Override
        public void update(Graphics g) {
            // TODO Auto-generated method stub
            //super.update(g);
            paint(g);
        }


    }

}