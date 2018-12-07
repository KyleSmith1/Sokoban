package sokoban;

/**
 * @author 14001835
 */
public class Diamond extends MapElement {

    private Boolean hasCrate;
    
    public Diamond(){
        
    }
    
    public void createElement(){
        
    }
    
    public String displayImage(){
        return "Diamond.png";
    }
    
    public void setHasCrate(boolean crateStateChange){
        hasCrate = crateStateChange;
    }
    
    public boolean getHasCrate(){
        return hasCrate;
    }
}
