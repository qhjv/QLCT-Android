package Adapter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ListChiTieu {
    private String title;
    private String money;
    private Date date;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public ListChiTieu(String title, String money, Date date) {
        super();
        this.title = title;
        this.money = money;
        this.date = date;
    }
    public ListChiTieu() {
        super();
    }
    public String getDateFormat(Date d)
    {
        SimpleDateFormat dft=new
                SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dft.format(d);
    }
    @Override
    public String toString() {
        return this.title+"-"+ this.money +"-"+
                getDateFormat(this.date);
    }
}
