import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rout.StartTest;

public class RunTest {
    private static final Logger logger = LoggerFactory.getLogger(RunTest.class);

    public static void main(String[] args) {
    logger.info("Start program ");
    new StartTest().start();
    logger.info("Stop program");
    }
}
