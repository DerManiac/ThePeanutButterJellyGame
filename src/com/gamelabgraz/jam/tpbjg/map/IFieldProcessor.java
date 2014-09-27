package com.gamelabgraz.jam.tpbjg.map;

/**
 * {@link FunctionalInterface} for easy iteration over each field
 * 
 * @author vinzynth Sep 27, 2014 - 2:05:55 PM
 *
 */
public interface IFieldProcessor {
  void process(final int x, final int y, final FieldType type);
}
