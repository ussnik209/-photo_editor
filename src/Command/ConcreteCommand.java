package Command;

import Image.PixelImage;
import Strategy.Filter;

public class ConcreteCommand extends Command {

    public ConcreteCommand (Filter f){
        super(f);
    }

    @Override
    public void execute(PixelImage img) {
        filt.filter(img);
    }
}
