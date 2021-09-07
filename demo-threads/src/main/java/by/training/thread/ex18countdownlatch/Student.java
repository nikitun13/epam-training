package by.training.thread.ex18countdownlatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Student extends Thread {

    private static final Logger log
            = LogManager.getLogger(Student.class);

    private Integer idStudent;
    private final List<Task> taskList;
    private final CountDownLatch countDown;

    public Student(Integer idStudent, int numberTasks) {
        this.idStudent = idStudent;
        this.countDown = new CountDownLatch(numberTasks);
        this.taskList = new ArrayList<>(numberTasks);
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Integer idStudent) {
        this.idStudent = idStudent;
    }

    public CountDownLatch getCountDownLatch() {
        return countDown;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    @Override
    public void run() {
        int i = 0;
        for (Task inWork : taskList) {
            // на выполнение задания требуется некоторое время
            try {
                Thread.sleep(new Random().nextInt(100));
            } catch (InterruptedException e) {
                log.error(e);
            }
            // отправка ответа
            inWork.setAnswer("Answer #" + ++i);
            log.info("Answer #" + i + " from " + idStudent);
        }
        try {
            countDown.await(); // ожидание проверки заданий
        } catch (InterruptedException e) {
            log.error(e);
        }
        // подсчет средней оценки за все задачи
        float averageMark = 0;
        for (Task inWork : taskList) {
            // выполнение задания
            averageMark += inWork.getMark(); // отправка ответа
        }
        averageMark /= taskList.size();
        log.info("Student " + idStudent + ": Average mark = "
                + averageMark);
    }
}
