package chapter5;

public enum Style {
    A, F;
    
    public String toString() {
        switch(this) {
            case A: return "A";
            case F: return "F";
            default: return "Unspecified"; 
        }
    }
}
