package jp.co.tokyo_gas.batch.chunk.item;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import jp.co.tokyo_gas.batch.chunk.entity.Student;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StudentProcessor implements ItemProcessor<Student, Student>,StepExecutionListener{

  @Override
  public void beforeStep(StepExecution stepExecution) {
    log.debug("Student Processor initialized.");
    
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    log.debug("Student Processor ended.");
    return ExitStatus.COMPLETED;
  }

  @Override
  public Student process(Student stu) throws Exception {
    String sex = stu.getSex();
    if ("0".equals(sex)) {

      sex = "BOY";
    } else {
      sex = "GIRL";
    }
    // transform sex
    stu.setSex(sex);
    return stu;
  }

}
