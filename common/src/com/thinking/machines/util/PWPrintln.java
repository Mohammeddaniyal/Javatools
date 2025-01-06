package com.thinking.machines.util;
import java.io.*;
public class PWPrintln
{
public static void main(String gg[])
{
String fileName=gg[0];
try
{
File file=new File(fileName);
if(file.exists()==false) 
{
System.out.println("File : "+fileName+" does not exists.");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
File tmpFile=new File("tmp.tmp");
if(tmpFile.exists()) tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
String line;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
line=randomAccessFile.readLine();
line=line.replaceAll("\"","\\\\\"");
tmpRandomAccessFile.writeBytes("pw.println(\""+line+"\");\r\n");
}
randomAccessFile.close();
tmpRandomAccessFile.close();
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
}