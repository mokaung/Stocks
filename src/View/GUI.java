package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import portfolio.IPortfolioV2;


/**
 * Renders the program with GUI.
 */
public class GUI extends JFrame implements IView, ActionListener, ItemListener, ListSelectionListener {
  private final List<IViewListener> myListeners;
  private final JButton selectDate;
  private final JTextField textField;
  private final JPanel mainPanel;
  private final JScrollPane mainScrollPane;
  private final JButton getValue;
  private final JButton addStockButton;
  private final JButton createPortfolioButton;
  private final JButton buyStock;
  private final JButton sellStock;

  private JComboBox<Integer> yearComboBox;
  private JComboBox<Integer> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<IPortfolioV2> portfolioComboBox;
  private JPanel portfolioValuePanel;

  private JTextField portfolioValueField;
  private final JTextField portfolioNameField;

  private JPanel createPortfolioPanel;
  private JPanel createBuyStockPanel;
  private ArrayList<JPanel> stockInputPanels;

  private LocalDate selectedDate;

  private final JTextField stock;
  private final JTextField amount;
  private final JTextField date;

  public GUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 300);

    this.myListeners = new ArrayList<>();
    this.stockInputPanels = new ArrayList<>();

    this.yearComboBox = new JComboBox<Integer>();
    for (int i = 2000; i <= 2024; i++) {
      yearComboBox.addItem(i);
    }
    this.portfolioComboBox = new JComboBox<>();

    // make work
