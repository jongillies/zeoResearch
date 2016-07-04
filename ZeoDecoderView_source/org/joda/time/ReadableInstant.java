package org.joda.time;

public abstract interface ReadableInstant
  extends Comparable
{
  public abstract long getMillis();
  
  public abstract Chronology getChronology();
  
  public abstract DateTimeZone getZone();
  
  public abstract int get(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract boolean isSupported(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract Instant toInstant();
  
  public abstract int compareTo(Object paramObject);
  
  public abstract boolean isEqual(ReadableInstant paramReadableInstant);
  
  public abstract boolean isAfter(ReadableInstant paramReadableInstant);
  
  public abstract boolean isBefore(ReadableInstant paramReadableInstant);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract String toString();
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\ReadableInstant.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */