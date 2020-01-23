package ru.itvitality.sbrf.cu.patterns.behavioral.iterator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Family<E> implements Iterable<E> {
  private final Set<E> elements;

  public Family(Collection<E> elements) {
    this.elements = new HashSet<>();
    this.elements.addAll(elements);
  }

  @Override
  public Iterator<E> iterator() {
    return elements.iterator();
  }
}
