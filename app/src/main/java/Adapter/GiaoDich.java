package Adapter;

import java.io.Serializable;
import java.sql.Date;

public class GiaoDich implements Serializable {
    private int idgiaodich;
    private int idnguoidung;
    private String tengd;
    private Date ngaytao;
    private float sotien;

    public GiaoDich() {
    }

    public GiaoDich(String tengd, Date ngaytao, float sotien) {
        this.idgiaodich = idgiaodich;
        this.idnguoidung = idnguoidung;
        this.tengd = tengd;
        this.ngaytao = ngaytao;
        this.sotien = sotien;
    }

    public int getIdgiaodich() {
        return idgiaodich;
    }

    public void setIdgiaodich(int idgiaodich) {
        this.idgiaodich = idgiaodich;
    }

    public int getIdnguoidung() {
        return idnguoidung;
    }

    public void setIdnguoidung(int idnguoidung) {
        this.idnguoidung = idnguoidung;
    }

    public String getTengd() {
        return tengd;
    }

    public void setTengd(String tengd) {
        this.tengd = tengd;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public float getSotien() {
        return sotien;
    }

    public void setSotien(float sotien) {
        this.sotien = sotien;
    }
}

