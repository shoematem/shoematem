/**
 * IndexController - 
 * -----------------------------------------------------------------------------
 * @author Matthew Shoemate
 *         @date 09/01/2020
 */

package superherosightings.controller;

import java.util.List;
import superherosightings.dto.Sightings;
import superherosightings.service.SightingsServiceLayer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    private final SightingsServiceLayer sightingService;
    
    
    public IndexController(SightingsServiceLayer sightingService)
    {
        this.sightingService = sightingService;
    }
    
    @RequestMapping(value="/")
    public String loadIndexPage(Model model)
    {
        List<Sightings> sightingList = sightingService.getRecentSightings();
        
        model.addAttribute("sightingList", sightingList);
        
        return "index";
    }
}