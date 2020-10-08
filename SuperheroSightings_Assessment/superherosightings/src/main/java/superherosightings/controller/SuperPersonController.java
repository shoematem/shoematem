/**
 * SuperPersonController - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import superherosightings.dto.Organizations;
import superherosightings.dto.SuperPerson;
import superherosightings.dto.SuperPower;
import superherosightings.service.OrganizationsServiceLayer;
import superherosightings.service.SuperPersonServiceLayer;
import superherosightings.service.SuperPowerServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuperPersonController
{
    Set<ConstraintViolation<SuperPerson>> violations = new HashSet<>();
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    
    private final SuperPersonServiceLayer superPersonService;
    private final SuperPowerServiceLayer superPowerService;
    private final OrganizationsServiceLayer orgService;

    public SuperPersonController(SuperPersonServiceLayer superPersonService, SuperPowerServiceLayer superPowerService, OrganizationsServiceLayer orgService)
    {
        this.superPersonService = superPersonService;
        this.superPowerService = superPowerService;
        this.orgService = orgService;
    }
    
    @GetMapping("superPerson")
    public String displaySuperPerson(Model model)
    {
        List<SuperPerson> superPersonList = superPersonService.getAllSuperPersons();
        List<SuperPower> superPowerList = superPowerService.getAllSuperPowers();
        List<Organizations> organizationList = orgService.getAllOrganizations();
        
        model.addAttribute("superPersonList", superPersonList);
        model.addAttribute("superPowerList", superPowerList);
        model.addAttribute("organizationList", organizationList);
        
        return "superPerson";
    }
    
    @PostMapping("addSuperPerson")
    //public String addSuperPerson(String superPersonName, String superPersonDescription, int superPowerID, int superTypeID)
    public String addSuperPerson(HttpServletRequest request, @Valid SuperPerson superPerson, BindingResult result)
    {
        int superPowerID = Integer.parseInt(request.getParameter("superPowers"));
        SuperPower superPower = superPowerService.getSuperPowerByID(superPowerID);
        
        String[] organizationIDs = request.getParameterValues("organizations");
        List<Organizations> organizationList = new ArrayList<>();
           
        if(organizationIDs != null)
        {
            for(String orgID : organizationIDs)
            {
                organizationList.add(orgService.getOrganizationByID(Integer.parseInt(orgID)));
            }
        }
        
        superPerson.setSuperPersonName(request.getParameter("superPersonName"));
        superPerson.setSuperPersonDescription(request.getParameter("superPersonDescription"));
        superPerson.setIsSuperHero(Boolean.parseBoolean(request.getParameter("isSuperHero")));
        superPerson.setSuperPower(superPower);
        superPerson.setOrganizations(organizationList);
        
        violations = validate.validate(superPerson);

        if(violations.isEmpty())
        {
            superPersonService.addSuperPerson(superPerson);
        }
        
        return "redirect:/superPerson";
    }
    
    @GetMapping("deleteSuperPerson")
    public String deleteSuperPerson(HttpServletRequest request)
    {
        int superPersonID = Integer.parseInt(request.getParameter("superPersonID"));
        superPersonService.deleteSuperPersonByID(superPersonID);
        
        return "redirect:/superPerson";
    }
    
    @PostMapping("editSuperPerson")
    public String editSuperPerson(HttpServletRequest request, @Valid SuperPerson superPerson, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "editSuperPerson";
        }
       
        int superPowerID = 0;
        int superPersonID = Integer.parseInt(request.getParameter("superPersonID"));
        SuperPower superPower = null;
        
        String[] organizationIDs = request.getParameterValues("organizations");
        //String[] superPowerIDs = request.getParameterValues("superPowers");
        //List<SuperPower> superPowers = new ArrayList<>();
        List<Organizations> organizationList = new ArrayList<>();
        
        try
        {
            superPowerID = Integer.parseInt(request.getParameter("superPowers"));
            superPower = superPowerService.getSuperPowerByID(superPowerID);
        } catch(NumberFormatException e)
        {
            SuperPerson getSuperPowerForSuperPerson = superPersonService.getSuperPersonByID(superPersonID);
            superPower = getSuperPowerForSuperPerson.getSuperPower();
        }
        
        if(organizationIDs != null)
        {
            for(String orgID : organizationIDs)
            {
                organizationList.add(orgService.getOrganizationByID(Integer.parseInt(orgID)));
            }
        }
        
        superPerson.setSuperPersonID(Integer.parseInt(request.getParameter("superPersonID")));
        superPerson.setSuperPersonName(request.getParameter("superPersonName"));
        superPerson.setSuperPersonDescription(request.getParameter("superPersonDescription"));
        superPerson.setIsSuperHero(Boolean.parseBoolean(request.getParameter("isSuperHero")));
        superPerson.setOrganizations(organizationList);
        superPerson.setSuperPower(superPower);
        
        violations = validate.validate(superPerson);

        if(violations.isEmpty())
        {
            superPersonService.updateSuperPerson(superPerson);
        }

        return "redirect:/superPerson";
    }
}