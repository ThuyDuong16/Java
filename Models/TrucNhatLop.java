/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author pc
 */
public class TrucNhatLop implements Serializable {
    private TrucNhat tn;
    private String Tenlop;
    private String Malop;

    public String getMalop() {
        return Malop;
    }

    public void setMalop(String Malop) {
        this.Malop = Malop;
    }

    public TrucNhatLop(TrucNhat tn, String Tenlop, String Malop) {
        this.tn = tn;
        this.Tenlop = Tenlop;
        this.Malop = Malop;
    }

    public TrucNhat getTn() {
        return tn;
    }

    public void setTn(TrucNhat tn) {
        this.tn = tn;
    }

    public TrucNhatLop(TrucNhat tn, String Tenlop) {
        this.tn = tn;
        this.Tenlop = Tenlop;
    }

    public String getTenlop() {
        return Tenlop;
    }

    public void setTenlop(String Tenlop) {
        this.Tenlop = Tenlop;
    }
        @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.Malop);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TrucNhatLop other = (TrucNhatLop) obj;
        return Objects.equals(this.Tenlop, other.getTenlop());
    }
    
    
}
