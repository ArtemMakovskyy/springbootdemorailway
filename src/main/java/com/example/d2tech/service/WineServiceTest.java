package com.example.d2tech.service;


import com.example.d2tech.model.Wine;
import com.example.d2tech.model.WineColor;
import com.example.d2tech.model.WineType;
import com.example.d2tech.repository.WineRepository;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class WineServiceTest {
    private static final String SAVE_PATH = "upload/pictures/wine/";
    private final WineRepository wineRepository;

    public void addRaring(Long wineId, Integer rating) {
//        final Wine wine = wineRepository.findById(wineId)
//                .orElseThrow(() -> new RuntimeException("Can't get wine by id " + wineId));
//        if (wine.getRatings().isEmpty()) {
//            List<Integer> ratings = new ArrayList<>();
//            wine.setRatings(ratings);
//        }
//        LinkedList<Integer> ratings = new LinkedList<>(wine.getRatings());
//        if (ratings.size() >= 100) {
//            ratings.removeFirst();
//        }
//        ratings.add(rating);
//        final BigDecimal averageRatingScore =
//                BigDecimal.valueOf(
//                        calculateAverageRatingScore(ratings)
//                ).setScale(2, RoundingMode.HALF_UP);
//        wine.setAverageRatingScore(averageRatingScore);
//        wine.setRatings(new ArrayList<>(ratings));
//        Wine save = wineRepository.save(wine);
//        System.out.println(save);
    }

    private double calculateAverageRatingScore(List<Integer> ratings) {
        final double numberOfRatingsAsPercentage = ratings.size() / (double) 100;
        return ratings.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.00) + numberOfRatingsAsPercentage;
    }

    public void addPictureIntoDisc(Long id, MultipartFile multipartFile) {
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

    public Wine getWineById(Long id) {

        return wineRepository.findById(id).orElseThrow();
    }

    public ResponseEntity<Resource> getPictureByIdFromDb(Long id) throws IOException {
        Wine wine = getWineById(id);
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

    public ResponseEntity<Resource> getPictureByIdByPath(Long id) throws IOException {

        String imagePath = "upload/pictures/wine/" + id.toString() + ".jpg"; // Путь к изображению

        byte[] picture = loadImageAsByteArray(imagePath);

        ByteArrayResource byteArrayResource = new ByteArrayResource(picture);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=originFileName.jpg");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .body(byteArrayResource);
    }

    private static byte[] loadImageAsByteArray(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        return Files.readAllBytes(path);
    }
    public Wine create(
            String name,
            double price,
            String grape,
            Boolean decantation,
            WineType wineType,
            double strengthFrom,
            double strengthTo,
            WineColor wineColor,
            String colorDescribing,
            String taste,
            String aroma,
            String gastronomy,
            String description
    ) {
        Wine wine = new Wine();
        wine.setName(name);
        List<Integer> ratings = new ArrayList<>();
//        wine.setRatings(ratings);
        wine.setPrice(BigDecimal.valueOf(price));
        wine.setGrape(grape);
        wine.setDecantation(decantation);
        wine.setWineType(wineType);
        wine.setStrengthFrom(BigDecimal.valueOf(strengthFrom));
        wine.setStrengthTo(BigDecimal.valueOf(strengthTo));
        wine.setWineColor(wineColor);
        wine.setColorDescribing(colorDescribing);
        wine.setTaste(taste);
        wine.setAroma(aroma);
        wine.setGastronomy(gastronomy);
        wine.setDescription(description);
        wine.setAverageRatingScore(BigDecimal.valueOf(0));
        final Wine save = wineRepository.save(wine);
        System.out.println(save);
        return save;
    }
}
