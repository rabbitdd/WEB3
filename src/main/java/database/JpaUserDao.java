package database;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaUserDao implements DAO<UserPoint> {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Entity");
    private static EntityManager manager = emf.createEntityManager();
    public boolean create(UserPoint model) throws SQLException {
        System.out.println("create..");
        //model.setId(generatedId(2));
        System.out.println(model.getId());
        manager.getTransaction().begin();
        manager.persist(model);
        manager.getTransaction().commit();
        System.out.println("success create");
        return true;
    }


    public boolean read(UserPoint model) throws SQLException {
        //UserPoint userPoint = manager.find(UserPoint.class, 5);
        //manager.detach(userPoint);
        //System.out.println(userPoint.getX() + " " + userPoint.getDate() + " " + userPoint.getAnswer());
        return true;
    }

    public boolean update(UserPoint model) throws SQLException {
        return false;
    }

    public boolean delete(List<UserPoint> userPoints) throws SQLException {
        for (UserPoint userPoint : userPoints) {
            manager.getTransaction().begin();
            manager.remove(userPoint);
            manager.getTransaction().commit();
        }
        return false;
    }

    public void clear() throws SQLException {
        // manager.createQuery("delete from " + UserPoint.class.getName() + " p").executeUpdate();
        // manager.createNativeQuery("REMOVE ").executeUpdate();
    }

    public List<UserPoint> readAll() throws SQLException {
        List<UserPoint> userPoints = manager.createQuery("SELECT p from " + UserPoint.class.getName() + " p").getResultList();
//        for (UserPoint point: userPoints) {
//            System.out.println(point.getId() + " " + point.getDate() + " " + point.getX() + "---------------");
//        }
        return userPoints;
    }
}
