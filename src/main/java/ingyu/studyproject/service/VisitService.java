package ingyu.studyproject.service;

import ingyu.studyproject.entity.VisitEntity;
import ingyu.studyproject.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;

    public void newVisitor(String ipAddr){
        VisitEntity entity = new VisitEntity(ipAddr);
        visitRepository.save(entity);
    }

    public Long getTotalVisitor() {

        return visitRepository.count();
    }
}
