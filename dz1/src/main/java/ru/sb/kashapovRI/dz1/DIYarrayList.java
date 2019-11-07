package ru.sb.kashapovRI.dz1;

import java.util.*;
import java.lang.*;
import java.util.stream.Stream;

public class DIYarrayList<T> implements List<T> {
    protected T[] array;
    @Override
    public int size() {
        if(this.isEmpty()==true)
            return 0;
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        if(array==null)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        if(this.array==null){
            this.array = (T[])new Object[1];
            this.array[0]=t;
        }
        else {
            this.array=Arrays.copyOf(this.array,this.array.length+1);
        }
        this.array[this.array.length-1]=t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int i, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(int i) {
//        throw new UnsupportedOperationException();
        return array[i];
    }

    @Override
    public T set(int i, T t) {
          array[i]=t;
//        throw new UnsupportedOperationException();
        if (i==0)
            return null;
        return array[i-1];
    }

    @Override
    public void add(int i, T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        ListIterator<T> tListIterator=new ListIterator<T>() {
            private int index=-1;
            @Override
            public boolean hasNext() {
                return index<array.length-1;
            }

            @Override
            public T next() {
                if(hasNext())
                    return array[++index];
                return array[index];
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public T previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {

            }

            @Override
            public void set(T t) {
                array[index]=t;
            }

            @Override
            public void add(T t) {

            }
        };
        return tListIterator;
    }

    @Override
    public ListIterator<T> listIterator(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int i, int i1) {
        throw new UnsupportedOperationException();
    }

    public String toString(){
        return Arrays.toString(array);
    }
}
