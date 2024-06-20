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

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ControllerImplGUI;
import controller.IController;


/**
 * Renders the program with GUI.
 */
public class GUI implements IView, ActionListener, ItemListener, ListSelectionListener {
  private JFrame frame;
  private JTextField textField;
  private JButton button;
  private JTextArea textArea;
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;

  private JComboBox<Integer> yearComboBox;
  private JComboBox<Integer> monthComboBox;
  private JComboBox<Integer> dayComboBox;
  private JComboBox<String> portfolioComboBox;
  private JPanel portfolioValuePanel;
  private ControllerImplGUI controller;

  private JTextField portfolioValueField;
  private JTextField portfolioNameField;

  private JPanel createPortfolioPanel;
  private ArrayList<JPanel> stockInputPanels;

  private JComboBox<String> savePortfolioComboBox;
  private JComboBox<String> loadPortfolioComboBox;

  private LocalDate selectedDate;

  public GUI(String title, ControllerImplGUI controller) {
    this.controller = controller;
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 1000);

    textField = new JTextField(20);
    button = new JButton("Select Date");
    textArea = new JTextArea(10, 30);
    textArea.setEditable(false);

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    frame.add(mainScrollPane);

    //for createPortfolio
    //for getPortfolioValue
    getPortfolioValueWindow();

    createPorfolioWindow();

    mainPanel.add(new JScrollPane(textArea));
  }


  @Override
  public void render() {
    frame.setVisible(true);
  }


  private void getPortfolioValueWindow() {
    // Add a panel for portfolio value selection
    portfolioValuePanel = new JPanel();
    portfolioValuePanel.setBorder(BorderFactory.createTitledBorder("Get Portfolio Value"));
    portfolioValuePanel.setLayout(new BoxLayout(portfolioValuePanel, BoxLayout.Y_AXIS));
    mainPanel.add(portfolioValuePanel);

    // Add portfolio selection components
    portfolioValuePanel.add(new JLabel("Select Portfolio:"));
    portfolioComboBox = new JComboBox<>();
    portfolioValuePanel.add(portfolioComboBox);

    selectDate(portfolioValuePanel);

    button = new JButton("Get Portfolio Value");
    button.setActionCommand("GetPortfolioValue");
    button.addActionListener(this);
    portfolioValuePanel.add(button);

    JPanel valueDisplayPanel = new JPanel();
    valueDisplayPanel.setLayout(new FlowLayout());
    valueDisplayPanel.add(new JLabel("Selected Portfolio Value: $"));
    portfolioValueField = new JTextField(10);
    portfolioValueField.setEditable(false);
    valueDisplayPanel.add(portfolioValueField);
    portfolioValuePanel.add(valueDisplayPanel);

  }

  private void createPorfolioWindow() {
    createPortfolioPanel = new JPanel();
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    createPortfolioPanel.setLayout(new BoxLayout(createPortfolioPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createPortfolioPanel);

    // Add components for portfolio creation
    createPortfolioPanel.add(new JLabel("Portfolio Name:"));
    portfolioNameField = new JTextField(20);
    createPortfolioPanel.add(portfolioNameField);

    // Add the initial stock input panel
    JPanel stockInputPanel = new JPanel();
    stockInputPanel.setLayout(new BoxLayout(stockInputPanel, BoxLayout.Y_AXIS));
    createPortfolioPanel.add(stockInputPanel);
    addStockInputPanel(stockInputPanel);

    // Add button to add more stocks
    JButton addStockButton = new JButton("+ Add Stock");
    addStockButton.setActionCommand("AddStock");
    addStockButton.addActionListener(this);
    createPortfolioPanel.add(addStockButton);

    // Add button to create portfolio
    JButton createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.setActionCommand("CreatePortfolio");
    createPortfolioButton.addActionListener(this);
    createPortfolioPanel.add(createPortfolioButton);
  }

  private void buySellStocksWindow() {
    JPanel buySellPanel = new JPanel();
    buySellPanel.setBorder(BorderFactory.createTitledBorder("Buy/Sell Stocks"));
    buySellPanel.setLayout(new BoxLayout(buySellPanel, BoxLayout.Y_AXIS));
    mainPanel.add(buySellPanel);

    // Add portfolio selection components
    buySellPanel.add(new JLabel("Select Portfolio:"));
    JComboBox<String> portfolioComboBox = new JComboBox<>();
    buySellPanel.add(portfolioComboBox);

    // Reuse selectDate method
    selectDate(buySellPanel);

    // Add stock input fields
    JPanel stockInputPanel = new JPanel(new FlowLayout());
    JTextField stockField = new JTextField(10);
    JTextField sharesField = new JTextField(5);

    stockInputPanel.add(new JLabel("Stock Ticker: $"));
    stockInputPanel.add(stockField);
    stockInputPanel.add(new JLabel("Shares:"));
    stockInputPanel.add(sharesField);

    buySellPanel.add(stockInputPanel);

    // Add Buy and Sell buttons
    JButton buyButton = new JButton("Buy Stock");
    buyButton.setActionCommand("BuyStock");
    buyButton.addActionListener(this);

    JButton sellButton = new JButton("Sell Stock");
    sellButton.setActionCommand("SellStock");
    sellButton.addActionListener(this);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new FlowLayout());
    bottomPanel.add(buyButton);
    bottomPanel.add(sellButton);

    buySellPanel.add(bottomPanel);
  }

  private void saveLoadPortfoliosWindow() {
    JPanel saveLoadPanel = new JPanel();
    saveLoadPanel.setBorder(BorderFactory.createTitledBorder("Save/Load Portfolios"));
    saveLoadPanel.setLayout(new GridLayout(1, 2));
    mainPanel.add(saveLoadPanel);

    // Save Portfolios Panel
    JPanel savePanel = new JPanel();
    savePanel.setBorder(BorderFactory.createTitledBorder("Save Portfolios"));
    savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
    saveLoadPanel.add(savePanel);

    savePanel.add(new JLabel("Select Portfolio to Save:"));
    savePortfolioComboBox = new JComboBox<>();
    savePanel.add(savePortfolioComboBox);

    JButton saveButton = new JButton("Save Portfolio");
    saveButton.setActionCommand("SavePortfolio");
    saveButton.addActionListener(this);
    savePanel.add(saveButton);

    // Load Portfolios Panel
    JPanel loadPanel = new JPanel();
    loadPanel.setBorder(BorderFactory.createTitledBorder("Load Portfolios"));
    loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.Y_AXIS));
    saveLoadPanel.add(loadPanel);

    loadPanel.add(new JLabel("Select Portfolio to Load:"));
    loadPortfolioComboBox = new JComboBox<>();
    ArrayList<String> loadableNames = controller.listLoadablePortfolios();
    for (String name: loadableNames) {
      loadPortfolioComboBox.addItem(name);
    }
