# discount-calculator

Pre-Requisite:
You need to have following softwares installed on your machine.
- JDK 1.8 
- Maven

Instructions to run:

1. On Terminal, go to discount-calculator package and run following command:
   mvn clean install
 
  This will build the project and create a fat jar in target folder.
  
2. Go to target folder and run following commands.
   cd target
   java -jar discount-calculator-1.0.0-SNAPSHOT-fat.jar
   
3. This should generate a output.txt in the resources folder
   discount-calculator/src/main/resources/output.txt
   
   This output.txt contains the expected output for the given problem
   You can find input data in src/main/resources in following files
   
   Invenntory Details.txt - Inventory of the Store
   CustomerOrders.txt - Customer Order Details
   
   You can change the input in these files and see the output for same in output.txt
   
   
DESIGN
   
   
