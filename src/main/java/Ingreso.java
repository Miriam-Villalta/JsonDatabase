public class Ingreso {


    int id;
    int anho;
    int clasificacion;
    String empresa;
    double ingresos;

    public Ingreso(int id, int anho, int clasificacion, String empresa, double ingresos){
        this.id = id;
        this.anho = anho;
        this.clasificacion = clasificacion;
        this.empresa = empresa;
        this.ingresos = ingresos;
    }

    public static Ingreso from(String[] linea) {
        return new Ingreso(Integer.parseInt(linea[0]),
                Integer.parseInt(linea[1]),
                Integer.parseInt(linea[2]),
                linea[3],
                Double.parseDouble(linea[4]));
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " año: " + this.anho + ", clasificación: " + this.clasificacion
                + ", empresa: " + this.empresa + ", ingresos: " + this.ingresos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnho() {
        return this.anho;
    }

    public int getClasificacion() {
        return this.clasificacion;
    }

    public String getEmpresa() {
        return this.empresa;
    }

    public double getIngresos() {
        return this.ingresos;
    }
}
