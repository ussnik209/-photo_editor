package filters;

import Image.Pixel;
import Image.PixelImage;


import java.awt.image.BufferedImage;

public class InvertFilter extends AbstractFilter {

    public InvertFilter () {
        super("Invert");
    }

    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Image/invert.png";

    public String getFileName() {
        return  nameFile;
    }

    @Override
    public void filter(PixelImage the_image) {
        BufferedImage newImg = the_image;
        for (int v = 0; v < the_image.getWidth(); v++) {
            for (int u = 0; u < the_image.getHeight(); u++) {
                int a = the_image.getRGB(v,u) & 0xff000000;
                newImg.setRGB(v,u,a | (~the_image.getRGB(v,u) & 0x00ffffff));
            }
        }

    }
}
