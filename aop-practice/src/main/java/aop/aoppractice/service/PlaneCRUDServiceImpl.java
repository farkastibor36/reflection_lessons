package aop.aoppractice.service;

import aop.aoppractice.dto.PlaneDto;
import aop.aoppractice.mapper.PlaneMapper;
import aop.aoppractice.model.Plane;
import aop.aoppractice.repository.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneCRUDServiceImpl implements PlaneService {
    private final PlaneMapper planeMapper;
    private final PlaneRepository planeRepository;

    @Override
    public Plane savePlane(PlaneDto planeDto) {
        return planeRepository.save(planeMapper.toEntity(planeDto));
    }

    @Override
    public PlaneDto getPlaneById(Long id) {
        return planeMapper.toDto(planeRepository.findById(id).orElseThrow());
    }

    @Override
    public List<PlaneDto> getAllPlanes() {
        return planeRepository.findAll().stream().map(planeMapper::toDto).toList();
    }

    @Override
    public void deletePlane(Long id) {
        planeRepository.deleteById(id);
    }

    @Override
    public PlaneDto updatePlane(Long id, PlaneDto planeDto) {
        return planeMapper.toDto(planeRepository.save(planeMapper.toEntity(planeDto)));
    }
}