//    this.portfolioComboBox.addItem();

    portfolioValuePanel = new JPanel();
    this.stock = new JTextField();
    this.amount = new JTextField();
    this.date = new JTextField();

    this.getValue = new JButton("Get Value");
    this.getValue.setActionCommand("getValue");
    this.getValue.addActionListener(this);

    this.buyStock = new JButton("Buy Stock");
    this.buyStock.setActionCommand("buyStock");
    this.buyStock.addActionListener(this);

    this.sellStock = new JButton("Sell Stock");
    this.sellStock.setActionCommand("sellStock");
    this.sellStock.addActionListener(this);

    this.portfolioNameField = new JTextField();

    createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.setActionCommand("createPortfolio");
    createPortfolioButton.addActionListener(this);

    addStockButton = new JButton("+ Add Stock");
    // buy vs add stock
    addStockButton.setActionCommand("buyStock");
    addStockButton.addActionListener(this);

    selectDate = new JButton("Select Date");
    this.selectDate.setActionCommand("selectDate");
    this.selectDate.addActionListener(this);

    textField = new JTextField();
    textField.setEditable(false);

    createBuyStockPanel = new JPanel();
    createPortfolioPanel = new JPanel();
    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    //for getPortfolioValue
    getPortfolioValueWindow();

    //for createPortfolio
    createPortfolioWindow();

    createBuyStockWindow();

    mainPanel.add(new JScrollPane(textField));

    setFocusable(true);
    requestFocus();
  }


  @Override
  public void render(boolean b) {
    setVisible(b);
  }

  private void getPortfolioValueWindow() {
    // Add a panel for portfolio value selection
    portfolioValuePanel.setBorder(BorderFactory.createTitledBorder("Get Portfolio Value"));
    portfolioValuePanel.setLayout(new BoxLayout(portfolioValuePanel, BoxLayout.Y_AXIS));
    mainPanel.add(portfolioValuePanel);

    // Add portfolio selection components
    portfolioValuePanel.add(new JLabel("Select Portfolio:"));
    portfolioComboBox = new JComboBox<>();
    portfolioValuePanel.add(portfolioComboBox);

    selectDate(portfolioValuePanel);

    portfolioValuePanel.add(getValue);

    JPanel valueDisplayPanel = new JPanel();
    valueDisplayPanel.setLayout(new FlowLayout());
    valueDisplayPanel.add(new JLabel("Selected Portfolio Value: $"));
    portfolioValueField = new JTextField(10);
    portfolioValueField.setEditable(false);
    valueDisplayPanel.add(portfolioValueField);
    portfolioValuePanel.add(valueDisplayPanel);

  }

  private void createPortfolioWindow() {
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    createPortfolioPanel.setLayout(new BoxLayout(createPortfolioPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createPortfolioPanel);

    // Add components for portfolio creation
    createPortfolioPanel.add(new JLabel("Portfolio Name:"));
    createPortfolioPanel.add(portfolioNameField);

    // Add the initial stock input panel
    JPanel stockInputPanel = new JPanel();
    stockInputPanel.setLayout(new BoxLayout(stockInputPanel, BoxLayout.Y_AXIS));
    createPortfolioPanel.add(stockInputPanel);
    addStockInputPanel(stockInputPanel);

    createPortfolioPanel.add(addStockButton);
    createPortfolioPanel.add(createPortfolioButton);
  }

  private void createBuyStockWindow() {
    createBuyStockPanel.setBorder(BorderFactory.createTitledBorder("Buy/Sell stock"));
    createBuyStockPanel.setLayout(new BoxLayout(createBuyStockPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createBuyStockPanel);

    // Add components for portfolio creation
    createBuyStockPanel.add(new JLabel("Portfolio Name:"));
    createBuyStockPanel.add(portfolioNameField);

    // Add the initial stock input panel
    JPanel stockInputPanel = new JPanel();
    stockInputPanel.setLayout(new BoxLayout(stockInputPanel, BoxLayout.Y_AXIS));
    createBuyStockPanel.add(stockInputPanel);
    addStockInputPanel(stockInputPanel);

    createBuyStockPanel.add(buyStock);
    createBuyStockPanel.add(sellStock);
  }


  private void addStockInputPanel(JPanel createPanel) {
    JPanel stockPanel = new JPanel(new FlowLayout());
    JTextField stockField = new JTextField(10);
    JTextField sharesField = new JTextField(5);
    selectDate(stockPanel);
    stockPanel.add(new JLabel("Stock Ticker: "));
    stockPanel.add(stockField);
    stockPanel.add(new JLabel("Shares:"));
    stockPanel.add(sharesField);
    createPanel.add(stockPanel);
    stockInputPanels.add(stockPanel);
    createPanel.revalidate();
    createPanel.repaint();
  }

  /**
   * This method updates the combo boxes of the dates to allow selection of only valid dates.
   * Therefore, the method accounts for month day differences and leap years.
   *
   * @throws NullPointerException when somehow the selection in the combo boxes are null.
   */
  private void updateDays() throws NullPointerException {
    try {
      int year = (int) yearComboBox.getSelectedItem();
      int month = (int) monthComboBox.getSelectedItem();
      int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

      dayComboBox.removeAllItems();
      for (int i = 1; i <= daysInMonth; i++) {
        dayComboBox.addItem(i);
      }
    } catch (NullPointerException e) {
      showError(e.getMessage());
    }
  }

  private void selectDate(JPanel panel) {
    // Add date selection components
    JPanel dateSelectionPanel = new JPanel();
    dateSelectionPanel.setLayout(new FlowLayout());

    // For years
    dateSelectionPanel.add(new JLabel("Year:"));
    yearComboBox = new JComboBox<>();
    for (int i = 2500; i >= 0; i--) {
      yearComboBox.addItem(i);
    }
    dateSelectionPanel.add(yearComboBox);

    // For months
    dateSelectionPanel.add(new JLabel("Month:"));
    monthComboBox = new JComboBox<>();
    for (Month month : Month.values()) {
      monthComboBox.addItem(month.getValue());
    }
    dateSelectionPanel.add(monthComboBox);

    // For days
    dateSelectionPanel.add(new JLabel("Day:"));
    dayComboBox = new JComboBox<>();
    updateDays();
    dateSelectionPanel.add(dayComboBox);

    yearComboBox.addItemListener(this);
    monthComboBox.addItemListener(this);

    panel.add(dateSelectionPanel);
  }

  private LocalDate getSelectedDate() {
    try {
      int year = (Integer) yearComboBox.getSelectedItem();
      int month = (Integer) monthComboBox.getSelectedItem();
      int day = (Integer) dayComboBox.getSelectedItem();
      return LocalDate.of(year, month, day);
    } catch (Exception e) {
      showError("Invalid date selected.");
    }
    return null;
  }


  //temp, change to switch for future buttons
  @Override
  public void itemStateChanged(ItemEvent arg) {
    if (arg.getStateChange() == ItemEvent.SELECTED) {
      updateDays();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "createPortfolio":
        fireCreatePortfolioEvent();
        break;
      case "buyStock":
        fireSellStock(false);
        break;
      case "sellStock":
        fireSellStock(true);
        break;
      case "getValue":
        fireGetValue();
        break;
      case "savePortfolio":
        fireSavePortfolio();
        break;
      case "loadPortfolio":
        break;
    }
  }

  private void fireSellStock(boolean sell) {
    String name = this.portfolioNameField.getText();
    String stock = this.stock.getText();
    double amount = Double.parseDouble(this.amount.getText());
    LocalDate date = LocalDate.parse(this.date.getText());

    if (sell) {
      amount = amount * -1;
    }
    for (IViewListener listener : myListeners) {
      listener.handleSellStock(stock, amount, name, date);
    }
  }

  private void fireSavePortfolio() {
    String name = this.portfolioNameField.getText();
    for (IViewListener listener : myListeners) {
      listener.handleSavePortfolio(name);
    }
  }

  private void fireGetValue() {
    String name = this.portfolioNameField.getText();
    LocalDate date = LocalDate.parse(this.date.getText());
    for (IViewListener listener : myListeners) {
      listener.handleGetPortfolioValue(name, date);
    }
  }

  private void fireCreatePortfolioEvent() {
    String name = this.portfolioNameField.getText();
    String stock = this.stock.getText();
    double amount = (double) portfolioComboBox.getSelectedItem();
    LocalDate date = LocalDate.parse(this.date.getText());

    for (IViewListener listener : myListeners) {
      listener.handleCreatePortfolio(stock, amount, name, date);
    }
  }

  //MAKE THIS WORK
  private void getPortfolioValue() throws IOException {
//    String selectedPortfolio = (String) portfolioComboBox.getSelectedItem();
//    if (selectedPortfolio == null || selectedPortfolio.isEmpty()) {
//      showError("Please select a portfolio.");
//    }
//
//    LocalDate selectedDate = getSelectedDate();
//    if (selectedDate == null) {
//      showError("Please select a valid date.");
//    }
//
//    try {
//      double value = controller.getPortfolioValueController(selectedPortfolio, selectedDate);
//      portfolioValueField.setText(String.format("%.2f", value));
//    } catch (Exception e) {
//      showError("Error retrieving portfolio value: " + e.getMessage());
//    }
  }

  public void createPortfolio() throws IOException {
//    String portfolioName = portfolioNameField.getText();
//    if (portfolioName.isEmpty()) {
//      showError("Portfolio name cannot be empty.");
//    }
//
//    boolean isFirstStock = true;
//    for (JPanel currentStockPanel : stockInputPanels) {
//      Component[] components = currentStockPanel.getComponents();
//      JTextField stockField = (JTextField) components[3];
//      JTextField sharesField = (JTextField) components[5];
//      JComboBox<Integer> yearComboBox = (JComboBox<Integer>) components[6];
//      JComboBox<Integer> monthComboBox = (JComboBox<Integer>) components[7];
//      JComboBox<Integer> dayComboBox = (JComboBox<Integer>) components[8];
//
//      String stockTicker = stockField.getText();
//      if (stockTicker.isEmpty()) {
//        showError("Stock ticker cannot be empty.");
//      }
//
//      int shareCount = -1;
//      try {
//        shareCount = Integer.parseInt(sharesField.getText());
//        if (shareCount <= 0) {
//          throw new IllegalArgumentException("Integer not positive.");
//        }
//      } catch (Exception e) {
//        showError("Shares must be valid positive integers.");
//      }
//
//      LocalDate purchaseDate = LocalDate.of(
//              (Integer) yearComboBox.getSelectedItem(),
//              (Integer) monthComboBox.getSelectedItem(),
//              (Integer) dayComboBox.getSelectedItem()
//      );
//
//      if (isFirstStock) {
//        controller.createPortfolio(portfolioName, stockTicker, shareCount, purchaseDate);
//        isFirstStock = false;
//      } else {
//        controller.addToPortfolio(portfolioName, stockTicker, shareCount, purchaseDate);
//      }
//    }
//    // Reset the portfolio creation form after successful creation
//    resetPortfolioForm();
  }


  public void addToPortfolio() {

  }


  private void resetPortfolioForm() {
    portfolioNameField.setText("");
    stockInputPanels.forEach(panel -> panel.getParent().remove(panel));
    stockInputPanels.clear();
    addStockInputPanel(createPortfolioPanel);
    createPortfolioPanel.revalidate();
    createPortfolioPanel.repaint();
  }


  @Override
  public void addViewListener(IViewListener listener) {

  }

  public void requestFocus() {

  }

  public void showError(String errorMessage) {
    JOptionPane.showMessageDialog(this, "Error: " + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void valueChanged(ListSelectionEvent e) {

  }
}


