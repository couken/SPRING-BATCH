package jp.co.tokyo_gas.batch.chunk.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import jp.co.tokyo_gas.batch.chunk.entity.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtils {

  private String fileName;
  private CSVReader CSVReader;
  private CSVWriter CSVWriter;
  private FileReader fileReader;
  private FileWriter fileWriter;
  private File file;

  public FileUtils(String fileName) {
    this.fileName = fileName;
  }

  public Student readLine() {
    try {
      if (CSVReader == null)
        initReader();
      String[] obj = CSVReader.readNext();
      if (obj == null)
        return null;
      return new Student(obj[0], obj[1], obj[2]);
    } catch (Exception e) {
      log.error("Error while reading line in file: " + this.fileName);
      return null;
    }
  }

  public void writeLine(Student stu) {
    try {
      if (CSVWriter == null)
        initWriter();
      String[] stus = new String[3];
      stus[0] = stu.getId();
      stus[1] = stu.getName();
      stus[2] = stu.getSex();
      CSVWriter.writeNext(stus);
    } catch (Exception e) {
      log.error("Error while writing line in file: " + this.fileName);
    }
  }

  private void initReader() throws Exception {
    if (file == null)
      file = new File(System.getProperty("user.dir")+"/"+fileName);
    if (fileReader == null)
      fileReader = new FileReader(file);
    if (CSVReader == null)
      CSVReader = new CSVReader(fileReader);
  }

  private void initWriter() throws Exception {
    if (file == null) {
      file = new File(System.getProperty("user.dir")+"/"+fileName);
      file.createNewFile();
    }
    if (fileWriter == null)
      fileWriter = new FileWriter(file, true);
    if (CSVWriter == null)
      CSVWriter = new CSVWriter(fileWriter);
  }

  public void closeWriter() {
    try {
      CSVWriter.close();
      fileWriter.close();
    } catch (IOException e) {
      log.error("Error while closing writer.");
    }
  }

  public void closeReader() {
    try {
      CSVReader.close();
      fileReader.close();
    } catch (IOException e) {
      log.error("Error while closing reader.");
    }
  }

}
