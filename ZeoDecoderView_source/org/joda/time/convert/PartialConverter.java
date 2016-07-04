package org.joda.time.convert;

import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;
import org.joda.time.format.DateTimeFormatter;

public abstract interface PartialConverter
  extends Converter
{
  public abstract Chronology getChronology(Object paramObject, DateTimeZone paramDateTimeZone);
  
  public abstract Chronology getChronology(Object paramObject, Chronology paramChronology);
  
  public abstract int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology);
  
  public abstract int[] getPartialValues(ReadablePartial paramReadablePartial, Object paramObject, Chronology paramChronology, DateTimeFormatter paramDateTimeFormatter);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\convert\PartialConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */