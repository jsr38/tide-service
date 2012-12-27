package nz.co.jsrsolutions.tideservice.core.reference;

public enum TidePredictionType {

  HighWater("HW"), LowWater("LW");

  private String mText;

  TidePredictionType(String text) {
    mText = text;
  }

  public String getText() {
    return mText;
  }

  public static TidePredictionType fromString(String text) {
    if (text != null) {
      for (TidePredictionType t : TidePredictionType.values()) {
        if (text.equalsIgnoreCase(t.mText)) {
          return t;
        }
      }
    }
    throw new IllegalArgumentException(text);
  }
}
