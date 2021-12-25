package com.company.jmixtesr.entity.depart;

import io.jmix.ui.screen.*;
import com.company.jmixtesr.entity.Depart;

@UiController("Depart.edit")
@UiDescriptor("depart-edit.xml")
@EditedEntityContainer("departDc")
public class DepartEdit extends StandardEditor<Depart> {
}