/**
 * Created by Jaden on 01/12/2015.
 */



public class Main {
    public static void main(String[] args) {
        //Alloy(long s, long t, long c1, long c2, long c3, int width, long defaultTemp) {
        Alloy alloy = new Alloy(1000, 500, 0.75, 1, 1.25, 10, 100);
        TempUpdater tempUpdater = new TempUpdater(alloy);
        //tempUpdater.start();
        AlloyViewer alloyViewer = new AlloyViewer(alloy);

        alloyViewer.setVisible(true);
        tempUpdater.start();



    }
}
