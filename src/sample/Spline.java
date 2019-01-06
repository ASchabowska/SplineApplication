package sample;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.interpolation.UnivariateInterpolator;
import org.apache.commons.math3.stat.StatUtils;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Spline {

    private double[] x;
    private double[] y;

    public Spline(List xList, List yList){
        int length = xList.size();
        x = new double[length];
        y = new double[length];
        for( int i = 0; i < length; i++){
            this.x[i] = Double.parseDouble(xList.get(i).toString());
            this.y[i] = Double.parseDouble(yList.get(i).toString());
        }
    }// end of constructor


    public void calculate() {
        sort(x,y);
        UnivariateInterpolator interpolator = new SplineInterpolator();
        Plot2DPanel plot = new Plot2DPanel();
        UnivariateFunction polynomial = interpolator.interpolate(x,y);
        int n =(int) Math.abs((StatUtils.max(x) - StatUtils.min(x))/0.1);
        double[] xc = new double[n];
        double[] yc = new double[n];
        double xi = StatUtils.min(x);
        for(int i = 0; i < xc.length; i++){
            xc[i] = xi + 0.1*i;
            yc[i] = polynomial.value(xc[i]);
        }

        plot.addLegend("East");
        plot.addScatterPlot("Data",x,y);
        plot.addLinePlot("Spline Interpolation",xc,yc);
        BaseLabel title = new BaseLabel("Spline Interpolation", Color.black,0.5,1.1);
        plot.addPlotable(title);

        JFrame frame = new JFrame("Spline interpolation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,500);
        frame.add(plot, BorderLayout.CENTER);
        frame.setVisible(true);
    }// end of calculate method

    // points x have to be in increasing order
    public void sort(double[] x,double[] y){
        for (int j = 0; j < x.length - 1; j++) {
            for (int i = 0; i < x.length - 1; i++) {
                if (x[i] > x[i + 1]) {
                    double z = x[i];
                    double w = y[i];
                    x[i] = x[i + 1];
                    x[i + 1] = z;
                    y[i] = y[i + 1];
                    y[i + 1] = w;
                } } }
    } // end of sort

} //end of Spline class
