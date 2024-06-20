Part 3: Design Changes
- Created a view class and interface representing the GUI
- Created IViewListener and IGuiController
- Created ControllerImplGUI that implements IViewListener and IGuiController
- IViewListener serves as a link between the view and controller
- Created mockController for testing

Part 2: Design Changes
- Created a new model interface and class. The purpose is to follow the SOLID principals, so we could add functionalities.
  This is for portfolio and model.
- Changed return type of crossover in IModel to the interface type List<> instead of ArrayList<>.
- Added additional checks for illegal arguments in gainOrLoss and movingAverage command.
- The getPortfolioValue command now prints with the unit "$" to give better clarification.
- Created new interface and class for Portfolio.
  The purpose is to follow the SOLID principals, so we could add functionalities.
- The new Portfolio class uses a Map<String, Map<LocalDate, Double>> for share to give access to the date a transition happened.
- All methods dealing with shares are changed to take in a double instead of int to allow partial stocks.
- Controller now re-prints the menu whenever an error happens.
- Controller now takes in a IModel2. We decided not create a new controller.
- Created IWeight and ITransaction interface (and respective classes) to allow future extensions.
- Created IPerformance to allow for future extensions.
- Created APerformance to abstract common methods.
- Created IParseXml to parse xml files. Interface allows for extension.
  (Refer to README for more in-depth information on what the interfaces do.)

Part 1:
The design of this program involves an ICommand interface that has numerous class that implements it.
Their purpose is to call the corresponding method in models and prints a corresponding message for each input.
These commands will be given to a map in the main controller class (command pattern).
The controller implements the IController interface that allows for future extensions to the program.
The model implements the IModel interface. Again, this is meant for future extensions.
This is the case for both portfolio and stock; they both have a corresponding interface.
The information is obtained from either AlphaVantage or a pre-saved CSV file.
This information is obtained via a IReader interface, where each class that implements it gets information differently.