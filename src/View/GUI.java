package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import controller.ControllerImplGUI;
import controller.IController;


/**
 * Renders the program with GUI.
 */
public class GUI implements IView, ActionListener, ItemListener {
  private JFrame frame;
  private JButton button;
  private JTextField textField;
  private JTextArea textArea;
  private JPanel mainPanel;
  private JScrollPane mainScrollBar;
  private DefaultListModel<String> tickerListModel;
  private JList<String> tickerList;
  private JComboBox<String> portfolioComboBox;
  private JPanel portfolioValuePanel;
  private ControllerImplGUI controller;
  private JTextField portfolioValueField;
  private JTextField portfolioNameField;

  private JPanel createPortfolioPanel;
  private ArrayList<JPanel> stockInputPanels;
  private ArrayList<JTextField> stockTickerFields;
  private ArrayList<JTextField> stockSharesFields;
  private ArrayList<JComboBox<Integer>> yearInputs;
  private ArrayList<JComboBox<Integer>> monthInputs;
  private ArrayList<JComboBox<Integer>> dayInputs;

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
    mainScrollBar = new JScrollPane(mainPanel);
    frame.add(mainScrollBar);

    stockInputPanels = new ArrayList<>();
    stockTickerFields = new ArrayList<>();
    stockSharesFields = new ArrayList<>();
    yearInputs = new ArrayList<>();
    monthInputs = new ArrayList<>();
    dayInputs = new ArrayList<>();

    populateStocksWindow();
    buySellStocksWindow();
    saveLoadPortfoliosWindow();

