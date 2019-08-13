package jp.co.tokyo_gas.batch.chunk;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ChunkConfig.class})
public class ChunkConfigTest{
  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  public void doJob() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();
    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}
