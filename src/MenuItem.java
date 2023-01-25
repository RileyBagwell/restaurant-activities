class MenuItem{
  private String itemCode;
  private String name;
  private double price;

  // Constructor
  MenuItem(String _itemCode, String _name, double _price){
    itemCode = _itemCode;
    name = _name;
    price = _price;
  }
  
  // Accessors
  public String getItemCode(){
    return itemCode;
  }
  public String getName(){
    return name;
  }
  public double getPrice(){
    return price;
  }
}