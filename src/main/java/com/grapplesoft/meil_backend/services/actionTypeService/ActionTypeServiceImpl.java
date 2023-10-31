package com.grapplesoft.meil_backend.services.actionTypeService;

import com.grapplesoft.meil_backend.models.entities.Actiontype;
import com.grapplesoft.meil_backend.models.request.AddActionTypeDto;
import com.grapplesoft.meil_backend.repositories.ActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ActionTypeServiceImpl implements ActionTypeService {

    private final ActionTypeRepository actionTypeRepository;

    @Autowired
    public ActionTypeServiceImpl(@Qualifier("actionTypeRepository") ActionTypeRepository actionTypeRepository) {
        this.actionTypeRepository = actionTypeRepository;
    }

    @Override
    public Actiontype addActionType(AddActionTypeDto request) {
        Actiontype actionType = Actiontype.builder()
                .action(request.action())
                .description(request.description())
                .build();
        return this.actionTypeRepository.save(actionType);
    }
}
