package com.cursodavinchicoder.common.util;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

// Clase encargada de agregar la imagenes recibidas en la peticiones
@Service
public class FileUtils {

    public String saveProductImage(MultipartFile file) {
        String uniqueFileName;

        try (InputStream inputStream = file.getInputStream()) {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//            La configuracon anterior es para eliminar los conta slag que trae la ruta que se sube desde el cliente
//            String fileName = file.getOriginalFilename();

            uniqueFileName = UUID.randomUUID().toString().concat("-").concat(fileName);

            Path path = Path.of("uploads/products");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(inputStream, path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);
            /*
              Nota: Se copia la imagen que viene en el inputStream, junto con la ruta que se le indica y se le
              coloca el estandar de que si existe lo remplace.
            */

        } catch (Exception e) {
            throw new RuntimeException("Error al subir la imagen");
        }
        return uniqueFileName;
    }
}
