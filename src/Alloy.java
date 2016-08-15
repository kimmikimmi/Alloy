import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.ArrayList;

/**
 * Created by Jaden on 01/12/2015.
 */

// will be the shared resourses of multiple threads.
public class Alloy {

    public final double p1 =  0.3;
    public final double p2 =  0.4;
    public final double p3 =  0.3;
    private ArrayList<Double> percentagesOfMetal;
    private int width; // the width of Alloy.
    private int height; // the height of Alloy.

    private long defaultTemp; // default temparature of the metal.

    private double c1; // the thermal constant for metal
    private double c2;
    private double c3;
    private ArrayList<Double> thermalConstants;
    private long s; //  The top left corner (at the mesh element at index [0,0]) is heated at $S$ degrees Celsius
    private long t; // the bottom right corner (index [width - 1,height - 1]) is heated at $T$ degrees Celsius.

    private long[][] index;

    public Alloy(Alloy alloy) {
        this.width = alloy.width;
        this.height = alloy.height;

        this.c1 = alloy.c1;
        this.c2 = alloy.c2;
        this.c3 = alloy.c3;

        this.s = alloy.s;
        this.t = alloy.t;

        this.thermalConstants = new ArrayList<>(alloy.thermalConstants);
        this.percentagesOfMetal = new ArrayList<>(alloy.percentagesOfMetal);

        this.defaultTemp = alloy.defaultTemp;
        this.index = alloy.index;

    }

    public Alloy(long s, long t, double c1, double c2, double c3, int width, long defaultTemp) {
        // initialising values.
        percentagesOfMetal = new ArrayList<>();
        percentagesOfMetal.add(p1);
        percentagesOfMetal.add(p2);
        percentagesOfMetal.add(p3);

        this.width = width;
        height = width / 2;

        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;

        thermalConstants = new ArrayList<>();
        thermalConstants.add(c1);
        thermalConstants.add(c2);
        thermalConstants.add(c3);

        this.s = s;
        this.t = t;

        this.defaultTemp = defaultTemp;

        // Initializing 2 dimentional array as[height][width];
        index = new long[height][width];

        for (int i=0; i<height; i++) {
            for (int j=0; j<width; j++) {
                    index[i][j] = defaultTemp;
            }
        }

        index[0][0] = s;
        index[height-1][width-1] = t;
    }

    public void displayToConsole() {
        for(int i=0; i<height; i++) {
            for(int j=0; j<width; j++) {
                System.out.print(index[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("---------------------------------------------");
    }

    public ArrayList<Double> getThermalConstants() {
        return thermalConstants;
    }

    public long getIndex(int x, int y) {
        return index[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Double> getPercentagesOfMetal() {
        return percentagesOfMetal;
    }

    public void setIndex(int x, int y, long value) {
        index[x][y] = value;
    }

    public long getT() {
        return t;
    }

    public long getS() {
        return s;
    }

}
