package facerecognition.service;

import facerecognition.model.FaceProperties;
import java.awt.image.BufferedImage;
import java.util.List;

public class ServiceCropImage {

    public List<FaceProperties> cropImage(List<FaceProperties> faceData, BufferedImage image) {

        for (FaceProperties data : faceData) {
            data.setCroppedImage(image.getSubimage(data.getX(), data.getY(), data.getWidth(), data.getHeight()));
        }

        return faceData;
    }

}
