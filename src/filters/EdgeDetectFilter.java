package filters;

import Image.PixelImage;

public class EdgeDetectFilter extends AbstractWeightedFilter
{

    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/detect.png";

    public String getFileName() {
        return  nameFile;
    }

    private static final int[][] WEIGHTS = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};

    public EdgeDetectFilter()
    {
        super("Edge Detect",WEIGHTS);
    }

}
