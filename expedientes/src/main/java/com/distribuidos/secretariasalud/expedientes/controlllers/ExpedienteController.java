package com.distribuidos.secretariasalud.expedientes.controlllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.distribuidos.secretariasalud.expedientes.modelos.Consulta;
import com.distribuidos.secretariasalud.expedientes.modelos.Expedientes;
import com.distribuidos.secretariasalud.expedientes.repositorios.RepositorioExpedientes;
import io.jsonwebtoken.io.IOException;

@RestController
@RequestMapping("/expedientes")
public class ExpedienteController {
    @Autowired
    private RepositorioExpedientes expedienteRepository;

    @PostMapping
    public Expedientes crearExpediente(@RequestParam("nombre") String nombre,
                                      @RequestParam("consulta") Consulta pregunta,
                                      @RequestParam("archivosPDF") MultipartFile[] archivosPDF,
                                      @RequestParam("archivosImagenes") MultipartFile[] archivosImagenes) throws IOException, java.io.IOException {
        Consulta consulta = new Consulta(pregunta);
        List<byte[]> pdfs = convertirMultipartABytes(archivosPDF);
        List<byte[]> imagenes = convertirMultipartABytes(archivosImagenes);

        Expedientes expediente = new Expedientes();
        expediente.setNombre(nombre);
        expediente.setConsulta(consulta);
        expediente.setArchivosPDF(pdfs);
        expediente.setArchivosImagenes(imagenes);

        return expedienteRepository.save(expediente);
    }

    private List<byte[]> convertirMultipartABytes(MultipartFile[] archivos) throws IOException, java.io.IOException {
        List<byte[]> listaBytes = new ArrayList<>();
        for (MultipartFile archivo : archivos) {
            listaBytes.add(archivo.getBytes());
        }
        return listaBytes;
    }
}