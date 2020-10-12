package common;

public enum Tags {
    OPEN("{OPEN"),
    PREOPEN("{PREOPEN"),
    POSTOPEN("{POSTOPEN"),
    CLOSE("{CLOSE"),
    PRECLOSE("{PRECLOSE"),
    POSTCLOSE("{POSTCLOSE");

    public final String label;

    private Tags(String label){
        this.label = label;
    }
}
