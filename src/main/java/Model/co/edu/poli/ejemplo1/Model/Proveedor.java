package Model.co.edu.poli.ejemplo1.Model;

import java.util.List;

public class Proveedor {

    private List<Evaluacion> evaluaciones;
    private List<Certificacion> certificaciones;
    private List<PoliticaDeEntrega> politicasEntrega;

    private Proveedor(ProveedorBuilder builder) {
        this.evaluaciones = builder.evaluaciones;
        this.certificaciones = builder.certificaciones;
        this.politicasEntrega = builder.politicasEntrega;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public List<Certificacion> getCertificaciones() {
        return certificaciones;
    }

    public List<PoliticaDeEntrega> getPoliticasEntrega() {
        return politicasEntrega;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "evaluaciones=" + evaluaciones +
                ", certificaciones=" + certificaciones +
                ", politicasEntrega=" + politicasEntrega +
                '}';
    }

    public static class ProveedorBuilder {
        private List<Evaluacion> evaluaciones;
        private List<Certificacion> certificaciones;
        private List<PoliticaDeEntrega> politicasEntrega;

        public ProveedorBuilder setEvaluaciones(List<Evaluacion> evaluaciones) {
            this.evaluaciones = evaluaciones;
            return this;
        }

        public ProveedorBuilder setCertificaciones(List<Certificacion> certificaciones) {
            this.certificaciones = certificaciones;
            return this;
        }

        public ProveedorBuilder setPoliticasEntrega(List<PoliticaDeEntrega> politicasEntrega) {
            this.politicasEntrega = politicasEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }
}

