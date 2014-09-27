package com.gamelabgraz.jam.tpbjg.config;

import java.io.File;

import at.chrl.nutils.configuration.ConfigUtil;
import at.chrl.nutils.configuration.Property;

/**
 * Config class for ThePeanutButterJellyGame
 * 
 * @author vinzynth Sep 27, 2014 - 12:59:05 PM
 *
 */
public class TPBJGConfig {
  static {
    ConfigUtil.loadAndExport(TPBJGConfig.class);
  }

  @Property(key = "level.file.extension", defaultValue = "tpbjgmap", description = "Level File extension")
  public static String LEVEL_FILE_EXTENSION;

  @Property(key = "level.file.directory", defaultValue = "levels", description = "Level File directory")
  public static File LEVEL_FILE_DIRECTORY;

  
}
