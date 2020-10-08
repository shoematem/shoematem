/**
 * SuperPowerController - 
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
import superherosightings.dto.SuperPerson;
import superherosightings.dto.SuperPower;
import superherosightings.service.SuperPersonServiceLayer;
import superherosightings.service.SuperPowerServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class SuperPowerController
{
    private final SuperPersonServiceLayer superPersonService;
    private final SuperPowerServiceLayer superPowerService; 
    
    Set<ConstraintViolation<SuperPower>> violations = new HashSet<>();
    Validator validate = Validation.buildDefaultValidatorFactory().getValidator();

    public SuperPowerController(SuperPersonServiceLayer superPersonService, SuperPowerServiceLayer superPowerService)
    {
        this.superPersonService = superPersonService;
        this.superPowerService = superPowerService;
    }
    
    @GetMapping("superPower")
    public String displaySuperPowers(Model model)
    {
        List<SuperPower> superPowerList = superPowerService.getAllSuperPowers();
        List<SuperPerson> superPersonList = new ArrayList<SuperPerson>();
        
        for(SuperPower superPower : superPowerList)
        {
            superPersonList = superPersonService.getAllSuperPersonsBySuperPower(superPower.getSuperPowerID());
            superPower.setSuperPerson(superPersonList);
        }
        
        model.addAttribute("superPowerList", superPowerList);
        
        return "superPower";
    }
    
    @PostMapping("addSuperPower")
    public String addSighting(HttpServletRequest request)
    {
        SuperPower superPower = new SuperPower();
        //superPower.setSuperPowerID(Integer.parseInt(request.getParameter("superPowerID")));
        superPower.setSuperPowerName(request.getParameter("superPowerName"));
        
        
        violations = validate.validate(superPower);

        if(violations.isEmpty())
        {
            superPowerService.addSuperPower(superPower);
        }
        
        return "redirect:/superPower";
    }
    
    @GetMapping("deleteSuperPower")
    public String deleteSuperPower(HttpServletRequest request)
    {
        int superPowerID = Integer.parseInt(request.getParameter("superPowerID"));
        superPowerService.deleteSuperPowerByID(superPowerID);
        
        return "redirect:/superPower";
    }
    
    @PostMapping("editSuperPower")
    public String editSuperPower(HttpServletRequest request, @Valid SuperPower editSuperPower, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "editOrganization";
        }

        editSuperPower.setSuperPowerID(Integer.parseInt(request.getParameter("superPowerID")));
        editSuperPower.setSuperPowerName(request.getParameter("superPowerName"));
        
        violations = validate.validate(editSuperPower);

        if(violations.isEmpty())
        {
            superPowerService.updateSuperPower(editSuperPower);
        }
        
        return "redirect:/superPower";
    }
}