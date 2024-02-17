package com.example.d2tech.service;

import com.example.d2tech.dto.mapper.WineMapper;
import com.example.d2tech.dto.wine.WineCreateRequestDto;
import com.example.d2tech.dto.wine.WineWithoutReviewsAndPicturesDto;
import com.example.d2tech.model.Wine;
import com.example.d2tech.repository.WineRepository;
import java.io.FileOutputStream;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class WineServiceImpl {
    private static final String SAVE_PATH = "upload/pictures/wine/";
    private WineRepository wineRepository;
    private WineMapper wineMapper;

    public WineWithoutReviewsAndPicturesDto save(
            WineCreateRequestDto createRequestDto,
            MultipartFile multipartFile) {
        WineWithoutReviewsAndPicturesDto wineWithoutReviewsAndPicturesDto
                = new WineWithoutReviewsAndPicturesDto();
        final Wine wine = wineMapper.toEntityFromCreate(createRequestDto);
//        wine.setPicture(addPictureIntoDisc(1L,multipartFile));


        return wineWithoutReviewsAndPicturesDto;
    }

    private void addPictureIntoDisc(Long id, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            throw new RuntimeException("Please select the file");
        }
        Wine wine = wineRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find by id " + id));
        try {
            final byte[] pictureBytes = multipartFile.getBytes();
            if (true) {
                System.out.println("save into db");
                wine.setPicture(pictureBytes);
                wineRepository.save(wine);
            }
            if (true) {
                System.out.println("save into fs");
                String filePath = SAVE_PATH + id + getFileExtension(multipartFile);

                try (FileOutputStream fos = new FileOutputStream(filePath)) {
                    fos.write(pictureBytes);
                    System.out.println("Изображение успешно сохранено в файл: " + filePath);
                } catch (IOException e) {
                    System.err.println("Ошибка при сохранении изображения в файл: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error picture saving");
        }
    }

    public String getFileExtension(MultipartFile multipartFile) {
        // Получаем оригинальное имя файла
        String originalFilename = multipartFile.getOriginalFilename();

        if (originalFilename != null && originalFilename.contains(".")) {
            // Извлекаем расширение из имени файла
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        } else {
            // Если оригинальное имя файла не содержит расширение
            return "";
        }
    }

    public ResponseEntity<Resource> getPictureByIdFromDb(Long id) throws IOException {
        final Wine wine = wineRepository.findById(id).orElseThrow();
        byte[] picture = wine.getPicture();

        ByteArrayResource byteArrayResource = new ByteArrayResource(picture);

        // TODO: 01.02.2024 whi this need
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=originFileName.jpg");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(byteArrayResource);
    }
}
