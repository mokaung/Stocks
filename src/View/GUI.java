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
  private JComboBox<String> portfolioComboBox;
  private JPanel portfolioValuePanel;
  private IController controller;
  private JTextField portfolioValueField;
  private JTextField portfolioNameField;

  private JPanel createPortfolioPanel;
  private ArrayList<JPanel> stockInputPanels;
  private ArrayList<JTextField> stockTickerFields;
  private ArrayList<JTextField> stockSharesFields;
  private ArrayList<JComboBox<Integer>> yearInputs;
  private ArrayList<JComboBox<Integer>> monthInputs;
  private ArrayList<JComboBox<Integer>> dayInputs;

  private LocalDate selectedDate;

  public GUI(String title) {
    frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);

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

    //for createPortfolio
    //for getPortfolioValue
    getPortfolioValueWindow();

    createPortfolioWindow();

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

  private void createPortfolioWindow() {
    createPortfolioPanel = new JPanel();
    createPortfolioPanel.setBorder(BorderFactory.createTitledBorder("Create Portfolio"));
    createPortfolioPanel.setLayout(new BoxLayout(createPortfolioPanel, BoxLayout.Y_AXIS));
    mainPanel.add(createPortfolioPanel);

    // Add components for portfolio creation
    createPortfolioPanel.add(new JLabel("Portfolio Name:"));
    portfolioNameField = new JTextField(20);
    createPortfolioPanel.add(portfolioNameField);

    // Add the initial stock input panel
//    JPanel stockInputPanel = new JPanel();
//    stockInputPanel.setLayout(new BoxLayout(stockInputPanel, BoxLayout.Y_AXIS));
//    createPortfolioPanel.add(stockInputPanel);
    addStockInputPanel(createPortfolioPanel);

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

  private void addStockInputPanel(JPanel parentPanel) {
    JPanel stockPanel = new JPanel(new FlowLayout());
    JTextField stockField = new JTextField(10);
    JTextField sharesField = new JTextField(5);

    selectDate(stockPanel); // Use selectDate for consistency

    stockPanel.add(new JLabel("Stock Ticker: $"));
    stockPanel.add(stockField);
    stockPanel.add(new JLabel("Shares:"));
    stockPanel.add(sharesField);

    // Add stock panel above the buttons
    parentPanel.add(stockPanel, parentPanel.getComponentCount() - 2);
    stockInputPanels.add(stockPanel);
    stockTickerFields.add(stockField);
    stockSharesFields.add(sharesField);
    parentPanel.revalidate();
    parentPanel.repaint();
  }


  /**
   * This method updates the combo boxes of the dates to allow selection of only valid dates.
   * Therefore, the method accounts for month day differences and leap years.
   *
   * @throws NullPointerException when somehow the selection in the combo boxes are null.
   */
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
    String who = ((JCheckBox) arg.getItemSelectable()).getActionCommand();
    switch (who) {
      case "YearComboBox":
      case "MonthComboBox":
        for (int i = 0; i < yearInputs.size(); i++) {
          updateDays(yearInputs.get(i), monthInputs.get(i), dayInputs.get(i));
        }
        break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent arg) {
    try {
      switch (arg.getActionCommand()) {
        case "GetPortfolioValue":
          addToPortfolio();
          break;
        case "AddStock":
          addStockInputPanel(createPortfolioPanel);
          break;
        case "CreatePortfolio":
          createPortfolio();
          break;
      }
    }
    catch(IOException e){

    }
  }

  private void getPortfolioValue() throws IOException {
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



    public void addToPortfolio() {

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


