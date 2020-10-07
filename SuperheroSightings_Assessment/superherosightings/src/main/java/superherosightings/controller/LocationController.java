/**
 * LocationController - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 08/28/2020
 */

package superherosightings.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import superherosightings.dto.Location;
import superherosightings.service.LocationServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LocationController
{
    private final LocationServiceLayer locationService;
    
    Set<ConstraintViolation<Location>> violations = new HashSet<>();
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();

    public LocationController(LocationServiceLayer locationService)
    {
        this.locationService = locationService;
    }
    
    @GetMapping("location")
    public String displayLocations(Model model)
    {
        List locationList = locationService.getAllLocations();
        model.addAttribute("locationList", locationList);
        
        return "location";
    }
    
    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request)
    {
        Location newLocation = new Location();
        newLocation.setLocationName(request.getParameter("locationName"));
        newLocation.setLocationDescription(request.getParameter("locationDescription"));
        newLocation.setAddress(request.getParameter("address"));
        newLocation.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        newLocation.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        
        violations = validate.validate(newLocation);

        if(violations.isEmpty())
        {
            locationService.addLocation(newLocation);
        }
        
        return "redirect:/location";
    }
    
    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request)
    {
        int locationID = Integer.parseInt(request.getParameter("locationID"));
        locationService.deleteLocationByID(locationID);
        
        return "redirect:/location";
    }
    
    @PostMapping("editLocation")
    public String editLocation(HttpServletRequest request, @Valid Location location, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "editLocation";
        }
        
        Location editLocation = new Location();
        editLocation.setLocationID(Integer.parseInt(request.getParameter("locationID")));
        editLocation.setLocationName(request.getParameter("locationName"));
        editLocation.setLocationDescription(request.getParameter("locationDescription"));
        editLocation.setAddress(request.getParameter("address"));
        editLocation.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        editLocation.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        
        violations = validate.validate(editLocation);

        if(violations.isEmpty())
        {
            locationService.updateLocation(editLocation);
        }
        
        return "redirect:/location";
    }
}