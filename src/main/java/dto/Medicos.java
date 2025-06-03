/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Hakur
 */
@Entity
@Table(name = "medicos")
@NamedQueries({
    @NamedQuery(name = "Medicos.findAll", query = "SELECT m FROM Medicos m"),
    @NamedQuery(name = "Medicos.findByCodiMedi", query = "SELECT m FROM Medicos m WHERE m.codiMedi = :codiMedi"),
    @NamedQuery(name = "Medicos.findByNdniMedi", query = "SELECT m FROM Medicos m WHERE m.ndniMedi = :ndniMedi"),
    @NamedQuery(name = "Medicos.findByAppaMedi", query = "SELECT m FROM Medicos m WHERE m.appaMedi = :appaMedi"),
    @NamedQuery(name = "Medicos.findByApmaMedi", query = "SELECT m FROM Medicos m WHERE m.apmaMedi = :apmaMedi"),
    @NamedQuery(name = "Medicos.findByNombMedi", query = "SELECT m FROM Medicos m WHERE m.nombMedi = :nombMedi"),
    @NamedQuery(name = "Medicos.findByFechNaciMedi", query = "SELECT m FROM Medicos m WHERE m.fechNaciMedi = :fechNaciMedi"),
    @NamedQuery(name = "Medicos.findByLogiMedi", query = "SELECT m FROM Medicos m WHERE m.logiMedi = :logiMedi"),
    @NamedQuery(name = "Medicos.findByPassMedi", query = "SELECT m FROM Medicos m WHERE m.passMedi = :passMedi")})
public class Medicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiMedi")
    private Integer codiMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ndniMedi")
    private String ndniMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "appaMedi")
    private String appaMedi;
    @Size(max = 50)
    @Column(name = "apmaMedi")
    private String apmaMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombMedi")
    private String nombMedi;
    @Column(name = "fechNaciMedi")
    @Temporal(TemporalType.DATE)
    private Date fechNaciMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "logiMedi")
    private String logiMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "passMedi")
    private String passMedi;
    @Column(name = "secret2fa")
    private String secret2fa;

    public Medicos() {
    }

    public Medicos(Integer codiMedi) {
        this.codiMedi = codiMedi;
    }

    public Medicos(Integer codiMedi, String ndniMedi, String appaMedi, String nombMedi, String logiMedi, String passMedi) {
        this.codiMedi = codiMedi;
        this.ndniMedi = ndniMedi;
        this.appaMedi = appaMedi;
        this.nombMedi = nombMedi;
        this.logiMedi = logiMedi;
        this.passMedi = passMedi;
    }

    public Integer getCodiMedi() {
        return codiMedi;
    }

    public void setCodiMedi(Integer codiMedi) {
        this.codiMedi = codiMedi;
    }

    public String getNdniMedi() {
        return ndniMedi;
    }

    public void setNdniMedi(String ndniMedi) {
        this.ndniMedi = ndniMedi;
    }

    public String getAppaMedi() {
        return appaMedi;
    }

    public void setAppaMedi(String appaMedi) {
        this.appaMedi = appaMedi;
    }

    public String getApmaMedi() {
        return apmaMedi;
    }

    public void setApmaMedi(String apmaMedi) {
        this.apmaMedi = apmaMedi;
    }

    public String getNombMedi() {
        return nombMedi;
    }

    public void setNombMedi(String nombMedi) {
        this.nombMedi = nombMedi;
    }

    public Date getFechNaciMedi() {
        return fechNaciMedi;
    }

    public void setFechNaciMedi(Date fechNaciMedi) {
        this.fechNaciMedi = fechNaciMedi;
    }

    public String getLogiMedi() {
        return logiMedi;
    }

    public void setLogiMedi(String logiMedi) {
        this.logiMedi = logiMedi;
    }

    public String getPassMedi() {
        return passMedi;
    }

    public void setPassMedi(String passMedi) {
        this.passMedi = passMedi;
    }

    public String getSecret2fa() {
        return secret2fa;
    }

    public void setSecret2fa(String secret2fa) {
        this.secret2fa = secret2fa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMedi != null ? codiMedi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.codiMedi == null && other.codiMedi != null) || (this.codiMedi != null && !this.codiMedi.equals(other.codiMedi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Medicos[ codiMedi=" + codiMedi + " ]";
    }
    
}
