package co.istad.elearningapi.features.media;

import co.istad.elearningapi.features.media.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {

    MediaResponse uploadSingle(MultipartFile file, String folderName);


}
