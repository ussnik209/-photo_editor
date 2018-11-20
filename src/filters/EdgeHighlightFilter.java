package filters;

public class EdgeHighlightFilter extends AbstractWeightedFilter
{
    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/highlight.png";

    public String getFileName() {
        return  nameFile;
    }

    private static final int[][] WEIGHTS = {{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};

    public EdgeHighlightFilter()
    {
        super("Edge Highlight", WEIGHTS);
    }


}
