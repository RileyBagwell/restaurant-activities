class Table{
  private int tableNum;
  private int numMaxSeats;
  private int numGuests;
  private Order tableOrder;
  private boolean hasServed;

  Table(int _tableNum, int _numMaxSeats){
    tableNum = _tableNum;
    numMaxSeats = _numMaxSeats;
    numGuests = 0;
    tableOrder = null;
    hasServed = false;
  }

  // Serve to the table
  public void serve(){
    hasServed = true;
  }

  // Accessors
  public int getTableNum(){
    return tableNum;
  }
  public int getNumMaxSeats(){
    return numMaxSeats;
  }
  public int getNumGuests(){
    return numGuests;
  }
  public Order getTableOrder(){
    return tableOrder;
  }
  public boolean getHasServed(){
    return hasServed;
  }

  // Mutators
  public void setNumGuests(int n){
    numGuests = n;
  }
  public void setTableOrder(Order ord){
    tableOrder = ord;
  }
}