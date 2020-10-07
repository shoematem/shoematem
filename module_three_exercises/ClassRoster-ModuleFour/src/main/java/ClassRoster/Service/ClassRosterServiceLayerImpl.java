/**
 * @author Matthew Shoemate
 */

package ClassRoster.Service;

import ClassRoster.DAO.ClassRosterAuditDao;
import ClassRoster.DAO.ClassRosterDao;
import ClassRoster.DAO.ClassRosterPersistenceException;
import ClassRoster.DTO.Student;
import java.util.List;

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer
{
    private final ClassRosterDao dao;
    private final ClassRosterAuditDao auditDao;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao)
    {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    private void validateStudentData(Student student) throws ClassRosterDataValidationException
    {
        String firstName = student.getFirstName();
        String lastName = student.getLastName();
        String cohort = student.getCohort();
        
        if(firstName == null
                || firstName.trim().length() == 0
                || lastName == null
                || lastName.trim().length() == 0
                || cohort == null
                || cohort.trim().length() == 0)
        {
            throw new ClassRosterDataValidationException("ERROR: All fields [First Name, Last Name, Cohort] are required.");
        }
    }
    
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIDException, ClassRosterDataValidationException, ClassRosterPersistenceException
    {
        //First check to see if there is already a student
        //associated with the given student's id
        //If so, we're all done here -
        //throw a ClassRosterDuplicateIDException
        if(dao.getStudent(student.getStudentID()) != null)
        {
            throw new ClassRosterDuplicateIDException("ERROR: Could not create student. Student ID " + student.getStudentID() + " already exists.");
        }
        
        //Now validate all the fields on the given Student object.
        //This method will throw an
        //exception if any of the validation rules are violated.
        validateStudentData(student);
        
        //We passed all our business rules checks so go ahead
        //and persist the Student object
        dao.addStudent(student.getStudentID(), student);
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException
    {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentID) throws ClassRosterPersistenceException
    {
        return dao.getStudent(studentID);
    }

    @Override
    public Student removeStudent(String studentID) throws ClassRosterPersistenceException
    {
        return dao.removeStudent(studentID);
    }
    
}