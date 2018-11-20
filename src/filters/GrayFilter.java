package filters;

import Image.Pixel;
import Image.PixelImage;
import filters.AbstractFilter;

import java.awt.image.BufferedImage;


public class GrayFilter extends AbstractFilter{

    public GrayFilter() {
        super("Gray");
    }

    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/gray.png";

    public String getFileName() {
        return  nameFile;
    }

    @Override
    public void filter(PixelImage the_image) {
        BufferedImage newImg = the_image;
        for (int v = 0; v < the_image.getWidth(); v++) {
            for (int u = 0; u < the_image.getHeight(); u++) {
                int a = the_image.getRGB(v,u) & 0xff000000;
                int r = (the_image.getRGB(v,u) >> 16) & 0xff;
                int g = (the_image.getRGB(v,u) >> 8) & 0xff;
                int b = the_image.getRGB(v,u) & 0xff;
                r = (r+255)/2;
                g = (g+255)/2;
                b = (b+255)/2;
                newImg.setRGB(v,u, a | (r << 16) | (g << 8) | b);

            }
        }

    }
}
