//@author Matthew Shoemate

package ClassModeling;

public class Airplane
{
    private String controlSystem;
    private String flightSim;
    
    public Airplane(){}
    
    public Airplane(String inControlSystem, String inFlightSim)
    {
        this.controlSystem = inControlSystem;
        this.flightSim = inFlightSim;
    }
    
    public String getControlSystem()
    {
        return controlSystem;
    }
    
    public void setControlSystem(String controlSystem)
    {
        this.controlSystem = controlSystem;
    }
    
    public String getFlightSim()
    {
        return flightSim;
    }
    
    public void setFlightSim(String flightSim)
    {
        this.flightSim = flightSim;
    }
}