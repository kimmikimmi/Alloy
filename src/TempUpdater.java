/**
 * Created by Jaden on 01/12/2015.
 */
public class TempUpdater extends Thread{

    private Alloy alloy;
    private final int numOfMetals = 3;

    public TempUpdater(Alloy alloy) {
        this.alloy = new Alloy(alloy);
    }




    public void update() {

        for(int i=0; i<alloy.getHeight(); i++) {
            for(int j=0; j<alloy.getWidth(); j++) {

                if((i==0 && j==0) || (i==alloy.getHeight()-1 && j==alloy.getWidth()-1)) {
                    continue;
                }

                long temp = 0;
                //long temp = alloy.getIndex(i,j);
                for(int m = 0 ; m < numOfMetals ; m++){
                    temp += alloy.getThermalConstants().get(m) * getSubSum(i, j, m);

                }

                alloy.setIndex(i,j,temp);
            }
        }

//        if(AlloyViewer.alloyViewer.canvas != null){
//            AlloyViewer.alloyViewer.canvas.repaint();
//            try {
//                sleep(100);// animation speed.
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }

    }

    public double getSubSum(int row, int col, int m){
        double subSum=0;
        int N =0;
        for(int i = -1 ; i < 2; i++){
            for(int j = -1 ; j < 2; j++){
                if( (i==0 && j==0) || row+i<0 || col+j<0 || row+i>alloy.getHeight()-1 || col+j>alloy.getWidth()-1 ) {
                    continue;
                }
                subSum += alloy.getIndex(row+i, col+j) * alloy.getPercentagesOfMetal().get(m);
                if(subSum < 0) {
                    subSum = Long.MAX_VALUE;
                }
                N++;
            }
        }
        subSum = subSum / N;
        return subSum;
    }

    @Override
    public void run() {
        super.run();
        for(int i=0; i<1000; i++) {
            alloy.displayToConsole();
            update();

        }

    }
}
