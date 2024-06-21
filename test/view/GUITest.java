//package view;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDate;
//
//import controller.MockController;
//import model.IModel2;
//import model.ModelImpl2;
//
//import static org.junit.Assert.assertEquals;
//
//public class GUITest {
//  GUI gui;
//  StringBuilder received;
//  IViewListener controller;
//  IModel2 model;
//
//  @Before
//  public void setUp() {
//    gui = new GUI();
//    received = new StringBuilder();
//    model = new ModelImpl2();
//
//    controller = new MockController(gui, model, received);
//  }
//
//  @Test
//  public void testFireCreatePortfolio() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleCreatePortfolio("AMZN", 1.0, "A", date);
//    assertEquals("handleCreatePortfolio", received.toString());
//  }
//
//  @Test
//  public void testFireSavePortfolio() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleSavePortfolio("A");
//    assertEquals("handleSavePortfolio", received.toString());
//  }
//
//  @Test
//  public void testFireLoadPortfolio() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleLoadPortfolioEvent("A");
//    assertEquals("handleLoadPortfolio", received.toString());
//  }
//
//  @Test
//  public void testFireQueryPortfolio() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireQueryPortfolioEvent("A");
//    assertEquals("handleQueryPortfolio", received.toString());
//  }
//
//  @Test
//  public void testFireAddStockEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleAddStock("AMZN", 1.0, "A", date);
//    assertEquals("handleAddStock", received.toString());
//  }
//
//  @Test
//  public void testFireRemoveStockEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleRemoveStockEvent("AMZN", 1.0, "A", date);
//    assertEquals("handleRemoveStock", received.toString());
//  }
//
//  @Test
//  public void testFirePopulateEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandlePopulateEvent("AMZN", 1.0, "A", date);
//    assertEquals("handlePopulate", received.toString());
//  }
//
//  @Test
//  public void testFireCSVStockEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleCSVStock("AMZN", 1.0, "A", date);
//    assertEquals("handleCSStock", received.toString());
//  }
//
//  @Test
//  public void testFireBuyStockEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleBuyStockEvent("AMZN", 1.0, "A", date);
//    assertEquals("handleBuyStock", received.toString());
//  }
//
//  @Test
//  public void testFireSellStockEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleSellStockEvent("AMZN", 1.0, "A", date);
//    assertEquals("handleSellStock", received.toString());
//  }
//}