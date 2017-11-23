package kz.zhakhanyergali.qrscanner.Entity;

/**
 * Created by zhakhanyergali on 21.11.17.
 */

public class History {

    private int id;
    private String date;
    private String context;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
