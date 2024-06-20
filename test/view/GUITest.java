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
//    gui.fireHandleCreatePortfolioEvent("AMZN", 1.0, "A", date);
//    assertEquals("handleCreatePortfolio", received.toString());
//  }
//
//  @Test
//  public void testFireStockEvent() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleStockEvent("AMZN", 1.0, "A", date);
//    assertEquals("handleStock", received.toString());
//  }
//
//  @Test
//  public void testFireGetValue() {
//    LocalDate date = LocalDate.of(2023, 1, 1);
//    gui.fireHandleGetValue("A", date);
//    assertEquals("handleGetPortfolioValue", received.toString());
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
//}