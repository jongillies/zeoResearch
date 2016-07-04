package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;

public abstract interface InstantConverter
  extends Converter
{
  public abstract Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone);
  
  public abstract Chronology getChronology(Object paramObject, Chronology paramChronology);
  
  public abstract long getInstantMillis(Object paramObject, Chronology paramChronology);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\InstantConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */