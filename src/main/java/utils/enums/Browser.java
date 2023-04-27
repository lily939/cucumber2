package utils.enums;


public enum Browser {
  FIREFOX, CHROME, EDGE, IE, SAFARI, OPERA;

  public static Browser lookup(String b) throws Exception {
    for (Browser browser : Browser.values()) {
      if (browser.name().equalsIgnoreCase(b)) {
        return browser;
      }
    }
    throw new Exception("The selected browser " + b + " is not an applicable choice");
  }

  public static Browser getBrowser() {
    if (System.getProperty("browser") != null) {
      try {
        return lookup(System.getProperty("browser"));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    // Default Chrome
    return CHROME;
  }
}
