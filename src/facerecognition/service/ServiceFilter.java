package facerecognition.service;

import facerecognition.util.Util;
import java.awt.image.BufferedImage;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ServiceFilter {

    public BufferedImage blurImage(Mat mat) {

        mat = blur(mat);

        return Util.convertToImage(mat);
    }

    private Mat blur(Mat image) {

        Mat destination = new Mat(image.rows(), image.cols(), image.type());
        
        //***FILTRO UTILIZADO: DESFOQUE GAUSSIANO***//
        Imgproc.GaussianBlur(image, destination, new Size(45, 45), 0);

        return destination;
    }
    
    public BufferedImage grayImage(Mat mat) {

        mat = gray(mat);

        return Util.convertToImage(mat);
    }

    private Mat gray(Mat image) {

        Mat destination = new Mat(image.rows(), image.cols(), image.type());
        
        //***FILTRO UTILIZADO: ESCALA DE CINZA***//
        Imgproc.cvtColor(image, destination, Imgproc.COLOR_RGB2GRAY);

        return destination;
    }
    
    public BufferedImage thresholdImage(Mat mat) {

        mat = threshold(mat);

        return Util.convertToImage(mat);
    }

    private Mat threshold(Mat image) {

        Mat destination = new Mat(image.rows(), image.cols(), image.type());
        
        //***FILTRO UTILIZADO: THRESHOLD***//
        Imgproc.threshold(image,destination,110,255,Imgproc.THRESH_TOZERO);

        return destination;
    }
}
