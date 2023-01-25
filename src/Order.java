class Order{
  private int length;
  private MenuItem[] itemsOrdered;

  // Constructor
  public Order(MenuItem[] _itemsOrdered){
    itemsOrdered = _itemsOrdered;
    length = itemsOrdered.length;
  }

  // Add items to ordered
  public void addItems(MenuItem[] _itemsOrdered){
    MenuItem[] temp = new MenuItem[itemsOrdered.length + _itemsOrdered.length];
    for(int i = 0; i < itemsOrdered.length; i++){ // Copy previous items
      temp[i] = itemsOrdered[i];
    }
    for(int i = 0; i < _itemsOrdered.length;i++){ // Add new items
      temp[i + itemsOrdered.length] = _itemsOrdered[i];
    }
    itemsOrdered = temp;
    length = itemsOrdered.length;
  }

  // Accessors
  public MenuItem getItem(int index){
    return itemsOrdered[index];
  }
  public int getLength(){
    return length;
  }
}