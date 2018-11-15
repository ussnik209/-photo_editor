package Strategy;

import Image.PixelImage;

public interface Filter
{

    String getFileName();

    void filter(PixelImage the_image);

    String getDescription();
}
