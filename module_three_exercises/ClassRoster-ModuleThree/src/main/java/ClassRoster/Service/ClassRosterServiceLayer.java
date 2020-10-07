/**
 * @author Matthew Shoemate
 */

package ClassRoster.Service;

import ClassRoster.DAO.ClassRosterPersistenceException;
import ClassRoster.DTO.Student;
import java.util.List;

public interface ClassRosterServiceLayer
{
    void createStudent(Student student) throws ClassRosterDuplicateIDException, ClassRosterDataValidationException, ClassRosterPersistenceException;
    List<Student> getAllStudents() throws ClassRosterPersistenceException;
    Student getStudent(String studentID) throws ClassRosterPersistenceException;
    Student removeStudent(String studentID) throws ClassRosterPersistenceException;
}