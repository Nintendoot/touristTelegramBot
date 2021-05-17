package by.nintendo.touristtelegrambot.bot;

public enum Constants {
  START("/start"),
  HELP("help"),
   ALL_CITY("all city"),
    HELLO("Привет!)Я помогу тебе узнать о интересных местах в разных городах. Введи город: ");

  public String item;

    Constants(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
