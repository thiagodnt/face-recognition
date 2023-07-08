package facerecognition.service;

import facerecognition.model.FaceProperties;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

public class ServiceImageOverlay {

    public BufferedImage mergeImages(List<FaceProperties> faceData, BufferedImage mainImage) {

        for (FaceProperties data : faceData) {
            mainImage = overlapImage(mainImage, data.getCroppedImage(), data.getX(), data.getY());
        }

        return mainImage;

    }

    public static BufferedImage overlapImage(BufferedImage mainImage, BufferedImage croppedImage, int x, int y) {

        Graphics2D g = mainImage.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(mainImage, 0, 0, null);

        g.drawImage(croppedImage, x, y, null);

        g.dispose();
        
        return mainImage;
    }

}
