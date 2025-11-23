package com.example.framework.page;

import com.example.framework.model.StepResult;
import com.example.framework.model.TestStep;

public interface PageActionHandler {
    String getName();

    StepResult execute(TestStep step);
}
