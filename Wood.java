public enum Wood {
    
    INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, CEDAR, ALDER;
    
    public String toString() {
        switch(this) {
            case INDIAN_ROSEWOOD: return "indian rosewood";
            case BRAZILIAN_ROSEWOOD: return "brazilian rosewood";
            case MAHOGANY: return "mahogany";
            case MAPLE: return "maple";
            case CEDAR: return "cedar";
            case ALDER: return "alder";
            default: return "default"; 
        }
    }

}
