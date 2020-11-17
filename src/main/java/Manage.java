import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import database.JpaUserDao;
import database.UserPoint;
import exceptions.InvalidException;
import oracle.jdbc.OracleDriver;
@SessionScoped
@ManagedBean

public class Manage {
    static HashSet<Integer> idSet = new HashSet<Integer>();
    private final List<String> xArea = Arrays.asList("-3", "-2", "-1", "0", "1", "2", "3", "4", "5");
    // private final List<String> yArea = Arrays.asList("-2", "-1.5", "-1", "-0.5", "0", "0.5", "1", "1.5", "2");
    private ArrayList<Point> points = new ArrayList<Point>();
    private List<UserPoint> userPoints;
    Point point;
    JpaUserDao jpaUserDao;
    @PostConstruct
    private void init() throws ClassNotFoundException {
        //new SomeClass().idinahui();
        point = new Point();
        jpaUserDao = new JpaUserDao();
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void add() throws IOException, ClassNotFoundException {
        long startTime = System.nanoTime();


        // SomeEntity someEntity = new SomeEntity();
        point.setDate(new Date());
        String answer = check(point.getR(), point.getX(), point.getY()) ? "YES" : "NO";
        point.setAnswer(answer);
        System.out.println(point.getY());
        long estimatedTime = System.nanoTime() - startTime;
        point.setWorkTime(estimatedTime);
        points.add(point);
        try {
            jpaUserDao.create(initData());
            // jpaUserDao.clear();
            // jpaUserDao.delete(jpaUserDao.readAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        point = new Point();
        System.out.println("add point");
        FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/table.xhtml");
//        Class.forName("oracle.jdbc.driver.OracleDriver");
        // EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entity");
        // EntityManager manager = emf.createEntityManager();
        //
        // EntityTransaction et = manager.getTransaction();
        // et.begin();
        // someEntity.setId(3);
        // someEntity.setName("Nikita");
        //manager.refresh(someEntity);
        // manager.persist(someEntity);
        // manager.close();

        //point.setAnswer(point.getR(), point.getX(), point.getY());
    }

    public void delete() {
        try {
            jpaUserDao.delete(jpaUserDao.readAll());
            points.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserPoint> initTable() throws SQLException {
            userPoints = jpaUserDao.readAll();
            return userPoints;
    }
    public boolean check(double r, double x, double y) {
        System.out.println(r + "**" + x + "**" + y);
        if (x > 0 && y < 0)
            return false;
        else if (x <= 0 && y <= 0 && (-2 * x - y - r <= 0))
            return true;
        else if (x >= 0 && y >= 0 && (x * x + y * y + 0.17 <= (r * r / 4)))
            return true;
        else return x <= 0 && y >= 0 && (y - 0.000001 <= r) && (x - 0.000001 >= -r / 2);
    }
    public void isData(String r, String x, String y) {
        try {

            boolean checkX = xArea.contains(x);
            double newR = Double.parseDouble(r);
            double newY = Double.parseDouble(y);
            boolean checkR = newR - 0.00001 >= 2 && newR + 0.00001 <= 5;
            boolean checkY = newY + 0.00001 >= -3 && newY + 0.0001 <= 3;
            if (!(checkR && checkX && checkY))
                throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void requestFromJs() {
        // System.out.println("work");
        long startTime = System.nanoTime();
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> map = context.getExternalContext().getRequestParameterMap();
        point.setX((int)Double.parseDouble(map.get("x")));
        point.setY(Double.parseDouble(map.get("y")));
        point.setR(Double.parseDouble(map.get("r")));
        point.setDate(new Date());
        String answer = check(point.getR(), Double.parseDouble(map.get("x")), point.getY()) ? "YES" : "NO";
        point.setAnswer(answer);
        long estimatedTime = System.nanoTime() - startTime;
        point.setWorkTime(estimatedTime);
        points.add(point);
        try {
            jpaUserDao.create(initData());
            // jpaUserDao.clear();
            // jpaUserDao.delete(jpaUserDao.readAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        point = new Point();
        System.out.println("fromJs");
        // System.out.println(x + " " + y + " " + r);
        // System.out.println("3343");
    }

    public UserPoint initData() {
        UserPoint userPoint = new UserPoint();
        userPoint.setX(point.getX());
        userPoint.setY(point.getY());
        userPoint.setR(point.getR());
        userPoint.setDate(point.getDate());
        userPoint.setAnswer(point.getAnswer());
        userPoint.setWorkTime(point.getWorkTime());
        return userPoint;
    }
}
