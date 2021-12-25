package com.company.jmixtesr.entity.depart;

import io.jmix.ui.screen.*;
import com.company.jmixtesr.entity.Depart;

@UiController("Depart.browse")
@UiDescriptor("depart-browse.xml")
@LookupComponent("departsTable")
public class DepartBrowse extends StandardLookup<Depart> {
}