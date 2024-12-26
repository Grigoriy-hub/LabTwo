package dbServices.service;
import dbServices.DTO.PointDTO;
import dbServices.model.FunctionEntity;
import dbServices.model.PointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dbServices.repository.PointRepository;
import dbServices.repository.FunctionRepository;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PointService {
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private FunctionRepository functionRepository;
    public PointDTO create(PointDTO pointDTO) {
        PointEntity pointEntity = toEntity(pointDTO);
        PointEntity createdPoint = pointRepository.save(pointEntity);
        return toDTO(createdPoint);
    }
    public PointDTO read(Long id) {
        return pointRepository.findById(id).map(this::toDTO).orElse(null);
    }
    public PointDTO update(PointDTO pointDTO) {
        PointEntity pointEntity = toEntity(pointDTO);
        PointEntity updatedPoint = pointRepository.save(pointEntity);
        return toDTO(updatedPoint);
    }
    public void delete(Long id) {
        pointRepository.deleteById(id);
    }
    public List<PointDTO> getByFunction(Long functionId) {
        FunctionEntity function = functionRepository.findById(functionId).orElse(null);
        if (function == null) {
            return null;
        }
        return pointRepository.findByFunction(function)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    private PointEntity toEntity(PointDTO dto) {
        PointEntity entity = new PointEntity();
        entity.setPointId(dto.getPointId());
        entity.setFunction(functionRepository.findById(dto.getFunctionId()).orElse(null));
        entity.setX(dto.getX());
        entity.setY(dto.getY());
        return entity;
    }
    private PointDTO toDTO(PointEntity entity) {
        PointDTO dto = new PointDTO();
        dto.setPointId(entity.getPointId());
        dto.setFunctionId(entity.getFunction().getFunctionId());
        dto.setX(entity.getX());
        dto.setY(entity.getY());
        return dto;
    }
}