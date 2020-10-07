/**
 * OrganizationsController - 
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
import superherosightings.dto.Location;
import superherosightings.dto.Organizations;
import superherosightings.dto.SuperPerson;
import superherosightings.service.LocationServiceLayer;
import superherosightings.service.OrganizationsServiceLayer;
import superherosightings.service.SuperPersonServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrganizationsController
{
    private final LocationServiceLayer locationService;
    private final OrganizationsServiceLayer orgService;
    private final SuperPersonServiceLayer superPersonService;
    
    Set<ConstraintViolation<Organizations>> violations = new HashSet<>();
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();

    public OrganizationsController(LocationServiceLayer locationService, OrganizationsServiceLayer orgService, SuperPersonServiceLayer superPersonService)
    {
        this.locationService = locationService;
        this.orgService = orgService;
        this.superPersonService = superPersonService;
    }
    
    @GetMapping("organizations")
    public String displayOrganizations(Model model)
    {
        List<Organizations> organizationList = orgService.getAllOrganizations();
        List<SuperPerson> superPersonList = superPersonService.getAllSuperPersons();
        List<Location> locationList = locationService.getAllLocations();
        
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("superPersonList", superPersonList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("errors", violations);
        
        return "organizations";
    }
    
    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request, @Valid Organizations organization)
    {
        int locationID = Integer.parseInt(request.getParameter("locations"));
        Location location = locationService.getLocationByID(locationID);
        
        String[] superPersonIDs = request.getParameterValues("orgMembers");
        List<SuperPerson> superPersonList = new ArrayList<SuperPerson>();
        
        if(superPersonIDs != null)
        {
            for(String superPersonID : superPersonIDs)
            {
                superPersonList.add(superPersonService.getSuperPersonByID(Integer.parseInt(superPersonID)));
            }
        }
        
        Organizations newOrganization = new Organizations();
        newOrganization.setOrganizationName(request.getParameter("organizationName"));
        newOrganization.setOrganizationDescription(request.getParameter("organizationDescription"));
        newOrganization.setContactInfo(request.getParameter("contactInfo"));
        newOrganization.setSuperPerson(superPersonList);
        newOrganization.setLocation(location);
        
        violations = validate.validate(newOrganization);
        
        if(violations.isEmpty())
        {
            orgService.addOrganization(newOrganization);
        }
        
        return "redirect:/organizations";
    }
    
    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request)
    {
        int organizationID = Integer.parseInt(request.getParameter("organizationID"));
        orgService.deleteOrganizationByID(organizationID);
        
        return "redirect:/organizations";
    }
    
    @PostMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, @Valid Organizations organization, BindingResult result)
    {   
        if(result.hasErrors())
        {
            return "editOrganization";
        }
        
        int locationID = 0;
        int organizationID = Integer.parseInt(request.getParameter("organizationID"));
        
        Location location = null;
        
        try
        {
            locationID = Integer.parseInt(request.getParameter("locations"));
            location = locationService.getLocationByID(locationID);
        } catch(NumberFormatException e)
        {
            Organizations getLocationForOrganization = orgService.getOrganizationByID(organizationID);
            location = getLocationForOrganization.getLocation();
        }

        String[] superPersonIDs = request.getParameterValues("orgMembers");
        List<SuperPerson> superPersonList = new ArrayList<SuperPerson>();
        
        if(superPersonIDs != null)
        {
            for(String superPersonID : superPersonIDs)
            {
                superPersonList.add(superPersonService.getSuperPersonByID(Integer.parseInt(superPersonID)));
            }
        }
        
        organization.setOrganizationID(organizationID);
        organization.setOrganizationName(request.getParameter("organizationName"));
        organization.setOrganizationDescription(request.getParameter("organizationDescription"));
        organization.setContactInfo(request.getParameter("contactInfo"));
        organization.setLocation(location);
        organization.setSuperPerson(superPersonList);
        
        violations = validate.validate(organization);
        
        if(violations.isEmpty())
        {
            orgService.updateOrganization(organization);
        }
        
        return "redirect:/organizations";
    }
}