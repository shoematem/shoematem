//@author Matthew Shoemate

package ClassRoster.Controller;

import ClassRoster.DAO.ClassRosterPersistenceException;
import ClassRoster.DTO.Student;
import ClassRoster.Service.ClassRosterDataValidationException;
import ClassRoster.Service.ClassRosterDuplicateIDException;
import ClassRoster.Service.ClassRosterServiceLayer;
import ClassRoster.UI.ClassRosterView;
import java.util.List;

public class ClassRosterController
{
    private ClassRosterView view;
    private ClassRosterServiceLayer service;
    
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view)
    {
        this.service = service;
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
        } catch(ClassRosterPersistenceException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
    }
    
    private int getMenuSelection()
    {
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent() throws ClassRosterPersistenceException
    {
        boolean hasErrors = false;
        
        view.displayCreateStudentBanner();
        
        do
        {
            Student currentStudent = view.getNewStudentInfo();
            
            try
            {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                
                hasErrors = false;
            } catch(ClassRosterDuplicateIDException | ClassRosterDataValidationException e)
            {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while(hasErrors);
    }
    
    private void listStudents() throws ClassRosterPersistenceException
    {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void removeStudent() throws ClassRosterPersistenceException
    {
        view.displayRemoveStudentBanner();
        String studentID = view.getStudentIDChoice();
        service.removeStudent(studentID);
        view.displayRemoveSuccessBanner();
    }
    
    private void viewStudent() throws ClassRosterPersistenceException
    {
        view.displayDisplayStudentBanner();
        String studentID = view.getStudentIDChoice();
        Student student = service.getStudent(studentID);
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