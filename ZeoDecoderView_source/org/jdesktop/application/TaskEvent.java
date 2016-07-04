/*    */ package org.jdesktop.application;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskEvent<T>
/*    */   extends EventObject
/*    */ {
/*    */   private final T value;
/*    */   
/*    */   public final T getValue()
/*    */   {
/* 23 */     return (T)this.value;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public TaskEvent(Task source, T value)
/*    */   {
/* 33 */     super(source);
/* 34 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\billiam.liu\Downloads\ZeoDecoderViewer0.3aRelease\!\org\jdesktop\application\TaskEvent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */