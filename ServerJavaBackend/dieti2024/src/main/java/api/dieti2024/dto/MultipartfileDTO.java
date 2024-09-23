package api.dieti2024.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Deprecated
public record MultipartfileDTO(
    List<MultipartFile> files,
    int idAsta
){}
