package jp.co.tokyo_gas.batch.chunk.item;

import java.util.List;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import jp.co.tokyo_gas.batch.chunk.entity.Student;
import jp.co.tokyo_gas.batch.chunk.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentWriter implements ItemWriter<Student>, StepExecutionListener {

  private FileUtils fu;

  @Override
  public void beforeStep(StepExecution stepExecution) {
    fu = new FileUtils("data/output.csv");
    log.debug("Student Writer initialized.");

  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fu.closeWriter();
    log.debug("Student Writer ended.");
    return ExitStatus.COMPLETED;
  }

  @Override
  public void write(List<? extends Student> stus) {
    try {
      for (Student stu : stus) {
        fu.writeLine(stu);
        log.debug("Wrote student " + stu.toString());
      }

    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

}
