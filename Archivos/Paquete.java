public class Paquete <T>{
    public String codigo;
    public double peso;
    public T prioridad;
    public int valor;
    public int zona;

    public Paquete(String codigo, double peso, T prioridad, int valor, int zona) {
        this.codigo = codigo;
        this.peso = peso;
        this.prioridad = prioridad;
        this.valor = valor;
        this.zona = zona;
    }

    public double getPeso() {
        return peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public T getPrioridad() {
        return prioridad;
    }

    public int getValor() {
        return valor;
    }
    public int getZona() {
        return zona;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setPrioridad(T prioridad) {
        this.prioridad = prioridad;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    public void setZona(int zona) {
        this.zona = zona;
    }
    @Override
    public String toString() {
        return "Codigo: " + codigo + "Peso: " + peso + "Prioridad: " + prioridad + "Zona: " + zona;
    }

}
