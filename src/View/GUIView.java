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
  private final JButton createPortfolio;
  private final JButton buyStock;
  private final JButton sellStock;
  private final JButton getValue;
  private final JButton savePortfolio;
  private final JButton getPortfolio;


  public GUIView() {
    setSize(new Dimension(800, 400));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    this.myListeners = new ArrayList<>();

    setLayout(new FlowLayout());
    this.enterTextLabel = new JLabel("Enter text: ");
    this.createPortfolio = new JButton("Create Portfolio");
    this.buyStock = new JButton("Buy Stock");
    this.sellStock = new JButton("Sell Stock");
    this.getValue = new JButton("Get Value");
    this.savePortfolio = new JButton("Save Portfolio");
    this.getPortfolio = new JButton("Get Portfolio");

    this.sellStock.setActionCommand("sellStock");
    this.buyStock.setActionCommand("buyStock");
    this.createPortfolio.setActionCommand("createPortfolio");
    this.getValue.setActionCommand("getValue");
    this.savePortfolio.setActionCommand("savePortfolio");
    this.getPortfolio.setActionCommand("getPortfolio");

    this.getValue.addActionListener(this);
    this.sellStock.addActionListener(this);
    this.buyStock.addActionListener(this);
    this.createPortfolio.addActionListener(this);
    this.savePortfolio.addActionListener(this);
    this.getPortfolio.addActionListener(this);

    this.addKeyListener(this);
    this.enterTextLabel.addMouseListener(this);

    add(this.enterTextLabel);
    add(this.buyStock);
    add(this.sellStock);
    add(this.createPortfolio);
    add(this.getValue);
    add(this.savePortfolio);
    add(this.getPortfolio);

    setVisible(true);
    setFocusable(true);
    requestFocus();
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

  private void fireCreatePortfolioEvent() {
    for (IViewListener listener : myListeners) {

      listener.handleCreatePortfolio();
    }
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