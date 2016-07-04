package org.joda.time;

public abstract interface ReadablePartial
{
  public abstract int size();
  
  public abstract DateTimeFieldType getFieldType(int paramInt);
  
  public abstract DateTimeField getField(int paramInt);
  
  public abstract int getValue(int paramInt);
  
  public abstract Chronology getChronology();
  
  public abstract int get(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract boolean isSupported(DateTimeFieldType paramDateTimeFieldType);
  
  public abstract DateTime toDateTime(ReadableInstant paramReadableInstant);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
  
  public abstract String toString();
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\ReadablePartial.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */