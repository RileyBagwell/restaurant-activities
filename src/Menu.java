class Menu{
  private int numItems;
  private MenuItem menu[];

  // Constructor
  public Menu(int _numItems){
    numItems = _numItems;
    menu = new MenuItem[numItems];
  }

  // Adds an item to the Menu
  public void addItem(int index, MenuItem item){
    menu[index] = item;
  }

  // Returns the index in menu of the given item code. -1 if not found
  public int findIndex(String itemToFind){
    for(int i = 0; i < menu.length; i++){
      if(itemToFind.equals(menu[i].getItemCode()))
        return i;
    }
    return -1;
  }

  // Returns an item at given index
  public MenuItem returnItem(int index){
    return menu[index];
  }
}