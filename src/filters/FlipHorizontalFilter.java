package filters;

import Image.Pixel;
import Image.PixelImage;

public class FlipHorizontalFilter extends AbstractFilter
{
    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/flipH.png";

    public String getFileName() {
        return  nameFile;
    }

    public FlipHorizontalFilter()
    {
        super("Flip Horizontal");
    }

    @Override
    public void filter(final PixelImage the_image)
    {
        final Pixel[][] data = the_image.getPixelData();
        for (int row = 0; row < the_image.getHeight(); row++)
        {
            for (int col = 0; col < the_image.getWidth() / 2; col++)
            {
                swap(data, row, col, row, the_image.getWidth() - col - 1);
            }
        }
        the_image.setPixelData(data);
    }
}
