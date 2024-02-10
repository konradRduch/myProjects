/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.dbmanager;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import pl.polsl.exceptions.IdNotExistsException;

/**
 * The DbManager class represents a singleton for managing database operations
 * using Java Persistence API (JPA).
 *
 *
 * @author Konrad Rduch
 * @version f5
 * @since f5
 */
public class DbManager {

    private static final DbManager instance = new DbManager();
    private EntityManagerFactory emf;

    /**
     * Private constructor to enforce Singleton pattern and initialize the
     * EntityManagerFactory. The EntityManagerFactory is created using the
     * persistence unit "com.mycompany_Organizer_WEB_war_1.0-SNAPSHOTPU".
     */
    private DbManager() {

        emf = Persistence.createEntityManagerFactory("com.mycompany_Organizer_WEB_war_1.0-SNAPSHOTPU");
    }

    /**
     * Returns the single instance of the DbManager class, following the
     * Singleton pattern.
     *
     * @return The DbManager instance.
     */
    public static DbManager getInstance() {
        return instance;
    }

    /**
     * Provides methods for persisting objects into the database.
     *
     * @param object The object to be persisted in the database.
     */
    public void persistObject(Object object) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves a list of objects from the database based on the provided JPQL
     * query string.
     *
     * @param queryStr The JPQL query string for retrieving objects.
     * @return A list of objects matching the query.
     */
    public List<Object> findObjects(String queryStr) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> resultList = null;
        try {
            Query query = em.createQuery(queryStr);
            resultList = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return resultList;
    }

    /**
     * Retrieves a list of objects from the database based on the provided JPQL
     * query string and parameters.
     *
     * @param queryStr The JPQL query string for retrieving objects.
     * @param parameters The parameters to be set in the query.
     * @return A list of objects matching the query with specified parameters.
     */
    public List<Object> findObjects(String queryStr, Map<String, Object> parameters) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Object> resultList = null;

        try {
            Query query = em.createQuery(queryStr);

            if (parameters != null) {
                for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                    query.setParameter(entry.getKey(), entry.getValue());
                }
            }
            resultList = query.getResultList();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return resultList;
    }

    /**
     * Edits an existing object in the database.
     *
     * @param object The object to be edited in the database.
     * @throws IdNotExistsException If the object with the given ID does not
     * exist.
     */
    public void editObject(Object object) throws IdNotExistsException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            if (!em.contains(object)) {

                Object existingObject = em.find(object.getClass(), getId(object));
                if (existingObject == null) {
                    throw new IdNotExistsException("Object with given ID does not exist");
                }
            }

            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new IdNotExistsException("Object with given ID does not exist");
        } finally {
            em.close();
        }
    }

    /**
     * Removes an object from the database.
     *
     * @param object The object to be removed from the database.
     * @throws IdNotExistsException If the object with the given ID does not
     * exist.
     */
    public void removeObject(Object object) throws IdNotExistsException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Object objectId = getId(object);

            if (objectId != null) {
                Object managedObject = em.find(object.getClass(), objectId);

                if (managedObject != null) {
                    em.remove(managedObject);
                } else {
                    throw new IdNotExistsException("Object with given ID does not exist");
                }
            } else {
                throw new IdNotExistsException("Object ID is null");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new IdNotExistsException("Error removing object");
        } finally {
            em.close();
        }
    }

    /**
     * Toggles the "done" value of a boolean field in the provided object.
     * Assumes the object has a boolean field named "done".
     *
     * @param object The object for which to toggle the "done" value.
     * @throws IdNotExistsException If the object with the given ID does not
     * exist.
     */
    public void toggleObject(Object object) throws IdNotExistsException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Object objectId = getId(object);

            if (objectId != null) {
                Object managedObject = em.find(object.getClass(), objectId);

                if (managedObject != null) {
                    toggleDoneValue(managedObject);
                } else {
                    throw new IdNotExistsException("Object with given ID does not exist");
                }
            } else {
                throw new IdNotExistsException("Object ID is null");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new IdNotExistsException("Object with given ID does not exist");
        } finally {
            em.close();
        }
    }

    /**
     * Closes the EntityManagerFactory if it is not null and is currently open,
     * releasing associated resources.
     */
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Toggles the boolean value of the "done" field in the provided object
     * using reflection. Assumes the object has a boolean field named "done".
     *
     * @param object The object for which to toggle the "done" value.
     */
    private void toggleDoneValue(Object object) {
        try {
            // Użyj refleksji, aby uzyskać dostęp do pola done
            Field doneField = object.getClass().getDeclaredField("done");
            if (doneField != null) {
                doneField.setAccessible(true);
                boolean currentValue = (boolean) doneField.get(object);
                doneField.set(object, !currentValue); // Zmień wartość done na przeciwną
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the ID of the provided object using reflection. Assumes the
     * object has either a method named "getId" or a field named "id".
     *
     * @param object The object for which to retrieve the ID.
     * @return The ID of the object, or null if not found.
     */
    private Object getId(Object object) {
        try {
            Method getIdMethod = object.getClass().getMethod("getId");
            if (getIdMethod != null) {
                return getIdMethod.invoke(object);
            }
            Field idField = object.getClass().getDeclaredField("id");
            if (idField != null) {
                idField.setAccessible(true);
                return idField.get(object);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

}