//    // Example portfolios - Replace this with actual method to get portfolios from the rest of the program
//    loadPortfolioComboBox.addItem("Example Portfolio 1");
//    loadPortfolioComboBox.addItem("Example Portfolio 2");
//    loadPortfolioComboBox.addItem("Example Portfolio 3");
    loadPanel.add(loadPortfolioComboBox);

    JButton loadButton = new JButton("Load Portfolio");
    loadButton.setActionCommand("LoadPortfolio");
    loadButton.addActionListener(this);
    loadPanel.add(loadButton);
  }



  private void addStockInputPanel(JPanel createPanel) {
    JPanel stockPanel = new JPanel(new FlowLayout());
    JTextField stockField = new JTextField(10);
    JTextField sharesField = new JTextField(5);
    selectDate(stockPanel);
    stockPanel.add(new JLabel("Stock Ticker: $"));
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
      int year = (Integer) yearComboBox.getSelectedItem();
      int month = (Integer) monthComboBox.getSelectedItem();
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
        return null;
    }
  }


  //temp, change to switch for future buttons
  @Override
  public void itemStateChanged(ItemEvent arg) {
    if (arg.getStateChange() == ItemEvent.SELECTED) {
      updateDays();
    }
  }

  @Override
  public void actionPerformed(ActionEvent arg) {
    try {
      switch (arg.getActionCommand()) {
        case "GetPortfolioValue":
          addToPortfolio();
          break;
        case "CreatePortfolio":
          createPortfolio();
          break;
        case "PopulateStock":
          // Example logic to add the new ticker to the list
          String newTicker = ((JTextField) ((JPanel) ((JButton) arg.getSource()).getParent()).getComponent(1)).getText();
          if (!newTicker.isEmpty()) {
//            tickerListModel.addElement(newTicker);
            // Implement the logic to populate the stock using the controller
            // controller.populateStock(newTicker);
          }
          break;
        case "BuyStock":
          // Implement the logic to buy the stock using the controller
          // String portfolio = (String) portfolioComboBox.getSelectedItem();
          // LocalDate date = getSelectedDate(yearInputs.get(0), monthInputs.get(0), dayInputs.get(0));
          // String ticker = stockField.getText();
          // int shares = Integer.parseInt(sharesField.getText());
          // controller.buyStock(portfolio, ticker, shares, date);
          break;
        case "SellStock":
          // Implement the logic to sell the stock using the controller
          // String portfolio = (String) portfolioComboBox.getSelectedItem();
          // LocalDate date = getSelectedDate(yearInputs.get(0), monthInputs.get(0), dayInputs.get(0));
          // String ticker = stockField.getText();
          // int shares = Integer.parseInt(sharesField.getText());
          // controller.sellStock(portfolio, ticker, shares, date);
          break;
        case "SavePortfolio":
           String savePortfolioSelection = (String) savePortfolioComboBox.getSelectedItem();
           try{
             controller.handleSavePortfolio(savePortfolioSelection);
             JOptionPane.showMessageDialog(frame, "Successfully saved portfolio!", "Success", JOptionPane.INFORMATION_MESSAGE);
           } catch (IllegalArgumentException e) {
             showError(e.getMessage());
           }
          break;
        case "LoadPortfolio":
           String loadPortfolioSelection = (String) loadPortfolioComboBox.getSelectedItem();
          try {
            controller.handleLoadPortfolio(loadPortfolioSelection);
            JOptionPane.showMessageDialog(frame, "Successfully loaded portfolio!", "Success", JOptionPane.INFORMATION_MESSAGE);
          } catch (IllegalArgumentException e) {
            showError(e.getMessage());
          }
          break;
        default:
          showError("Something's not right...");
          break;
      }
    } catch (IOException e) {

    }
  }

  //MAKE THIS WORK
  private void getPortfolioValue() throws IOException {
    String selectedPortfolio = (String) portfolioComboBox.getSelectedItem();
    if (selectedPortfolio == null || selectedPortfolio.isEmpty()) {
      showError("Please select a portfolio.");
    }

    LocalDate selectedDate = getSelectedDate();
    if (selectedDate == null) {
      showError("Please select a valid date.");
    }
    try {
//      double value = controller.getPortfolioValueController(selectedPortfolio, selectedDate);
//      portfolioValueField.setText(String.format("%.2f", value));
    } catch (Exception e) {
      showError("Error retrieving portfolio value: " + e.getMessage());
    }
  }

  public void createPortfolio() throws IOException {
    String portfolioName = portfolioNameField.getText();
    if (portfolioName.isEmpty()) {
      showError("Portfolio name cannot be empty.");
    }

    boolean isFirstStock = true;
    for (JPanel currentStockPanel : stockInputPanels) {
      Component[] components = currentStockPanel.getComponents();
      JTextField stockField = (JTextField) components[3];
      JTextField sharesField = (JTextField) components[5];
      JComboBox<Integer> yearComboBox = (JComboBox<Integer>) components[6];
      JComboBox<Integer> monthComboBox = (JComboBox<Integer>) components[7];
      JComboBox<Integer> dayComboBox = (JComboBox<Integer>) components[8];

      String stockTicker = stockField.getText();
      if (stockTicker.isEmpty()) {
        showError("Stock ticker cannot be empty.");
      }

      int shareCount = -1;
      try {
        shareCount = Integer.parseInt(sharesField.getText());
        if (shareCount <= 0) {
          throw new IllegalArgumentException("Integer not positive.");
        }
      } catch (Exception e) {
        showError("Shares must be valid positive integers.");
      }

      LocalDate purchaseDate = LocalDate.of(
              (Integer) yearComboBox.getSelectedItem(),
              (Integer) monthComboBox.getSelectedItem(),
              (Integer) dayComboBox.getSelectedItem()
      );

      if (isFirstStock) {
//        controller.createPortfolio(portfolioName, stockTicker, shareCount, purchaseDate);
        isFirstStock = false;
      } else {
//        controller.addToPortfolio(portfolioName, stockTicker, shareCount, purchaseDate);
      }
    }
    // Reset the portfolio creation form after successful creation
    resetPortfolioForm();
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
    JOptionPane.showMessageDialog(frame, "Error: " + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void setVisible() {

  }

  @Override
  public void valueChanged(ListSelectionEvent e) {

  }
}


