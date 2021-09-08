package by.training.thread.ex18count_down_latch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tutor extends Thread {

    private static final Logger log
            = LogManager.getLogger(Tutor.class);

    private Integer idTutor;
    private final List<Student> list;

    public Tutor() {
        this.list = new ArrayList<>();
    }

    public Tutor(List<Student> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (Student st : list) {
            // проверить, выданы ли студенту задания
            List<Task> tasks = st.getTaskList();
            for (Task current : tasks) {
                // проверить наличие ответа!
                int mark = 3 + new Random().nextInt(7);
                current.setMark(mark);
                log.info(mark + " for student N "
                        + st.getIdStudent());
                st.getCountDownLatch().countDown();
            }
            log.info("All estimates made for " + st.getIdStudent());
        }
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer id) {
        this.idTutor = id;
    }
}
