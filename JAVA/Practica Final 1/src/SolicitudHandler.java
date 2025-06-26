import java.util.ArrayList;

public class SolicitudHandler {
    private ArrayList<Solicitud> solicitud;

    public void addSolicitud(Solicitud s) throws Exception{
        if(solicitud == null){
            throw new SolicitudException("La solicitud no puede ser nulo");
        }
        solicitud.addLast(s);
    }
    public Solicitud exeSolicitud() {
        return solicitud.removeFirst();
    }

}
