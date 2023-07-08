package facerecognition.service;

import facerecognition.model.FaceProperties;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

public class ServiceFaceDetection {

    public MatOfRect detectFaces(CascadeClassifier cascadeClassifier, Mat mat) {

        MatOfRect matOfRect = new MatOfRect();
        cascadeClassifier.detectMultiScale(mat, matOfRect);
        return matOfRect;
    }

    public List getFacesData(MatOfRect matOfRect) {

        List data = new ArrayList();

        for (Rect rect : matOfRect.toArray()) {

            FaceProperties prop = new FaceProperties();
            prop.setX(rect.x);
            prop.setY(rect.y);
            prop.setHeight(rect.height);
            prop.setWidth(rect.width);

            data.add(prop);

        }

        return data;
    }
    
}
