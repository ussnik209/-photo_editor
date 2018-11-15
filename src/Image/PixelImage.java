package Image;

import UI.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class PixelImage extends BufferedImage implements Prototype, MyImage
{

    public boolean rotated = false;

    private static final String ARRAY_ERROR = "Array size is invalid.";

    private PixelImage(final int the_width, final int the_height, final int the_type)
    {
        super(the_width, the_height, the_type);
    }

    public PixelImage (ColorModel cm,
                       WritableRaster raster,
                       boolean isRasterPremultiplied,
                       Hashtable<?,?> properties) {
        super(cm, raster, isRasterPremultiplied, properties);
    }

    public static PixelImage load(final File the_file) throws IOException
    {

        final BufferedImage buf = ImageIO.read(the_file);

        if (buf == null)
        {
            throw new IOException("File did not contain a valid image: " + the_file);
        }

        final PixelImage image =
                new PixelImage(buf.getWidth(), buf.getHeight(), BufferedImage.TYPE_INT_RGB);
        final Graphics g = image.getGraphics();
        g.drawImage(buf, 0, 0, null);

        return image;
    }

    public void save(final File the_file) throws IOException
    {
        ImageIO.write(this, "jpg", the_file);
    }

    public Pixel[][] getPixelData()
    {
        final Raster r = getRaster();
        final Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
        int[] samples = new int[Pixel.NUM_CHANNELS];

        for (int row = 0; row < r.getHeight(); row++)
        {
            for (int col = 0; col < r.getWidth(); col++)
            {
                samples = r.getPixel(col, row, samples);
                final Pixel new_pixel = new Pixel(samples[0], samples[1], samples[2]);
                data[row][col] = new_pixel;
            }
        }

        return data;
    }

    public void setPixelData(final Pixel[][] the_data) throws IllegalArgumentException
    {
        final int[] pixel_values = new int[Pixel.NUM_CHANNELS];
        final WritableRaster wr = getRaster();

        if (the_data == null || the_data.length != wr.getHeight())
        {
            throw new IllegalArgumentException(ARRAY_ERROR);
        }
        else if (the_data[0].length != wr.getWidth())
        {
            for (int i = 0; i < the_data.length; i++)
            {
                if (the_data[i] == null || the_data[i].length != wr.getWidth())
                {
                    throw new IllegalArgumentException(ARRAY_ERROR);
                }
            }
        }

        for (int row = 0; row < wr.getHeight(); row++)
        {
            for (int col = 0; col < wr.getWidth(); col++)
            {
                pixel_values[0] = the_data[row][col].getRed();
                pixel_values[1] = the_data[row][col].getGreen();
                pixel_values[2] = the_data[row][col].getBlue();
                wr.setPixel(col, row, pixel_values);
            }
        }
    }

    public  PixelImage resizeImage(BufferedImage image, int width, int height) {

        ColorModel cm = image.getColorModel();
        WritableRaster raster = cm.createCompatibleWritableRaster(width, height);
        boolean isRasterPremultiplied = cm.isAlphaPremultiplied();

        PixelImage target = new PixelImage(cm, raster, isRasterPremultiplied, null);
        Graphics2D g2 = target.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        double scalex = (double) target.getWidth()/ image.getWidth();
        double scaley = (double) target.getHeight()/ image.getHeight();

        AffineTransform xform = AffineTransform.getScaleInstance(scalex, scaley);
        g2.drawRenderedImage(image, xform);
        g2.dispose();
        return target;

    }

    public  PixelImage rotate(int width, int height) {

        Pixel [][]temp = this.getPixelData();
        PixelImage tempI = new PixelImage(this.getHeight(), this.getWidth(),TYPE_INT_RGB);
        Pixel [][]temp1 = new Pixel[temp[0].length][temp.length];
        for (int i = 0; i < temp[0].length; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp1[i][j] = temp[j][i];
            }
        }
        tempI.setPixelData(temp1);
        return tempI;

    }

    @Override
    public Prototype Clone() throws CloneNotSupportedException {
        //deep
        PixelImage newPI = new PixelImage(getWidth(),getHeight(),getType());
        //clone the array of Pixel
        final Pixel [][]data = getPixelData();
        Pixel [][]temp = new Pixel[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                temp[i][j] = (Pixel) data[i][j].Clone();
            }
        }
        newPI.setPixelData(temp);
        return newPI;
    }

    @Override
    public PixelImage display(JLabel my_label) throws IOException {

        PixelImage temp = this;
        Dimension screenSize = my_label.getSize();
        if (temp.getWidth() > temp.getHeight()) {
            double koef = (double) temp.getWidth() / temp.getHeight();
            temp =  temp.resizeImage(this, (int)screenSize.getWidth() , (int) ((screenSize.getWidth()) / koef ));
        } else {
            double koef = (double) temp.getHeight() / temp.getWidth();

            temp = temp.resizeImage(this, (int) ((screenSize.getHeight())/ koef), (int)screenSize.getHeight());
        }
        return temp;
    }
}

