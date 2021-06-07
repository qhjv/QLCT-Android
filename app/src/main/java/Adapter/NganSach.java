package Adapter;

import java.io.Serializable;
import java.sql.Date;

public class NganSach implements Serializable {
    private int idngansach;
    private int idkhachhang;
    private float tongtien;
    private Date startDate;
    private Date endDate;

    public NganSach() {
    }

    public NganSach(float tongtien, Date startDate, Date endDate) {
        this.idngansach = idngansach;
        this.idkhachhang = idkhachhang;
        this.tongtien = tongtien;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getIdngansach() {
        return idngansach;
    }

    public void setIdngansach(int idngansach) {
        this.idngansach = idngansach;
    }

    public int getIdkhachhang() {
        return idkhachhang;
    }

    public void setIdkhachhang(int idkhachhang) {
        this.idkhachhang = idkhachhang;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}


