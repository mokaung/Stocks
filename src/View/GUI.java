package View;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

import javax.swing.*;

import controller.IController;


/**
 * Renders the program with GUI.
 */
public class GUI implements IView {
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

  private JTextField portfolioValueField;
  private IController controller;

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
    mainScrollPane = new JScrollPane(mainPanel);
    frame.add(mainScrollPane);

    // Add a panel for portfolio value selection
    portfolioValuePanel = new JPanel();
    portfolioValuePanel.setBorder(BorderFactory.createTitledBorder("Get Portfolio Value"));
    portfolioValuePanel.setLayout(new BoxLayout(portfolioValuePanel, BoxLayout.Y_AXIS));
    mainPanel.add(portfolioValuePanel);

    // Add portfolio selection components
    portfolioValuePanel.add(new JLabel("Select Portfolio:"));
    portfolioComboBox = new JComboBox<>();
    portfolioValuePanel.add(portfolioComboBox);

    selectDate();

    // Add button to get portfolio value
//    button.addActionListener(e -> {
//      if (controller != null) {
//        controller.onGetPortfolioValue();
//      }
//    });
//    portfolioValuePanel.add(button);

    JPanel valueDisplayPanel = new JPanel();
    valueDisplayPanel.setLayout(new FlowLayout());
    valueDisplayPanel.add(new JLabel("$"));
    portfolioValueField = new JTextField(10);
    portfolioValueField.setEditable(false);
    valueDisplayPanel.add(portfolioValueField);
    portfolioValuePanel.add(valueDisplayPanel);

    mainPanel.add(new JScrollPane(textArea));
  }


  @Override
  public void render() {
    frame.setVisible(true);
  }

  //WIP, update days whenever user selects month or year

  private void updateDays()throws NullPointerException {
    int year = (Integer) yearComboBox.getSelectedItem();
    int month = (Integer) monthComboBox.getSelectedItem();
    int daysInMonth = YearMonth.of(year, month).lengthOfMonth();

    dayComboBox.removeAllItems();
    for (int i = 1; i <= daysInMonth; i++) {
      dayComboBox.addItem(i);
    }
  }

  private void selectDate() {
    // Add date selection components
    JPanel dateSelectionPanel = new JPanel();
    dateSelectionPanel.setLayout(new FlowLayout());
    dateSelectionPanel.add(new JLabel("Year:"));
    yearComboBox = new JComboBox<>();
    for (int i = 2500; i >= 0; i--) {
      yearComboBox.addItem(i);
    }
    dateSelectionPanel.add(yearComboBox);

    dateSelectionPanel.add(new JLabel("Month:"));
    monthComboBox = new JComboBox<>();
    for (Month month : Month.values()) {
      monthComboBox.addItem(month.getValue());
    }
    dateSelectionPanel.add(monthComboBox);

    dateSelectionPanel.add(new JLabel("Day:"));
    dayComboBox = new JComboBox<>();
    updateDays();

    dateSelectionPanel.add(dayComboBox);

    yearComboBox.addItemListener(e -> updateDays());
    monthComboBox.addItemListener(e -> updateDays());

    portfolioValuePanel.add(dateSelectionPanel);
  }

}


