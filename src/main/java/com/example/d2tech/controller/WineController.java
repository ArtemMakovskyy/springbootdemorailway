package com.example.d2tech.controller;

import com.example.d2tech.model.Order;
import com.example.d2tech.model.OrderDeliveryInformation;
import com.example.d2tech.model.OrderPaymentStatus;
import com.example.d2tech.model.PurchaseObject;
import com.example.d2tech.model.Review;
import com.example.d2tech.model.ShoppingCard;
import com.example.d2tech.model.User;
import com.example.d2tech.model.Wine;
import com.example.d2tech.model.WineColor;
import com.example.d2tech.model.WineType;
import com.example.d2tech.repository.OrderDeliveryInformationRepository;
import com.example.d2tech.repository.OrderRepository;
import com.example.d2tech.repository.PurchaseObjectRepository;
import com.example.d2tech.repository.ReviewRepository;
import com.example.d2tech.repository.ShoppingCardRepository;
import com.example.d2tech.repository.UserRepository;
import com.example.d2tech.repository.WineRepository;
import com.example.d2tech.service.WineServiceTest;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/oto")
public class WineController {
    private final WineServiceTest wineServiceTest;
    private final UserRepository userRepository;
    private final PurchaseObjectRepository purchaseObjectRepository;
    private final ShoppingCardRepository shoppingCardRepository;
    private final OrderDeliveryInformationRepository orderDeliveryInformationRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final WineRepository wineRepository;

    @GetMapping({"/w/{id}"})
    public Wine d(@PathVariable Long id){
        return wineRepository.findById(id).orElseThrow();
    }

    @GetMapping({"/w"})
    public List<Wine> all(){
        final List<Wine> all = wineRepository.findAll();
        return all;
    }

    @GetMapping("/pdb/{id}")
    public ResponseEntity<Resource> getWinePictureDb(@PathVariable Long id) throws IOException {
        return wineServiceTest.getPictureByIdFromDb(id);
    }

