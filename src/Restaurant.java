import java.util.*;
import java.io.File;

class Restaurant{
  static Menu menu; // Contains all MenuItems
  static Table[] tables; // Array to store the tables

  public static void main(String[] args) throws Exception{
    Scanner input = new Scanner(System.in);
    Scanner finput = new Scanner(new File("config.txt"));
    String inputStr = "";
    String command = "";
    int tableNum;

    // Setup tables
    int numTables = finput.nextInt();
    tables = new Table[numTables+1];
    finput.nextLine(); // Move to next line
    for(int i = 1; i < numTables+1; i++){ // Create each table from file
      int tempTableNum = finput.nextInt();
      int tempNumMaxSeats = finput.nextInt();
      tables[i] = new Table(tempTableNum, tempNumMaxSeats);
    }
    finput.nextLine(); // Move to next line

    // Setup Menu
    int numItems = finput.nextInt();
    finput.nextLine(); // Move to next line
    menu = new Menu(numItems); // Create the Menu
    for(int i = 0; i < numItems; i++){
      String tempItemCode = finput.next();
      String tempName = finput.next();
      double tempPrice = finput.nextDouble();
      MenuItem item = new MenuItem(tempItemCode, tempName, tempPrice);
      menu.addItem(i, item);
    }
    
    // Start getting commands
    while(true){
      inputStr = input.nextLine();
      if(inputStr.equals("C")) // Check if user wants to close program
        break;
      tableNum = Integer.parseInt(inputStr.substring(0,1));
      command = inputStr.substring(2);
      command = command.trim();
      int partySize;
      ArrayList<MenuItem> itemsList = new ArrayList<MenuItem>();
      String itemCode;
      MenuItem[] itemsToAdd;
      switch(command.substring(0,1)){
        case "O": // Order items
          for(int i = 0; i < 3; i++){
            itemCode = command.substring(3*i+2, 3*i+4);
            partySize = menu.findIndex(itemCode); // Represents the index of the item in menu
            if(partySize > -1)
              itemsList.add(menu.returnItem(partySize));
            else
              System.out.println("No item with code " + itemCode);
          }
          itemsToAdd = new MenuItem[itemsList.size()];
          for(int i = 0; i < itemsToAdd.length; i++){
            itemsToAdd[i] = itemsList.get(i);
          }
          if(tables[tableNum].getTableOrder() == null){
            System.out.println(itemsToAdd.length + " items ordered for Table " + tableNum);
            tables[tableNum].setTableOrder(new Order(itemsToAdd));
          }
          else{
            System.out.println(itemsToAdd.length + " additional items ordered for Table " + tableNum);
            tables[tableNum].getTableOrder().addItems(itemsToAdd);
          }
          break;
        case "C": // Close table
          if(tables[tableNum].getHasServed() == false) // Check if table is served
            System.out.println("Food not served for Table " + tableNum + " yet!");
          else{ // Close the table
            System.out.println("Table " + tableNum + " is closed. Here is the bill.\n");
            printReceipt(tableNum);
            tables[tableNum].setNumGuests(0);
          }
          break;
        case "S": // Serve food
          if(tables[tableNum].getTableOrder() == null) // Check if table ordered
            System.out.println("Order not placed at Table " + tableNum + " yet!");
          else{ // Serve table
            tables[tableNum].serve();
            System.out.println("Food served in table " + tableNum);
          }
          break;
        default: // Assign a party
          partySize = Integer.parseInt(command.substring(1));
          if(tables[tableNum].getNumGuests() != 0) // Check if table is occupied
            System.out.println("Table " + tableNum + " already occupied!");
          else if(tables[tableNum].getNumMaxSeats() < partySize) // Check if party is too large
            System.out.println("Sorry, max " + tables[tableNum].getNumMaxSeats() + " seats in table " + tableNum + "!");
          else{ // Assign party to table
            tables[tableNum].setNumGuests(partySize);
            System.out.println("Party of " + partySize + " assigned to Table " + tableNum);
          }
          break;
      }
    }
  }

  // Print receipt for given table
  static void printReceipt(int tableNum){
    double total = 0;
    Table closedTable = tables[tableNum];
    Order closedOrder = closedTable.getTableOrder();
    System.out.println("Receipt Table# " + tableNum + " Party " + closedTable.getNumGuests());
    for(int i = 0; i < closedOrder.getLength(); i++){
      System.out.print(closedOrder.getItem(i).getItemCode() + " ");
      System.out.printf("%18s ", closedOrder.getItem(i).getName() + " ");
      System.out.printf("%6s%n", closedOrder.getItem(i).getPrice());
      total += closedOrder.getItem(i).getPrice();
    }
    System.out.println("Total " + total);
  }
}