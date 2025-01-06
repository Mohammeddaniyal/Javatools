package com.thinking.machines.util;
public class TMLinkedList<T> implements TMList<T>
{
class TMNode<T>
{
public T data;
public TMNode<T> next;
public TMNode()
{
this.data=null;
this.next=null;
}
}
class TMLinkedListIterator<T> implements TMIterator<T>
{
public TMNode<T> ptr;
public TMLinkedListIterator(TMNode<T> ptr)
{
this.ptr=ptr;
}
public boolean hasNext()
{
return this.ptr!=null;
}
public T next()
{
if(this.ptr==null) throw new InvalidIteratorException("Iterator has no more elements");
T data=(T)this.ptr.data;
this.ptr=this.ptr.next;
return data;
}
}
private TMNode<T> start,end;
private int size;

public TMIterator<T> iterator()
{
TMLinkedListIterator<T> tmLinkedListIterator;
tmLinkedListIterator=new TMLinkedListIterator<T>(this.start);
return tmLinkedListIterator;
}
public void forEach(TMListItemAcceptor<T> a)
{
if(a==null) return;
for(TMNode<T> t=this.start;t!=null;t=t.next) a.accept(t.data);
}
public TMLinkedList()
{
this.start=null;
this.end=null;
this.size=0;
}
public void add(T data)
{
TMNode node;
node=new TMNode();
node.data=data;
if(this.start==null && this.end==null)
{
this.start=this.end=node;
}else{
this.end.next=node;
this.end=node;
}
this.size++;
}
public void add(T data,int index)
{
if(index<0 || index>this.size) throw new IndexOutOfBoundsException("Invalid Index :"+ index);
if(index==this.size)
{
this.add(data);
return;
}
TMNode<T> t,j;
j=null;
int x=0;
t=this.start;
while(x<index)
{
j=t;
t=t.next;
x++;
}
TMNode<T> node;
node=new TMNode<T>();
node.data=data;
if(t==this.start)
{
node.next=this.start;
this.start=node;
}else
{
node.next=t;
j.next=node;
}
this.size++;
}
public void insert(T data,int index)
{
this.add(data,index);
}
public T removeAt(int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid Index :"+ index);
TMNode<T> t,j;
j=null;
int x=0;
t=this.start;
while(x<index)
{
j=t;
t=t.next;
x++;
}
if(t==this.start && t==this.end)
{
this.start=this.end=t;
}else if(t==this.start)
{
this.start=this.start.next;
}else if(t==this.end)
{
this.end=j;
this.end.next=null;
}else
{
j.next=t.next;
}
this.size--;
return t.data;
}
public void update(T data,int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid Index :"+ index);
TMNode<T> t;
int x=0;
t=this.start;
while(x<index)
{
t=t.next;
x++;
}
t.data=data;
}
public void clear()
{
this.start=this.end=null;
this.size=0;
}
public void removeAll()
{
this.clear();
}
public T get(int index)
{
if(index<0 || index>=this.size) throw new IndexOutOfBoundsException("Invalid Index :"+ index);
TMNode<T> t;
int x=0;
t=this.start;
while(x<index)
{
t=t.next;
x++;
}
return t.data;
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