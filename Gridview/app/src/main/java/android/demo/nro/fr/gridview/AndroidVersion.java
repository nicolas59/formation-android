package android.demo.nro.fr.gridview;

/**
 * Created by Nicolas on 03/05/2016.
 */
public class AndroidVersion {

    private String label;

    private String imageRef;

    public AndroidVersion(String label, String imageRef) {
        this.label = label;
        this.imageRef = imageRef;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageRef() {
        return imageRef;
    }

    public void setImageRef(String imageRef) {
        this.imageRef = imageRef;
    }
}
