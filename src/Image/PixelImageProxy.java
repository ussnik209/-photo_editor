package Image;


import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PixelImageProxy implements MyImage {

    private PixelImage real_image = null;
    private String filename;

    public PixelImageProxy (String file) {
        this.filename = file;
    }

    @Override
    public PixelImage display(JLabel my_label) throws IOException {
        if(real_image!=null) {
            // modify the image
            real_image = real_image.display(my_label);
        }
        else {
            my_label.setText("Loading...");
            synchronized(this) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            Thread.currentThread().sleep(2000);
                            my_label.setText(null);
                            my_label.setIcon(null);
                            my_label.setIcon(new ImageIcon(real_image));

                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
            real_image = PixelImage.load(new File(filename));
            try {
                display(my_label);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return real_image;
    }

}
