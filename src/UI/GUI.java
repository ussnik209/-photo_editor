package UI;

import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Command.ConcreteCommand;
import Command.Command;
import Command.Invoker;
import Image.PixelImage;
import Image.PixelImageProxy;
import Strategy.Context;
import Strategy.Filter;


public class GUI
{


    private Context context;

    {
        context = new Context();
    }

    private final List<JButton> FILTER_BUTTONS;
    private JButton apply;
    private JButton original;
    private static final JFrame my_frame;

    static {
        my_frame = new JFrame();
    }

    private final JLabel my_label;

    {
        my_label = new JLabel("", JLabel.CENTER);
    }

    private final JFileChooser my_file_chooser;

    {
        my_file_chooser = new JFileChooser("./images");
    }

    private List<JMenuItem> items;

    {
        items = new ArrayList<>();
    }

    private JMenuItem cleanMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;

    private final JPanel my_panel1;
    private final JPanel my_panel2;

    private PixelImage main_image;
    private PixelImage my_image;
    private PixelImage cloned;

    private BufferedImage defaultIcon;

    private JMenuBar menuBar;

    private List<Command> filtersCommands = new ArrayList<>();

    private Invoker invoker;

    private ImageTransferHandler my_imgTransf;

    public PixelImage my_image1;

    public GUI() {
        FILTER_BUTTONS = new ArrayList<JButton>();
        apply = new JButton("Apply");
        original = new JButton("Original");
        my_panel1 = new JPanel();
        my_panel2 = new JPanel();
    }

    public void start() throws CloneNotSupportedException
    {
        my_frame.setTitle("Photo Editor by SaliyPhoto");
        my_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        my_frame.setVisible(true);
        my_label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        my_label.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Dimension size = my_label.getSize();
                if(e.getX()>my_image.getWidth()-20 && e.getX()>my_image.getHeight()-20 ) {
                    System.out.println("inside");
                    PixelImage temp = null;
                    try {
                        temp = (PixelImage) my_image1.Clone();
                    } catch (CloneNotSupportedException e1) {
                        e1.printStackTrace();
                    }
                    int width = (int)(size.getWidth() - temp.getWidth()), height = (int)(size.getHeight() - temp.getHeight());
                    my_image = my_image.resizeImage(temp, e.getX() - width/2, e.getY() - height/2);
                    my_label.setIcon(new ImageIcon(my_image));
                    my_label.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });



        addMenuBar();
        addButtonsTop();
        setDefaultIcon();
        addButtonsBottom();
        createButtonApply();
        imageTransfer();

