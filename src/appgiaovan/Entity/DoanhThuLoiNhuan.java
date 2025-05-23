/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package appgiaovan.Entity;



import java.util.Date;

public class DoanhThuLoiNhuan {
    private Date ngay;
    private Double doanhThu;
    private Double loiNhuan;

    // Constructor mặc định
    public DoanhThuLoiNhuan() {}

    // Constructor có tham số
    public DoanhThuLoiNhuan(Date ngay, Double doanhThu, Double loiNhuan) {
        this.ngay = ngay;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    // Getters and Setters
    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(Double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }
}

