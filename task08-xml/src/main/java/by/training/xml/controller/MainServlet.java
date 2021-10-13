package by.training.xml.controller;

import by.training.xml.entity.Gem;
import by.training.xml.entity.SyntheticGem;
import by.training.xml.service.ServiceException;
import by.training.xml.service.ServiceFactory;
import by.training.xml.service.XmlService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet("/")
public class MainServlet extends HttpServlet {

    private static final Logger log = LogManager.getLogger(MainServlet.class);

    private final XmlService<Gem> gemXmlService;

    public MainServlet() {
        gemXmlService = ServiceFactory.getInstance().getGemXmlService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.getRequestDispatcher("WEB-INF/jsp/main-get.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            log.error(e);
            printError(resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Part part = req.getPart("xml");
            InputStream stream = part.getInputStream();
            Path pathToNewFile = Path.of(Objects.requireNonNull(
                    getClass().getClassLoader().getResource("data")
            ).toURI()).resolve(part.getSubmittedFileName());
            Files.write(pathToNewFile, stream.readAllBytes());
            List<Gem> gems = gemXmlService.findEntities("data/" + part.getSubmittedFileName(),
                    req.getParameter("parser")
            );
            Map<Long, String> laboratoriesMap = new HashMap<>();
            for (Gem gem : gems) {
                String laboratory = "-";
                if (gem instanceof SyntheticGem syntheticGem) {
                    laboratory = syntheticGem.getLaboratory();
                }
                laboratoriesMap.put(gem.getId(), laboratory);
            }
            Files.deleteIfExists(pathToNewFile);
            req.setAttribute("laboratoriesMap", laboratoriesMap);
            req.setAttribute("gems", gems);
            req.getRequestDispatcher("WEB-INF/jsp/main-post.jsp").forward(req, resp);
        } catch (ServiceException | ServletException | IOException | URISyntaxException e) {
            log.error(e);
            printError(resp);
        }
    }

    private void printError(HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            resp.getWriter().write("<h1>Error occurred!</h1>");
        } catch (IOException e) {
            log.error(e);
        }
    }
}
