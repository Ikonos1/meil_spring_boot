package com.grapplesoft.meil_backend.services.actionTypeService;

import com.grapplesoft.meil_backend.models.entities.Actiontype;
import com.grapplesoft.meil_backend.models.request.AddActionTypeDto;

public interface ActionTypeService {
    Actiontype addActionType(AddActionTypeDto request);
}
