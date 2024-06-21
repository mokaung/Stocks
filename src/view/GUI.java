package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import controller.ControllerImplGUI;


/**
 * Renders the program with GUI.
 */
public class GUI extends JFrame implements IView, ActionListener, ItemListener {
  private final List<IViewListener> myListeners;
  private JButton button;
  private JTextArea textArea;
  private JPanel mainPanel;
  private JScrollPane mainScrollBar;
  private DefaultListModel<String> tickerListModel;
  private JComboBox<String> portfolioComboBox;
  private JPanel portfolioValuePanel;
  private JTextArea portfolioValueArea;
  private JTextField portfolioNameField;
  private JPanel createPortfolioPanel;
  private ArrayList<JPanel> stockInputPanels;
  private ArrayList<JTextField> stockTickerFields;
  private ArrayList<JTextField> stockSharesFields;
  private ArrayList<JComboBox<Integer>> createYearInputs;
  private ArrayList<JComboBox<Integer>> createMonthInputs;
  private ArrayList<JComboBox<Integer>> createDayInputs;
  private ArrayList<JComboBox<Integer>> buySellYearInputs;
  private ArrayList<JComboBox<Integer>> buySellMonthInputs;
  private ArrayList<JComboBox<Integer>> buySellDayInputs;
  private ArrayList<JComboBox<Integer>> queryYearInputs;
  private ArrayList<JComboBox<Integer>> queryMonthInputs;
  private ArrayList<JComboBox<Integer>> queryDayInputs;
  private JComboBox<String> savePortfolioComboBox;
  private JComboBox<String> loadPortfolioComboBox;
  private JComboBox<String> portfolioComboBoxBuySell;
  private JList<String> predefinedStocksList;
  private JTextField stockField;
  private JTextField sharesField;