    @GetMapping("/pbp/{id}")
    public ResponseEntity<Resource> getWinePicturePath(@PathVariable Long id) throws IOException {
        return wineServiceTest.getPictureByIdByPath(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getEmployeePicture(
            @PathVariable Long id) throws IOException {

        final Wine wineById = wineServiceTest.getWineById(id);
        if (wineById != null) {
            final byte[] picture = wineById.getPicture();

            final ByteArrayResource byteArrayResource = new ByteArrayResource(picture);

            return ResponseEntity.ok()
                    .header("fileName", "originFileName.jpg")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(byteArrayResource);
        }
        throw new RemoteException();
    }

    @PostMapping
    public ResponseEntity<String> addPictureToWine(
            @RequestParam Long id,
            @RequestParam("file") MultipartFile multipartFile) {
        wineServiceTest.addPictureIntoDisc(id, multipartFile);
        return ResponseEntity.ok("download picture by id " + id);
    }

    @PostConstruct
//    @GetMapping
    public String start() {
        final Wine wine = wineServiceTest.create(
                "wow wine",
                220.5,
                "grape",
                true,
                WineType.SWEET,
                15.1,
                18.5,
                WineColor.RED,
                "colorDescribing",
                "taste",
                "aroma",
                "gastronomy",
                "description"
        );

        System.out.println(wine);
        wineServiceTest.addRaring(1L, 1);
        wineServiceTest.addRaring(1L, 2);
        wineServiceTest.addRaring(1L, 3);
        wineServiceTest.addRaring(1L, 4);
        wineServiceTest.addRaring(1L, 5);
        wineServiceTest.addRaring(1L, 1);

        final Wine wine2 = wineServiceTest.create(
                "wow wine2",
                230.5,
                "grap2",
                true,
                WineType.DRY,
                35.1,
                38.5,
                WineColor.WHITE,
                "colorDescribing2",
                "taste2",
                "aroma2",
                "gastronomy2",
                "description2"
        );
        System.out.println(wine2);
        wineServiceTest.addRaring(2L, 5);
        wineServiceTest.addRaring(2L, 5);
        wineServiceTest.addRaring(2L, 5);
//        orderChaneCreating();
//        order2();
        return "ok";
    }

    private void order2() {
        Order order = new Order();
        order.setCompletedTime(LocalDate.now());
        order.setPaymentStatus(OrderPaymentStatus.CREATED);
        final Order save = orderRepository.save(order);
        final Long saveId = save.getId();

        OrderDeliveryInformation orderDeliveryInformation = new OrderDeliveryInformation();
        orderDeliveryInformation.setId(saveId);
        orderDeliveryInformation.setCity("Kyiv");
        orderDeliveryInformation.setStreet("Poleva");
        orderDeliveryInformation.setHouse(16);
        orderDeliveryInformation.setFloor(1);
        orderDeliveryInformation.setApartment(2);
        orderDeliveryInformation.setPhone("05012345678");
        orderDeliveryInformation.setAdditionally("addition info");
        final OrderDeliveryInformation saveODI = orderDeliveryInformationRepository.save(orderDeliveryInformation);
//
    }


    private void orderChaneCreating() {
        createUser();
        createPurchaseObject1();
        createPurchaseObject2();
        createShoppingCard();

        createOrderDeliveryInformation();
        createOrder();
        queries();
        createTwoReviews();
        findWineWithReviews();
        findReiewByWine();
        dif();
    }

    private void dif() {
        final Order order = orderRepository.findById(1L).get();
        System.out.println(order.getDeliveryInformation().getOrder().getId());
    }

    private void findReiewByWine() {
        final Set<Review> reviews = wineRepository.findById(1L).get().getReviews();
        final Stream<Integer> integerStream = reviews.stream().map(s -> s.getRating());
        final Integer collect = integerStream.collect(Collectors.summingInt(Integer::intValue));
        System.out.println((double) collect / 2);
    }

    private void findWineWithReviews() {
        final List<Review> all = reviewRepository.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i).getId());
        }
    }

    private void createTwoReviews() {
        Review review1 = new Review();
        review1.setMessage("review message 1");
        review1.setRating(4);
        review1.setReviewDate(LocalDate.now());
        review1.setUser(userRepository.findById(2L).get());
        review1.setWine(wineServiceTest.getWineById(1L));
        reviewRepository.save(review1);

        Review review2 = new Review();
        review2.setMessage("review message 2");
        review2.setRating(3);
        review2.setReviewDate(LocalDate.now());
        review2.setUser(userRepository.findById(1L).get());
        review2.setWine(wineServiceTest.getWineById(1L));
        reviewRepository.save(review2);
    }

    private void queries() {
//        orderRepository.findById(1L);
//        final List<Order> all = orderRepository.findAll();
//        for (int i = 0; i < all.size(); i++) {
//            System.out.println(all.get(i).getShoppingCard().getPurchaseObjects().get(1).getQuantity());
//        }
    }

    private void createOrder() {
        Order order = new Order();
        order.setUser(userRepository.findById(1L).get());
        order.setShoppingCard(shoppingCardRepository.findById(1L).get());
        order.setDeliveryInformation(orderDeliveryInformationRepository.findById(1L).get());
        order.setPaymentStatus(OrderPaymentStatus.CREATED);
        order.setRegistrationTime(LocalDate.now());
        order.setCompletedTime(LocalDate.now());
        orderRepository.save(order);
    }

    private void createOrderDeliveryInformation() {
        OrderDeliveryInformation orderDeliveryInformation = new OrderDeliveryInformation();
        orderDeliveryInformation.setCity("Kyiv");
        orderDeliveryInformation.setStreet("Poleva");
        orderDeliveryInformation.setHouse(16);
        orderDeliveryInformation.setFloor(1);
        orderDeliveryInformation.setApartment(2);
        orderDeliveryInformation.setPhone("05012345678");
        orderDeliveryInformation.setAdditionally("addition info");
        final OrderDeliveryInformation save = orderDeliveryInformationRepository.save(orderDeliveryInformation);
//        System.out.println("OrderDeliveryInformation" + save.getOrder().getId());
    }

    private void createShoppingCard() {
        ShoppingCard shoppingCard = new ShoppingCard();
        // TODO: 03.02.2024 how to get list
        List<PurchaseObject> purchaseObjects = new ArrayList<>();
        purchaseObjects.add(purchaseObjectRepository.findById(1L).get());
        purchaseObjects.add(purchaseObjectRepository.findById(2L).get());
//        shoppingCard.setPurchaseObjects(purchaseObjects);
        final BigDecimal totalCost = purchaseObjects.stream()
                .map(d -> d.getPrice().multiply(BigDecimal.valueOf(d.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        shoppingCard.setTotalCost(totalCost);
        shoppingCardRepository.save(shoppingCard);
    }

    private void createPurchaseObject1() {
        PurchaseObject purchaseObject = new PurchaseObject();
        final Wine wineById = wineServiceTest.getWineById(1L);
        purchaseObject.setWines(wineById);
        purchaseObject.setPrice(wineById.getPrice());
        purchaseObject.setQuantity(1);
        final PurchaseObject save = purchaseObjectRepository.save(purchaseObject);
    }

    private void createPurchaseObject2() {
        PurchaseObject purchaseObject = new PurchaseObject();
        final Wine wineById = wineServiceTest.getWineById(2L);
        purchaseObject.setWines(wineById);
        purchaseObject.setPrice(wineById.getPrice());
        purchaseObject.setQuantity(3);
        final PurchaseObject save = purchaseObjectRepository.save(purchaseObject);
    }

    private void createUser() {
        User user = new User();
        user.setFirstName("Anton");
        final User savedUser = userRepository.save(user);
        log.info(savedUser);

        User user2 = new User();
        user2.setFirstName("Ivan");
        final User savedUser2 = userRepository.save(user2);
        log.info(savedUser2);

        User user3 = new User();
        user3.setFirstName("Petr");
        final User savedUser3 = userRepository.save(user3);
        log.info(savedUser3);
    }
}
