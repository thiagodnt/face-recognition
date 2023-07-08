package facerecognition.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

public class Util {

    public static BufferedImage convertToImage(Mat image) {

        MatOfByte bytemat = new MatOfByte();

        Highgui.imencode(".jpg", image, bytemat);

        byte[] bytes = bytemat.toArray();

        InputStream in = new ByteArrayInputStream(bytes);

        BufferedImage img = null;

        try {
            img = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

}
