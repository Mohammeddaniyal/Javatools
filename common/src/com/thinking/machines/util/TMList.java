package com.thinking.machines.util;
public interface TMList<T>
{
public void add(T data);
public void add(T data,int index);
public void insert(T data,int index);
public T removeAt(int index);
public void update(T data,int index);
public void clear();
public void removeAll();
public T get(int index);
public int size();
public void copyTo(TMList<T> other);
public void copyFrom(TMList<T> other);
public void appendTo(TMList<T> other);
public void appendFrom(TMList<T> other);
public TMIterator<T> iterator();
public void forEach(TMListItemAcceptor<T> a);
}