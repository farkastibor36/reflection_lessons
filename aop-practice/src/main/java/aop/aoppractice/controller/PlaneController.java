package aop.aoppractice.controller;

import aop.aoppractice.dto.PlaneDto;
import aop.aoppractice.model.Plane;
import aop.aoppractice.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PlaneController {
    private final PlaneService planeCRUDService;

    @PostMapping("/create")
    public ResponseEntity<Plane> createPlane(@RequestBody PlaneDto planeDto) {
        return ResponseEntity.ok(planeCRUDService.savePlane(planeDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlaneDto>> getPlanes() {
        return ResponseEntity.ok(planeCRUDService.getAllPlanes());
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<PlaneDto> getPlaneById(@PathVariable Long id) {
        return ResponseEntity.ok(planeCRUDService.getPlaneById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable Long id) {
        planeCRUDService.deletePlane(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlaneDto> updatePlane(@PathVariable Long id, @RequestBody PlaneDto planeDto) {
        return ResponseEntity.ok(planeCRUDService.updatePlane(id, planeDto));
    }
}