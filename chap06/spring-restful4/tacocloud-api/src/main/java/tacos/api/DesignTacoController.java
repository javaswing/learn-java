package tacos.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.Order;
import tacos.Taco;
import tacos.data.OrderRepository;
import tacos.data.TacoRepository;

import java.util.Optional;


@RestController
@RequestMapping(path="/design",
        produces="application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {

    private TacoRepository tacoRepo;

    private OrderRepository orderRepository;

    public DesignTacoController(TacoRepository tacoRepo, OrderRepository orderRepository) {
        this.tacoRepo = tacoRepo;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> taco = this.tacoRepo.findById(id);
        if(taco.isPresent()) {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    @PutMapping("/{orderId}")
    public Order putOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PatchMapping(value = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,@RequestBody Order patch) {
        Order order = orderRepository.findById(orderId).get();

        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }

        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }

        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }

        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }

        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }

        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }

        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }

        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }

        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e){}

    }
}
