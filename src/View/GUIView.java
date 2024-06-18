package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class GUIView extends JFrame implements ActionListener, KeyListener, MouseListener, IView {
  private final List<IViewListener> myListeners;
  private final JLabel enterTextLabel;
  private final JButton sendData;
  private final JTextArea enterTextArea;
  private final JButton getData;

  public GUIView() {
    setSize(new Dimension(800, 400));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.myListeners = new ArrayList<>();

    setLayout(new BorderLayout());
    this.enterTextLabel = new JLabel("Enter text: ");
    this.sendData = new JButton("Send Data");
    this.enterTextArea = new JTextArea("Enter your text here.");
    this.getData = new JButton("Get Data");


    this.getData.setActionCommand("getData");
    this.sendData.setActionCommand("sendData");

    this.getData.addActionListener(this);
    this.sendData.addActionListener(this);
    this.addKeyListener(this);
    this.addKeyListener(this);
    this.enterTextLabel.addMouseListener(this);

    add(this.enterTextLabel, BorderLayout.NORTH);
    add(this.getData, BorderLayout.WEST);
    add(this.sendData, BorderLayout.EAST);
    add(this.enterTextArea, BorderLayout.SOUTH);

    setFocusable(true);
    requestFocus();
    pack();
  }

  @Override
  public void render() {

  }

  @Override
  public String readMessage() {
    return "";
  }

  @Override
  public void addViewListener(IViewListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.myListeners.add(listener);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // command pattern
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }

  @Override
  public void mouseClicked(MouseEvent e) {

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
}