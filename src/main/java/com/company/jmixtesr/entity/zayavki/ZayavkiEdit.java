package com.company.jmixtesr.entity.zayavki;

import io.jmix.ui.screen.*;
import com.company.jmixtesr.entity.Zayavki;

@UiController("Zayavki.edit")
@UiDescriptor("zayavki-edit.xml")
@EditedEntityContainer("zayavkiDc")
public class ZayavkiEdit extends StandardEditor<Zayavki> {
}