Stocks (Part 1) - Design 

This program utilizes object oriented programming to organize code. The program utilizes a couple of design patterns to reduce code reuse and coupling. 

Model-View-Controller (MVC)

The program's calculations and computing is stored in the model package. This package includes an interface for Model, as well as supporting interfaces for classes that are needed in the Model implementation, ModelImpl. The Stock class represents a certain stock on a specific day, and houses the necessary information of a stock, such as closing and opening price. The Portfolio class represents a single portfolio created by the user. It holds the total value of the portfolio, and the shares the user owns. 