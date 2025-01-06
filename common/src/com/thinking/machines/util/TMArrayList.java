package com.thinking.machines.util;
public class TMArrayList<T> implements TMList<T>
{
private Object [] collection;
private int size;
class TMArrayListIterator<T> implements TMIterator<T>
{
public int index;
public TMArrayListIterator(int index)
{
this.index=index;
}
public boolean hasNext()
{
return index<TMArrayList.this.size;
}
public T next()
{
if(index>=size) throw new InvalidIteratorException("Iterator has no more elements");
T data=(T)TMArrayList.this.get(index);
index++;
return (T)data;
}
}
public TMIterator<T> iterator()
{
TMArrayListIterator<T> tmArrayListIterator;
tmArrayListIterator=new TMArrayListIterator<T>(0);
return tmArrayListIterator;
}
public void forEach(TMListItemAcceptor<T> a)
{
if(a==null) return;
for(int i=0;i<this.size;i++) a.accept((T)this.collection[i]);
}
public TMArrayList()
{
this.collection=new Object[10];
this.size=0; 
}
private void reSize()
{
Object [] tmp=new Object[this.size+10];
for(int i=0;i<this.size;i++) tmp[i]=this.collection[i];
this.collection=tmp;
}
public void add(T data)
{
if(this.size==this.collection.length)
{
this.reSize();
}
this.collection[this.size++]=data;
}
public void add(T data,int index)
{
if(index<0 || index>this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
if(this.size==this.collection.length)
{
reSize();
}
for(int i=this.size;i>index;i--) this.collection[i]=this.collection[i-1];
this.collection[index]=data;
this.size++;
}
public void insert(T data,int index)
{
this.add(data,index);
}
public T removeAt(int index)
{
if(index<0 || index>this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
T data=(T)this.get(index);
int ep=this.size-2;
for(int i=index;i<=ep;i++) this.collection[i]=this.collection[i+1];
this.size--;
return (T)data;
}
public void update(T data,int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
this.collection[index]=data;
}
public void clear()
{
this.size=0;
}
public void removeAll()
{
this.clear();
}
public T get(int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid Index : "+index);
return (T)this.collection[index];
}
public int size()
{
return this.size;
}
public void copyTo(TMList<T> other)
{
other.clear();
for(int i=0;i<this.size();i++) other.add(this.get(i));
}
public void copyFrom(TMList<T> other)
{
this.clear();
for(int i=0;i<other.size();i++) this.add(other.get(i));
}
public void appendTo(TMList<T> other)
{
for(int i=0;i<this.size();i++) other.add(this.get(i));
}
public void appendFrom(TMList<T> other)
{
for(int i=0;i<other.size();i++) this.add(other.get(i));
}
}