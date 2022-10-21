package ham_fisted;



import clojure.lang.Util;
import clojure.lang.RT;


public class Casts {
  public static boolean booleanCast(Object obj) {
    if(obj == null)
      return false;
    if(obj instanceof Boolean)
      return (Boolean)obj;
    if(obj instanceof Number) {
      final Number nobj = (Number)obj;
      return ((obj instanceof Long) || (obj instanceof Integer)) ? nobj.longValue() != 0 : booleanCast(nobj.doubleValue());
    }
    if(obj instanceof Character)
      return ((Character)obj).charValue() != 0;
    return true;
  }
  public static boolean booleanCast(long obj) {
    return obj != 0;
  }
  public static boolean booleanCast(double obj) {
    if (! Double.isFinite(obj))
      throw new RuntimeException("Non-finite double cannot be casted to boolean: "
				 + String.valueOf(obj));
    return obj != 0.0;
  }
  public static boolean booleanCast(float obj) {
    if (! Float.isFinite(obj))
      throw new RuntimeException("Non-finite double cannot be casted to boolean: "
				 + String.valueOf(obj));
    return obj != 0.0;
 }
  public static boolean booleanCast(boolean obj) {
    return obj;
  }
  public static char charCast(double obj) {
    if(!Double.isFinite(obj))
      throw new RuntimeException("Non-finite double cannot be casted to long: "
				 + String.valueOf(obj));
    return RT.charCast(obj);
  }
  public static char charCast(float obj) {
    if(!Float.isFinite(obj))
      throw new RuntimeException("Non-finite float cannot be casted to long: "
				 + String.valueOf(obj));
    return RT.charCast(obj);
  }
  public static char charCast(Object obj) {
    if (obj instanceof Boolean)
      return ((Boolean)obj) ? (char)1 : (char)0;
    else if (obj instanceof Number) {
      final Number nobj = (Number)obj;
      if(obj instanceof Double)
	return charCast(nobj.doubleValue());
      else if(obj instanceof Float)
	return charCast(nobj.floatValue());
      else
	return charCast(nobj.longValue());
    }
    return RT.charCast(obj);
  }
  public static long longCast(Object obj) {
    if (obj instanceof Boolean)
      return ((Boolean)obj) ? 1 : 0;
    else if (obj instanceof Double)
      return longCast(((Double)obj).doubleValue());
    else if (obj instanceof Float)
      return longCast(((Float)obj).floatValue());
    return RT.longCast(obj);
  }
  public static long longCast(long obj) {
    return obj;
  }
  public static long longCast(char obj) {
    return obj;
  }
  public static long longCast(double obj) {
    if(!Double.isFinite(obj))
      throw new RuntimeException("Non-finite double cannot be casted to long: "
				 + String.valueOf(obj));
    return (long)obj;
  }
  public static long longCast(float obj) {
    if(Float.isFinite(obj))
      throw new RuntimeException("Non-finite float cannot be casted to long: "
				 + String.valueOf(obj));
    return (long)obj;
  }
  public static long longCast(boolean obj) {
    return obj ? 1 : 0;
  }
  public static double doubleCast(Object obj) {
    if (obj == null)
      return Double.NaN;
    if (obj instanceof Boolean)
      return ((Boolean)obj) ? 1.0 : 0.0;
    return RT.doubleCast(obj);
  }
  public static double doubleCast(long obj) {
    return RT.doubleCast(obj);
  }
  public static double doubleCast(double obj) {
    return RT.doubleCast(obj);
  }
  public static double doubleCast(boolean obj) {
    return obj ? 1.0 : 0.0;
  }
}
