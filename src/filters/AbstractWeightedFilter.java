
package filters;

import Image.Pixel;
import Image.PixelImage;

public abstract class AbstractWeightedFilter extends AbstractFilter
{

    private final int[][] my_weights;

    public AbstractWeightedFilter(final String the_description, final int[][] the_weights)
    {
        super(the_description);
        my_weights = the_weights.clone();
    }

    @Override
    public void filter(final PixelImage the_image)
    {
        weight(the_image, my_weights);
    }

    protected void weight(final PixelImage the_image, final int[][] the_weights)
            throws IllegalArgumentException
    {
        checkWeights(the_weights);
        int sum = 0;
        for (final int[] row : the_weights) {
            for (final int col : row) {
                sum += col;
            }
        }

        if (sum == 0) {
            sum = sum + 1;
        }

        weight(the_image, the_weights, sum);
    }

    protected void weight(final PixelImage the_image, final int[][] the_weights,
                          final int the_scale) throws IllegalArgumentException
    {
        checkWeights(the_weights);

        final int w = the_image.getWidth(null);
        final int h = the_image.getHeight(null);
        final Pixel[][] old_pixels = the_image.getPixelData();
        final Pixel[][] new_pixels = new Pixel[h][w];

        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                // add up 9 neighboring pixels
                int red = 0;
                int green = 0;
                int blue = 0;
                //included situation with borders
                for (int j = Math.max(0, y - 1); j <= Math.min(y + 1, h - 1); j++)
                {
                    for (int i = Math.max(0, x - 1); i <= Math.min(x + 1, w - 1); i++)
                    {
                        // Pixel p = oldPixels[i][j];
                        final Pixel p = old_pixels[j][i];
                        final int weight = the_weights[y - j + 1][x - i + 1];
                        red = red + p.getRed() * weight;
                        green = green + p.getGreen() * weight;
                        blue = blue + p.getBlue() * weight;
                    }
                }

                // account for negative / too high color values
                red = normalize(red / the_scale);
                green = normalize(green / the_scale);
                blue = normalize(blue / the_scale);

                new_pixels[y][x] = new Pixel(red, green, blue);
            }
        }

        the_image.setPixelData(new_pixels);
    }

    private void checkWeights(final int[][] the_weights) throws IllegalArgumentException
    {
        if (the_weights == null || the_weights.length != Pixel.NUM_CHANNELS
                || the_weights[0] == null || the_weights[0].length != Pixel.NUM_CHANNELS
                || the_weights[1] == null || the_weights[1].length != Pixel.NUM_CHANNELS
                || the_weights[2] == null || the_weights[2].length != Pixel.NUM_CHANNELS)
        {
            throw new IllegalArgumentException("must pass correctly-sized grid");
        }
    }

}
