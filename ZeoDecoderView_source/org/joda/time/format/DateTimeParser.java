package org.joda.time.format;

public abstract interface DateTimeParser
{
  public abstract int estimateParsedLength();
  
  public abstract int parseInto(DateTimeParserBucket paramDateTimeParserBucket, String paramString, int paramInt);
}


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\joda\time\format\DateTimeParser.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */