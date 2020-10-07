//@author Matthew Shoemate

package ClassRoster.Controller;

import ClassRoster.DAO.ClassRosterDao;
import ClassRoster.DAO.ClassRosterDaoException;
import ClassRoster.DTO.Student;
import ClassRoster.UI.ClassRosterView;
import java.util.List;

public class ClassRosterController
{
    private ClassRosterView view;
    private ClassRosterDao dao;
    
    public ClassRosterController(ClassRosterDao dao, ClassRosterView view)
    {
        this.dao = dao;
        this.view = view;
    }
    
    public void run()
    {
        boolean keepGoing = true;
        int menuSelection = 0;
        
        try
        {
            while(keepGoing)
            {
                menuSelection = getMenuSelection();
            
                switch(menuSelection)
                {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
        } catch(ClassRosterDaoException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
    }
    
    private int getMenuSelection()
    {
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent() throws ClassRosterDaoException
    {
        view.displayCreateStudentBanner();
        Student newStudent = view.getNewStudentInfo();
        dao.addStudent(newStudent.getStudentID(), newStudent);
        view.displayCreateSuccessBanner();
    }
    
    private void listStudents() throws ClassRosterDaoException
    {
        view.displayDisplayAllBanner();
        List<Student> studentList = dao.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void removeStudent() throws ClassRosterDaoException
    {
        view.displayRemoveStudentBanner();
        String studentID = view.getStudentIDChoice();
        Student removedStudent = dao.removeStudent(studentID);
        view.displayRemoveResult(removedStudent);
    }
    
    private void viewStudent() throws ClassRosterDaoException
    {
        view.displayDisplayStudentBanner();
        String studentID = view.getStudentIDChoice();
        Student student = dao.getStudent(studentID);
        view.displayStudent(student);
    }
    
    private void unknownCommand()
    {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage()
    {
        view.displayExitBanner();
    }
}