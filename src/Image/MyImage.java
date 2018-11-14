package Image;

import javax.swing.*;
import java.io.IOException;

public interface MyImage {
    PixelImage display(JLabel my_label) throws IOException;
}
