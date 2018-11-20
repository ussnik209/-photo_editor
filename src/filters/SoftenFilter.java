package filters;

public class SoftenFilter extends AbstractWeightedFilter
{
    public String nameFile = "C:/Users/Vadim/IdeaProjects/-photoeditor/-photo_editor/src/Pictures/soft.png";

    public String getFileName() {
        return  nameFile;
    }

    private static final int[][] WEIGHTS = {{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};

    public SoftenFilter()
    {
        super("Soften", WEIGHTS);
    }


}
