package facerecognition.util;

import facerecognition.main.ImgProcess;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;

public class ImgShow {

    JFrame frame;
    JLabel label;
    JFileChooser jFileChooser1;
    File file;
    BufferedImage bufImage;
    String directory = System.getProperty("user.dir");

    public ImgShow() {
        frame = new JFrame();
        label = new JLabel();
        jFileChooser1 = new JFileChooser(directory);
        jFileChooser1.addChoosableFileFilter(new FileNameExtensionFilter("*.jpg", "jpg"));
        jFileChooser1.setAcceptAllFileFilterUsed(false);
        frame.getContentPane().add(label);
        frame.setResizable(false);
    }

    public void show(Mat matFrame) {
        MatOfByte matOfByte = new MatOfByte();
        Highgui.imencode(".jpg", matFrame, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        bufImage = null;
        try {
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            label.setIcon(new ImageIcon(bufImage));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveImage(Mat matFrame) {

        int option = JOptionPane.showConfirmDialog(null, "Deseja salvar a imagem?");

        if (option == JOptionPane.YES_OPTION) {
            int returnVal = jFileChooser1.showSaveDialog(frame);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = jFileChooser1.getSelectedFile();
                String fname = jFileChooser1.getSelectedFile().getAbsolutePath();
                try {
                    if (!fname.endsWith(".jpg")) {
                        fname += ".jpg";
                    }
                    ImageIO.write(bufImage, "JPG", new File(fname));
                    JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
                    this.frame.dispose();
                } catch (IOException ex) {
                    Logger.getLogger(ImgShow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.frame.dispose();
                System.out.println("Cancelled.");
            }
        } else {
            this.frame.dispose();
        }
    }
}
