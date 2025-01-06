package com.thinking.machines.util;
import java.lang.reflect.*;
import java.io.*; 
public class SetterGetterGenerator
{
private static String getDefaultValue(Class c)
{
String className=c.getName();
if(className.equalsIgnoreCase("java.util.Long") || className.equalsIgnoreCase("long")) return "0";
if(className.equalsIgnoreCase("java.util.Integer") || className.equalsIgnoreCase("int")) return "0";
if(className.equalsIgnoreCase("java.util.Short") || className.equalsIgnoreCase("short")) return "0";
if(className.equalsIgnoreCase("java.util.Byte") || className.equalsIgnoreCase("byte")) return "0";
if(className.equalsIgnoreCase("java.util.Double") || className.equalsIgnoreCase("double")) return "0.0";
if(className.equalsIgnoreCase("java.util.Float") || className.equalsIgnoreCase("float")) return "0.0f";
if(className.equalsIgnoreCase("java.util.Character") || className.equalsIgnoreCase("char")) return "' '";
if(className.equalsIgnoreCase("java.util.Boolean") || className.equalsIgnoreCase("boolean")) return "false";
if(className.equalsIgnoreCase("java.lang.String")) return "\"\"";
return "null";
}
public static void main(String gg[])
{
if(gg.length!=1 && gg.length!=2)
{
System.out.println("Usage : java classpath path_to_jar_file;. com.thinking.machines.util.SetterGetterGenerator class_name");
return;
}
String className=gg[0];
if(gg.length==2)
{
if(gg[2].equalsIgnoreCase("constructor=true")==false || gg[2].equalsIgnoreCase("constructor=false")==false)
{
System.out.println("Usage : java classpath path_to_jar_file;. com.thinking.machines.util.SetterGetterGenerator class_name constructor=true/false");
return;
}
}
try
{
String setterName;
String getterName;
String line;
Class c=Class.forName(className);
Field fields[];
fields=c.getDeclaredFields();
Class fieldType;
Field field;
String fieldName;
String fieldTypeName;
String tmp;
TMList<String> list=new TMArrayList<String>();

if(gg.length==1 || (gg.length==2 && gg[2].equalsIgnoreCase("constructor=true")==true))
{
line="public "+c.getSimpleName()+"()";
list.add(line);
list.add("{");
for(int e=0;e<fields.length;e++)
{
field=fields[e];
line="this."+field.getName()+"="+getDefaultValue(field.getType())+";";
list.add(line);
}
list.add("}");
}

for(int e=0;e<fields.length;e++)
{
field=fields[e];
fieldName=field.getName();
fieldType=field.getType();
fieldTypeName=fieldType.getName();
if(fieldName.charAt(0)>=97 && fieldName.charAt(0)<=122)
{
tmp=fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
}
else
{
tmp=fieldName;
}
setterName="set"+tmp;
getterName="get"+tmp;
line="public void "+setterName+"("+fieldType.getName()+" "+fieldName+")";
list.add(line);
list.add("{");
line="this."+fieldName+"="+fieldName+";";
list.add(line);
list.add("}");

line="public "+fieldType.getName()+" "+getterName+"()";
list.add(line);
list.add("{");
line="return this."+fieldName+";";
list.add(line);
list.add("}");
}//for loop ends


File file;
file=new File("tmp.tmp");
if(file.exists()) file.delete();
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
TMIterator<String> iterator=list.iterator();
while(iterator.hasNext())
{
line=iterator.next();
randomAccessFile.writeBytes(line+"\r\n");
}
randomAccessFile.close();
System.out.println("Setter/Getter for : "+c.getName()+" generated in file named as tmp.tmp");
}catch(ClassNotFoundException classNotFoundException)
{

}
catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}
}