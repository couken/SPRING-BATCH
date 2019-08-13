package jp.co.tokyo_gas.batch.chunk.item;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;
import jp.co.tokyo_gas.batch.chunk.entity.Student;
import jp.co.tokyo_gas.batch.chunk.utils.FileUtils;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Component
public class StudentReader implements ItemReader<Student>,StepExecutionListener {

  private FileUtils fu;
  
  @Override
  public Student read()
      throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
    Student student = fu.readLine();
    if (null != student) {
      log.debug("Read Student:" + student.toString());
    }
    return student;
  }

  @Override
  public void beforeStep(StepExecution stepExecution) {
    fu = new FileUtils("data/student.csv");
    log.debug("Student Reader initialized.");
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeReader();
    log.debug("Student Reader ended.");
    return ExitStatus.COMPLETED;
  }

}
