package tacos.web.api;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {
  @Autowired EntityLinks entityLinks;
  private TacoRepository tacoRepo;

  public DesignTacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {
    PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepo.findAll(page).getContent();
  }

  //  @GetMapping("/recenth")
  //  public Resources<TacoResource> recentTacosH() {
  //    PageRequest page = PageRequest.of(
  //            0, 12, Sort.by("createdAt").descending());
  //    List<Taco> tacos = tacoRepo.findAll(page).getContent();
  //
  //    List<TacoResource> tacoResources =
  //        new TacoResourceAssembler().toResources(tacos);
  //    Resources<TacoResource> recentResources =
  //        new Resources<TacoResource>(tacoResources);
  //    recentResources.add(
  //        linkTo(methodOn(DesignTacoController.class).recentTacos())
  //        .withRel("recents"));
  //    return recentResources;
  //  }

  // ControllerLinkBuilder.linkTo(DesignTacoController.class)
  // .slash("recent")
  // .withRel("recents"));

  //  @GetMapping("/recenth")
  //  public Resources<TacoResource> recenthTacos() {
  //    PageRequest page = PageRequest.of(
  //            0, 12, Sort.by("createdAt").descending());
  //    List<Taco> tacos = tacoRepo.findAll(page).getContent();
  //
  //    List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
  //
  //    Resources<TacoResource> tacosResources = new Resources<>(tacoResources);
  ////    Link recentsLink = ControllerLinkBuilder
  ////        .linkTo(DesignTacoController.class)
  ////        .slash("recent")
  ////        .withRel("recents");
  //
  //    Link recentsLink =
  //        linkTo(methodOn(DesignTacoController.class).recentTacos())
  //        .withRel("recents");
  //    tacosResources.add(recentsLink);
  //    return tacosResources;
  //  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepo.save(taco);
  }

  @GetMapping("/{id}")
  public Taco tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepo.findById(id);
    if (optTaco.isPresent()) {
      return optTaco.get();
    }
    return null;
  }

  //  @GetMapping("/{id}")
  //  public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
  //    Optional<Taco> optTaco = tacoRepo.findById(id);
  //    if (optTaco.isPresent()) {
  //      return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
  //    }
  //    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
  //  }

}
