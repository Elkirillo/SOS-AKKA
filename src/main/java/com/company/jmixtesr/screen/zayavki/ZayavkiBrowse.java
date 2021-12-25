package com.company.jmixtesr.screen.zayavki;

import io.jmix.ui.screen.*;
import com.company.jmixtesr.entity.Zayavki;

@UiController("Zayavki.browse")
@UiDescriptor("zayavki-browse.xml")
@LookupComponent("zayavkisTable")
public class ZayavkiBrowse extends StandardLookup<Zayavki> {
}