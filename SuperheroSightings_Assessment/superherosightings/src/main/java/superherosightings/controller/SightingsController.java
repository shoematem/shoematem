/**
 * SightingsController - 
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
import superherosightings.dto.Sightings;
import superherosightings.dto.SuperPerson;
import superherosightings.service.LocationServiceLayer;
import superherosightings.service.SightingsServiceLayer;
import superherosightings.service.SuperPersonServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SightingsController
{
    private final LocationServiceLayer locationService;
    private final SightingsServiceLayer sightingsService;
    private final SuperPersonServiceLayer superPersonService;
    
    Set<ConstraintViolation<Sightings>> violations = new HashSet<>();
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
    
    public SightingsController(LocationServiceLayer locationService, SightingsServiceLayer sightingsService, SuperPersonServiceLayer superPersonService)
    {
        this.locationService = locationService;
        this.sightingsService = sightingsService;
        this.superPersonService = superPersonService;
    }
    
    @GetMapping("sightings")
    public String displaySightings(Model model)
    {
        List<Sightings> sightingList = sightingsService.getAllSightings();
        List<Location> locationList = locationService.getAllLocations();
        List<SuperPerson> superPersonList = superPersonService.getAllSuperPersons();
        
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superPersonList", superPersonList);
        
        return "sightings";
    }
    
    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request)
    {
        int locationID = Integer.parseInt(request.getParameter("locations"));
        Location location = locationService.getLocationByID(locationID);
        
        String[] superPersonIDs = request.getParameterValues("superPersons");
        List<SuperPerson> superPersonList = new ArrayList<SuperPerson>();
        
        if(superPersonIDs != null)
        {
            for(String superPersonID : superPersonIDs)
            {
                superPersonList.add(superPersonService.getSuperPersonByID(Integer.parseInt(superPersonID)));
            }
        }
        
        Sightings newSighting = new Sightings();
        newSighting.setSightingDate(request.getParameter("sightingDate"));
        newSighting.setLocation(location);
        newSighting.setSuperPerson(superPersonList);
        
        violations = validate.validate(newSighting);

        if(violations.isEmpty())
        {
            sightingsService.addSighting(newSighting);
        }
        
        return "redirect:/sightings";
    }
    
    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request)
    {
        int sightingID = Integer.parseInt(request.getParameter("sightingID"));
        
        sightingsService.deleteSightingByID(sightingID);
        
        return "redirect:/sightings";
    }
    
    @PostMapping("editSighting")
    public String editSighting(HttpServletRequest request, @Valid Sightings sighting, BindingResult result)
    {
        if((result.hasErrors()) && (request.getParameter("sightingDate") != null))
        {
            return "redirect:/sightings";
        }
        
        String sightingDate = "";
        int locationID = Integer.parseInt(request.getParameter("location"));
        Location location = locationService.getLocationByID(locationID);
        
        String[] superPersonIDs = request.getParameterValues("superPersons");
        List<SuperPerson> superPersonList = new ArrayList<SuperPerson>();
       
        if(superPersonIDs != null)
        {
            for(String superPersonID : superPersonIDs)
            {
                superPersonList.add(superPersonService.getSuperPersonByID(Integer.parseInt(superPersonID)));
            }
        }
        
        if(request.getParameter("sightingDate") == null)
        {
            int sightingID = Integer.parseInt(request.getParameter("sightingID"));
            Sightings holdSighting = sightingsService.getSightingByID(sightingID);
            sightingDate = holdSighting.getSightingDate();
        }
        
        Sightings editSighting = new Sightings();
        editSighting.setSightingID(Integer.parseInt(request.getParameter("sightingID")));
        editSighting.setSightingDate(sightingDate);
        editSighting.setLocation(location);
        editSighting.setSuperPerson(superPersonList);
        
        violations = validate.validate(editSighting);

        if(violations.isEmpty())
        {
            sightingsService.updateSighting(editSighting);
        }
        
        return "redirect:/sightings";
    }
}