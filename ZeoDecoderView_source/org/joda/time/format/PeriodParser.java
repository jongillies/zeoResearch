package org.joda.time.format;

import java.util.Locale;
import org.joda.time.ReadWritablePeriod;

public abstract interface PeriodParser
{
  public abstract int parseInto(ReadWritablePeriod paramReadWritablePeriod, String paramString, int paramInt, Locale paramLocale);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\PeriodParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */