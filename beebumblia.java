/*
    Given N circles and 1 line segment 
    Determine if the line segment intersects any of the N circles.
*/
import java.util.ArrayList;
import java.util.Scanner;

public class beebumblia {
    static final boolean debug = false;
    public static void main(String []args){

        Scanner scan = new Scanner(System.in);
        int ds = 1;
        while(scan.hasNextInt()){
            double diameter = 0, x = 0 , y = 0;
            int nArmies = scan.nextInt();
            if(!scan.hasNextDouble()){
                break;
            }
            ArrayList<double[]> armies = new ArrayList<>();
            for(int i = 0; i < nArmies; i++){
                armies.add(new double[]{scan.nextDouble(), scan.nextDouble(), scan.nextDouble()});
            }

            double p_x1 = scan.nextDouble();
            double p_y1 = scan.nextDouble();
            double p_x2 = scan.nextDouble();
            double p_y2 = scan.nextDouble();

            double dx, dy, dr, D, t_px1, t_py1, t_px2, t_py2;
            double cx, cy;
            double ix, iy;
            double under_sqrt;
            boolean alive = false;
            for(int i = 0; i < nArmies; i++) {
                cx = armies.get(i)[0];
                cy = armies.get(i)[1];

                t_px1 = p_x1 - cx;
                t_py1 = p_y1 - cy;
                t_px2 = p_x2 - cx;
                t_py2 = p_y2 - cy;
                //dx = cx >= 0 ? (p_x2 - cx) - (p_x1 - cx) :  (p_x2 + cx) - (p_x1 + cx);
                dx = t_px2 - t_px1;
                dy = t_py2 - t_py1;
                //dy = cy >= 0 ? (p_y2 - cy) - (p_y1 - cy) : (p_y2 + cy) - (p_y1 + cy);
                dr = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy,2));
                D = t_px1 * t_py2 - t_px2 * t_py1;
                under_sqrt = Math.pow((armies.get(i)[2] / 2.0), 2) * Math.pow(dr, 2) - Math.pow(D, 2);
                if(under_sqrt >= 0) {
                    print("Under sqrt:" + under_sqrt);
                    ix = (dy < 0) ? (D * dy + -1 * dx * Math.sqrt(under_sqrt)) / Math.pow(dr, 2)
                            : (D * dy + dx * Math.sqrt(under_sqrt)) / Math.pow(dr, 2);
                    iy = (-D * dx + Math.abs(dy) * Math.sqrt(under_sqrt))/Math.pow(dr, 2);
                   if(distance(t_px1, t_py1, ix, iy ) + distance(ix, iy, t_px2, t_py2) == distance(t_px1, t_py1, t_px2, t_py2)){
                       alive = false;
                       break;
                   }else{
                       print("Distance p1 to intercept: " + distance(t_px1, t_py1, ix, iy ) );
                       print("Distance intercept to p2: " + distance(ix, iy, t_px2, t_py2));
                       double sum = distance(t_px1, t_py1, ix, iy ) + distance(ix, iy, t_px2, t_py2);
                       print("Sum: "+ sum);
                       print("Distance p1 to p2: " + distance(t_px1, t_py1, t_px2, t_py2));
                       print("ix: " + ix + " iy: " + iy);
                   }
                } else {
                    alive = true;
                }

                if(!alive) break;
            }
            if(alive){
                System.out.println("Data Set " + ds++ + ": OK");
            } else {
                System.out.println("Data Set " + ds++ + ": EATEN");
            }
        }
        scan.close();
    }

    static void print(String s){
        if(debug == true) {
            System.out.println(s);
        }
    }
    static double distance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1), 2));
    }
}
