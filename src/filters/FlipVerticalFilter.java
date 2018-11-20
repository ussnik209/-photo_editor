package filters;

import Image.Pixel;
import Image.PixelImage;

public class FlipVerticalFilter extends AbstractFilter
{
    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/flipV.png";

    public String getFileName() {
        return  nameFile;
    }

    public FlipVerticalFilter()
    {
        super("Flip Vertical");
    }

    @Override
    public void filter(final PixelImage the_image)
    {
        final Pixel[][] data = the_image.getPixelData();
        for (int row = 0; row < the_image.getHeight() / 2; row++)
        {
            for (int col = 0; col < the_image.getWidth(); col++)
            {
                swap(data, row, col, the_image.getHeight() - row - 1, col);
            }
        }
        the_image.setPixelData(data);
    }
}
