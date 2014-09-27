package com.gamelabgraz.jam.tpbjg.map.xml.implementaion;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;

import com.gamelabgraz.jam.tpbjg.config.TPBJGConfig;
import com.gamelabgraz.jam.tpbjg.map.IGameMap;
import com.gamelabgraz.jam.tpbjg.map.implementation.GameMap;
import com.gamelabgraz.jam.tpbjg.map.xml.IXMLGameMapProvider;

/**
 * default implementation of {@link IXMLGameMapProvider}
 * 
 * @author vinzynth Sep 27, 2014 - 12:25:07 PM
 *
 */
public class XMLGameMapProvider implements IXMLGameMapProvider {

  /**
   * {@inheritDoc}
   * 
   * @throws FileNotFoundException
   *           if given file or directory was not found
   * @throws RuntimeException
   *           if given single file is not a XML
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.xml.IXMLGameMapProvider#provide(java.io.File)
   */
  @Override
  public Iterable<IGameMap> provide(final File file) throws FileNotFoundException, IOException {
    if (!file.exists())
      throw new FileNotFoundException(file.getAbsolutePath() + " does not exist.");

    Collection<File> filesToRead = new ArrayList<File>(1);

    if (!file.isDirectory()) {
      if (!file.getAbsolutePath().endsWith("xml")) {
        throw new RuntimeException(file.getAbsolutePath() + " is not a XML file.");
      }
      filesToRead.add(file);
    } else
      filesToRead = FileUtils.listFiles(file, new String[] { "xml" }, true);

    Collection<XMLGameMapDTO> dtos = new ArrayList<XMLGameMapDTO>();
    filesToRead.forEach(f -> {
      BufferedInputStream in = null;
      try {
        in = new BufferedInputStream(new FileInputStream(f), 8192);
        try {
          JAXBContext context = JAXBContext.newInstance(XMLGameMapDTO.class);
          Unmarshaller unmarshaller = context.createUnmarshaller();
          dtos.add((XMLGameMapDTO) unmarshaller.unmarshal(in));
        } catch (JAXBException e) {
          throw new IOException(e.getMessage() + ": Error reading map from XML map file: " + f.getAbsolutePath(), e);
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally{
        try {
          in.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    return dtos.stream().map(dto -> new GameMap(dto.getId(), dto.getWidth(), dto.getHeight())).collect(Collectors.toList());
  }

  /**
   * Searches the given directory for files with
   * {@link TPBJGConfig#LEVEL_FILE_EXTENSION} files and counts them. Generated
   * map file is generated with proper number as name.
   * <p>
   * Example: 001.tpbjgmap for default
   * <p>
   * 
   * {@inheritDoc}
   * 
   * @throws IllegalArgumentException
   *           if given file parameter is not a directory
   * 
   * @see com.gamelabgraz.jam.tpbjg.map.xml.IXMLGameMapProvider#save(java.io.File,
   *      com.gamelabgraz.jam.tpbjg.map.IGameMap)
   */
  @Override
  public void save(File targetDirectory, IGameMap map) throws FileNotFoundException {
    if (!targetDirectory.exists())
      throw new FileNotFoundException(targetDirectory.getAbsolutePath() + " does not exist.");
    if (!targetDirectory.isDirectory())
      throw new IllegalArgumentException(targetDirectory.getAbsolutePath() + " is not a directory");

    Collection<File> existingLevelFiles = FileUtils.listFiles(targetDirectory, new String[] { TPBJGConfig.LEVEL_FILE_EXTENSION }, false);

    File targetFile = new File(targetDirectory.getAbsoluteFile() + File.separator + String.format("%03d", existingLevelFiles.size()) + "."
        + TPBJGConfig.LEVEL_FILE_EXTENSION);

    try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile), 8192);) {
      JAXBContext context = JAXBContext.newInstance(XMLGameMapDTO.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.marshal(new XMLGameMapDTO(map), out);
    } catch (JAXBException | IOException e) {
      System.err.println(e.getMessage() + ": Could not save File: " + targetFile.getAbsolutePath());
      throw new RuntimeException("Error marshalling object to file: " + targetFile.getAbsolutePath(), e);
    }
  }
}
