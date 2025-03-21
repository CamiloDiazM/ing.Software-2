package Model.co.edu.poli.ejemplo1.Model;

public class Proveedor {

    private String evaluacion ;
    private String certificacion ;
    private String politicaDeEntrega;

    private Proveedor(ProveedorBuilder builder){
        this.evaluacion = builder.evaluacion;
        this.certificacion = builder.certificacion;
        this.politicaDeEntrega = builder.politicaDeEntrega;
    }

    public String getEvaluacion() {
        return evaluacion;
    }

    public String getCertificacion() {
        return certificacion;
    }

    public String getPoliticaDeEntrega() {
        return politicaDeEntrega;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "evaluacion='" + evaluacion + '\'' +
                ", certificacion='" + certificacion + '\'' +
                ", politicaDeEntrega='" + politicaDeEntrega + '\'' +
                '}';
    }

    public static class ProveedorBuilder {
        private String evaluacion ;
        private String certificacion ;
        private String politicaDeEntrega;

        public ProveedorBuilder setEvaluacion(String evaluacion) {
            this.evaluacion = evaluacion;
            return this;
        }

        public ProveedorBuilder setCertificacion(String certificacion) {
            this.certificacion = certificacion;
            return this;
        }

        public ProveedorBuilder setPoliticaDeEntrega(String politicaDeEntrega) {
            this.politicaDeEntrega = politicaDeEntrega;
            return this;
        }

        public Proveedor build(){
            return new Proveedor(this);
        }


    }
}
