USE [Northwind]
GO

-- 1. Selecteer klanten die in londen wonen + < 5 orders hebben gedaan.. order dit opp aantal geplaatste orders

SELECT Customers.CustomerID, CompanyName, COUNT(Orders.OrderID) as Total_Products
FROM Customers
INNER JOIN Orders ON Orders.CustomerID = Customers.CustomerID
WHERE City = 'London' 
GROUP BY Customers.CustomerID, CompanyName
HAVING COUNT(Orders.OrderID) <5
ORDER BY COUNT(Orders.OrderID);


-- 2. Selecteer all orders voor 'Pavlova" met en salesresultaat van minstens 800

SELECT Orders.CustomerID, SUM([Order Details].Quantity * [Order Details].UnitPrice) as SalesResult
From [Order Details]
INNER JOIN Products ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON Orders.OrderID = [Order Details].OrderID
WHERE Products.ProductName = 'Pavlova'
GROUP BY Orders.CustomerID
HAVING SUM([Order Details].Quantity * [Order Details].UnitPrice) > 800;

-- 3. Selecteer alle regio's waarin het product "Chocolade" is verkocht

SELECT DISTINCT Products.ProductName, Region.RegionDescription
FROM Products
INNER JOIN [Order Details] ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
INNER JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID
INNER JOIN EmployeeTerritories ON Employees.EmployeeID = EmployeeTerritories.EmployeeID
INNER JOIN Territories ON EmployeeTerritories.TerritoryID = Territories.TerritoryID
INNER JOIN Region ON Territories.RegionID = Region.RegionID
WHERE Products.ProductName = 'Chocolade';


-- 4. Selecteer all orders voor het product 'Tofu' waar de 'freight' kosten tussen 25 en 50 waren

SELECT DISTINCT Orders.OrderID, Orders.CustomerID, Products.ProductName, Orders.Freight
FROM Products
INNER JOIN [Order Details] ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
WHERE Products.ProductName = 'Tofu' AND Orders.Freight > 25 AND Orders.Freight <50;

-- 5. Selecteer de plaatsnamen waarin zowel klanten als werknemers wonen. 

SELECT Employees.City 
FROM Customers 
INNER JOIN Orders ON Orders.CustomerID = Customers.CustomerID 
INNER JOIN Employees ON Employees.EmployeeID = Orders.EmployeeID 
WHERE EXISTS (SELECT Customers.City From Customers WHERE Employees.City = Customers.City )
GROUP BY Employees.City;

-- 6. Welke producten zijn het meest verkocht (amount) voor Duitse klanten + welke werknemers hebben deze producten verkocht? + Order resultaat op aantal (1e5 laten zien)

SELECT DISTINCT TOP (5)  Products.ProductID, Products.ProductName, SUM([Order Details].Quantity) as Sales_Quantity 
INTO #TempTableQuantity
FROM  Products
INNER JOIN [Order Details] ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
INNER JOIN Customers ON Customers.CustomerID = Orders.CustomerID
WHERE Customers.Country = 'Germany'
GROUP BY Products.ProductID, Products.ProductName
ORDER BY SUM([Order Details].Quantity) DESC;

Select * From #TempTableQuantity;

SELECT DISTINCT Employees.EmployeeID, Employees.LastName, Employees.FirstName, Employees.Title, Products.ProductName, [Order Details].Quantity
FROM  Products
INNER JOIN [Order Details] ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
INNER JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID
INNER JOIN Customers ON Customers.CustomerID = Orders.CustomerID
WHERE Products.ProductName IN (SELECT ProductName FROM #TempTableQuantity WHERE ProductName = Products.ProductName) AND Customers.Country = 'Germany'
GROUP BY Employees.EmployeeID, Employees.LastName, Employees.FirstName, Employees.Title, Products.ProductName, [Order Details].Quantity
ORDER BY Products.ProductName;

DROP TABLE #TempTableQuantity;

-- 7. Welke producten zorgden voor de hoogste salesresultaten voor Duitse klanten? + welke werknemers hebben deze verkocht? 

SELECT DISTINCT TOP (5)  Products.ProductID, Products.ProductName, SUM([Order Details].Quantity * [Order Details].UnitPrice) as SalesResult
INTO #TempTableSales
FROM  Products
INNER JOIN [Order Details] ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
INNER JOIN Customers ON Customers.CustomerID = Orders.CustomerID
WHERE Customers.Country = 'Germany'
GROUP BY Products.ProductID, Products.ProductName
ORDER BY SUM([Order Details].Quantity * [Order Details].UnitPrice) DESC;

Select * From #TempTableSales;

SELECT DISTINCT Employees.EmployeeID, Employees.LastName, Employees.FirstName, Employees.Title, Products.ProductName, SUM([Order Details].Quantity * [Order Details].UnitPrice) as SalesResult
FROM  Products
INNER JOIN [Order Details] ON Products.ProductID = [Order Details].ProductID
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
INNER JOIN Employees ON Orders.EmployeeID = Employees.EmployeeID
INNER JOIN Customers ON Customers.CustomerID = Orders.CustomerID
WHERE Products.ProductName IN (SELECT ProductName FROM #TempTableSales WHERE ProductName = Products.ProductName) AND Customers.Country = 'Germany'
GROUP BY Employees.EmployeeID, Employees.LastName, Employees.FirstName, Employees.Title, Products.ProductName, [Order Details].Quantity
ORDER BY Products.ProductName;

DROP TABLE #TempTableSales;

-- 8. Join de tabellen PRODUCTS and SUPLLIERS, met INNER JOIN, LEFT JOIN, RIGHT JOIN, FULL JOIN

SELECT *
FROM Products
INNER JOIN Suppliers ON Suppliers.SupplierID = Products.SupplierID;
SELECT *
FROM Products
LEFT JOIN Suppliers ON Suppliers.SupplierID = Products.SupplierID;
SELECT *
FROM Products
RIGHT JOIN Suppliers ON Suppliers.SupplierID = Products.SupplierID;
SELECT *
FROM Products
FULL JOIN Suppliers ON Suppliers.SupplierID = Products.SupplierID;

-- 9. geeft AVG voor employee en order op sales resultaat

SELECT DISTINCT Employees.EmployeeID, Employees.LastName, Employees.FirstName, Employees.Title,  AVG([Order Details].Quantity * [Order Details].UnitPrice) as Average_SalesResult
FROM  [Order Details]
INNER JOIN Orders ON [Order Details].OrderID = Orders.OrderID
INNER JOIN Employees ON Employees.EmployeeID = Orders.EmployeeID
GROUP BY Employees.EmployeeID, Employees.LastName, Employees.FirstName, Employees.Title
ORDER BY AVG([Order Details].Quantity * [Order Details].UnitPrice) DESC;