    createPortfolioWindow();
    buySellStocksWindow();
    //for createPortfolio
    //for getPortfolioValue
    queryPortfolioWindow();
    mainPanel.add(new JScrollPane(textArea));
  }


  @Override
  public void render() {
    frame.setVisible(true);
  }

  private void populateStocksWindow() {
    JPanel populateStocksPanel = new JPanel();
    populateStocksPanel.setBorder(BorderFactory.createTitledBorder("Populate Stocks"));
    populateStocksPanel.setLayout(new FlowLayout());

    JPanel inputPanel = new JPanel(new FlowLayout());
    inputPanel.add(new JLabel("Stock Ticker: $"));
    JTextField stockTickerField = new JTextField(10);
    inputPanel.add(stockTickerField);

    JButton populateButton = new JButton("Populate Stock");
    populateButton.setActionCommand("PopulateStock");
    populateButton.addActionListener(this);
    inputPanel.add(populateButton);

    populateStocksPanel.add(inputPanel, BorderLayout.NORTH);

    // Initialize the ticker list model and list
    tickerListModel = new DefaultListModel<>();
    // Example tickers - Replace this with actual method to get tickers from the rest of the program
    tickerListModel.addElement("AAPL");
    tickerListModel.addElement("GOOGL");
    tickerListModel.addElement("MSFT");

    tickerList = new JList<>(tickerListModel);

    JPanel listPanel = new JPanel(new BorderLayout());
    listPanel.add(new JLabel("Populated Stocks"), BorderLayout.NORTH);
    JScrollPane listScrollPane = new JScrollPane(tickerList);
    listPanel.add(listScrollPane, BorderLayout.CENTER);

    populateStocksPanel.add(listPanel, BorderLayout.CENTER);

    mainPanel.add(populateStocksPanel, 0); // Add this panel at the top
  }

  private void queryPortfolioWindow() {
    // Add a panel for portfolio value selection
    portfolioValuePanel = new JPanel();
    portfolioValuePanel.setBorder(BorderFactory.createTitledBorder("Query Portfolio"));
    portfolioValuePanel.setLayout(new BoxLayout(portfolioValuePanel, BoxLayout.Y_AXIS));
    mainPanel.add(portfolioValuePanel);

    // Add portfolio selection components
    portfolioValuePanel.add(new JLabel("Select Portfolio:"));
    portfolioComboBox = new JComboBox<>();
    portfolioValuePanel.add(portfolioComboBox);

    selectDate(portfolioValuePanel);

    button = new JButton("Get Portfolio Stats");
    button.setActionCommand("QueryPortfolio");
    button.addActionListener(this);
    portfolioValuePanel.add(button);

    JPanel valueDisplayPanel = new JPanel();
    valueDisplayPanel.setLayout(new FlowLayout());
    valueDisplayPanel.add(new JLabel("Portfolio Statistics: "));
    portfolioValueField = new JTextField(10);
    portfolioValueField.setEditable(false);
    valueDisplayPanel.add(portfolioValueField);
    portfolioValuePanel.add(valueDisplayPanel);

  }

  private void createPortfolioWindow() {
    createPortfolioPanel = new JPanel();
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    createPortfolioPanel.setLayout(new BoxLayout(createPortfolioPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createPortfolioPanel);

    addStockInputPanel(createPortfolioPanel);

    // Add name field, buttons at the bottom
    JButton addStockButton = new JButton("+ Add Stock");
    addStockButton.setActionCommand("AddStock");
    addStockButton.addActionListener(this);

    JButton removeStockButton = new JButton("- Remove Stock");
    removeStockButton.setActionCommand("RemoveStock");
    removeStockButton.addActionListener(this);

    JButton createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.setActionCommand("CreatePortfolio");
    createPortfolioButton.addActionListener(this);

    // Add components for portfolio creation
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

    bottomPanel.add(new JLabel("Portfolio Name:"));
    portfolioNameField = new JTextField(20);
    bottomPanel.add(portfolioNameField);
    bottomPanel.add(addStockButton);
    bottomPanel.add(removeStockButton);
    bottomPanel.add(createPortfolioButton);

    createPortfolioPanel.add(bottomPanel);
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



  private void addStockInputPanel(JPanel parentPanel) {
    JPanel stockPanel = new JPanel(new FlowLayout());
    JTextField stockField = new JTextField(10);
    JTextField sharesField = new JTextField(5);

    selectDate(stockPanel); // Use selectDate for consistency

    stockPanel.add(new JLabel("Stock Ticker: $"));
    stockPanel.add(stockField);
    stockPanel.add(new JLabel("Shares:"));
    stockPanel.add(sharesField);

    // Add stock panel above the bottom elements
    parentPanel.add(stockPanel, parentPanel.getComponentCount() - 1);
    stockInputPanels.add(stockPanel);
    stockTickerFields.add(stockField);
    stockSharesFields.add(sharesField);
    parentPanel.revalidate();
    parentPanel.repaint();
  }

  private void removeStockInputPanel() throws IOException {
    if (stockInputPanels.size() > 1) {
      JPanel panelToRemove = stockInputPanels.remove(stockInputPanels.size() - 1);
      stockTickerFields.remove(stockTickerFields.size() - 1);
      stockSharesFields.remove(stockSharesFields.size() - 1);
      yearInputs.remove(yearInputs.size() - 1);
      monthInputs.remove(monthInputs.size() - 1);
      dayInputs.remove(dayInputs.size() - 1);
      createPortfolioPanel.remove(panelToRemove);
      createPortfolioPanel.revalidate();
      createPortfolioPanel.repaint();
    } else {
      showError("You are at the minimum stock count for a portfolio.");
    }
  }

  private void updateDays(JComboBox<Integer> yearComboBox, JComboBox<Integer> monthComboBox, JComboBox<Integer> dayComboBox) {
    int year = (Integer) yearComboBox.getSelectedItem();
    int month = (Integer) monthComboBox.getSelectedItem();
    int daysInMonth = java.time.YearMonth.of(year, month).lengthOfMonth();

    dayComboBox.removeAllItems();
    for (int i = 1; i <= daysInMonth; i++) {
      dayComboBox.addItem(i);
    }
  }

  private void selectDate(JPanel panel) {
    JPanel dateSelectionPanel = new JPanel();
    dateSelectionPanel.setLayout(new FlowLayout());

    JComboBox<Integer> yearInput = new JComboBox<>();
    for (int i = 2500; i >= 0; i--) {
      yearInput.addItem(i);
    }
    yearInput.setActionCommand("YearComboBox");
    yearInput.addItemListener(this);

    JComboBox<Integer> monthInput = new JComboBox<>();
    for (int i = 1; i <= 12; i++) {
      monthInput.addItem(i);
    }
    monthInput.setActionCommand("MonthComboBox");
    monthInput.addItemListener(this);

    JComboBox<Integer> dayInput = new JComboBox<>();
    updateDays(yearInput, monthInput, dayInput);

    dateSelectionPanel.add(new JLabel("Year:"));
    dateSelectionPanel.add(yearInput);
    dateSelectionPanel.add(new JLabel("Month:"));
    dateSelectionPanel.add(monthInput);
    dateSelectionPanel.add(new JLabel("Day:"));
    dateSelectionPanel.add(dayInput);

    yearInputs.add(yearInput);
    monthInputs.add(monthInput);
    dayInputs.add(dayInput);

    panel.add(dateSelectionPanel);
  }

  private LocalDate getSelectedDate(JComboBox<Integer> yearInput, JComboBox<Integer> monthInput, JComboBox<Integer> dayInput) {
    try {
      int year = (Integer) yearInput.getSelectedItem();
      int month = (Integer) monthInput.getSelectedItem();
      int day = (Integer) dayInput.getSelectedItem();
      return LocalDate.of(year, month, day);
    } catch (Exception e) {
      try {
        showError("Invalid date selected.");
        return null;
      } catch (IOException g) {
      }
    }
    return null;
  }


  //temp, change to switch for future buttons
  @Override
  public void itemStateChanged(ItemEvent arg) {
    Object source = arg.getSource();
    for (int i = 0; i < yearInputs.size(); i++) {
      if (source == yearInputs.get(i) || source == monthInputs.get(i)) {
        updateDays(yearInputs.get(i), monthInputs.get(i), dayInputs.get(i));
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent arg) {
    try {
      switch (arg.getActionCommand()) {
        case "QueryPortfolio":
          queryPortfolio();
          break;
        case "AddStock":
          addStockInputPanel(createPortfolioPanel);
          break;
        case "RemoveStock":
          removeStockInputPanel();
          break;
        case "CreatePortfolio":
          createPortfolio();
          break;
        case "PopulateStock":
          // Example logic to add the new ticker to the list
          String newTicker = ((JTextField) ((JPanel) ((JButton) arg.getSource()).getParent()).getComponent(1)).getText();
          if (!newTicker.isEmpty()) {
            tickerListModel.addElement(newTicker);
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
    }
    catch(IOException e){

    }
  }

  private void queryPortfolio() throws IOException {
    String selectedPortfolio = (String) portfolioComboBox.getSelectedItem();
    if (selectedPortfolio == null || selectedPortfolio.isEmpty()) {
      showError("Please select a portfolio.");
    }

    if (yearInputs.isEmpty() || monthInputs.isEmpty() || dayInputs.isEmpty()) {
      showError("Please select a valid date.");
      return;
    }

    LocalDate selectedDate = getSelectedDate(yearInputs.get(0), monthInputs.get(0), dayInputs.get(0));
    if (selectedDate == null) {
      showError("Please select a valid date.");
      return;
    }

//    try {
//      double value = controller.getPortfolioValueController(selectedPortfolio, selectedDate);
//      portfolioValueField.setText(String.format("%.2f", value));
//    } catch (Exception e) {
//      showError("Error retrieving portfolio value: " + e.getMessage());
//    }
  }

    public void createPortfolio()throws IOException {
      String portfolioName = portfolioNameField.getText();
      if (portfolioName.isEmpty()) {
        showError("Portfolio name cannot be empty.");
      }

      boolean isFirstStock = true;
      for (int i = 0; i < stockInputPanels.size(); i++) {
        JTextField stockTickerField = stockTickerFields.get(i);
        JTextField stockSharesField = stockSharesFields.get(i);
        JComboBox<Integer> yearInput = yearInputs.get(i);
        JComboBox<Integer> monthInput = monthInputs.get(i);
        JComboBox<Integer> dayInput = dayInputs.get(i);

        String stockTicker = stockTickerField.getText();
        if (stockTicker.isEmpty()) {
          showError("Stock ticker cannot be empty.");
          return;
        }

        int shareCount;
        try {
          shareCount = Integer.parseInt(stockSharesField.getText());
          if (shareCount <= 0) {
            throw new IllegalArgumentException("Integer not positive.");
          }
        } catch (Exception e) {
          showError("Shares must be valid positive integers.");
          return;
        }

        LocalDate purchaseDate = LocalDate.of(
                (Integer) yearInput.getSelectedItem(),
                (Integer) monthInput.getSelectedItem(),
                (Integer) dayInput.getSelectedItem()
        );

        if (isFirstStock) {
          // Replace with actual controller call
          // controller.createPortfolio(portfolioName, stockTicker, shareCount, purchaseDate);
          isFirstStock = false;
        } else {
          // Replace with actual controller call
          // controller.addToPortfolio(portfolioName, stockTicker, shareCount, purchaseDate);
        }
      }
      // Reset the portfolio creation form after successful creation
      resetPortfolioForm();
    }




  private void resetPortfolioForm() {
    portfolioNameField.setText("");
    stockInputPanels.forEach(panel -> panel.getParent().remove(panel));
    stockInputPanels.clear();
    stockTickerFields.clear();
    stockSharesFields.clear();
    yearInputs.clear();
    monthInputs.clear();
    dayInputs.clear();
    addStockInputPanel(createPortfolioPanel);
    createPortfolioPanel.revalidate();
    createPortfolioPanel.repaint();
  }



  public void requestFocus () {

    }

    public void showError (String errorMessage)throws IOException{
      JOptionPane.showMessageDialog(frame, "Error: " + errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
      throw new IOException(errorMessage);
    }

  }


