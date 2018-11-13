package UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GUI {


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

    private BufferedImage defaultIcon;

    private JMenuBar menuBar;

    public GUI() {
        FILTER_BUTTONS = new ArrayList<JButton>();
        apply = new JButton("Apply");
        original = new JButton("Original");
        my_panel1 = new JPanel();
        my_panel2 = new JPanel();
    }

    public void start()
    {
        my_frame.setTitle("Photo Editor");
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

        addMenuBar();

        my_frame.pack();
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
}