  public GUI() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1000, 1000);

    this.myListeners = new ArrayList<>();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1000, 1000);

    button = new JButton("Select Date");
    textArea = new JTextArea(10, 30);
    textArea.setEditable(false);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    mainScrollBar = new JScrollPane(mainPanel);
    add(mainScrollBar);

    stockInputPanels = new ArrayList<>();
    stockTickerFields = new ArrayList<>();
    stockSharesFields = new ArrayList<>();

    createYearInputs = new ArrayList<>();
    createMonthInputs = new ArrayList<>();
    createDayInputs = new ArrayList<>();

    buySellYearInputs = new ArrayList<>();
    buySellMonthInputs = new ArrayList<>();
    buySellDayInputs = new ArrayList<>();

    queryYearInputs = new ArrayList<>();
    queryMonthInputs = new ArrayList<>();
    queryDayInputs = new ArrayList<>();

    populateStocksWindow();
    saveLoadPortfoliosWindow();
    createPortfolioWindow();
    buySellStocksWindow();
    queryPortfolioWindow();
    mainPanel.add(new JScrollPane(textArea));
  }


  @Override
  public void render(boolean visible) {
    setVisible(visible);
  }

  private void populateStocksWindow() {
    JPanel populatePanel = new JPanel();
    populatePanel.setBorder(BorderFactory.createTitledBorder("Populate Stocks"));
    populatePanel.setLayout(new BoxLayout(populatePanel, BoxLayout.Y_AXIS));
    mainPanel.add(populatePanel);

    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new FlowLayout());
    inputPanel.add(new JLabel("Stock Ticker: $"));
    JTextField tickerInputField = new JTextField(10);
    inputPanel.add(tickerInputField);
    JButton populateButton = new JButton("Populate Stock");
    populateButton.setActionCommand("PopulateStock");
    populateButton.addActionListener(this);

    inputPanel.add(populateButton);
    populatePanel.add(inputPanel);
    JPanel tickerListPanel = new JPanel();
    tickerListPanel.setBorder(BorderFactory.createTitledBorder("Populated Stocks"));
    tickerListPanel.setLayout(new BorderLayout());
    tickerListModel = new DefaultListModel<>();
    ArrayList<String> listOfStocks = new ArrayList<>();

    for (IViewListener listener : myListeners) {
      listOfStocks = listener.listStocks();
    }
    for (String ticker : listOfStocks) {
      tickerListModel.addElement(ticker);
    }
    JList<String> tickerList = new JList<>(tickerListModel);
    tickerListPanel.add(new JScrollPane(tickerList), BorderLayout.CENTER);
    populatePanel.add(tickerListPanel);
    JPanel csvPanel = new JPanel();
    csvPanel.setBorder(BorderFactory.createTitledBorder("Populate from CSV"));
    csvPanel.setLayout(new BorderLayout());
    DefaultListModel<String> predefinedStocksListModel = new DefaultListModel<>();
    predefinedStocksListModel.addElement("META");
    predefinedStocksListModel.addElement("AAPL");
    predefinedStocksListModel.addElement("AMZN");
    predefinedStocksListModel.addElement("GOOG");
    predefinedStocksListModel.addElement("NFLX");
    predefinedStocksList = new JList<>(predefinedStocksListModel);
    csvPanel.add(new JScrollPane(predefinedStocksList), BorderLayout.CENTER);

    JButton populateFromCSVButton = new JButton("Populate Stock from CSV");
    populateFromCSVButton.setActionCommand("PopulateFromCSV");
    populateFromCSVButton.addActionListener(this);
    csvPanel.add(populateFromCSVButton, BorderLayout.SOUTH);

    populatePanel.add(csvPanel);
  }


  private void queryPortfolioWindow() {
    portfolioValuePanel = new JPanel();
    portfolioValuePanel.setBorder(BorderFactory.createTitledBorder("Query Portfolio"));
    portfolioValuePanel.setLayout(new BoxLayout(portfolioValuePanel, BoxLayout.Y_AXIS));
    mainPanel.add(portfolioValuePanel);

    portfolioValuePanel.add(new JLabel("Select Portfolio:"));
    portfolioComboBox = new JComboBox<>();
    portfolioValuePanel.add(portfolioComboBox);

    selectDate(portfolioValuePanel, queryYearInputs, queryMonthInputs, queryDayInputs);

    button = new JButton("Get Portfolio Stats");
    button.setActionCommand("QueryPortfolio");
    button.addActionListener(this);
    portfolioValuePanel.add(button);

    JPanel valueDisplayPanel = new JPanel();
    valueDisplayPanel.setLayout(new BorderLayout());
    valueDisplayPanel.add(new JLabel("Portfolio Statistics: "), BorderLayout.NORTH);

    portfolioValueArea = new JTextArea(10, 30);
    portfolioValueArea.setEditable(false);
    portfolioValueArea.setLineWrap(true);
    portfolioValueArea.setWrapStyleWord(true);

    JScrollPane scrollPane = new JScrollPane(portfolioValueArea);
    valueDisplayPanel.add(scrollPane, BorderLayout.CENTER);

    portfolioValuePanel.add(valueDisplayPanel);
  }


  private void createPortfolioWindow() {
    createPortfolioPanel = new JPanel();
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    createPortfolioPanel.setLayout(new BoxLayout(createPortfolioPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createPortfolioPanel);

    addStockInputPanel(createPortfolioPanel);

    JButton addStockButton = new JButton("+ Add Stock");
    addStockButton.setActionCommand("AddStock");
    addStockButton.addActionListener(this);

    JButton removeStockButton = new JButton("- Remove Stock");
    removeStockButton.setActionCommand("RemoveStock");
    removeStockButton.addActionListener(this);

    JButton createPortfolioButton = new JButton("Create Portfolio");
    createPortfolioButton.setActionCommand("CreatePortfolio");
    createPortfolioButton.addActionListener(this);

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

    buySellPanel.add(new JLabel("Select Portfolio:"));
    portfolioComboBoxBuySell = new JComboBox<>();
    buySellPanel.add(portfolioComboBoxBuySell);

    selectDate(buySellPanel, buySellYearInputs, buySellMonthInputs, buySellDayInputs);

    JPanel stockInputPanel = new JPanel(new FlowLayout());
    stockField = new JTextField(10);
    sharesField = new JTextField(5);

    stockInputPanel.add(new JLabel("Stock Ticker: $"));
    stockInputPanel.add(stockField);
    stockInputPanel.add(new JLabel("Shares:"));
    stockInputPanel.add(sharesField);

    buySellPanel.add(stockInputPanel);

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

    JPanel loadPanel = new JPanel();
    loadPanel.setBorder(BorderFactory.createTitledBorder("Load Portfolios"));
    loadPanel.setLayout(new BoxLayout(loadPanel, BoxLayout.Y_AXIS));
    saveLoadPanel.add(loadPanel);
    loadPanel.add(new JLabel("Select Portfolio to Load:"));
    loadPortfolioComboBox = new JComboBox<>();
    ArrayList<String> loadableNames = new ArrayList<>();
    for (IViewListener listener : myListeners) {
      loadableNames = listener.listLoadablePortfolios();
    }
    for (String name : loadableNames) {
      loadPortfolioComboBox.addItem(name);
    }
    loadPanel.add(loadPortfolioComboBox);

    JButton loadButton = new JButton("Load Portfolio");
    loadButton.setActionCommand("LoadPortfolio");
    loadButton.addActionListener(this);
    loadPanel.add(loadButton);
  }


  private void addStockInputPanel(JPanel ogPanel) {
    JPanel stockPanel = new JPanel(new FlowLayout());
    JTextField stockField = new JTextField(10);
    JTextField sharesField = new JTextField(5);

    selectDate(stockPanel, createYearInputs, createMonthInputs, createDayInputs); // Use selectDate for consistency

    stockPanel.add(new JLabel("Stock Ticker: $"));
    stockPanel.add(stockField);
    stockPanel.add(new JLabel("Shares:"));
    stockPanel.add(sharesField);

    ogPanel.add(stockPanel, ogPanel.getComponentCount() - 1);
    stockInputPanels.add(stockPanel);
    stockTickerFields.add(stockField);
    stockSharesFields.add(sharesField);
    ogPanel.revalidate();
    ogPanel.repaint();
  }


  private void removeStockInputPanel() throws IOException {
    if (stockInputPanels.size() > 1) {
      JPanel panelToRemove = stockInputPanels.remove(stockInputPanels.size() - 1);
      stockTickerFields.remove(stockTickerFields.size() - 1);
      stockSharesFields.remove(stockSharesFields.size() - 1);
      createYearInputs.remove(createYearInputs.size() - 1);
      createMonthInputs.remove(createMonthInputs.size() - 1);
      createDayInputs.remove(createDayInputs.size() - 1);
      createPortfolioPanel.remove(panelToRemove);
      createPortfolioPanel.revalidate();
      createPortfolioPanel.repaint();
    } else {
      showError("You are at the minimum stock count for a portfolio.");
    }
  }

  private void updateDays(JComboBox<Integer> yearComboBox,
                          JComboBox<Integer> monthComboBox,
                          JComboBox<Integer> dayComboBox) {
    int year = (Integer) yearComboBox.getSelectedItem();
    int month = (Integer) monthComboBox.getSelectedItem();
    int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

    dayComboBox.removeAllItems();
    for (int i = 1; i <= daysInMonth; i++) {
      dayComboBox.addItem(i);
    }
  }

  private void selectDate(JPanel panel,
                          ArrayList<JComboBox<Integer>> yearInputs,
                          ArrayList<JComboBox<Integer>> monthInputs,
                          ArrayList<JComboBox<Integer>> dayInputs) {
    JPanel dateSelectionPanel = new JPanel();
    dateSelectionPanel.setLayout(new FlowLayout());

    JComboBox<Integer> yearInput = new JComboBox<>();
    for (int i = 2024; i >= 1990; i--) {
      yearInput.addItem(i);
    }
    yearInput.setActionCommand("YearSelect");
    yearInput.addItemListener(this);

    JComboBox<Integer> monthInput = new JComboBox<>();
    for (int i = 1; i <= 12; i++) {
      monthInput.addItem(i);
    }
    monthInput.setActionCommand("MonthSelect");
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


  private LocalDate getSelectedDate(ArrayList<JComboBox<Integer>> yearInputs,
                                    ArrayList<JComboBox<Integer>> monthInputs,
                                    ArrayList<JComboBox<Integer>> dayInputs,
                                    int index) throws IOException {
    try {
      int year = (Integer) yearInputs.get(index).getSelectedItem();
      int month = (Integer) monthInputs.get(index).getSelectedItem();
      int day = (Integer) dayInputs.get(index).getSelectedItem();
      return LocalDate.of(year, month, day);
    } catch (Exception e) {
      showError("Invalid date selected.");
    }
    return null;
  }

  @Override
  public void itemStateChanged(ItemEvent arg) {
    Object dateSource = arg.getSource();
    for (int i = 0; i < createYearInputs.size(); i++) {
      if (dateSource == createYearInputs.get(i) || dateSource == createMonthInputs.get(i)) {
        updateDays(createYearInputs.get(i), createMonthInputs.get(i), createDayInputs.get(i));
      }
    }
    for (int i = 0; i < buySellYearInputs.size(); i++) {
      if (dateSource == buySellYearInputs.get(i) || dateSource == buySellMonthInputs.get(i)) {
        updateDays(buySellYearInputs.get(i), buySellMonthInputs.get(i), buySellDayInputs.get(i));
      }
    }
    for (int i = 0; i < queryYearInputs.size(); i++) {
      if (dateSource == queryYearInputs.get(i) || dateSource == queryMonthInputs.get(i)) {
        updateDays(queryYearInputs.get(i), queryMonthInputs.get(i), queryDayInputs.get(i));
      }
    }
  }


  @Override
  public void actionPerformed(ActionEvent userInput) {
    try {
      switch (userInput.getActionCommand()) {
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
          populateOnlineStock(userInput);
          break;
        case "PopulateFromCSV":
          populateCSVStock();
          break;
        case "BuyStock":
          buyStock();
          break;
        case "SellStock":
          sellStock();
          break;
        case "SavePortfolio":
          savePortfolio();
          break;
        case "LoadPortfolio":
          loadPortfolio();
          break;
        default:
          showError("Something's not right...");
          break;
      }
    } catch (IOException e) {
      try {
        showError("Unknown Command.");
      } catch (IOException g) {
        throw new RuntimeException(g);
      }
    }
  }

  private void populateCSVStock() throws IOException {
    try {
      String newTicker2 = predefinedStocksList.getSelectedValue();
      if (newTicker2 != null && !newTicker2.isEmpty()) {

        for (IViewListener listener : myListeners) {
          listener.handleCSVStock(newTicker2);
        }
        tickerListModel.addElement(newTicker2);
      }
    } catch (IllegalArgumentException e) {
      showError(e.getMessage());
    }
  }

  private void populateOnlineStock(ActionEvent userInput) throws IOException {
    try {
      String newTicker = ((JTextField) (((JButton)
              userInput.getSource()).getParent()).getComponent(1)).getText();
      if (!newTicker.isEmpty()) {
        for (IViewListener listener : myListeners) {
          listener.handlePopulateStock(newTicker);
        }
        tickerListModel.addElement(newTicker);
      }
    } catch (IllegalArgumentException e) {
      showError(e.getMessage());
    }
  }

  private void loadPortfolio() throws IOException {
    String loadPortfolioSelection = (String) loadPortfolioComboBox.getSelectedItem();
    try {
      for (IViewListener listener : myListeners) {
        listener.handleLoadPortfolio(loadPortfolioSelection);
      }
      updatePortfolioDropdowns();
      JOptionPane.showMessageDialog(this, "Successfully loaded portfolio!",
              "Success",
              JOptionPane.INFORMATION_MESSAGE);
      JOptionPane.showMessageDialog(this, "Successfully loaded portfolio!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IllegalArgumentException e) {
      showError(e.getMessage());
    }
  }

  private void savePortfolio() throws IOException {
    String savePortfolioSelection = (String) savePortfolioComboBox.getSelectedItem();
    try {
      JOptionPane.showMessageDialog(this, "Successfully saved portfolio!",
              "Success", JOptionPane.INFORMATION_MESSAGE);
      for (IViewListener listener : myListeners) {
        listener.handleSavePortfolio(savePortfolioSelection);
      }

      JOptionPane.showMessageDialog(this, "Successfully saved portfolio!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IllegalArgumentException e) {
      showError(e.getMessage());
    }
  }

  private void sellStock() throws IOException {
    try {
      String portfolio2 = (String) portfolioComboBox.getSelectedItem();
      String ticker2 = stockField.getText();
      LocalDate date2 = getSelectedDate(buySellYearInputs, buySellMonthInputs,
              buySellDayInputs, 0);
      int shares2 = Integer.parseInt(sharesField.getText());

      for (IViewListener listener : myListeners) {
        listener.handleSellStock(portfolio2, ticker2, shares2, date2);
      }

      resetBuySellStockFields();
    } catch (IllegalArgumentException e) {
      showError(e.getMessage());
    }
  }

  private void buyStock() throws IOException {
    try {
      String portfolio = (String) portfolioComboBox.getSelectedItem();
      String ticker = stockField.getText();
      LocalDate date = getSelectedDate(buySellYearInputs, buySellMonthInputs,
              buySellDayInputs, 0);
      int shares = Integer.parseInt(sharesField.getText());

      for (IViewListener listener : myListeners) {
        listener.handleBuyStock(portfolio, ticker, shares, date);
      }
      resetBuySellStockFields();
    } catch (Exception e) {
      showError(e.getMessage());
    }
  }


  private void queryPortfolio() throws IOException {
    String selectedPortfolio = (String) portfolioComboBox.getSelectedItem();
    if (selectedPortfolio == null || selectedPortfolio.isEmpty()) {
      showError("Please select a portfolio.");
    }

    if (queryYearInputs.isEmpty() || queryMonthInputs.isEmpty() || queryDayInputs.isEmpty()) {
      showError("Please select a valid date.");
    }

    LocalDate selectedDate = getSelectedDate(queryYearInputs,
            queryMonthInputs,
            queryDayInputs,
            0);
    if (selectedDate == null) {
      showError("Please select a valid date.");
    }
    try {
//      String value = controller.handleGetPortfolioValue(selectedPortfolio, selectedDate);
//      portfolioValueArea.setText(value);
    } catch (Exception e) {
      showError(e.getMessage());
    }
  }
  
  public void createPortfolio() throws IOException {
    try {
      String portfolioName = portfolioNameField.getText();
      if (portfolioName.isEmpty()) {
        showError("Portfolio name cannot be empty.");
      }

      boolean isFirstStock = true;
      for (int i = 0; i < stockInputPanels.size(); i++) {
        JTextField stockTickerField = stockTickerFields.get(i);
        JTextField stockSharesField = stockSharesFields.get(i);
        JComboBox<Integer> yearInput = createYearInputs.get(i);
        JComboBox<Integer> monthInput = createMonthInputs.get(i);
        JComboBox<Integer> dayInput = createDayInputs.get(i);

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
        String value = "";

        if (isFirstStock) {
          for (IViewListener listener : myListeners) {
            listener.handleCreatePortfolio(stockTicker, shareCount, portfolioName, purchaseDate);
            isFirstStock = false;
          }

        } else {
          for (IViewListener listener : myListeners) {
            listener.handleAddToPortfolio(stockTicker, shareCount, portfolioName, purchaseDate);
          }
        }
      }
      resetPortfolioForm();
      updatePortfolioDropdowns();
      JOptionPane.showMessageDialog(this,
              "Successfully created portfolio!",
              "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (IllegalArgumentException | IOException e) {
      showError(e.getMessage());
    }
  }


  private void resetBuySellStockFields() {
    stockField.setText("");
    sharesField.setText("");
    buySellYearInputs.get(0).setSelectedIndex(0);
    buySellMonthInputs.get(0).setSelectedIndex(0);
    updateDays(buySellYearInputs.get(0), buySellMonthInputs.get(0), buySellDayInputs.get(0));
    buySellDayInputs.get(0).setSelectedIndex(0);
  }



  private void resetPortfolioForm() {
    portfolioNameField.setText("");
    for (JPanel panel: stockInputPanels) {
      panel.getParent().remove(panel);
    }
    stockInputPanels.clear();
    stockTickerFields.clear();
    stockSharesFields.clear();
    createYearInputs.clear();
    createMonthInputs.clear();
    createDayInputs.clear();
    addStockInputPanel(createPortfolioPanel);
    createPortfolioPanel.revalidate();
    createPortfolioPanel.repaint();
  }


  private void updatePortfolioDropdowns() {
    ArrayList<String> portfolios = new ArrayList<>();
    for (IViewListener listener : myListeners) {
      portfolios = listener.listPortfolios();
    }

    if (portfolioComboBox != null) {
      portfolioComboBox.removeAllItems();
    }
    if (savePortfolioComboBox != null) {
      savePortfolioComboBox.removeAllItems();
    }
    if (portfolioComboBoxBuySell != null) {
      portfolioComboBoxBuySell.removeAllItems();
    }

    for (String portfolio : portfolios) {
      portfolioComboBox.addItem(portfolio);
      savePortfolioComboBox.addItem(portfolio);
      portfolioComboBoxBuySell.addItem(portfolio);
    }
  }


  public void showError(String errorMessage) throws IOException {
    JOptionPane.showMessageDialog(this, "Error: " + errorMessage,
            "Error", JOptionPane.ERROR_MESSAGE);
    throw new IOException(errorMessage);
  }

  @Override
  public void addViewListener(IViewListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.myListeners.add(listener);
  }

  public void requestFocus() {
  }

}


