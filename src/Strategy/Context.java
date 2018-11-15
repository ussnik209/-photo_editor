package Strategy;

import Image.PixelImage;

public class Context {
    private Filter strategy;

    public Filter getStrategy() {
        return strategy;
    }

    public void setStrategy(Filter str) {
        this.strategy = str;
    }

    public void execute (PixelImage image) {
        this.strategy.filter(image);
    }
}
