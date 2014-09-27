package com.gamelabgraz.jam.tpbjg.map;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.IntStream;

import org.junit.Test;

import com.gamelabgraz.jam.tpbjg.map.implementation.GameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.implementation.SampleGameMapFactory;
import com.gamelabgraz.jam.tpbjg.map.xml.IXMLGameMapProvider;
import com.gamelabgraz.jam.tpbjg.map.xml.implementaion.XMLGameMapProvider;
import com.gamelabgraz.jam.tpbjg.util.GameMapUtils;
import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;;

/**
 * @author vinzynth
 * Sep 27, 2014 - 3:25:20 PM
 *
 */
public class MapIOTest {

  @Test
  public void testSave() {
    SampleGameMapFactory sgmf = new SampleGameMapFactory();
    
    Collection<IGameMap> maps = new ArrayList<>(); 
    
    IntStream.range(0, 20).forEach(i -> maps.add(sgmf.getGameMap(i)));
    
    IXMLGameMapProvider provider = new XMLGameMapProvider();
    
    for (IGameMap iGameMap : maps) {
      try {
        provider.save(TPBJGConfig.LEVEL_FILE_DIRECTORY, iGameMap);
      } catch (FileNotFoundException e) {
        TPBJGConfig.LEVEL_FILE_DIRECTORY.mkdirs();
        try {
          provider.save(TPBJGConfig.LEVEL_FILE_DIRECTORY, iGameMap);
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      }
    }
  }
  
  @Test
  public void testLoad() throws Exception {
    IGameMapFactory igmf = GameMapFactory.getInstance();
    IntStream.range(0, 20).forEach(igmf::getGameMap);
  }
  
  
  @Test
  public void testMapPrint() throws Exception {
    IGameMapFactory igmf = GameMapFactory.getInstance();
    GameMapUtils.printMap(igmf.getGameMap(0));
  }
}
