package android.demo.nro.fr.appreceiver;

/**
 * Created by Nicolas on 19/09/2016.
 */
public class Application extends android.app.Application {

    public static Application INSTANCE;

    private String data;

    public Application() {
        INSTANCE = this;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
