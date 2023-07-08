package facerecognition.main;

import facerecognition.service.ServiceFilter;
import facerecognition.service.ServiceCropImage;
import facerecognition.service.ServiceFaceDetection;
import facerecognition.service.ServiceImageOverlay;
import facerecognition.util.Util;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class ImgProcess {

    JFileChooser jFileChooser1;
    File file;
    String directory = System.getProperty("user.dir");

    public void blurProcess() throws IOException {

        jFileChooser1 = new JFileChooser(directory);
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
        jFileChooser1.setAcceptAllFileFilterUsed(false);

        int option = JOptionPane.showConfirmDialog(null, "Deseja aplicar o filtro em uma imagem?");

        if (option == JOptionPane.YES_OPTION) {

            int returnVal = jFileChooser1.showOpenDialog(null);

            if (returnVal == jFileChooser1.APPROVE_OPTION) {

                file = jFileChooser1.getSelectedFile();

                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                CascadeClassifier cascadeClassifier = new CascadeClassifier(System.getProperty("user.dir") + "/haarcascade_frontalface_alt_tree.xml");
                Mat mat = Highgui.imread(file.getAbsolutePath());

                ServiceFaceDetection serviceExtractFaces = new ServiceFaceDetection();
                MatOfRect matOfRect = serviceExtractFaces.detectFaces(cascadeClassifier, mat);

                List propsFaces = serviceExtractFaces.getFacesData(matOfRect);

                ServiceFilter serviceBlur = new ServiceFilter();
                BufferedImage imageBlurCrop = serviceBlur.blurImage(mat);

                ServiceCropImage serviceCrop = new ServiceCropImage();
                propsFaces = serviceCrop.cropImage(propsFaces, imageBlurCrop);

                ServiceImageOverlay serviceOverlay = new ServiceImageOverlay();

                BufferedImage defaultImage = Util.convertToImage(mat);

                imageBlurCrop = serviceOverlay.mergeImages(propsFaces, defaultImage);

                File outputfile = new File("gauss.jpg");

                try {
                    ImageIO.write(imageBlurCrop, "jpg", outputfile);
                    JOptionPane.showMessageDialog(null, "Filtro aplicado com sucesso!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void grayProcess() throws IOException {

        jFileChooser1 = new JFileChooser(directory);
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
        jFileChooser1.setAcceptAllFileFilterUsed(false);

        int option = JOptionPane.showConfirmDialog(null, "Deseja aplicar o filtro em uma imagem?");

        if (option == JOptionPane.YES_OPTION) {

            int returnVal = jFileChooser1.showOpenDialog(null);

            if (returnVal == jFileChooser1.APPROVE_OPTION) {

                file = jFileChooser1.getSelectedFile();

                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                CascadeClassifier cascadeClassifier = new CascadeClassifier(System.getProperty("user.dir") + "/haarcascade_frontalface_alt_tree.xml");
                Mat mat = Highgui.imread(file.getAbsolutePath());

                ServiceFaceDetection serviceExtractFaces = new ServiceFaceDetection();
                MatOfRect matOfRect = serviceExtractFaces.detectFaces(cascadeClassifier, mat);

                List propsFaces = serviceExtractFaces.getFacesData(matOfRect);

                ServiceFilter serviceGray = new ServiceFilter();
                BufferedImage imageGrayCrop = serviceGray.grayImage(mat);

                ServiceCropImage serviceCrop = new ServiceCropImage();
                propsFaces = serviceCrop.cropImage(propsFaces, imageGrayCrop);

                ServiceImageOverlay serviceOverlay = new ServiceImageOverlay();

                BufferedImage defaultImage = Util.convertToImage(mat);

                imageGrayCrop = serviceOverlay.mergeImages(propsFaces, defaultImage);

                File outputfile = new File("gray.jpg");

                try {
                    ImageIO.write(imageGrayCrop, "jpg", outputfile);
                    JOptionPane.showMessageDialog(null, "Filtro aplicado com sucesso!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void thresholdProcess() throws IOException {

        jFileChooser1 = new JFileChooser(directory);
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
        jFileChooser1.setAcceptAllFileFilterUsed(false);

        int option = JOptionPane.showConfirmDialog(null, "Deseja aplicar o filtro em uma imagem?");

        if (option == JOptionPane.YES_OPTION) {

            int returnVal = jFileChooser1.showOpenDialog(null);

            if (returnVal == jFileChooser1.APPROVE_OPTION) {

                file = jFileChooser1.getSelectedFile();

                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                CascadeClassifier cascadeClassifier = new CascadeClassifier(System.getProperty("user.dir") + "/haarcascade_frontalface_alt_tree.xml");
                Mat mat = Highgui.imread(file.getAbsolutePath());

                ServiceFaceDetection serviceExtractFaces = new ServiceFaceDetection();
                MatOfRect matOfRect = serviceExtractFaces.detectFaces(cascadeClassifier, mat);

                List propsFaces = serviceExtractFaces.getFacesData(matOfRect);

                ServiceFilter serviceThreshold = new ServiceFilter();
                BufferedImage imageThresholdCrop = serviceThreshold.thresholdImage(mat);

                ServiceCropImage serviceCrop = new ServiceCropImage();
                propsFaces = serviceCrop.cropImage(propsFaces, imageThresholdCrop);

                ServiceImageOverlay serviceOverlay = new ServiceImageOverlay();

                BufferedImage defaultImage = Util.convertToImage(mat);

                imageThresholdCrop = serviceOverlay.mergeImages(propsFaces, defaultImage);

                File outputfile = new File("threshold.jpg");

                try {
                    ImageIO.write(imageThresholdCrop, "jpg", outputfile);
                    JOptionPane.showMessageDialog(null, "Filtro aplicado com sucesso!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
