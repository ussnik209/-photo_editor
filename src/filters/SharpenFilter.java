
package filters;

public class SharpenFilter extends AbstractWeightedFilter
{
    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/sharp.png";

    public String getFileName() {
        return  nameFile;
    }

    private static final int[][] WEIGHTS = {{-1, -2, -1}, {-2, 28, -2}, {-1, -2, -1}};

    public SharpenFilter()
    {
        super("Sharpen", WEIGHTS);
    }

}
