package com.gamelabgraz.jam.tpbjg.map.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.gamelabgraz.jam.tpbjg.map.IGameMap;

/**
 * interface for XML Serialization of {@link IGameMap} objects
 * 
 * @author vinzynth Sep 27, 2014 - 12:19:23 PM
 *
 */
public interface IXMLGameMapProvider {

  /**
   * Returns a {@link Iterable} of Game maps loaded from given file <br>
   * file paramater may be a single file or a directory
   * 
   * @param file
   *          given file or directory with level xmls
   * @return {@link Iterable} with {@link IGameMap} objects
   * 
   * @throws FileNotFoundException
   *           if given file or directory does not exist
   * @throws IOException
   *           if a error occurs on file reading
   */
  Iterable<IGameMap> provide(final File file) throws FileNotFoundException, IOException;

  /**
   * saves given game map to target file directory
   * 
   * @param target
   *          given target directory
   * @param map
   *          given {@link IGameMap} to persist
   * @throws FileNotFoundException
   *           if given directory does not exists
   */
  void save(final File targetDirectory, final IGameMap map) throws FileNotFoundException;
}
