package org.joda.time.tz;

import java.util.Locale;

public abstract interface NameProvider
{
  public abstract String getShortName(Locale paramLocale, String paramString1, String paramString2);
  
  public abstract String getName(Locale paramLocale, String paramString1, String paramString2);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\tz\NameProvider.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */