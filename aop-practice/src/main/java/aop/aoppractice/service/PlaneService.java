package aop.aoppractice.service;

import aop.aoppractice.dto.PlaneDto;
import aop.aoppractice.model.Plane;

import java.util.List;

public interface PlaneService {
    Plane savePlane(PlaneDto planeDto);

    PlaneDto getPlaneById(Long id);

    List<PlaneDto> getAllPlanes();

    void deletePlane(Long id);

    PlaneDto updatePlane(Long id, PlaneDto planeDto);
}