package by.training.multithreading.service.impl;

import by.training.multithreading.dao.DaoException;
import by.training.multithreading.dao.DaoFactory;
import by.training.multithreading.dao.ThreadsConfigDao;
import by.training.multithreading.entity.Matrix;
import by.training.multithreading.service.ParallelMatrixFillerService;
import by.training.multithreading.service.ServiceException;
import by.training.multithreading.service.thread.SetFillerThread;
import by.training.multithreading.service.validator.Validator;
import by.training.multithreading.service.validator.impl.SquareMatrixWithEmptyMainDiagonalValidator;
import by.training.multithreading.service.validator.impl.ThreadsConfigValidator;
import by.training.multithreading.util.ThreadUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class SetFillerService implements ParallelMatrixFillerService {

    private static final Logger log
            = LogManager.getLogger(SetFillerService.class);

    private final ThreadsConfigDao dao
            = DaoFactory.getInstance().getThreadsConfigDao();
    private final Validator<Matrix> matrixValidator
            = SquareMatrixWithEmptyMainDiagonalValidator.getInstance();
    private final Validator<List<Integer>> threadsConfigValidator
            = ThreadsConfigValidator.getInstance();

    @Override
    public void fillMatrix(Matrix matrix, String pathToThreadsConfig)
            throws ServiceException {
        log.debug("received pathToThreadsConfig: {}. Matrix: {}",
                pathToThreadsConfig, matrix
        );
        if (pathToThreadsConfig == null) {
            throw new ServiceException("path can't be null");
        }
        if (!matrixValidator.isValid(matrix)) {
            throw new ServiceException("matrix is invalid");
        }
        try {
            Path path = Path.of(pathToThreadsConfig);
            List<Integer> threadsConfig = dao.readConfigs(path);
            log.debug("configs: {}", threadsConfig);
            if (!threadsConfigValidator.isValid(threadsConfig)) {
                throw new ServiceException("invalid threads config");
            }
            ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
            List<SetFillerThread> threads = threadsConfig.stream()
                    .map(value -> new SetFillerThread(value, matrix, set))
                    .toList();
            threads.forEach(Thread::start);
            ThreadUtil.joinThreads(threads);
            log.debug("result: {}", matrix);
        } catch (DaoException e) {
            throw new ServiceException("dao exception occurred", e);
        } catch (InvalidPathException e) {
            throw new ServiceException("invalid path", e);
        }
    }
}
