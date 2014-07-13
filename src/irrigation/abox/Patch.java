package irrigation.abox;

import irrigation.generated.Irrigation;

import java.util.*;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Statement;

public class Patch extends AbstractOntClass
{
    
    public static final String TYPE_KEY = "PATCH_TYPE"; 
    public static final String LOCATION_KEY = "LOCATION"; 
    public static final String SOIL_KEY = "SOIL"; 
    
    
    protected String type = "";
    protected Location location = null;
    protected Soil soil = null;
    
    public Patch(Model model)
    {
        this.model = model;
        this.location = new Location(model);
        this.individual = Irrigation.Patch.createIndividual(Irrigation.Patch.getURI() + System.currentTimeMillis());
    }
    
    public void setType(String type)
    {
        removeExistingStatement(TYPE_KEY);
        
        this.type = type;
        statements.put(TYPE_KEY, model.createStatement(individual, Irrigation.name, type));
    }
    
    public void setLocation(Location location)
    {
        removeExistingStatement(LOCATION_KEY);
        
        this.location = location;
        statements.put(LOCATION_KEY, model.createStatement(individual, Irrigation.hasLocation, location.getIndividual()));
    }
    
    public void setSoil(Soil soil)
    {
        removeExistingStatement(SOIL_KEY);
        this.soil = soil;
        statements.put(SOIL_KEY, model.createStatement(individual, Irrigation.hasSoil, soil.getIndividual()));
    }
    
    @Override
    public List<Statement> getStatements()
    {
        ArrayList <Statement> statementsList = new ArrayList<Statement>(statements.values());
        statementsList.addAll(location.getStatements());
        statementsList.addAll(soil.getStatements());
        
        return statementsList;
    }

}