package filters;

import Image.Pixel;
import Strategy.Filter;

public abstract class AbstractFilter implements Filter
{
    private static final String FILTER_SUFFIX = "Filter";

    private String my_description;

    public AbstractFilter(final String the_description)
    {
        my_description = the_description;
    }

    @Override
    public String getDescription()
    {
        return my_description;
    }

    protected int normalize(final int the_color)
    {
        return Math.max(Pixel.MIN_COLOR_VALUE, Math.min(Pixel.MAX_COLOR_VALUE, the_color));
    }

    protected void swap(final Pixel[][] the_data, final int row_1, final int col_1,
                        final int row_2, final int col_2)
    {
        final Pixel temp = the_data[row_1][col_1];
        the_data[row_1][col_1] = the_data[row_2][col_2];
        the_data[row_2][col_2] = temp;
    }
}
