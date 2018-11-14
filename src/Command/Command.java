package Command;

import Image.PixelImage;
import Strategy.Filter;

public abstract class Command {

    protected Filter filt;

    public Filter getFilt() {
        return filt;
    }

    public Command (Filter f) {
        this.filt = f;
    }

    public abstract void execute(PixelImage img);
}
