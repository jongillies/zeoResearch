package org.joda.time;

import java.util.Locale;

public abstract interface ReadableDateTime
  extends ReadableInstant
{
  public abstract int getDayOfWeek();
  
  public abstract int getDayOfMonth();
  
  public abstract int getDayOfYear();
  
  public abstract int getWeekOfWeekyear();
  
  public abstract int getWeekyear();
  
  public abstract int getMonthOfYear();
  
  public abstract int getYear();
  
  public abstract int getYearOfEra();
  
  public abstract int getYearOfCentury();
  
  public abstract int getCenturyOfEra();
  
  public abstract int getEra();
  
  public abstract int getMillisOfSecond();
  
  public abstract int getMillisOfDay();
  
  public abstract int getSecondOfMinute();
  
  public abstract int getSecondOfDay();
  
  public abstract int getMinuteOfHour();
  
  public abstract int getMinuteOfDay();
  
  public abstract int getHourOfDay();
  
  public abstract DateTime toDateTime();
  
  public abstract MutableDateTime toMutableDateTime();
  
  public abstract String toString(String paramString)
    throws IllegalArgumentException;
  
  public abstract String toString(String paramString, Locale paramLocale)
    throws IllegalArgumentException;
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\ReadableDateTime.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */