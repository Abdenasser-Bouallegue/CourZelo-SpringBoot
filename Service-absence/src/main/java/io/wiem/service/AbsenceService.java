package io.wiem.service;


import io.wiem.entity.Absence;

import java.util.List;

public interface AbsenceService {
List<Absence>findall();
void addAbsence(List<Integer> e);
    public List<Absence> saveSelectedUsers(List<Absence> selectedUsers) ;


}
