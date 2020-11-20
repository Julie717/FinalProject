package com.buyalskaya.fitclub.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * The type Image transformer.
 *
 * @author Buyalskaya Yuliya
 * @version 1.0
 */
public class ImageTransformer {
    private static final Logger logger = LogManager.getLogger();
    private static final String IMAGE_TYPE = "jpg";
    private static final int IMAGE_WIDTH = 320;
    private static final int IMAGE_HEIGHT = 420;

    private ImageTransformer() {
    }

    /**
     * Transform image to string base 64 string.
     *
     * @param inputStream the input stream
     * @return the string
     */
    public static String transformImageToStringBase64(InputStream inputStream) {
        String base64Image = null;
        if (inputStream != null) {
            try {
                byte[] image = inputStream.readAllBytes();
                base64Image = Base64.getEncoder().encodeToString(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return base64Image;
    }

    /**
     * Decrease image input stream.
     * Is used to make picture smaller
     *
     * @param image the image
     * @return the input stream
     */
    public static InputStream decreaseImage(InputStream image) {
        InputStream newImage = null;
        try {
            BufferedImage img = ImageIO.read(image);
            BufferedImage decreaseImg = Scalr.resize(img, Scalr.Method.QUALITY, IMAGE_WIDTH, IMAGE_HEIGHT);
            ByteArrayOutputStream imgByte = new ByteArrayOutputStream();
            ImageIO.write(decreaseImg, IMAGE_TYPE, imgByte);
            newImage = new ByteArrayInputStream(imgByte.toByteArray());
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Error during decrease image", ex);
        }
        return newImage;
    }
}