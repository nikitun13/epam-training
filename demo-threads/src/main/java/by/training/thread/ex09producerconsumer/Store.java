package by.training.thread.ex09producerconsumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Store {

    private static final Logger log
            = LogManager.getLogger(Store.class);

    int counter = 0; // счетчик товаров
    final int maxNumber = 5; // максимально допустимое число

    // синхронизированный метод для производителей
    synchronized int put() {
        if (counter < maxNumber) { //если товаров меньше
            counter++; // кладем товар
            log.info("склад имеет " + counter + " товар(ов)");
            return 1; // в случае удачного выполнения возвращаем 1
        }
        return 0;// в случае неудачного выполнения возвращаем 0
    }

    // метод для покупателей
    synchronized int get() {
        if (counter > 0) {//если хоть один товар присутствует
            counter--; //берем товар
            log.info("склад имеет " + counter + " товар(ов)");
            return 1;// в случае удачного выполнения возвращаем 1
        }
        return 0;// в случае неудачного выполнения возвращаем 0
    }
}
