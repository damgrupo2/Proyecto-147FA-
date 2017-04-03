
import java.util.Date;


public class Trabajador {
    
    private int id_trabajador;
    private String nombre;
    private String ap1;
    private String ap2;
    private String direccion;
    private String telf_empresa;
    private String telf_personal;
    private Categoria categoria;
    private double salario;
    private Date fechanac;

    public Trabajador() {
    }

    public Trabajador(int id_trabajador, String nombre, String ap1, String ap2, String direccion, String telf_empresa, String telf_personal, Categoria categoria, double salario, Date fechanac) {
        this.id_trabajador = id_trabajador;
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.direccion = direccion;
        this.telf_empresa = telf_empresa;
        this.telf_personal = telf_personal;
        this.categoria = categoria;
        this.salario = salario;
        this.fechanac = fechanac;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelf_empresa() {
        return telf_empresa;
    }

    public void setTelf_empresa(String telf_empresa) {
        this.telf_empresa = telf_empresa;
    }

    public String getTelf_personal() {
        return telf_personal;
    }

    public void setTelf_personal(String telf_personal) {
        this.telf_personal = telf_personal;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechanac() {
        return fechanac;
    }

    public void setFechanac(Date fechanac) {
        this.fechanac = fechanac;
    }
                        
    
    
}