        my_frame.pack();
    }

    public void addFiles (File [] files) {
        if (my_label.getIcon()!=defaultIcon) {
            clearImage cl = new clearImage();
            cl.actionPerformed(null);
        }
        Image image = null;
        File f = null;
        for (File file : files) {
            try {
                image = ImageIO.read(file);
                f = file;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (image != null) {
            try {
                //my_image = PixelImage.load(f);
                setUp(f.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void imageTransfer () {
        my_imgTransf = new ImageTransferHandler(this);
        my_label.setTransferHandler(my_imgTransf);
    }

    public void setDefaultIcon () {
        try {
            defaultIcon = ImageIO.read(new File("Photoeditor.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        my_label.setIcon(new ImageIcon(defaultIcon));
        my_frame.add(my_label, BorderLayout.CENTER);
        my_frame.pack();
        my_label.setEnabled(true);
    }

    private void addMenuBar (){

        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        cleanMenuItem = new JMenuItem("Clean");
        cleanMenuItem.setAccelerator(KeyStroke.getKeyStroke('C',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        cleanMenuItem.setActionCommand("Clean");

        openMenuItem = new JMenuItem("Open");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke('O',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        openMenuItem.setActionCommand("Open");

        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke('S',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        saveMenuItem.setActionCommand("Save");

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke('E',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        exitMenuItem.setActionCommand("Exit");

        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke('Z',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        undoMenuItem.setActionCommand("Undo");

        JMenuItem redoMenuItem = new JMenuItem("Redo");
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke('Y',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        redoMenuItem.setActionCommand("Redo");

        JMenuItem rotateMenuItem = new JMenuItem("Rotate");
        rotateMenuItem.setAccelerator(KeyStroke.getKeyStroke('R',
                Toolkit.getDefaultToolkit(  ).getMenuShortcutKeyMask(  ), false));
        rotateMenuItem.setActionCommand("Rotate");

        items.add(cleanMenuItem);
        items.add(openMenuItem);
        items.add(saveMenuItem);
        items.add(exitMenuItem);
        items.add(undoMenuItem);
        items.add(redoMenuItem);
        items.add(rotateMenuItem);

        cleanMenuItem.addActionListener(new clearImage());
        openMenuItem.addActionListener(new openImage());
        saveMenuItem.addActionListener(new saveImage());
        exitMenuItem.addActionListener(new exitApp());
        undoMenuItem.addActionListener(new UndoEdit());
        redoMenuItem.addActionListener(new RedoEdit());
        rotateMenuItem.addActionListener(new RotateEdit());

        fileMenu.add(cleanMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        editMenu.add(undoMenuItem);
        editMenu.add(redoMenuItem);
        editMenu.add(rotateMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        my_frame.setJMenuBar(menuBar);

        cleanMenuItem.setEnabled(false);
        saveMenuItem.setEnabled(false);
        undoMenuItem.setEnabled(false);
        redoMenuItem.setEnabled(false);
        rotateMenuItem.setEnabled(false);

    }

    class RotateEdit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PixelImage temp = null;
            try {
                temp= (PixelImage) my_image.Clone();
            } catch (CloneNotSupportedException e1) {
                e1.printStackTrace();
            }
            my_image = my_image.rotate(my_image.getWidth(),my_image.getHeight());
            my_image.rotated= my_image.rotated?false:true;
            my_label.setIcon(new ImageIcon(my_image));
            my_label.repaint();
        }
    }

    class UndoEdit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            invoker.runToPrevious(my_image);
            if (my_label.getIcon()!=new ImageIcon(my_image))
                my_label.setIcon(new ImageIcon(my_image));
            my_label.repaint();
            apply.setEnabled(false);

            if (!invoker.isPossibleRedo())
                items.get(5).setEnabled(false);
            else items.get(5).setEnabled(true);
            if (!invoker.isPossibleUndo())
                items.get(4).setEnabled(false);
            else items.get(4).setEnabled(true);

        }
    }

    class RedoEdit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            invoker.runToNext(my_image);
            if (my_label.getIcon()!=new ImageIcon(my_image))
                my_label.setIcon(new ImageIcon(my_image));
            my_label.repaint();
            apply.setEnabled(false);

            if (!invoker.isPossibleUndo())
                items.get(4).setEnabled(false);
            else items.get(4).setEnabled(true);
            if (!invoker.isPossibleRedo())
                items.get(5).setEnabled(false);
            else items.get(5).setEnabled(true);
        }
    }

    class saveImage implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            RotateEdit re = new RotateEdit();
            PixelImage temp = null;
            temp = invoker.doAll(temp);
            if (my_image.rotated==true)
                temp = temp.rotate(main_image.getWidth(),main_image.getHeight());
            my_file_chooser.showSaveDialog(null);
            try {
                temp.save(my_file_chooser.getSelectedFile());
            }
            catch (final IOException ex) {
                ex.getMessage();
            }
            clearImage cl = new clearImage();
            cl.actionPerformed(e);
        }
    }

    class clearImage implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            my_label.setText(null);

            for (int i = 0; i < FILTER_BUTTONS.size(); i++)
            {
                FILTER_BUTTONS.get(i).setEnabled(false);

            }
            original.setEnabled(false);
            items.get(0).setEnabled(false);
            items.get(2).setEnabled(false);
            items.get(4).setEnabled(false);
            items.get(5).setEnabled(false);
            items.get(6).setEnabled(false);
            apply.setEnabled(false);
            my_label.setIcon(null);
            my_label.setIcon(new ImageIcon(defaultIcon));

            filtersCommands.removeAll(filtersCommands);
            my_frame.pack();
            my_frame.repaint();
        }
    }

    class openImage implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            if (my_label.getIcon()!=defaultIcon) {
                clearImage cl = new clearImage();
                cl.actionPerformed(e);
            }
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
            my_file_chooser.setFileFilter(filter);
            my_file_chooser.setAcceptAllFileFilterUsed(false);
            my_file_chooser.showOpenDialog(null);
            try {
                setUp(my_file_chooser.getSelectedFile().toString());

            }
            catch (final IOException ex) {
                ex.getMessage();
            }
        }
    }

    public  void setUp(String file) throws IOException {

        PixelImageProxy temp = new PixelImageProxy(file);
        my_image = temp.display(my_label);

        items.get(0).setEnabled(true);
        items.get(2).setEnabled(true);
        items.get(6).setEnabled(true);

        original.setEnabled(true);
        for (int i = 0; i < FILTER_BUTTONS.size(); i++) {
            FILTER_BUTTONS.get(i).setEnabled(true);
        }

        try {
            my_image1 = (PixelImage) my_image.Clone();
            main_image = (PixelImage) my_image.Clone();
            invoker = new Invoker(main_image);
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }
        setFiltersCommands();
    }

    class exitApp implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }

    }

    private void addButtonsBottom()
    {
        my_panel2.add(apply);
        my_frame.add(my_panel2, BorderLayout.SOUTH);
        my_frame.pack();
    }

    private void createButtonApply()
    {
        apply.addActionListener(new ActionListener()
        {
            /**
             * Handles an ActionEvent for the apply button.
             *
             * @param the_event The event.
             */
            public void actionPerformed(final ActionEvent the_event)
            {

                my_image = cloned;
                cloned = null;
                my_label.setIcon(new ImageIcon(my_image));
                my_label.repaint();
                for (Command com: filtersCommands) {
                    if (com.getFilt().getClass() == context.getStrategy().getClass())
                        invoker.addCommand(com);
                }
                //check availability of undo and redo items
                if (!invoker.isPossibleRedo())
                    items.get(5).setEnabled(false);
                else items.get(5).setEnabled(true);
                if (!invoker.isPossibleUndo())
                    items.get(4).setEnabled(false);
                else items.get(4).setEnabled(true);
                apply.setEnabled(false);
            }
        });
        apply.setEnabled(false);
    }

    private void addButtonsTop() throws CloneNotSupportedException
    {
        my_panel1.add(original);
        for (int i = 0; i < FILTER_BUTTONS.size(); i++)
        {
            my_panel1.add(FILTER_BUTTONS.get(i));
        }

        JScrollPane scroll = new JScrollPane(my_panel1);
        Dimension size = new Dimension();
        size.setSize(800, 120);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(size);
        my_frame.getContentPane().add(scroll,BorderLayout.NORTH);
        my_frame.pack();
    }

    private void createButtonOriginal() throws CloneNotSupportedException
    {

        PixelImage temp = null, temp1 = null;
        try {
            temp = PixelImage.load(new File("original.png"));// eventually C:\\ImageTest\\pic2.jpg
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        temp1 = temp.resizeImage(temp,100,70);
        original.setIcon(new ImageIcon(temp1));
        original.setHorizontalTextPosition(SwingConstants.CENTER);
        original.setVerticalTextPosition(SwingConstants.TOP);

        //button.setSize(50,50);
        original.addActionListener(new ActionListener()
        {
            /**
             * Handles an ActionEvent for button Original.
             *
             * @param the_event The event.
             */
            public void actionPerformed(final ActionEvent the_event)
            {
                apply.setEnabled(false);
                my_label.setIcon(new ImageIcon(main_image));
                my_label.repaint();
            }
        });
        original.setEnabled(false);
    }

    private void createButtonFilter(final Filter the_object) throws CloneNotSupportedException
    {
        JButton button = new JButton(the_object.getDescription());

        PixelImage temp = null, temp1 = null;
        try {
            temp = PixelImage.load(new File(the_object.getFileName()));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        temp1 = temp.resizeImage(temp,100,70);
        button.setIcon(new ImageIcon(temp1));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.TOP);
        FILTER_BUTTONS.add(button);
        button.addActionListener(new ActionListener()
        {
            /**
             * Handles an ActionEvent for the filter buttons.
             *
             * @param the_event The event.
             */
            public void actionPerformed(final ActionEvent the_event)
            {
                try {
                    context.setStrategy(the_object);
                    cloned = (PixelImage) my_image.Clone();
                    context.execute(cloned);
                    my_label.setIcon(new ImageIcon(cloned));
                    my_label.repaint();
                    apply.setEnabled(true);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        });
        button.setEnabled(false);
    }
}
